package org.zenja.dataanalysis.analysistools.utils;

import java.math.BigDecimal;

/**
 * Given a coefficient matrix (double[][])
 * and a constant vector(double[]), 
 * make the corresponding String representing
 * the formulas
 * 
 * @author wangxing
 *
 */
public class FormulaMaker {
	protected static final double SUPER_TINY_NUMBER = 0.0000000001d;
	protected static final int DEFAULT_PRECISION = 2;
	
	public String makeSingleFormula(double[] coefficients, double constant, int precition) {
		StringBuilder sb = new StringBuilder();
		
		BigDecimal firstCoef = new BigDecimal(Math.abs(coefficients[0]));
	    BigDecimal roundedFirstCoef = firstCoef.setScale(2, BigDecimal.ROUND_HALF_UP);
		sb.append(roundedFirstCoef.toString());
		sb.append("x0");
		for (int i = 1; i < coefficients.length; i++) {
			double num = coefficients[i];
			BigDecimal bdCoef = new BigDecimal(Math.abs(num));
		    BigDecimal roundedCoef = bdCoef.setScale(2, BigDecimal.ROUND_HALF_UP);

			if (num - 0.0 <= SUPER_TINY_NUMBER) {
				continue;
			} else if(num > 0) {
				sb.append(" + ");
			} else if(num < 0) {
				sb.append(" - ");
			}
			sb.append(roundedCoef.toString());
			sb.append('x');
			sb.append(i);
		}
		
		BigDecimal bdConst = new BigDecimal(Math.abs(constant));
	    BigDecimal roundedConst = bdConst.setScale(precition, BigDecimal.ROUND_HALF_UP);
		sb.append(" = ");
		sb.append(roundedConst.toString());
		
		return sb.toString();
	}
	
	public String makeSingleFormula(double[] coefficients, double constant) {
		return makeSingleFormula(coefficients, constant, DEFAULT_PRECISION);
	}
	
	/**
	 * Prerequisite: the sizes of the two parameters match
	 * 
	 * @param coefficientMatrix
	 * @param constants
	 * @return
	 */
	public String makeFormulars(double[][] coefficientMatrix, double[] constants, int precisioin) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < coefficientMatrix.length; i++) {
			sb.append(makeSingleFormula(coefficientMatrix[i], constants[i]));
			sb.append(",");
		}
		return sb.toString();
	}
	
	public String makeFormulars(double[][] coefficientMatrix, double[] constants) {
		return makeFormulars(coefficientMatrix, constants, DEFAULT_PRECISION);
	}
	
	/**
	 * test method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FormulaMaker maker = new FormulaMaker();
		double[] coefs = new double[]{-1.122d, 2.345d, 3.87554d};
		double constant = 5.3420d;
		System.out.println(maker.makeSingleFormula(coefs, constant));
		
		System.out.println("---------");
		
		double[][] coefficientMatrix = new double[][]{
				{1, 2, 3}, 
				{4, 5, 6,},
				{7, 8, 9}
		};
		double[] constants = new double[]{10, 20, 30};
		System.out.println(maker.makeFormulars(coefficientMatrix, constants));
	}
}
