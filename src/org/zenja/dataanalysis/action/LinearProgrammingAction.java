package org.zenja.dataanalysis.action;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lpsolve.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.linear.LinearConstraint;
import org.apache.commons.math3.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optimization.linear.Relationship;
import org.apache.commons.math3.optimization.linear.SimplexSolver;
import org.zenja.dataanalysis.action.enums.InputType;
import org.zenja.dataanalysis.analysistools.enums.FormulaRelationship;
import org.zenja.dataanalysis.analysistools.utils.FormulaRelationshipStringGenerator;

import com.opensymphony.xwork2.ActionSupport;

public class LinearProgrammingAction extends ActionSupport {
	
	private static final long serialVersionUID = 4711136781974115994L;
	
	static {
		// load lp_solve and its java adapter libraries
		System.loadLibrary("lpsolve55");
		System.loadLibrary("lpsolve55j");
		System.out.println("load lpsolve libraries successfully!");
	}
	
	/*
	 * Received from view
	 */
	private InputType inputType;
	
	private File file;					// Input type: FILE
	
	private List<String> constraints = new ArrayList<String>();		// Input type: MULTI_FIELDS
	private String objectiveFunction;	// Input type: MULTI_FIELDS
	private String minOrMax;			// Input type: MULTI_FIELDS
	
