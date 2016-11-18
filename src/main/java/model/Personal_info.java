package model;

import java.util.UUID;

public class Personal_info {

	private String name;
	private String email;
	private String phone;

	public Personal_info() {

	}

	public Personal_info(String name, String email, String phone) {
	
		this.name = name;
		this.email = email;
		this.phone = phone;
	}


	public boolean isNil() {
		return false;
	}
	
	public String getPersonalName(){
		return this.name;
	}

}
