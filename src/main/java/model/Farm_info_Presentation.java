package model;

public class Farm_info_Presentation {
	
	/*
	 * "fid": "123",
  		"name": "Tall Trees",
  		"address": "25550 W Cuba Rd, Barrington, IL 60010",
  		"phone": "847-651-2140",
  		"web": ""
	 */
	private String fid;
	private String name;
	private String address;
	private String phone;
	private String web;
	
	public Farm_info_Presentation(){
		
	}
	
public Farm_info_Presentation(String fid, String name,String address,String phone,String web){
		this.fid=fid;
		this.name=name;
		this.address=address;
		this.phone=phone;
		this.web=web;
	}

public String getFid() {
	return fid;
}

public void setFid(String fid) {
	this.fid = fid;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getWeb() {
	return web;
}

public void setWeb(String web) {
	this.web = web;
}


}
