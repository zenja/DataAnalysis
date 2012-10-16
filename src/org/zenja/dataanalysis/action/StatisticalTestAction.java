package org.zenja.dataanalysis.action;

import org.apache.commons.math3.stat.inference.TestUtils;
import org.zenja.dataanalysis.action.enums.InputType;

import com.opensymphony.xwork2.ActionSupport;

public class StatisticalTestAction extends ActionSupport {
	
	private static final long serialVersionUID = 2915281439356862579L;
	
	/*
	 * Received from view
	 */
	private InputType inputType;
	private String observedStr;
	private String expectedStr;
	
	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public String getObservedStr() {
		return observedStr;
	}

	public void setObservedStr(String observedStr) {
		this.observedStr = observedStr;
	}

	public String getExpectedStr() {
		return expectedStr;
	}

	public void setExpectedStr(String expectedStr) {
		this.expectedStr = expectedStr;
	}

	/*
	 * Passed To View
	 */
	private double pValue;
	
	public double getPValue() {
		return pValue;
	}

	public void setPValue(double pValue) {
		this.pValue = pValue;
	}
	
	/***********************
	 * Helper Methods Below
	 ***********************/
	
	public String handleChiSquareTestActionForPlainText() {
		/* check params availbility */
		if (observedStr == null || expectedStr == null) {
			return INPUT;
		}
		
		/* get observed and expected from the strings */
		String[] observedItemStrs = observedStr.replace(" ", "").split(",");
		long[] observed = new long[observedItemStrs.length];
		for (int i = 0; i < observedItemStrs.length; i++) {
			String observedItemStr = observedItemStrs[i];
			observed[i] = Long.parseLong(observedItemStr);
		}
		String[] expectedItemStrs = expectedStr.replace(" ", "").split(",");
		double[] expected = new double[expectedItemStrs.length];
		for (int i = 0; i < expectedItemStrs.length; i++) {
			String expectedItemStr = expectedItemStrs[i];
			expected[i] = Double.parseDouble(expectedItemStr);
		}
		
		pValue = TestUtils.chiSquareTest(expected, observed);
		
		return SUCCESS;
	}
	
	/***********************
	 * Action Methods Below
	 ***********************/
	
	public String chiSquareTest() {
		if (inputType == null) {
			System.err.println("Missing field: inputType");
			return INPUT;
		}
		
		switch (inputType) {
		case PLAIN_TEXT:
			return handleChiSquareTestActionForPlainText();
		default:
			System.err.println("ERROR: no matched inputType: " + inputType.toString());
			return INPUT;
		}
	}
	
}
