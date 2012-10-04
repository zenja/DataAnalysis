package org.zenja.dataanalysis.action;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.math3.random.ValueServer;
import org.zenja.dataanalysis.action.enums.InputType;

import com.opensymphony.xwork2.ActionSupport;

public class ValueServerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 825412065958857180L;
	
	/*
	 * Received from view
	 */
	private InputType inputType;
	private File file;
	private int numSample;

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

	public int getNumSample() {
		return numSample;
	}

	public void setNumSample(int numSample) {
		this.numSample = numSample;
	}
	
	/*
	 * Passed to view
	 */
	private double[] randomSequence;

	public double[] getRandomSequence() {
		return randomSequence;
	}

	public void setRandomSequence(double[] randomSequence) {
		this.randomSequence = randomSequence;
	}

	/***********************
	 * Helper Methods Below
	 ***********************/

	private String handleActionForFile() {
		if (file == null) {
			return INPUT;
		}

		ValueServer vs = new ValueServer();
		
		try {
			vs.setValuesFileURL(file.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return ERROR;
		}
		
		vs.setMode(ValueServer.DIGEST_MODE);
		
		try {
			vs.computeDistribution();
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		
		try {
			randomSequence = vs.fill(numSample);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
