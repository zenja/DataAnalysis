package org.zenja.dataanalysis.analysistools.dataextractors;

import java.util.ArrayList;

public class MatrixExtractor {
	/**
	 * 
	 * get the double[][] representation of
	 * a string
	 * 
	 * @param matrixStr	the String of the matrix
	 * e.g.
	 * "1, 2, 3, 4\n
	 * 5, 6, 7, 8\n
	 * 9, 10, 11, 12"
	 * 
	 * @param lineDelimiterRegx
	 * e.g. "(\r\n|\n)"
	 * 
	 * @param numDelimiterRegx
	 * e.g. ","
	 * 
	 * @return
	 */
	public static double[][] extractMatrixAs2DArray(final String matrixStr, final String lineDelimiterRegx, final String numDelimiterRegx) {
		
		return get2DArrayFromArrayList(
				extractMatrixAsArrayList(matrixStr, lineDelimiterRegx, numDelimiterRegx));
	}
	
	public static ArrayList<ArrayList<Double>> extractMatrixAsArrayList(final String matrixStr, final String lineDelimiterRegx, final String numDelimiterRegx) {
		ArrayList<ArrayList<Double>> arrayListMatrix = new ArrayList<ArrayList<Double>>();
		
		String[] lineStrs = matrixStr.split(lineDelimiterRegx);
		for (String lineStr : lineStrs) {
			ArrayList<Double> listLine = new ArrayList<Double>();
			
			String[] numStrArray = lineStr.split(numDelimiterRegx);
			for (String numStr : numStrArray) {
				double num = Double.parseDouble(numStr);
				listLine.add(num);
			}
			
			arrayListMatrix.add(listLine);
		}
		
		return arrayListMatrix;
	}
	
	protected static double[][] get2DArrayFromArrayList(final ArrayList<ArrayList<Double>> arrayListMatrix) {
		double[][] arrayMatrix = new double[arrayListMatrix.size()][];
		for (int i = 0; i < arrayListMatrix.size(); i++) {
			ArrayList<Double> numList = arrayListMatrix.get(i);
			arrayMatrix[i] = new double[numList.size()];
			for (int j = 0; j < numList.size(); j++) {
				arrayMatrix[i][j] = numList.get(j);
			}
		}
		
		return arrayMatrix;
	}
	
	protected static void printMatrix(double[][] matrix) {
		for (double[] line : matrix) {
			for (double num : line) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		String matrixStr1 = "1, 2 ,3\n4, 5, 6";
		String lineDelimiterRegx1 = "(\r\n|\n\r|\n)";
		String numDelimiterRegx1 = ",";
		
		String matrixStr2 = "1 2 3\n4 5 6";
		String lineDelimiterRegx2 = "(\r\n|\n\r|\n)";
		String numDelimiterRegx2 = " "; 
		
		double[][] matrix1 = MatrixExtractor.extractMatrixAs2DArray(matrixStr1, lineDelimiterRegx1, numDelimiterRegx1);
		double[][] matrix2 = MatrixExtractor.extractMatrixAs2DArray(matrixStr2, lineDelimiterRegx2, numDelimiterRegx2);
		
		System.out.println("Matrix 1");
		MatrixExtractor.printMatrix(matrix1);
		System.out.println("Matrix 2");
		MatrixExtractor.printMatrix(matrix2);
	}
}
