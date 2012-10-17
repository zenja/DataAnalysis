package org.zenja.dataanalysis.analysistools.dataextractors;

import java.util.ArrayList;

public class VectorExtractor {
	public static double[] extractVectorAsArray(final String vectorStr, final String numDelimiterRegx) {
		ArrayList<Double> list = extractVectorAsArrayList(vectorStr, numDelimiterRegx);
		double[] result = new double[list.size()];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		
		return result;
	}
	
	public static ArrayList<Double> extractVectorAsArrayList(final String vectorStr, final String numDelimiterRegx) {
		ArrayList<Double> result = new ArrayList<Double>();
		String[] numStrs = vectorStr.split(numDelimiterRegx);
		for (String numStr : numStrs) {
			result.add(Double.parseDouble(numStr));
		}
		
		return result;
	}
	
}
