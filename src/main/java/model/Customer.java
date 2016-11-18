package model;

import java.util.UUID;

public class Customer {
	
	/*
	 {
  	"name": "Virgil B",
  	"street": "10 West 31st Street, #214",
  	"zip": "60616",
  	"phone": "312-567-5146",
  	"email": "virgilb@example.com"
    }
	 */

	private String cid;
	private String name;
	private String street;
	private String zip;
	private String phone;
	private String email;
	
	public Customer(){
		
	}
	
	public Customer(String name,String street,String zip,String phone,String email){
		this.name=name;
		this.street=street;
		this.zip=zip;
		this.phone=phone;
		this.email=email;
	}
	
	public void initCID(){
		this.cid=UUID.randomUUID().toString();
	}
	
	public String getCustomerCID(){
		return this.cid;
	}
	
	public boolean isNil() {
        return false;
    }
	
	public boolean matchesId(String cid) {
        return(cid.equals(this.cid));
    }
	// getter setters

	public void setCID(String cid) {
		// TODO Auto-generated method stub
		this.cid=cid;
	}

	public String getName() {
		return name;
	}

	public String getStreet() {
		return street;
	}

	public String getZip() {
		return zip;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}
	
	
}
