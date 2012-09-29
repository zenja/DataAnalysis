package org.zenja.dataanalysis.action;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.encog.ml.MLCluster;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.kmeans.KMeansClustering;
import org.zenja.dataanalysis.action.enums.InputType;

import com.opensymphony.xwork2.ActionSupport;

public class KMeansAction extends ActionSupport {

	private static final long serialVersionUID = 8519920701362373768L;
	
	/*
	 * Received from view
	 */
	private InputType inputType;
	private File file;
	private int numIterations;		// the number of training iterations
	private int numClusters;		// the number of clusters
	
	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getNumIterations() {
		return numIterations;
	}

	public void setNumIterations(int numIterations) {
		this.numIterations = numIterations;
	}

	public int getNumClusters() {
		return numClusters;
	}

	public void setNumClusters(int numClusters) {
		this.numClusters = numClusters;
	}
	
	/*
	 * Passed To View
	 */
	private ArrayList<ArrayList<double[]>> clusterDataList;
	private ArrayList<double[]> inputData;
	
	public ArrayList<ArrayList<double[]>> getClusterDataList() {
		return clusterDataList;
	}

	public void setClusterDataList(ArrayList<ArrayList<double[]>> clusterDataList) {
		this.clusterDataList = clusterDataList;
	}

	public ArrayList<double[]> getInputData() {
		return inputData;
	}

	public void setInputData(ArrayList<double[]> inputData) {
		this.inputData = inputData;
	}

	/***********************
	 * Helper Methods Below
	 ***********************/
	
	/*
	 * File input style:
	 * double_00,double_01,double_02,...
	 * double_10,double_11,double_12,...
	 */
	private String handleActionForFile() {
		if (file == null) {
			return INPUT;
		}
		
		FileInputStream fstream = null;
		DataInputStream in = null;
		BufferedReader br = null;
		try {
			fstream = new FileInputStream(file);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			
			BasicMLDataSet mlDataSet = new BasicMLDataSet();
			
			/* make empty input/output data structure */
			clusterDataList = new ArrayList<ArrayList<double[]>>();
			inputData = new ArrayList<double[]>();
			
			/* parse and save input data and feed them to data set */
			String strLine;
			while ((strLine = br.readLine()) != null) {
				String[] inputLineStrings = strLine.split(",");
				double[] inputLineDoubles = new double[inputLineStrings.length];
				int index = 0;
				for (String doubleStr : inputLineStrings) {
					inputLineDoubles[index++] = Double.parseDouble(doubleStr);
				}
				inputData.add(inputLineDoubles);
				mlDataSet.add(new BasicMLData(inputLineDoubles));
			}
			
			/* training */
			KMeansClustering kmeans = new KMeansClustering(numClusters, mlDataSet);
			kmeans.iteration(numIterations);
			
			/* build results data: clusterDataList */
			for (MLCluster cluster : kmeans.getClusters()) {
				ArrayList<double[]> clusterData = new ArrayList<double[]>();
				MLDataSet ds = cluster.createDataSet();
				MLDataPair pair = BasicMLDataPair.createPair(
					ds.getInputSize(), ds.getIdealSize());
				for (int i = 0; i < ds.getRecordCount(); i++) {
					ds.getRecord(i, pair);
					clusterData.add(pair.getInputArray());
				}
				clusterDataList.add(clusterData);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (Exception e) {
			// Default Error Handling
			return ERROR;
		} finally {
			try {
				if (null != br)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
				return ERROR;
			}
		}
		
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
		case FILE:
			return handleActionForFile();
		default:
			System.err.println("ERROR: no matched inputType: " + inputType.toString());
			return INPUT;
		}
	}

}
