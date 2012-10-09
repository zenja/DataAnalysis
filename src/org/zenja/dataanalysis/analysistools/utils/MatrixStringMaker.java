package org.zenja.dataanalysis.analysistools.utils;

public class MatrixStringMaker {
	public static String makeMatrixString(double[][] matrix) {
		StringBuilder sb = new StringBuilder();
		for (double[] line : matrix) {
			for (double num : line) {
				sb.append(num);
				sb.append(' ');
			}
			sb.append('\n');
		}
		return sb.toString();
	}
	
	public static String makeMatrixStringForHtml(double[][] matrix) {
		return makeMatrixString(matrix).replace("\n", "<br>");
	}
}
