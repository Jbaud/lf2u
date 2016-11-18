package model;

import java.util.UUID;

public class Manager{
	private String mid;
	private String name;
	private String phone;
	private String email;
	private String created_by;
	private String create_date;

	
	public Manager(String name, String phone, String email,String created_by,String created_date) {
		this.mid=UUID.randomUUID().toString();
		this.name=name;
		this.phone=phone;
		this.email=email;
		this.created_by = created_by;
		this.create_date= created_date;
	}
	
	// for the nill
	public Manager(){
		
	}
	
	public boolean matchesId(String mid) {
        return(mid.equals(this.mid));
    }

	public boolean isNil() {
	        return false;
	    }

	public String getName(){
		return this.name;
	}
	
}
