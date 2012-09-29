package org.zenja.dataanalysis.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.MLSequenceSet;
import org.encog.ml.hmm.HiddenMarkovModel;
import org.encog.ml.hmm.alog.MarkovGenerator;
import org.encog.ml.hmm.distributions.DiscreteDistribution;
import org.zenja.dataanalysis.action.enums.InputType;
import org.zenja.dataanalysis.analysistools.data.BaumWelchHMMData;
import org.zenja.dataanalysis.analysistools.data.BaumWelchHMMData.StateDistributionType;
import org.zenja.dataanalysis.analysistools.data.BaumWelchHMMData.TransitionProbabilityData;

import com.opensymphony.xwork2.ActionSupport;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * Hidden Markov Model with Regular Baum Welch Learning
 * 
 * NEEDS TO BE REVISED!
 * 
 * @author wangxing
 *
 */
public class BaumWelchHMMAction extends ActionSupport {
	
	private static final long serialVersionUID = 213810066701902440L;
	
	private BaumWelchHMMData baumWelchHMMData;
	
	/*
	 * Received from view
	 */
	private InputType inputType;
	
	private int numStates;
	private int numItems;									/* can be omitted, in such case its value is 0 */
	private String piListStr; 								/* e.g. "0.95, 0.05" */
	private StateDistributionType stateDistributionType;
	private String stateDistributionProbabilitiesListStr;	/* e.g. "0.95, 0.05; 0.20, 0.80" */
	private String transitionProbabilityDataListStr; 		/* e.g. "0, 1, 0.05; 0, 0, 0.95; 1, 0, 0.10; 1, 1, 0.90" */
	
