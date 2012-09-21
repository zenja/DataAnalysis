package org.zenja.dataanalysis.analysistools;

import org.encog.engine.network.activation.ActivationFunction;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.neural.data.NeuralDataSet;
import org.encog.neural.data.basic.BasicNeuralDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.Train;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.zenja.dataanalysis.analysistools.data.NeuralNetworkData;
import org.zenja.dataanalysis.analysistools.data.NeuralNetworkData.LayerData;

public class NeuralNetworkTool {
	private NeuralNetworkData neuralNetworkData;
	private String trainingLog;
	private String trainingResult;
	private String neuralNetworkName;
	
	public String getTrainingLog() {
		return trainingLog;
	}
	
	public String getTrainingResult() {
		return trainingResult;
	}
	
	public NeuralNetworkTool(NeuralNetworkData data) {
		this.neuralNetworkData = data;
	}
	
	public String getNeuralNetworkName() {
		return neuralNetworkName;
	}

	public void setNeuralNetworkName(String neuralNetworkName) {
		this.neuralNetworkName = neuralNetworkName;
	}

	public void train() {
		/* get training set */
		NeuralDataSet trainingSet = getTrainingSet();
		
		/* build network */
		BasicNetwork network = new BasicNetwork();
		for (LayerData layerData : neuralNetworkData.getLayerDataList()) {
			ActivationFunction activationFunc = null;
			switch (layerData.getActivationFuncType()) {
			case ACTIVATION_SIGMOID:
				activationFunc = new ActivationSigmoid();
				break;
			default:
			}
			BasicLayer layer = new BasicLayer(activationFunc, layerData.isHasBias(), layerData.getNeuronCount());
			network.addLayer(layer);
		}
		network.getStructure().finalizeStructure();
		network.reset();
		
		/* get the name of the neural network */
		neuralNetworkName = neuralNetworkData.getName();
		
		/* train the network and log the training process */
		Train train = new ResilientPropagation(network, trainingSet);
		StringBuilder trainingLogSB = new StringBuilder();
		trainingLogSB.append("Start training...\n");
		int epoch = 1;
		do {
			train.iteration();
			trainingLogSB.append("Epoch #" + epoch + " Error rate: " + train.getError() + "\n");
			epoch++;
		} while (train.getError() > neuralNetworkData.getTrainingErrorThreshold());
		trainingLogSB.append("Training finished!");
		trainingLog = trainingLogSB.toString();
		
		/* feed input into the network and record results */
		StringBuilder trainingResultSB = new StringBuilder();
		for (MLDataPair pair : trainingSet) {
			MLData output = network.compute(pair.getInput());
			trainingResultSB.append("Input = " + pair.getInput().getData(0) + ", "
					+ pair.getInput().getData(1) + "; Actual value = "
					+ output.getData(0) + "; Ideal value = "
					+ pair.getIdeal().getData(0) + "\n");
		}
		trainingResult = trainingResultSB.toString();
	}
	
	private NeuralDataSet getTrainingSet() {
		return new BasicNeuralDataSet(neuralNetworkData.getTrainingInput(), neuralNetworkData.getTrainingIdeal());
	}
}
