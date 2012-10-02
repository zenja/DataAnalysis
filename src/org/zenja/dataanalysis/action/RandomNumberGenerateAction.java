package org.zenja.dataanalysis.action;

import org.apache.commons.math3.distribution.BetaDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.WeibullDistribution;
import org.zenja.dataanalysis.action.enums.InputType;

import com.opensymphony.xwork2.ActionSupport;

public class RandomNumberGenerateAction extends ActionSupport {
	
	private static final long serialVersionUID = 3732624883856340120L;

	/*
	 * Received from view
	 */
	private InputType inputType;
	
	private DistributionType distributionType;
	private int numSample;
	
	private double exponentialMean;
	private double betaAlpha, betaBeta;
	private double normalMean, normalSd;
	private double gammaAlpha, gammaBeta;
	private double wbAlpha, wbBeta;
	
	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public DistributionType getDistributionType() {
		return distributionType;
	}

	public void setDistributionType(DistributionType distributionType) {
		this.distributionType = distributionType;
	}

	public int getNumSample() {
		return numSample;
	}

	public void setNumSample(int numSample) {
		this.numSample = numSample;
	}

	public double getExponentialMean() {
		return exponentialMean;
	}

	public void setExponentialMean(double exponentialMean) {
		this.exponentialMean = exponentialMean;
	}

	public double getBetaAlpha() {
		return betaAlpha;
	}

	public void setBetaAlpha(double betaAlpha) {
		this.betaAlpha = betaAlpha;
	}

	public double getBetaBeta() {
		return betaBeta;
	}

	public void setBetaBeta(double betaBeta) {
		this.betaBeta = betaBeta;
	}

	public double getNormalMean() {
		return normalMean;
	}

	public void setNormalMean(double normalMean) {
		this.normalMean = normalMean;
	}

	public double getNormalSd() {
		return normalSd;
	}

	public void setNormalSd(double normalSd) {
		this.normalSd = normalSd;
	}

	public double getGammaAlpha() {
		return gammaAlpha;
	}

	public void setGammaAlpha(double gammaAlpha) {
		this.gammaAlpha = gammaAlpha;
	}

	public double getGammaBeta() {
		return gammaBeta;
	}

	public void setGammaBeta(double gammaBeta) {
		this.gammaBeta = gammaBeta;
	}

	public double getWbAlpha() {
		return wbAlpha;
	}

	public void setWbAlpha(double wbAlpha) {
		this.wbAlpha = wbAlpha;
	}

	public double getWbBeta() {
		return wbBeta;
	}

	public void setWbBeta(double wbBeta) {
		this.wbBeta = wbBeta;
	}


	/*
	 * Passed To View
	 */
	
	private double[] randomSequence;
	private String distTypeStr;
	
	public double[] getRandomSequence() {
		return randomSequence;
	}

	public void setRandomSequence(double[] randomSequence) {
		this.randomSequence = randomSequence;
	}

	public String getDistTypeStr() {
		return distTypeStr;
	}

	public void setDistTypeStr(String distTypeStr) {
		this.distTypeStr = distTypeStr;
	}

	/***********************
	 * Helper Methods Below
	 ***********************/
	
	public String handleActionForPlainText() {
		if (distributionType == null) {
			return INPUT;
		}
		
		if (numSample <= 0) {
			return INPUT;
		}
		
		switch (distributionType) {
		case EXPONENTIAL:
			return generateExponential();
		case BETA:
			return generateBeta();
		case NORMAL:
			return generateNormal();
		case GAMMA:
			return generateGamma();
		case WEIBULL:
			return generateWeibull();
		default:
			return INPUT;
		}
		
	}
	
	private String generateExponential() {
		distTypeStr = "Exponential distribution";
		
		ExponentialDistribution expDist = new ExponentialDistribution(exponentialMean);
		randomSequence = expDist.sample(numSample);
		
		return SUCCESS;
	}
	
	private String generateBeta() {
		distTypeStr = "Beta distribution";
		
		BetaDistribution betaDist = new BetaDistribution(betaAlpha, betaBeta);
		randomSequence = betaDist.sample(numSample);
		
		return SUCCESS;
	}
	
	private String generateNormal() {
		distTypeStr = "Normal distribution";
		
		NormalDistribution normalDist = new NormalDistribution(normalMean, normalSd);
		randomSequence = normalDist.sample(numSample);
		
		return SUCCESS;
	}
	
	private String generateGamma() {
		distTypeStr = "Gamma distribution";
		
		GammaDistribution gammaDist = new GammaDistribution(gammaAlpha, gammaBeta);
		randomSequence = gammaDist.sample(numSample);
		
		return SUCCESS;
	}
	
	private String generateWeibull() {
		distTypeStr = "Weibull distribution";
		
		WeibullDistribution wbDist = new WeibullDistribution(wbAlpha, wbBeta);
		randomSequence = wbDist.sample(numSample);
		
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
		case PLAIN_TEXT:
			return handleActionForPlainText();
		default:
			System.err.println("ERROR: no matched inputType: " + inputType.toString());
			return INPUT;
		}
	}
	
	
	public enum DistributionType {
		EXPONENTIAL, BETA, NORMAL, GAMMA, WEIBULL
	}
}
