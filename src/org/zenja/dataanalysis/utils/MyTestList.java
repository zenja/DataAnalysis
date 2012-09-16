package org.zenja.dataanalysis.utils;

import java.util.ArrayList;

public class MyTestList extends ArrayList {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public MyTestList() {
		MyTestInfoObject o1 = new MyTestInfoObject(1, "wx", "wx@126.com", "comments!!");
		MyTestInfoObject o2 = new MyTestInfoObject(1, "wx", "wx@126.com", "comments!!");
		MyTestInfoObject o3 = new MyTestInfoObject(1, "wx", "wx@126.com", "comments!!");
		MyTestInfoObject o4 = new MyTestInfoObject(1, "wx", "wx@126.com", "comments!!");
		MyTestInfoObject o5 = new MyTestInfoObject(1, "wx", "wx@126.com", "comments!!");
		
		this.add(o1);
		this.add(o2);
		this.add(o3);
		this.add(o4);
		this.add(o5);
	}
	
}