	private int observationCount;
	private int observationLength;
	
	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}
	
	public int getNumStates() {
		return numStates;
	}

	public void setNumStates(int numStates) {
		this.numStates = numStates;
	}

	public int getNumItems() {
		return numItems;
	}

	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}

	public String getPiListStr() {
		return piListStr;
	}

	public void setPiListStr(String piListStr) {
		this.piListStr = piListStr;
	}

	public StateDistributionType getStateDistributionType() {
		return stateDistributionType;
	}

	public void setStateDistributionType(StateDistributionType stateDistributionType) {
		this.stateDistributionType = stateDistributionType;
	}

	public String getStateDistributionProbabilitiesListStr() {
		return stateDistributionProbabilitiesListStr;
	}

	public void setStateDistributionProbabilitiesListStr(
			String stateDistributionProbabilitiesListStr) {
		this.stateDistributionProbabilitiesListStr = stateDistributionProbabilitiesListStr;
	}

	public String getTransitionProbabilityDataListStr() {
		return transitionProbabilityDataListStr;
	}

	public void setTransitionProbabilityDataListStr(
			String transitionProbabilityDataListStr) {
		this.transitionProbabilityDataListStr = transitionProbabilityDataListStr;
	}
	
	public int getObservationCount() {
		return observationCount;
	}

	public void setObservationCount(int observationCount) {
		this.observationCount = observationCount;
	}

	public int getObservationLength() {
		return observationLength;
	}

	public void setObservationLength(int observationLength) {
		this.observationLength = observationLength;
	}
	
	/*
	 * Passed To View
	 */

	private double[][][] generatedSequence;

	public double[][][] getGeneratedSequence() {
		return generatedSequence;
	}

	public void setGeneratedSequence(double[][][] generatedSequence) {
		this.generatedSequence = generatedSequence;
	}

	/***********************
	 * Helper Methods Below
	 ***********************/
	
	private String handleActionForPlainText() {
		if (numStates <= 0 || 
			numItems <0 ||
			piListStr == null || 
			stateDistributionType == null || 
			stateDistributionProbabilitiesListStr == null || 
			transitionProbabilityDataListStr == null) {
			
			return INPUT;
		}
		
		/* build data */
		baumWelchHMMData = new BaumWelchHMMData();
		baumWelchHMMData.setNumState(numStates);
		baumWelchHMMData.setNumItem(numItems);
		baumWelchHMMData.setPiList(parsePiListString(piListStr));
		baumWelchHMMData.setStateDistributionType(stateDistributionType);
		baumWelchHMMData.setStateDistributionProbabilitiesList(
				parseStateDistributionProbabilitiesListString(stateDistributionProbabilitiesListStr));
		baumWelchHMMData.setTransitionProbabilityDataList(parseTransitionProbabilityDataListString(transitionProbabilityDataListStr));
		
		/* get corresponding hmm object */
		HiddenMarkovModel hmm = buildHMM(baumWelchHMMData);
		
		/* train hmm */
		// TODO: this action is to be revised
		
		/* generate sequences */
		MarkovGenerator mg = new MarkovGenerator(hmm);
		MLSequenceSet training = mg.generateSequences(observationCount, observationLength);
		
		generatedSequence = new double[training.getSequences().size()][][];
		int i = 0;
		for (MLDataSet set : training.getSequences()) {
			int j = 0;
			double[][] setArr = new double[set.size()][];
			for (MLDataPair pair : set) {
				setArr[j] = pair.getInputArray();
//				System.out.print("Input array: " + Arrays.toString(pair.getInputArray()));
//				System.out.println(" Ideal array: " + Arrays.toString(pair.getIdealArray()));
				
				j++;
			}
			
			generatedSequence[i] = setArr;
			
			i++;
		}
		
		return SUCCESS;
	}
	
	private String handleActionForFile() {
		// TODO: to be implemented
		return ERROR;
	}
	
	/* input e.g. "0.95, 0.05" */
	private ArrayList<Double> parsePiListString(String str) {
		ArrayList<Double> result = new ArrayList<Double>();
		String trimedStr = str.replace(" ", "");	// remove all spaces
		for (String numStr : trimedStr.split(",")) {
			result.add(Double.parseDouble(numStr));
		}
		return result;
	}
	
	/* input e.g. "0.95,0.05|0.87,0.92;0.20,0.80|..." */
	/* ;-first level |-second level */
	private ArrayList<double[][]> parseStateDistributionProbabilitiesListString(String str) {
		ArrayList<double[][]> result = new ArrayList<double[][]>();
		String trimmedStr = str.replace(" ", "");
		
		String[] bigGroupStrs = trimmedStr.split(";");
		for (int i = 0; i < bigGroupStrs.length; i++) {
			String[] smallGroupStrs = bigGroupStrs[i].split("\\|");
			double[][] resultItem = new double[smallGroupStrs.length][];
			for (int j = 0; j < smallGroupStrs.length; j++) {
				String[] numStrs = smallGroupStrs[j].split(",");
				double[] resultItemItem = new double[numStrs.length];
				for (int k = 0; k < numStrs.length; k++) {
					resultItemItem[k] = Double.parseDouble(numStrs[k]);
				}
				resultItem[j] = resultItemItem;
			}
			result.add(resultItem);
		}
		
		return result;
	}
	
	/* input e.g. "0, 1, 0.05; 0, 0, 0.95; 1, 0, 0.10; 1, 1, 0.90" */
	private ArrayList<TransitionProbabilityData> parseTransitionProbabilityDataListString(String str) {
		ArrayList<TransitionProbabilityData> result = new ArrayList<TransitionProbabilityData>();
		
		String trimmedStr = str.replace(" ", "");
		for (String groupStr : trimmedStr.split(";")) {
			String[] numStr = groupStr.split(",");
			int i = Integer.parseInt(numStr[0]);
			int j = Integer.parseInt(numStr[1]);
			double value = Double.parseDouble(numStr[2]);
			TransitionProbabilityData data = new TransitionProbabilityData(i, j, value);
			result.add(data);
		}
		
		return result;
	}
	
	private static HiddenMarkovModel buildHMM(BaumWelchHMMData data) {
		HiddenMarkovModel hmm = null;
		if (data.getNumItem() == 0) {
			hmm = new HiddenMarkovModel(data.getNumState());
		} else {
			hmm = new HiddenMarkovModel(data.getNumState(), data.getNumItem());
		}
		
		/* set pi */
		for (int i = 0; i < data.getPiList().size(); i++) {
			hmm.setPi(i, data.getPiList().get(i));
		}
		
		/* set state distribution */
		switch (data.getStateDistributionType()) {
		case DISCRETE:
			for (int i = 0; i < data.getStateDistributionProbabilitiesList().size(); i++) {
				hmm.setStateDistribution(i, 
						new DiscreteDistribution(data.getStateDistributionProbabilitiesList().get(i)));
			}
			break;
		case CONTINOUS:
			// TODO: to be implemented
			break;
		}
		
		/* set transition probability */
		for (TransitionProbabilityData d : data.getTransitionProbabilityDataList()) {
			hmm.setTransitionProbability(d.getI(), d.getJ(), d.getValue());
		}
		
		return hmm;
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
		case PLAIN_TEXT:
			return handleActionForPlainText();
		default:
			System.err.println("ERROR: no matched inputType: " + inputType.toString());
			return INPUT;
		}
	}
	
	/********** TEST **********/
	public static void main(String[] args) {
		BaumWelchHMMAction action = new BaumWelchHMMAction();
//		ArrayList<double[][]> result = action.parseStateDistributionProbabilitiesListString("0.95, 0.05|5.0,4.0; 0.20, 0.80");
//		for (double[][] item : result) {
//			System.out.println("-----new list item-----");
//			for (double[] itemItem : item) {
//				System.out.println("--new list item item--");
//				System.out.println(Arrays.toString(itemItem));
//			}
//		}
//		System.out.println("\n*********************\n");
//		
//		System.out.println(action.parsePiListString("1,2,3,4,5"));
		
		action.setNumStates(2);
		action.setNumItems(2);
		action.setPiListStr("0.95, 0.05");
		action.setStateDistributionType(StateDistributionType.DISCRETE);
		action.setStateDistributionProbabilitiesListStr("0.95, 0.05; 0.20, 0.80");
		action.setTransitionProbabilityDataListStr("0, 1, 0.05; 0, 0, 0.95; 1, 0, 0.10; 1, 1, 0.90");
		
		action.observationCount = 20;
		action.observationLength = 10;
		
		System.out.println("Calling action...");
		action.handleActionForPlainText();
		
		System.out.print("[");
		for (double[][] arr1 : action.generatedSequence) {
			System.out.print("[");
			for(double[] arr2 : arr1) {
				System.out.print(Arrays.toString(arr2));
				System.out.print(", ");
			}
			System.out.print("], ");
		}
		System.out.print("], ");
		
	}
	
}



/* For Reference:

	HiddenMarkovModel hmm = 
		new HiddenMarkovModel(2, 2);	//numState, numItems(can be omitted)
	
	hmm.setPi(0, 0.95);
	hmm.setPi(1, 0.05);
	
	hmm.setStateDistribution(0, new DiscreteDistribution(new double[][] { { 0.95, 0.05 } }));
	hmm.setStateDistribution(1, new DiscreteDistribution(new double[][] { { 0.20, 0.80 } }));
	
	hmm.setTransitionProbability(0, 1, 0.05);
	hmm.setTransitionProbability(0, 0, 0.95);
	hmm.setTransitionProbability(1, 0, 0.10);
	hmm.setTransitionProbability(1, 1, 0.90);

*/
