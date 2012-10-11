package org.zenja.dataanalysis.action;

import java.text.NumberFormat;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;
import org.zenja.dataanalysis.action.enums.InputType;

import com.opensymphony.xwork2.ActionSupport;

public class ComplexNumberOperationAction extends ActionSupport {

	private static final long serialVersionUID = 1814266659760124593L;
	
	/*
	 * Received from view
	 */
	
	private InputType inputType;
	private String cnStringOne;		// "cb": complex number
	private String cnStringTwo;
	private ComplexNumberOperationType operationType;
	private int precision;			// the precision of the results
	
	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public String getCnStringOne() {
		return cnStringOne;
	}

	public void setCnStringOne(String cnStringOne) {
		this.cnStringOne = cnStringOne;
	}

	public String getCnStringTwo() {
		return cnStringTwo;
	}

	public void setCnStringTwo(String cnStringTwo) {
		this.cnStringTwo = cnStringTwo;
	}

	public ComplexNumberOperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(ComplexNumberOperationType operationType) {
		this.operationType = operationType;
	}
	
	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	/*
	 * Passed To View
	 */
	
	private String cnOneStr;
	private String cnTwoStr;
	private String resultStr;
	private String operationName;
	
	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	
	public String getCnOneStr() {
		return cnOneStr;
	}

	public void setCnOneStr(String cnOneStr) {
		this.cnOneStr = cnOneStr;
	}

	public String getCnTwoStr() {
		return cnTwoStr;
	}

	public void setCnTwoStr(String cnTwoStr) {
		this.cnTwoStr = cnTwoStr;
	}

	/***********************
	 * Helper Methods Below
	 ***********************/

	private String handleActionForPlainText() {
		/* first string must be valid */
		if (cnStringOne == null) {
			return INPUT;
		}
		
		/* precision should not less than zero */
		if (precision < 0) {
			return INPUT;
		}
		
		Complex cnOne = new ComplexFormat().parse(cnStringOne);
		Complex cnTwo = null;
		if (cnStringTwo != null) {
			cnTwo = new ComplexFormat().parse(cnStringTwo);
		}
		
		Complex resultCN = null;
		
		switch (operationType) {
		case ADD:
			resultCN = cnOne.add(cnTwo);
			break;
		case SUBTRACT:
			resultCN = cnOne.subtract(cnTwo);
			break;
		case MULTIPLY:
			resultCN = cnOne.multiply(cnTwo);
			break;
		case DIVIDE:
			resultCN = cnOne.divide(cnTwo);
			break;
		case SIN:
			resultCN = cnOne.sin();
			break;
		case COS:
			resultCN = cnOne.cos();
			break;
		case TAN:
			resultCN = cnOne.tan();
			break;
		case ASIN:
			resultCN = cnOne.asin();
			break;
		case ACOS:
			resultCN = cnOne.acos();
			break;
		case ATAN:
			resultCN = cnOne.atan();
			break;
		case SINH:
			resultCN = cnOne.sinh();
			break;
		case COSH:
			resultCN = cnOne.cosh();
			break;
		case TANH:
			resultCN = cnOne.tanh();
			break;
		case LOG:
			resultCN = cnOne.log();
			break;
		case SQRT:
			resultCN = cnOne.sqrt();
			break;
		case EXP:
			resultCN = cnOne.exp();
			break;
		default:
			System.err.println(
				"ComplexNumberOperationAction: No such operation: " 
				+ operationType.toString());
			return INPUT;
		}
		
		/* Unary operator has no complex number two */
		if (operationType != ComplexNumberOperationType.ADD && 
			operationType != ComplexNumberOperationType.SUBTRACT &&
			operationType != ComplexNumberOperationType.MULTIPLY &&
			operationType != ComplexNumberOperationType.DIVIDE) {
			cnTwoStr = "None";
		}
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(precision);
		nf.setMaximumFractionDigits(precision);
		ComplexFormat cf = new ComplexFormat(nf);
		
		/* make values to be passed to the view */
		cnOneStr = cf.format(cnOne);
		cnTwoStr = cf.format(cnTwo);
		resultStr = cf.format(resultCN);
		operationName = operationType.toString();
		
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
	
	public enum ComplexNumberOperationType {
		ADD, SUBTRACT, MULTIPLY, DIVIDE, 
		SIN, COS, TAN, 
		ASIN, ACOS, ATAN, 
		SINH, COSH, TANH, 
		LOG, SQRT, EXP
	}

}
