package org.zenja.dataanalysis.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.inference.TestUtils;
import org.zenja.dataanalysis.action.enums.InputType;
import org.zenja.dataanalysis.analysistools.dataextractors.MatrixExtractor;
import org.zenja.dataanalysis.analysistools.dataextractors.VectorExtractor;

import com.opensymphony.xwork2.ActionSupport;

public class StatisticalTestAction extends ActionSupport {
	
	private static final long serialVersionUID = 2915281439356862579L;
	
	/*
	 * Received from view
	 */
	private InputType inputType;
	private String observedStr;
	private String expectedStr;
	private String oneWayAnovaTestDataStr;
	private String tTestSampleOneStr;
	private String tTestSampleTwoStr;
	
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

	public String getOneWayAnovaTestDataStr() {
		return oneWayAnovaTestDataStr;
	}

	public void setOneWayAnovaTestDataStr(String oneWayAnovaTestDataStr) {
		this.oneWayAnovaTestDataStr = oneWayAnovaTestDataStr;
	}

	public String getTTestSampleOneStr() {
		return tTestSampleOneStr;
	}

	public void setTTestSampleOneStr(String tTestSampleOneStr) {
		this.tTestSampleOneStr = tTestSampleOneStr;
	}

	public String getTTestSampleTwoStr() {
		return tTestSampleTwoStr;
	}

	public void setTTestSampleTwoStr(String tTestSampleTwoStr) {
		this.tTestSampleTwoStr = tTestSampleTwoStr;
	}

	/*
	 * Passed To View
	 */
	private double pValue;
	private double fStatistic;
	private double tStatistic;
	private double[][] oneWayAnovaTestData;
	
	public double getPValue() {
		return pValue;
	}

	public void setPValue(double pValue) {
		this.pValue = pValue;
	}
	
	public double getFStatistic() {
		return fStatistic;
	}

	public void setFStatistic(double fStatistic) {
		this.fStatistic = fStatistic;
	}

	public double[][] getOneWayAnovaTestData() {
		return oneWayAnovaTestData;
	}

	public void setOneWayAnovaTestData(double[][] oneWayAnovaTestData) {
		this.oneWayAnovaTestData = oneWayAnovaTestData;
	}

	public double getTStatistic() {
		return tStatistic;
	}

	public void setTStatistic(double tStatistic) {
		this.tStatistic = tStatistic;
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
	
	public String handleOneWayAnovaTestActionForPlainText() {
		/* check params availability */
		if (oneWayAnovaTestDataStr == null) {
			return INPUT;
		}
		
		oneWayAnovaTestData = MatrixExtractor.extractMatrixAs2DArray(oneWayAnovaTestDataStr, "(\r\n|\n\r|\n)", ",");
		List<double[]> classes = new ArrayList<double[]>(oneWayAnovaTestData.length);
		for (double[] arr : oneWayAnovaTestData) {
			classes.add(arr);
		}
		
		fStatistic = TestUtils.oneWayAnovaFValue(classes); // F-value
		pValue = TestUtils.oneWayAnovaPValue(classes);     // P-value
		
		return SUCCESS;
	}
	
	public String handleTwoSampleTTestActionForPlainText() {
		if (tTestSampleOneStr == null || tTestSampleTwoStr == null) {
			return INPUT;
		}
		
		double[] sample1 = VectorExtractor.extractVectorAsArray(tTestSampleOneStr, ",");
		double[] sample2 = VectorExtractor.extractVectorAsArray(tTestSampleTwoStr, ",");
		
		tStatistic = TestUtils.pairedT(sample1, sample2);
		pValue = TestUtils.pairedTTest(sample1, sample2);
		
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
	
	public String oneWayAnovaTest() {
		if (inputType == null) {
			System.err.println("Missing field: inputType");
			return INPUT;
		}
		
		switch (inputType) {
		case PLAIN_TEXT:
			return handleOneWayAnovaTestActionForPlainText();
		default:
			System.err.println("ERROR: no matched inputType: " + inputType.toString());
			return INPUT;
		}
	}
	
	public String twoSampleTTest() {
		if (inputType == null) {
			System.err.println("Missing field: inputType");
			return INPUT;
		}
		
		switch (inputType) {
		case PLAIN_TEXT:
			return handleTwoSampleTTestActionForPlainText();
		default:
			System.err.println("ERROR: no matched inputType: " + inputType.toString());
			return INPUT;
		}
	}
	
}
