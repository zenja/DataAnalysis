package org.zenja.dataanalysis.action;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.opensymphony.xwork2.ActionSupport;

public class StatisticsAction extends ActionSupport {

	private static final long serialVersionUID = -6904841166346950772L;
	
	/*
	 * Received from view
	 */
	private File file;
	private String dataType;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	/*
	 * Data sent to result view
	 */
	List<Double> inputList;
	double mean;
	double min;
	double max;
	double standardDeviation;
	double median;
	double sum;
	double skewness;
	double kurtosis;

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public double getMedian() {
		return median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public List<Double> getInputList() {
		return inputList;
	}

	public void setInputList(List<Double> inputList) {
		this.inputList = inputList;
	}

	public double getSkewness() {
		return skewness;
	}

	public void setSkewness(double skewness) {
		this.skewness = skewness;
	}

	public double getKurtosis() {
		return kurtosis;
	}

	public void setKurtosis(double kurtosis) {
		this.kurtosis = kurtosis;
	}

	/***********************
	 * Action Methods Below
	 ***********************/
	
	public String index() {
		FileInputStream fstream = null;
		DataInputStream in = null;
		BufferedReader br = null;
		try {
			fstream = new FileInputStream(file);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			
			// Get a DescriptiveStatistics instance
			DescriptiveStatistics stats = new DescriptiveStatistics();
			
			String strLine;
			inputList = new ArrayList<Double>();
			
			while ((strLine = br.readLine()) != null) {
				double number = Double.parseDouble(strLine);
				inputList.add(number);
				stats.addValue(number);
			}
			
			// processing data
			mean = stats.getMean();
			max = stats.getMax();
			min = stats.getMin();
			standardDeviation = stats.getStandardDeviation();
			median = stats.getPercentile(50);
			sum = stats.getSum();
			stats.getSkewness();
			stats.getKurtosis();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
