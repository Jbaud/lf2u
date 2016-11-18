package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;

public class ManagerManager implements ManagerInterface, ManagerProductInterface {

	private static List<Manager> Managers = new ArrayList<Manager>();
	private static List<ManagerProduct> Products = new ArrayList<ManagerProduct>();

	@Override
	public  List<Manager> getAllManagers() {
		// TODO Auto-generated method stub
		return Managers;
	}

	@Override
	public Manager createManager(String name, String phone, String email, String created_by, String created_date) {
		// TODO Auto-generated method stub
		Manager newManager = new Manager(name, phone, email, created_by, created_date);
		Managers.add(newManager);
		return newManager;
	}

	public Manager viewManager(String mid) {
		return (findManagerById(mid));
	}

	private Manager findManagerById(String mid) {
		Iterator<Manager> ma = Managers.listIterator();
		while (ma.hasNext()) {
			Manager m = ma.next();
			if (m.matchesId(mid))
				return (m);
		}
		return (new NullManager());
	}

	@Override
	public List<ManagerProduct> getAllManagersProducts() {
		// TODO Auto-generated method stub
		return Products;
	}

	@Override
	public ManagerProduct createAProduct(String name) {
		// TODO Auto-generated method stub
		ManagerProduct newProduct = new ManagerProduct(name);
		Products.add(newProduct);
		return newProduct;
	}

	public ManagerProduct findAProductbyID(String gcpid) {
		Iterator<ManagerProduct> pr = Products.listIterator();
		while (pr.hasNext()) {
			ManagerProduct p = pr.next();
			if (p.matchesId(gcpid))
				return (p);
		}
		return (new NullProduct());
	}

	@Override
	public boolean doeGcpidExists(String gcpid) {
		Iterator<ManagerProduct> pr = Products.listIterator();
		while (pr.hasNext()) {
			ManagerProduct p = pr.next();
			if (p.matchesId(gcpid)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ManagerProduct updateAproduct(String gcpid, String newName) {
		ManagerProduct toUpdate;
		toUpdate = findAProductbyID(gcpid);
		if (toUpdate.isNil()) {
			return (new NullProduct());
		}
		toUpdate.modifyName(newName);
		return toUpdate;
	}

}
