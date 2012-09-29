package org.zenja.dataanalysis.analysistools.data;

import java.util.ArrayList;

/**
 * The data needed to build a HMM with Regular Baum Welch Learning
 * 
 * Used by the actions:
 * 1. BaumWelchHMMAction
 * 
 * @author wangxing
 *
 */
public class BaumWelchHMMData {
	private int numState;
	private int numItem;
	private ArrayList<Double> piList;
	private StateDistributionType stateDistributionType;
	private ArrayList<double[][]> stateDistributionProbabilitiesList;
	private ArrayList<TransitionProbabilityData> transitionProbabilityDataList;
	
	public int getNumState() {
		return numState;
	}

	public void setNumState(int numState) {
		this.numState = numState;
	}

	public int getNumItem() {
		return numItem;
	}

	public void setNumItem(int numItem) {
		this.numItem = numItem;
	}

	public ArrayList<Double> getPiList() {
		return piList;
	}

	public void setPiList(ArrayList<Double> piList) {
		this.piList = piList;
	}

	public StateDistributionType getStateDistributionType() {
		return stateDistributionType;
	}

	public void setStateDistributionType(StateDistributionType stateDistributionType) {
		this.stateDistributionType = stateDistributionType;
	}

	public ArrayList<double[][]> getStateDistributionProbabilitiesList() {
		return stateDistributionProbabilitiesList;
	}

	public void setStateDistributionProbabilitiesList(
			ArrayList<double[][]> stateDistributionProbabilitiesList) {
		this.stateDistributionProbabilitiesList = stateDistributionProbabilitiesList;
	}

	public ArrayList<TransitionProbabilityData> getTransitionProbabilityDataList() {
		return transitionProbabilityDataList;
	}

	public void setTransitionProbabilityDataList(
			ArrayList<TransitionProbabilityData> transitionProbabilityDataList) {
		this.transitionProbabilityDataList = transitionProbabilityDataList;
	}

	public static class TransitionProbabilityData {
		private int i, j;
		private double value;
		
		public TransitionProbabilityData(int i, int j, double value) {
			this.i = i;
			this.j = j;
			this.value = value;
		}

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		public int getJ() {
			return j;
		}

		public void setJ(int j) {
			this.j = j;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

	}
	
	public enum StateDistributionType {
		DISCRETE, CONTINOUS
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

