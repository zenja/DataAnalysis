package org.zenja.dataanalysis.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.zenja.dataanalysis.action.enums.InputType;

import be.nbb.timeseries.simplets.TS;
import be.nbb.timeseries.simplets.TSCollection;
import be.nbb.timeseries.simplets.xml.Formatter;

import com.opensymphony.xwork2.ActionSupport;

public class TimeSeriesVisualizationAction extends ActionSupport {
	
	private static final long serialVersionUID = -8766083374481948658L;
	private static final String Y_AXIS_NAME = "\"seq\"";
	
	/*
	 * Received from view
	 */
	private InputType inputType;
	private File file;
	
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
	
	/*
	 * Passed To View
	 */
	private ArrayList<String> tsCollectionTitleList = new ArrayList<String>();
	private ArrayList<ArrayList<String>> allTsTitleList = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<ArrayList<Double>>> tsCollectionList = new ArrayList<ArrayList<ArrayList<Double>>>();
	private ArrayList<ArrayList<ArrayList<Object>>> chartData = new ArrayList<ArrayList<ArrayList<Object>>>();

	public ArrayList<String> getTsCollectionTitleList() {
		return tsCollectionTitleList;
	}

	public void setTsCollectionTitleList(ArrayList<String> tsCollectionTitleList) {
		this.tsCollectionTitleList = tsCollectionTitleList;
	}

	public ArrayList<ArrayList<ArrayList<Object>>> getChartData() {
		return chartData;
	}

	public void setChartData(ArrayList<ArrayList<ArrayList<Object>>> chartData) {
		this.chartData = chartData;
	}

	public ArrayList<ArrayList<String>> getAllTsTitleList() {
		return allTsTitleList;
	}

	public void setAllTsTitleList(ArrayList<ArrayList<String>> allTsTitleList) {
		this.allTsTitleList = allTsTitleList;
	}

	public ArrayList<ArrayList<ArrayList<Double>>> getTsCollectionList() {
		return tsCollectionList;
	}

	public void setTsCollectionList(
			ArrayList<ArrayList<ArrayList<Double>>> tsCollectionList) {
		this.tsCollectionList = tsCollectionList;
	}

	/***********************
	 * Helper Methods Below
	 ***********************/
	
	public String handleActionForFile() {
		if (file == null) {
			return INPUT;
		}
		
		Formatter fomatter = new Formatter();
		List<TSCollection> fileData = fomatter.xmlReadFile(file);
		
		/* build data */
		for (TSCollection c : fileData) {
			/* add collection's title */
			tsCollectionTitleList.add(c.getDescription());
			
			ArrayList<String> tsTitleList = new ArrayList<String>();
			
			ArrayList<ArrayList<Double>> tsList = new ArrayList<ArrayList<Double>>();	// tsList -> Time Series Collection
			for (TS ts : c) {
				/* add time series's title */
				tsTitleList.add(ts.getDescription());
				
				ArrayList<Double> itemList = new ArrayList<Double>();	// itemlist: the data of a time series
				for (double d : ts.getValues().getData()) {
					itemList.add(d);
				}
				tsList.add(itemList);
			}
			allTsTitleList.add(tsTitleList);
			tsCollectionList.add(tsList);
			
		}

		/* make chart data */
		makeChartData();
		
		return SUCCESS;
	}
	
	private void makeChartData() {
		/* for each collection, build reversed matrix */
		int index = 0;
		for (ArrayList<ArrayList<Double>> tsCollection: tsCollectionList) {
			ArrayList<ArrayList<Object>> chartDataForSimgleCollection = reverse2DList(tsCollection);
			
			/* add x axis info */
			for (int i = 0; i < chartDataForSimgleCollection.size(); i++) {
				chartDataForSimgleCollection.get(i).add(0, i);
			}
			
			/* add title information to the TOP */
			ArrayList<Object> tsTitleList = new ArrayList<Object>();
			tsTitleList.add(Y_AXIS_NAME);
			for (String title : allTsTitleList.get(index)) {
				tsTitleList.add("\"" + title + "\"");
			}
			chartDataForSimgleCollection.add(0, tsTitleList);
			
			chartData.add(chartDataForSimgleCollection);
			
			index++;
		}
	}
	
	private static ArrayList<ArrayList<Object>> reverse2DList(ArrayList<ArrayList<Double>> tdList) {
		ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
		
		/* find max length of time series */
		int maxLength = 0;
		for (ArrayList<Double> l : tdList) {
			if (maxLength < l.size()) { maxLength = l.size(); }
		}
		
		/* make empty result rows */
		for (int i = 0; i < maxLength; i++) {
			result.add(new ArrayList<Object>(tdList.size()));
		}
		
		/* make reversed matrix */
		for (int i = 0; i < tdList.size(); i++) {
			for (int j = 0; j < maxLength; j++) {
				Object item = null;
				try {
					item = tdList.get(i).get(j);
				} catch(IndexOutOfBoundsException e) {
					// do nothing
				}
				result.get(j).add(item);
			}
		}
		
		return result;
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
	
	/***********************
	 * Test Methods
	 ***********************/
	public static void main(String[] args) {
		/* make input */
		ArrayList<Double> a = new ArrayList<Double>();
		a.add(1.0);a.add(2.0);a.add(3.0);a.add(4.0);
		ArrayList<Double> b = new ArrayList<Double>();
		b.add(10.0);b.add(20.0);b.add(30.0);b.add(40.0);b.add(50.0);
		ArrayList<Double> c = new ArrayList<Double>();
		c.add(100.0);c.add(200.0);
		
		/* get output */
		ArrayList<ArrayList<Double>> input = new ArrayList<ArrayList<Double>>();
		input.add(a);input.add(b);input.add(c);
		ArrayList<ArrayList<Object>> result = reverse2DList(input);
		
		/* print output */
		for (ArrayList<Object> resultRow : result) {
			System.out.println(resultRow.toString());
		}
		
		System.out.println("\n\n\n\n");
		
		TimeSeriesVisualizationAction action = new TimeSeriesVisualizationAction();
		action.file = new File("/home/wangxing/Desktop/data/time-series-data-example.xml");
		action.inputType = InputType.FILE;
		action.index();
		/* print chartData */
		for (ArrayList<ArrayList<Object>> matrix : action.chartData) {
			System.out.println("----------Time Series Collection----------");
			for (ArrayList<Object> row : matrix) {
				System.out.println(row.toString());
			}
			System.out.println();
		}
	}
}
