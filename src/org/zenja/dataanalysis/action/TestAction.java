package org.zenja.dataanalysis.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6425230414791937246L;
	
	private List<String> testList = new ArrayList<String>();
	
	public List<String> getTestList() {
		return testList;
	}

	public void setTestList(List<String> testList) {
		this.testList = testList;
	}

	public String index() {
		testList.clear();
		testList.add("data");
		testList.add("analysis");
		
		return SUCCESS;
	}
}