	private String plaintext;			// Input type: PLAIN_TEXT
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public List<String> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<String> constraints) {
		this.constraints = constraints;
	}

	public String getObjectiveFunction() {
		return objectiveFunction;
	}

	public void setObjectiveFunction(String objectiveFunction) {
		this.objectiveFunction = objectiveFunction;
	}

	public String getMinOrMax() {
		return minOrMax;
	}

	public void setMinOrMax(String minOrMax) {
		this.minOrMax = minOrMax;
	}

	public String getPlaintext() {
		return plaintext;
	}

	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}

	/*
	 * Passed To View
	 */
	private String problemDescription;
	private double[] resultPoint;
	private double resultValue;

	public double[] getResultPoint() {
		return resultPoint;
	}

	public void setResultPoint(double[] resultPoint) {
		this.resultPoint = resultPoint;
	}

	public double getResultValue() {
		return resultValue;
	}

	public void setResultValue(double resultValue) {
		this.resultValue = resultValue;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	
	/***********************
	 * Helper Methods Below
	 ***********************/
	private String getFormulaString(double[] params, FormulaRelationship relationship, Double constant) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < params.length; i++) {
			if(params[i] > 0) {			// positive, need to add sign
				sb.append('+');
				sb.append(params[i]);
				sb.append("x");
				sb.append(i);
			} else if(params[i] < 0) {	// negative, don't need to add sign
				sb.append(params[i]);
				sb.append("x");
				sb.append(i);
			} else {					// zero, ignore
				// append nothing
			}
		}
		
		if (relationship != null) {
			sb.append(
					FormulaRelationshipStringGenerator.generate(relationship));
		}
		
		if(constant != null) {
			sb.append(constant);
		}
		
		return sb.toString();
	}
	
	private String getFormulaStringAdapter(double[] params, Relationship relationship, Double constant) {
		switch (relationship) {
		case EQ:
			return getFormulaString(params, FormulaRelationship.EQ, constant);
		case LEQ:
			return getFormulaString(params, FormulaRelationship.LEQ, constant);
		case GEQ:
			return getFormulaString(params, FormulaRelationship.GEQ, constant);
		}
		
		// this should not happen
		return null;
	}
	
	/*
	 * For action when input type is FILE
	 */
	@Deprecated
	private String handleActionForFile_OLD() {
		/*
		 * Input file example:
		 * 
		 * min
		 * -2 1 0 -5
		 * 1 2 0 6 <=
		 * 3 2 0 12 <=
		 * 0 1 0 0 >=
		 * 
		 * Corresponding Linear Program:
		 * MIN -2x + y -5
		 * S.T.
		 * x + 2y <=6
		 * 3x + 2y <= 12
		 * y >= 0
		 * 
		 */
		
		FileInputStream fstream = null;
		DataInputStream in = null;
		BufferedReader br = null;
		try {
			fstream = new FileInputStream(file);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			
			String strLine;
			
			StringBuilder problemDescriptionBuilder = new StringBuilder();
			
			/*
			 * First Line: Determine the goal type
			 */
			GoalType goalType = null;
			strLine = br.readLine();
			if (strLine.toLowerCase().equals("min")) {
				goalType = GoalType.MINIMIZE;
			} else if(strLine.toLowerCase().equals("max")) {
				goalType = GoalType.MAXIMIZE;
			} else {
				System.err.println("neither min or max");
				return "input_error";
			}
			
			
			// one step of building problem description
			problemDescriptionBuilder.append(goalType.name());
			problemDescriptionBuilder.append(": ");
			
			/*
			 * Second Line: Get the goal function
			 */
			strLine = br.readLine();
			String[] goalFuncParamStrs = strLine.split(" ");
			double[] goalFuncParams = new double[goalFuncParamStrs.length - 1];
			for (int i = 0; i < goalFuncParamStrs.length - 1; i++) {
				goalFuncParams[i] = Double.parseDouble(goalFuncParamStrs[i]);
			}
			double goalFuncconstantTerm = 
				Double.parseDouble(goalFuncParamStrs[goalFuncParamStrs.length - 1]);
			LinearObjectiveFunction goalFunc = 
				new LinearObjectiveFunction(goalFuncParams, goalFuncconstantTerm);
			
			// one step of building problem description
			problemDescriptionBuilder.append(getFormulaString(goalFuncParams, null, null));
			problemDescriptionBuilder.append("<br>");
			problemDescriptionBuilder.append("S.T.");
			problemDescriptionBuilder.append("<br>");
			
			/*
			 * The remaining lines: constraint conditions
			 */
			Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
			while ((strLine = br.readLine()) != null) {
				//constraints.add(new LinearConstraint(new double[] { 1, 2 }, Relationship.LEQ, 6));
				String[] constraintParamStrs = strLine.split(" ");
				int paramsLength = constraintParamStrs.length;
				double[] variableParams = new double[paramsLength - 2];
				for (int i = 0; i < paramsLength - 2; i++) {
					variableParams[i] = Double.parseDouble(constraintParamStrs[i]);
				}
				
				// get the constant term
				double constraintConstantTerm = Double.parseDouble(constraintParamStrs[paramsLength - 2]);
				
				// get the relationship
				Relationship rs;
				if (constraintParamStrs[paramsLength - 1].equals("=")) {
					rs = Relationship.EQ;
				} else if(constraintParamStrs[paramsLength - 1].equals("<=")) {
					rs = Relationship.LEQ;
				} else if(constraintParamStrs[paramsLength - 1].equals(">=")) {
					rs = Relationship.GEQ;
				} else {
					System.err.println("not: EQ, LEQ, or GEQ");
					return "input_error";
				}
				
				constraints.add(new LinearConstraint(variableParams, rs, constraintConstantTerm));
				
				// one step of building problem description
				problemDescriptionBuilder.append(getFormulaStringAdapter(variableParams, rs, constraintConstantTerm));
				problemDescriptionBuilder.append("<br>");
			}
			
			// create and run the solver
			PointValuePair solution = new SimplexSolver().optimize(goalFunc, constraints, goalType, false);
			
			resultPoint = solution.getPoint();
			resultValue = solution.getValue();
			problemDescription = problemDescriptionBuilder.toString();
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return "io_error";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "input_error";
		} finally {
			try {
				if (null != br)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return SUCCESS;
	
	}
	
	/*
	 * Use lp_solve library to solve lp problems
	 * 
	 * input file type: lp format
	 * (http://lpsolve.sourceforge.net/5.5/lp-format.htm)
	 */
	private String handleActionForFile() {
		FileInputStream fstream = null;
		DataInputStream in = null;
		BufferedReader br = null;
		try {
			LpSolve lpSolve = LpSolve.readLp(file.getAbsolutePath(), 0, "lp problem");
			lpSolve.solve();
			
			//DEBUG
			System.out.println("linear programming problem file " + file.getAbsolutePath() + " readed");
			
			fstream = new FileInputStream(file);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			
			String strLine;
			StringBuilder problemDescriptionBuilder = new StringBuilder();
			while ((strLine = br.readLine()) != null) {
				problemDescriptionBuilder.append(strLine);
				problemDescriptionBuilder.append("<br>");
			}
			
			// get results
			resultValue = lpSolve.getObjective();
			resultPoint = lpSolve.getPtrVariables();
			problemDescription = problemDescriptionBuilder.toString();
			
			// DON'T forget to release the memory!!
			lpSolve.deleteLp();
			
			return SUCCESS;
		} catch (LpSolveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	
	private String handleActionForPlainText() {
		// check if params are available
		if (plaintext == null) {
			return INPUT;
		}
		
		try {
			// Save the plaintext to a tmp file
			File problemFile = File.createTempFile("linear-programming-problem", ".tmp");
			FileUtils.writeStringToFile(problemFile, plaintext);
			
			//DEBUG
			System.out.println("linear programming problem file " + problemFile.getAbsolutePath() + " created");
			
			// load the file and solve the problem
			LpSolve solver = LpSolve.readLp(problemFile.getAbsolutePath(), 0, "linear programming problem");
			solver.solve();
			
			// get results
			resultValue = solver.getObjective();
			resultPoint = solver.getPtrVariables();
			problemDescription = plaintext.replaceAll("(\r\n|\n)", "<br>");
			
			// DON'T forget to release the memory!!
			solver.deleteLp();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LpSolveException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: LpSolveException!");
			e.printStackTrace();
		} 
		
		return SUCCESS;
	}
	
	private String handleActionForMultiFields() {
		// check if params are available
		if (minOrMax == null || constraints.size() == 0 || objectiveFunction == null) {
			return INPUT;
		}
		
		//DEBUG
		System.out.println("Length of constraints: " + constraints.size());
		
		// build plaintext according to passed fields
		StringBuilder sb = new StringBuilder();
		
		// min or max?
		if (minOrMax.toLowerCase().equals("min")) {
			sb.append("min: ");
		} else if (minOrMax.toLowerCase().equals("max")) {
			sb.append("max: ");
		} else {
			System.err.println("Error: minOrMax is neither min or max");
			return ERROR;
		}
		
		// objective function?
		sb.append(objectiveFunction);
		sb.append(";\r\n");
		
		// constraints?
		for (String constraint : constraints) {
			sb.append(constraint);
			sb.append(";\r\n");
		}
		
		// here comes the plain text for the problem
		plaintext = sb.toString();
		
		// handle the generated plain text
		return handleActionForPlainText();
		
	}

	/***********************
	 * Action Methods Below
	 ***********************/

	public String index() {
		if (inputType == null) {
			System.err.println("Missing field: inputType");
			return INPUT;
		}
		
		switch (inputType) {
		case FILE:
			return handleActionForFile();
		case PLAIN_TEXT:
			return handleActionForPlainText();
		case MULTI_FIELDS:
			return handleActionForMultiFields();
		default:
			System.err.println("ERROR: no matched inputType: " + inputType.toString());
			return "input_type_error";
		}
	}

	
}
