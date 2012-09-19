package org.zenja.dataanalysis.action;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.zenja.dataanalysis.action.enums.InputType;
import org.zenja.dataanalysis.datatype.TwoDimensionalPoint;

import com.opensymphony.xwork2.ActionSupport;

public class SimpleRegressionAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8038399855595406886L;
	
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
	List<TwoDimensionalPoint> inputList;
	double intercept;
	double slope;
	double stdErr;					// standard error of the slope estimate
	long numObservation;			// number of observations
	double meanSquareError;			// the sum of squared errors divided by the degrees of freedom, usually abbreviated MSE
	double r;						// Pearson's product moment correlation coefficient, usually denoted r
	double regressionSumSquares;	// the sum of squared deviations of the predicted y values about their mean (which equals the mean of y)
	double rSquare;					// the coefficient of determination, usually denoted r-square
	double significance;			// the significance level of the slope (equiv) correlation
	double slopeConfidenceInterval;	// the half-width of a 95% confidence interval for the slope estimate
	double sumOfCrossProducts;		// the sum of crossproducts, xi*yi
	double sumSquaredErrors;		// the sum of squared errors (SSE) associated with the regression model
	double totalSumSquares;			// the sum of squared deviations of the y values about their mean
	double xSumSquares;				// the sum of squared deviations of the x values about their mean
	double minX;
	double maxX;
	double minY;
	double maxY;
	
	
	public List<TwoDimensionalPoint> getInputList() {
		return inputList;
	}

	public void setInputList(List<TwoDimensionalPoint> inputList) {
		this.inputList = inputList;
	}

	public double getIntercept() {
		return intercept;
	}

	public void setIntercept(double intercept) {
		this.intercept = intercept;
	}

	public double getSlope() {
		return slope;
	}

	public void setSlope(double slope) {
		this.slope = slope;
	}


	public double getStdErr() {
		return stdErr;
	}

	public void setStdErr(double stdErr) {
		this.stdErr = stdErr;
	}

	public long getNumObservation() {
		return numObservation;
	}

	public void setNumObservation(long numObservation) {
		this.numObservation = numObservation;
	}

	public double getMeanSquareError() {
		return meanSquareError;
	}

	public void setMeanSquareError(double meanSquareError) {
		this.meanSquareError = meanSquareError;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getRegressionSumSquares() {
		return regressionSumSquares;
	}

	public void setRegressionSumSquares(double regressionSumSquares) {
		this.regressionSumSquares = regressionSumSquares;
	}

	public double getRSquare() {
		return rSquare;
	}

	public void setRSquare(double rSquare) {
		this.rSquare = rSquare;
	}

	public double getSignificance() {
		return significance;
	}

	public void setSignificance(double significance) {
		this.significance = significance;
	}

	public double getSlopeConfidenceInterval() {
		return slopeConfidenceInterval;
	}

	public void setSlopeConfidenceInterval(double slopeConfidenceInterval) {
		this.slopeConfidenceInterval = slopeConfidenceInterval;
	}

	public double getSumOfCrossProducts() {
		return sumOfCrossProducts;
	}

	public void setSumOfCrossProducts(double sumOfCrossProducts) {
		this.sumOfCrossProducts = sumOfCrossProducts;
	}

	public double getSumSquaredErrors() {
		return sumSquaredErrors;
	}

	public void setSumSquaredErrors(double sumSquaredErrors) {
		this.sumSquaredErrors = sumSquaredErrors;
	}

	public double getTotalSumSquares() {
		return totalSumSquares;
	}

	public void setTotalSumSquares(double totalSumSquares) {
		this.totalSumSquares = totalSumSquares;
	}

	public double getXSumSquares() {
		return xSumSquares;
	}

	public void setXSumSquares(double xSumSquares) {
		this.xSumSquares = xSumSquares;
	}

	public double getMinX() {
		return minX;
	}

	public void setMinX(double minX) {
		this.minX = minX;
	}

	public double getMinY() {
		return minY;
	}

	public void setMinY(double minY) {
		this.minY = minY;
	}

	public double getMaxX() {
		return maxX;
	}

	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	public double getMaxY() {
		return maxY;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
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

	private String handleActionForFile() {
		FileInputStream fstream = null;
		DataInputStream in = null;
		BufferedReader br = null;
		try {
			fstream = new FileInputStream(file);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			
			SimpleRegression regression = new SimpleRegression();
			
			String strLine;
			inputList = new ArrayList<TwoDimensionalPoint>();
			
			while ((strLine = br.readLine()) != null) {
				String[] strXAndY = strLine.split(",");
				double x = Double.parseDouble(strXAndY[0]);
				double y = Double.parseDouble(strXAndY[1]);
				
				regression.addData(x, y);
				
				TwoDimensionalPoint point = new TwoDimensionalPoint(x, y);
				inputList.add(point);
			}
			
			// processing data
			intercept = regression.getIntercept();
			slope = regression.getSlope();
			stdErr = regression.getSlopeStdErr();
			numObservation = regression.getN();
			meanSquareError = regression.getMeanSquareError();
			r = regression.getR();
			regressionSumSquares = regression.getRegressionSumSquares();
			rSquare = regression.getRSquare();
			significance = regression.getSignificance();
			slopeConfidenceInterval = regression.getSlopeConfidenceInterval();
			sumOfCrossProducts = regression.getSumOfCrossProducts();
			sumSquaredErrors = regression.getSumSquaredErrors();
			totalSumSquares = regression.getTotalSumSquares();
			xSumSquares = regression.getXSumSquares();
			
			// get X and Y's min/max value
			minX = inputList.get(0).getX();
			maxX = inputList.get(0).getX();
			minY = inputList.get(0).getY();
			maxY = inputList.get(0).getY();
			for (TwoDimensionalPoint point : inputList) {
				if (point.getX() < minX) {
					minX = point.getX();
				}
				if (point.getX() > maxX) {
					maxX = point.getX();
				}
				if (point.getY() < minY) {
					minY = point.getY();
				}
				if (point.getY() > maxY) {
					maxY = point.getY();
				}
			}
			
			return SUCCESS;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (Exception e) {
			// Default Error Handling
			e.printStackTrace();
			return ERROR;
		} finally {
			try {
				if (null != br)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
				return ERROR;
			}
		}
		
	}
	
}
