package model;

import java.util.UUID;

public class ManagerProduct {

	private String gcpid;
	private String name;

	public ManagerProduct() {

	}

	public ManagerProduct(String name) {
		this.gcpid = UUID.randomUUID().toString();
		this.name = name;
	}

	public boolean matchesId(String gcpid) {
		return (gcpid.equals(this.gcpid));
	}

	public boolean isNil() {
		return false;
	}

	public void modifyName(String name) {
		this.name = name;
	}

	public String getGcpid() {
		return this.gcpid;
	}

	public String getName() {
		return this.name;
	}

}
