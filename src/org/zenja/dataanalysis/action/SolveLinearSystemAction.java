package org.zenja.dataanalysis.action;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.zenja.dataanalysis.action.enums.InputType;
import org.zenja.dataanalysis.analysistools.utils.FormulaMaker;

import com.opensymphony.xwork2.ActionSupport;

public class SolveLinearSystemAction extends ActionSupport {
	
	private static final long serialVersionUID = 1303031587845130372L;
	
	/*
	 * Received from view
	 */
	private InputType inputType;
	private File file;
	
	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	/*
	 * Passed To View
	 */
	private String problemDescription;
	private ArrayList<Double> resultList = new ArrayList<Double>();
	private String resultDescription;
	private int precision;
	
	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public ArrayList<Double> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList<Double> resultList) {
		this.resultList = resultList;
	}

	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	/***********************
	 * Helper Methods Below
	 ***********************/
	
	/*
	 * Test file:
	 * e.g.
	 * 2x + 3y - 2z = 1
     * -x + 7y + 6x = -2
     * 4x - 3y - 5z = 1
     * 
     * File content:
     * 2, 3, -2
     * -1, 7, 6
     * 4, -3, -5
     * 
     * 1, -2, 1
	 * 
	 */
	private String handleActionForFile() {
		FileInputStream fstream = null;
		DataInputStream in = null;
		BufferedReader br = null;
		
		ArrayList<ArrayList<Double>> coefficientMatrix = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> constantVector = new ArrayList<Double>();
		
		try {
			fstream = new FileInputStream(file);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			
			String strLine;
			
			/* parse coefficient matrix */
			while ((strLine = br.readLine()) != null) {
				ArrayList<Double> coefficientLine = new ArrayList<Double>();
				
				strLine = strLine.replace(" ", "");
				
				// this loop just handle the coefficients
				if (strLine.length() == 0) break;
				
				String[] coefficientStrArray = strLine.split(",");
				for (String coefficientStr : coefficientStrArray) {
					double coefficient = Double.parseDouble(coefficientStr);
					coefficientLine.add(coefficient);
				}
				
				coefficientMatrix.add(coefficientLine);
			}
			
			/* parse constant vector, only on line */
			strLine = br.readLine();
			strLine = strLine.replace(" ", "");
			String[] constantStrArr = strLine.split(",");
			for (String constantStr : constantStrArr) {
				double constant = Double.parseDouble(constantStr);
				constantVector.add(constant);
			}
			
			/* transfer input (ArrayList -> double arrays) */
			double[][] coefficientArrays = new double[coefficientMatrix.size()][];
			for (int i = 0; i < coefficientMatrix.size(); i++) {
				ArrayList<Double> coefficientList = coefficientMatrix.get(i);
				coefficientArrays[i] = new double[coefficientList.size()];
				for (int j = 0; j < coefficientList.size(); j++) {
					coefficientArrays[i][j] = coefficientList.get(j);
				}
			}
			double[] constantArray = new double[constantVector.size()];
			for (int i = 0; i < constantVector.size(); i++) {
				constantArray[i] = constantVector.get(i);
			}
			RealMatrix coefficients = new Array2DRowRealMatrix(coefficientArrays);
			RealVector constants = new ArrayRealVector(constantArray, false);
			
			/* solve the linear system and get results */
			DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
			RealVector solution = solver.solve(constants);
			for (int i = 0; i < solution.getDimension(); i++) {
				resultList.add(solution.getEntry(i));
			}
			
			/* make problem description */
			FormulaMaker maker = new FormulaMaker();
			problemDescription = maker.makeFormulars(coefficientArrays, constantArray, precision);
			problemDescription = problemDescription.replace(",", "<br>");
			
			/* make result description */
			StringBuilder resultSB = new StringBuilder();
			for (int i = 0 ; i < resultList.size(); i++) {
				BigDecimal bd = new BigDecimal(resultList.get(i));
				BigDecimal rounded = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
				resultSB.append('x');
				resultSB.append(i);
				resultSB.append(" = ");
				resultSB.append(rounded.toString());
				resultSB.append("<br>");
			}
			resultDescription = resultSB.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		} catch (Exception e) {
			return ERROR;
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
		default:
			System.err.println("ERROR: no matched inputType: " + inputType.toString());
			return INPUT;
		}
	}
}
