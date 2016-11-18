package controller;

import model.*;

import java.util.List;

public interface ManagerInterface {
	List <Manager> getAllManagers();
	List<ManagerProduct> getAllManagersProducts();
	Manager createManager(String name, String phone, String email,String created_by,String created_date);
	Manager viewManager(String mid);
	ManagerProduct createAProduct(String name);
	ManagerProduct updateAproduct(String gcpid, String name);
	boolean doeGcpidExists(String gcpid);
	ManagerProduct findAProductbyID(String gcpid);
	
}
