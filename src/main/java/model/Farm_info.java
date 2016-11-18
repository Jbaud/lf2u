package model;

import java.util.UUID;

public class Farm_info {
	
	private String fid;
	private String name;
	private String address;
	private String phone;
	private String web;
	
	
	public Farm_info(){
		
	}
	
	public Farm_info(String name, String address, String phone, String web){
		this.name=name;
		this.address= address;
		this.phone=phone;
		this.web = web;
	}
	
	public Farm_info(String name, String address, String phone){
		this.name=name;
		this.address= address;
		this.phone=phone;
	}
	
	public void setFidOfFarmInfo(String fid){
		this.fid=fid;
	}
	
	public String getFidOfFarmInfo(){
		return this.fid;
	}
	
	public boolean isNil() {
	        return false;
	    }
	
	public String getName(){
		return this.name;
	}
	public String getAdd(){
		return this.address;
	}
	public String getPhone(){
		return this.phone;
	}
	public String getWeb(){
		return this.web;
	}
	
}
