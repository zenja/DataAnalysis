package org.zenja.dataanalysis.analysistools.dataextractors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.zenja.dataanalysis.analysistools.data.NeuralNetworkData;
import org.zenja.dataanalysis.analysistools.data.NeuralNetworkData.ActivationFuncType;
import org.zenja.dataanalysis.analysistools.data.NeuralNetworkData.LayerData;
import org.zenja.dataanalysis.analysistools.dataextractors.exceptions.InputFormatException;


public class NeuralNetworkDataExtractor {
	private NeuralNetworkData neuralNetworkData;
	private File inputFile;
	
	public NeuralNetworkDataExtractor(File inputFile) {
		this.inputFile = inputFile;
	}
	
	public NeuralNetworkData extract() throws InputFormatException, IOException {
		
		neuralNetworkData = new NeuralNetworkData();

		try {
			/* data for building NeuralNetworkData object */
			String name = "Default Name";
			List<double[]> trainingInputList = new ArrayList<double[]>();
			List<double[]> trainingIdealList = new ArrayList<double[]>();
			double[][] trainingInput;
			double[][] trainingIdeal;
			ArrayList<LayerData> layerDataList = new ArrayList<LayerData>();
			
			/* some root-like nodes */
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(inputFile);
			NodeList root = doc.getChildNodes();
			Node nodeNeuralNetworkData = getNode("NeuralNetworkData", root);
			Node nodeTrainingDataList = getNode("TrainingDataList", nodeNeuralNetworkData.getChildNodes());
			
			/* extract the name of the neural network */
			Node nodeName = getNode("Name", nodeNeuralNetworkData.getChildNodes());
			name = getNodeValue(nodeName);
			neuralNetworkData.setName(name);
			
			/* extract input/ideal */
			NodeList nodelistDataItem = nodeTrainingDataList.getChildNodes();
			for (int i = 0; i < nodelistDataItem.getLength(); i++) {
				Node nodeDataItem = nodelistDataItem.item(i);
				if (nodeDataItem.getNodeName().equalsIgnoreCase("DataItem")) {
					String inputStr = getNodeValue("Input", nodeDataItem.getChildNodes());
					String idealStr = getNodeValue("Output", nodeDataItem.getChildNodes());
					
					String[] inputStrArr = inputStr.split(",");
					double[] inputArr = new double[inputStrArr.length];
					for (int n = 0; n < inputStrArr.length; n++) {
						inputArr[n] = Double.parseDouble(inputStrArr[n]);
					}
					
					String[] idealStrArr = idealStr.split(",");
					double[] idealArr = new double[idealStrArr.length];
					for (int n = 0; n < idealStrArr.length; n++) {
						idealArr[n] = Double.parseDouble(idealStrArr[n]);
					}
					
					trainingInputList.add(inputArr);
					trainingIdealList.add(idealArr);
				}
			}
			trainingInput = new double[trainingInputList.size()][];
			trainingIdeal = new double[trainingIdealList.size()][];
			for (int i = 0; i < trainingInputList.size(); i++) {
				trainingInput[i] = trainingInputList.get(i);
			}
			for (int i = 0; i < trainingIdealList.size(); i++) {
				trainingIdeal[i] = trainingIdealList.get(i);
			}
			neuralNetworkData.setTrainingInput(trainingInput);
			neuralNetworkData.setTrainingIdeal(trainingIdeal);
			
			/* extract layer data */
			Node nodeNetworkLayerList = getNode("NetworkLayerList", nodeNeuralNetworkData.getChildNodes());
			NodeList nodeListLayer = nodeNetworkLayerList.getChildNodes();
			for (int i = 0; i < nodeListLayer.getLength(); i++) {
				LayerData layerData = neuralNetworkData.new LayerData();
				Node nodeLayer = nodeListLayer.item(i);
				if (nodeLayer.getNodeName().equalsIgnoreCase("Layer")) {
					String activationFuncTypeStr = getNodeValue("ActivationFuncType", nodeLayer.getChildNodes());
					String hasBiasStr = getNodeValue("HasBias", nodeLayer.getChildNodes());
					String neuronCountStr = getNodeValue("NeuronCount", nodeLayer.getChildNodes());
					/* layer field: ActivationFuncType */
					if (activationFuncTypeStr.equalsIgnoreCase("ActivationSigmoid")) {
						layerData.setActivationFuncType(ActivationFuncType.ACTIVATION_SIGMOID);
					}
					/* layer field: HasBias */
					if (hasBiasStr.equals("0")) {
						layerData.setHasBias(false);
					} else {
						layerData.setHasBias(true);
					}
					/* layer field: NeuronCount */
					layerData.setNeuronCount(Integer.parseInt(neuronCountStr));
					
					layerDataList.add(layerData);
				}
			}
			neuralNetworkData.setLayerDataList(layerDataList);
			
			/* extract TrainingErrorThreshold */
			Node nodeTrainingErrorThreshold = getNode("TrainingErrorThreshold", nodeNeuralNetworkData.getChildNodes());
			neuralNetworkData.setTrainingErrorThreshold(Double.parseDouble(getNodeValue(nodeTrainingErrorThreshold)));
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			throw new InputFormatException("NeuralNetworkDataExtractor.extract(): Input file format error");
		} catch (SAXException se) {
			se.printStackTrace();
			throw new InputFormatException("NeuralNetworkDataExtractor.extract(): Input file format error");
		} catch (IOException ioe) {
			throw ioe;
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			throw new InputFormatException("NeuralNetworkDataExtractor.extract(): Input file format error");
		}
		
		return neuralNetworkData;
	}
	
	
	/*
	 * find the node with the specified name in the specified node list
	 */
	protected Node getNode(String tagName, NodeList nodes) {
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				return node;
			}
		}

		return null;
	}

	/*
	 * get the value of the node
	 * e.g. "xing" in node <name>xing</name>
	 */
	protected String getNodeValue(Node node) {
		NodeList childNodes = node.getChildNodes();
		for (int x = 0; x < childNodes.getLength(); x++) {
			Node data = childNodes.item(x);
			if (data.getNodeType() == Node.TEXT_NODE)
				return data.getNodeValue();
		}
		return "";
	}

	/*
	 * find the node with the specified name in the specified node list,
	 * and return the value of it
	 */
	protected String getNodeValue(String tagName, NodeList nodes) {
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				NodeList childNodes = node.getChildNodes();
				for (int y = 0; y < childNodes.getLength(); y++) {
					Node data = childNodes.item(y);
					if (data.getNodeType() == Node.TEXT_NODE)
						return data.getNodeValue();
				}
			}
		}
		return "";
	}

	protected String getNodeAttr(String attrName, Node node) {
		NamedNodeMap attrs = node.getAttributes();
		for (int y = 0; y < attrs.getLength(); y++) {
			Node attr = attrs.item(y);
			if (attr.getNodeName().equalsIgnoreCase(attrName)) {
				return attr.getNodeValue();
			}
		}
		return "";
	}

	protected String getNodeAttr(String tagName, String attrName, NodeList nodes) {
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				NodeList childNodes = node.getChildNodes();
				for (int y = 0; y < childNodes.getLength(); y++) {
					Node data = childNodes.item(y);
					if (data.getNodeType() == Node.ATTRIBUTE_NODE) {
						if (data.getNodeName().equalsIgnoreCase(attrName))
							return data.getNodeValue();
					}
				}
			}
		}

		return "";
	}
}

