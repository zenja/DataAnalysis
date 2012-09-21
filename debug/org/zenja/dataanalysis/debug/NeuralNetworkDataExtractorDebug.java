package org.zenja.dataanalysis.debug;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.zenja.dataanalysis.analysistools.data.NeuralNetworkData;
import org.zenja.dataanalysis.analysistools.data.NeuralNetworkData.LayerData;
import org.zenja.dataanalysis.analysistools.dataextractors.NeuralNetworkDataExtractor;
import org.zenja.dataanalysis.analysistools.dataextractors.exceptions.InputFormatException;

import edu.emory.mathcs.backport.java.util.Arrays;

public class NeuralNetworkDataExtractorDebug {

	public static void main(String[] args) throws InputFormatException, IOException {
		File inputFile = new File("/home/wangxing/Desktop/test-data/neural-network-xor.xml");
		NeuralNetworkDataExtractor extractor = new NeuralNetworkDataExtractor(inputFile);
		
		NeuralNetworkData data = extractor.extract();
		
		double[][] trainingInput = data.getTrainingInput();
		double[][] trainingIdeal = data.getTrainingIdeal();
		ArrayList<LayerData> layerDataList = data.getLayerDataList();
		double threshold = data.getTrainingErrorThreshold();
		
		System.out.println("Input: ");
		for (double[] input : trainingInput) {
			System.out.println(Arrays.toString(input));
		}
		
		System.out.println("Ideal: ");
		for (double[] ideal : trainingIdeal) {
			System.out.println(Arrays.toString(ideal));
		}
		
		System.out.println("Layers: ");
		for (LayerData layer : layerDataList) {
			System.out.println("Layer");
			System.out.println(layer.getActivationFuncType());
			System.out.println(layer.getNeuronCount());
			System.out.println(layer.isHasBias());
		}
		
		System.out.println("Threshold: " + threshold);
	}

}
