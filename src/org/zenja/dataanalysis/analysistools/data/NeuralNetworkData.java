package org.zenja.dataanalysis.analysistools.data;

import java.util.ArrayList;

/**
 * 
 * The data needed for NeuralNetworkTool.
 * 
 * @author wangxing
 *
 */
public class NeuralNetworkData {
	
	public enum ActivationFuncType {
		ACTIVATION_SIGMOID
	}
	
	private String name;
	private double[][] trainingInput;
	private double[][] trainingIdeal;
	private ArrayList<LayerData> layerDataList;
	private double trainingErrorThreshold;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double[][] getTrainingInput() {
		return trainingInput;
	}

	public void setTrainingInput(double[][] trainingInput) {
		this.trainingInput = trainingInput;
	}

	public double[][] getTrainingIdeal() {
		return trainingIdeal;
	}

	public void setTrainingIdeal(double[][] trainingIdeal) {
		this.trainingIdeal = trainingIdeal;
	}

	public ArrayList<LayerData> getLayerDataList() {
		return layerDataList;
	}

	public void setLayerDataList(ArrayList<LayerData> layerDataList) {
		this.layerDataList = layerDataList;
	}

	public double getTrainingErrorThreshold() {
		return trainingErrorThreshold;
	}

	public void setTrainingErrorThreshold(double trainingErrorThreshold) {
		this.trainingErrorThreshold = trainingErrorThreshold;
	}

	public class LayerData {
		private ActivationFuncType activationFuncType;
		private boolean hasBias;
		private int neuronCount;
		
		public ActivationFuncType getActivationFuncType() {
			return activationFuncType;
		}
		
		public void setActivationFuncType(ActivationFuncType activationFuncType) {
			this.activationFuncType = activationFuncType;
		}
		
		public boolean isHasBias() {
			return hasBias;
		}
		
		public void setHasBias(boolean hasBias) {
			this.hasBias = hasBias;
		}
		
		public int getNeuronCount() {
			return neuronCount;
		}
		
		public void setNeuronCount(int neuronCount) {
			this.neuronCount = neuronCount;
		}
		
	}
}
