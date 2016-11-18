package model;

public class Report {
	
	/*
	 * "frid": "701",
  	    "name": "Orders to deliver today"
	 */
	
	private String frid;
	private String name;
	
	public Report(){
		
	}
	
	public Report(String frid, String name){
		this.frid=frid;
		this.name=name;
	}
}
