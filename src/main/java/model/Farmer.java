package model;

import java.util.UUID;

public class Farmer {

	private transient String fid;
	private Farm_info farm_info;
	private Delivers_to delivers_to;
	private Personal_info personal_info;
	private transient float delivey_charge=0;
	
	public Farmer(){
		
	}
	
	public Farmer(Farm_info myfarm,Personal_info myinfo,Delivers_to mydestinations){
		this.fid=UUID.randomUUID().toString();
		this.farm_info=myfarm;
		this.personal_info=myinfo;
		this.delivers_to=mydestinations;
	}
	
	public void updateDeliveryCharge(float newPrice){
		this.delivey_charge=newPrice;
	}
	
	public float viewDeliveryCharge(){
		return this.delivey_charge;
	}
	
	public boolean matchesId(String fid) {
        return(fid.equals(this.fid));
    }

	public boolean isNil() {
	        return false;
	    }
	
	public String getFarmerID(){
		return this.fid;
	}
	public Delivers_to getDelivery(){
		return this.delivers_to;
	}
	public Farm_info getFarmInfo(){
		return this.farm_info;
	}
	
	public void setFarmer_info(Farm_info farm){
		this.farm_info=farm;
	}
	public void setInfo(Personal_info info){
		this.personal_info=info;
	}
	
	public void setDelivery(Delivers_to deliversto){
		this.delivers_to=deliversto;
	}
	
	
	
}
