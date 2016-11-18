package model;

public class Ordered_by {

	/*
	 * "name": "Virgil B", "email": "virgil@example.com", "phone": "312-456-7890
	 */

	private String name;
	private String email;
	private String phone;

	public Ordered_by() {

	}

	public Ordered_by(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
