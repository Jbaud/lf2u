package controller;

import model.*;

import java.util.List;

public interface ManagerProductInterface {

	List<ManagerProduct> getAllManagersProducts();
	ManagerProduct createAProduct(String name);
	ManagerProduct updateAproduct(String gcpid, String name);
}
