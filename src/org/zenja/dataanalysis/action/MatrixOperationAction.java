package org.zenja.dataanalysis.action;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.RealMatrix;
import org.zenja.dataanalysis.action.enums.InputType;
import org.zenja.dataanalysis.analysistools.dataextractors.MatrixExtractor;
import org.zenja.dataanalysis.analysistools.utils.MatrixStringMaker;

import com.opensymphony.xwork2.ActionSupport;

public class MatrixOperationAction extends ActionSupport {

	private static final long serialVersionUID = 1677621381186889709L;
	
	/*
	 * Received from view
	 */
	private InputType inputType;
	private String matrixStringOne;
	private String matrixStringTwo;
	private MatrixOperationType matrixOperationType;
	
	public InputType getInputType() {
		return inputType;
	}


	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}


	public String getMatrixStringOne() {
		return matrixStringOne;
	}


	public void setMatrixStringOne(String matrixStringOne) {
		this.matrixStringOne = matrixStringOne;
	}


	public String getMatrixStringTwo() {
		return matrixStringTwo;
	}


	public void setMatrixStringTwo(String matrixStringTwo) {
		this.matrixStringTwo = matrixStringTwo;
	}


	public MatrixOperationType getMatrixOperationType() {
		return matrixOperationType;
	}


	public void setMatrixOperationType(MatrixOperationType matrixOperationType) {
		this.matrixOperationType = matrixOperationType;
	}

	/*
	 * Passed To View
	 */
	private double[][] resultArrMatrix;
	private String resultMatrixStr;
	private String matrixOneStr;
	private String matrixTwoStr;
	private String operationStr;
	
	public double[][] getResultArrMatrix() {
		return resultArrMatrix;
	}

	public void setResultArrMatrix(double[][] resultArrMatrix) {
		this.resultArrMatrix = resultArrMatrix;
	}

	public String getResultMatrixStr() {
		return resultMatrixStr;
	}

	public void setResultMatrixStr(String resultMatrixStr) {
		this.resultMatrixStr = resultMatrixStr;
	}
	
	public String getMatrixOneStr() {
		return matrixOneStr;
	}

	public void setMatrixOneStr(String matrixOneStr) {
		this.matrixOneStr = matrixOneStr;
	}

	public String getMatrixTwoStr() {
		return matrixTwoStr;
	}

	public void setMatrixTwoStr(String matrixTwoStr) {
		this.matrixTwoStr = matrixTwoStr;
	}

	public String getOperationStr() {
		return operationStr;
	}

	public void setOperationStr(String operationStr) {
		this.operationStr = operationStr;
	}


	/***********************
	 * Helper Methods Below
	 ***********************/
	
	private String handleActionForPlainText() {
		if (matrixStringOne == null || matrixStringTwo == null) {
			return INPUT;
		}
		
		MatrixExtractor extractor = new MatrixExtractor();
		double[][] arrMatrix1 = extractor.extractMatrix(matrixStringOne, "(\r\n|\n\r|\n)", " ");
		double[][] arrMatrix2 = extractor.extractMatrix(matrixStringTwo, "(\r\n|\n\r|\n)", " ");
		
		RealMatrix matrix1 = new Array2DRowRealMatrix(arrMatrix1);
		RealMatrix matrix2 = new Array2DRowRealMatrix(arrMatrix2);
		
		RealMatrix resultMatrix;
		try {
			switch(matrixOperationType) {
			case ADD:
				resultMatrix = matrix2.add(matrix1);
				operationStr = "Add";
				break;
			case SUBTRACT:
				resultMatrix = matrix2.subtract(matrix1);
				operationStr = "Subtract";
				break;
			case MULTIPLY:
				resultMatrix = matrix2.multiply(matrix1);
				operationStr = "Multiply";
				break;
			default:
				return INPUT;
			}
		} catch(DimensionMismatchException dme) {
			dme.printStackTrace();
			return "DimensionError";
		} catch(MatrixDimensionMismatchException mdme) {
			mdme.printStackTrace();
			return "DimensionError";
		}
		
		resultArrMatrix = resultMatrix.getData();
		
		/* build matrix strings */
		matrixOneStr = MatrixStringMaker.makeMatrixStringForHtml(arrMatrix1);
		matrixTwoStr = MatrixStringMaker.makeMatrixStringForHtml(arrMatrix2);
		resultMatrixStr = MatrixStringMaker.makeMatrixStringForHtml(resultArrMatrix);
		
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
		case PLAIN_TEXT:
			return handleActionForPlainText();
		default:
			System.err.println("ERROR: no matched inputType: " + inputType.toString());
			return INPUT;
		}
	}
	
	public enum MatrixOperationType {
		ADD, SUBTRACT, MULTIPLY
	}
	
}
