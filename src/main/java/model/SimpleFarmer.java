package model;

// this class is used to send a simplified version of a farmer (in the zip methods)
public class SimpleFarmer {

	private String fid;
	private String name;
	
	public SimpleFarmer(){
		
	}
	
	public SimpleFarmer(String fid,String name){
		this.fid=fid;
		this.name=name;
	}
	
	public String getFid(){
		return this.fid;
	}
}
