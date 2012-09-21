package org.zenja.dataanalysis.action;

import java.io.File;
import java.io.IOException;

import org.zenja.dataanalysis.action.enums.InputType;
import org.zenja.dataanalysis.analysistools.NeuralNetworkTool;
import org.zenja.dataanalysis.analysistools.data.NeuralNetworkData;
import org.zenja.dataanalysis.analysistools.dataextractors.NeuralNetworkDataExtractor;
import org.zenja.dataanalysis.analysistools.dataextractors.exceptions.InputFormatException;

import com.opensymphony.xwork2.ActionSupport;

public class NeuralNetworkAction extends ActionSupport {

	private static final long serialVersionUID = 7061481572457382061L;
	
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
	private String trainingLog;
	private String trainingResult;
	private String neuralNetworkName;
	
	public String getTrainingLog() {
		return trainingLog;
	}

	public void setTrainingLog(String trainingLog) {
		this.trainingLog = trainingLog;
	}

	public String getTrainingResult() {
		return trainingResult;
	}

	public void setTrainingResult(String trainingResult) {
		this.trainingResult = trainingResult;
	}

	public String getNeuralNetworkName() {
		return neuralNetworkName;
	}

	public void setNeuralNetworkName(String neuralNetworkName) {
		this.neuralNetworkName = neuralNetworkName;
	}

	/***********************
	 * Helper Methods Below
	 ***********************/
	
	public String handleActionForFile() {
		/*
		 * Check variables availability
		 */
		if (file == null || false) {
			return INPUT;
		}
		
		NeuralNetworkData neuralNetworkData;
		try {
			neuralNetworkData = new NeuralNetworkDataExtractor(file).extract();
		} catch (InputFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		NeuralNetworkTool tool = new NeuralNetworkTool(neuralNetworkData);
		
		tool.train();
		
		trainingLog = tool.getTrainingLog().replaceAll("(\r\n|\n)", "<br>");
		trainingResult = tool.getTrainingResult().replaceAll("(\r\n|\n)", "<br>");
		neuralNetworkName = tool.getNeuralNetworkName();
		
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
