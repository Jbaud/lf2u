package model;

import java.util.ArrayList;
import java.util.List;

public class FarmerReport {
	
	private transient String fid;
	private String frid;
	private String name;
	private List<FarmerOrders> orders = new ArrayList<FarmerOrders>();

	
	public FarmerReport(){
		
	}
		
	public void setFrid(String frid){
		this.frid=frid;
	}
	
	
	public void setFid(String fid){
		this.fid=fid;
	}

	public void setName(String name){
		this.name=name;
	}

	public boolean matchesId(String fid2) {
	    return(fid2.equals(this.fid));
	}

	public boolean matchesFrid(String frid2) {
		return(frid2.equals(this.frid));
	}

	public List<FarmerOrders> getOrders() {
		return orders;
	}

	public void addOrders(FarmerOrders orders) {
		this.orders.add(orders);
	}
	
}
