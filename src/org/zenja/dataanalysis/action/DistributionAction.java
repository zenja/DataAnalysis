package org.zenja.dataanalysis.action;

import org.apache.commons.math3.distribution.BetaDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.WeibullDistribution;
import org.zenja.dataanalysis.action.enums.DistributionType;
import org.zenja.dataanalysis.action.enums.InputType;
import com.opensymphony.xwork2.ActionSupport;

public class DistributionAction extends ActionSupport {

	private static final long serialVersionUID = -5478981398330352953L;
	
	/*
	 * Received from view
	 */
	private InputType inputType;
	
	private DistributionType distributionType;
	
	private double exponentialMean;
	private double betaAlpha, betaBeta;
	private double normalMean, normalSd;
	private double gammaAlpha, gammaBeta;
	private double wbAlpha, wbBeta;
	private double x;
	
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
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}


	/*
	 * Passed To View
	 */
	private String distTypeStr;
	private double numericalMean;
	private double numericalVariance;
	private double probability;
	private double cumulativeProbability;
	private double supportLowerBound;
	private double supportUpperBound;
	
	
	public String getDistTypeStr() {
		return distTypeStr;
	}

	public void setDistTypeStr(String distTypeStr) {
		this.distTypeStr = distTypeStr;
	}

	public double getNumericalMean() {
		return numericalMean;
	}

	public void setNumericalMean(double numericalMean) {
		this.numericalMean = numericalMean;
	}

	public double getNumericalVariance() {
		return numericalVariance;
	}

	public void setNumericalVariance(double numericalVariance) {
		this.numericalVariance = numericalVariance;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public double getSupportLowerBound() {
		return supportLowerBound;
	}

	public void setSupportLowerBound(double supportLowerBound) {
		this.supportLowerBound = supportLowerBound;
	}

	public double getSupportUpperBound() {
		return supportUpperBound;
	}

	public void setSupportUpperBound(double supportUpperBound) {
		this.supportUpperBound = supportUpperBound;
	}

	public double getCumulativeProbability() {
		return cumulativeProbability;
	}

	public void setCumulativeProbability(double cumulativeProbability) {
		this.cumulativeProbability = cumulativeProbability;
	}

	/***********************
	 * Helper Methods Below
	 ***********************/
	
	private String handleExponential() {
		distTypeStr = "Exponential distribution";
		
		ExponentialDistribution expDist = new ExponentialDistribution(exponentialMean);
		
		setResultValues(expDist);
		
		return SUCCESS;
	}
	
	private String handleBeta() {
		distTypeStr = "Beta distribution";
		
		BetaDistribution betaDist = new BetaDistribution(betaAlpha, betaBeta);
		
		setResultValues(betaDist);
		
		return SUCCESS;
	}
	
	private String handleNormal() {
		distTypeStr = "Normal distribution";
		
		NormalDistribution normalDist = new NormalDistribution(normalMean, normalSd);
		
		setResultValues(normalDist);
		
		return SUCCESS;
	}
	
	private String handleGamma() {
		distTypeStr = "Gamma distribution";
		
		GammaDistribution gammaDist = new GammaDistribution(gammaAlpha, gammaBeta);
		
		setResultValues(gammaDist);
		
		return SUCCESS;
	}
	
	private String handleWeibull() {
		distTypeStr = "Weibull distribution";
		
		WeibullDistribution wbDist = new WeibullDistribution(wbAlpha, wbBeta);
		
		setResultValues(wbDist);
		
		return SUCCESS;
	}
	
	private void setResultValues(RealDistribution rdist) {
		numericalMean = rdist.getNumericalMean();
		numericalVariance = rdist.getNumericalVariance();
		probability = rdist.probability(x);
		cumulativeProbability = rdist.cumulativeProbability(x);
		supportLowerBound = rdist.getSupportLowerBound();
		supportUpperBound = rdist.getSupportUpperBound();
	}
	
	public String handleActionForPlainText() {
		switch (distributionType) {
		case EXPONENTIAL:
			return handleExponential();
		case BETA:
			return handleBeta();
		case NORMAL:
			return handleNormal();
		case GAMMA:
			return handleGamma();
		case WEIBULL:
			return handleWeibull();
		default:
			return INPUT;
		}
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
}
