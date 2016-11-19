package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Customer;
import model.Farmer;
import model.NullCustomer;
import model.NullFarmer;
import model.Presentation;

public class Search implements SearchInterface {

	private FarmerInterface fi = new FarmerManager();
	private ManagerInterface mi = new ManagerManager();
	private CustomerInterface ci = new CustomerManager();

	@Override
	public List<Farmer> ifTopicisFarm(String key) {
		// TODO Auto-generated method stub

		List<Farmer> allfarmers = fi.getAllFarmers();

		// if empty all
		if (key.equals("")) {
			return allfarmers;
		}

		List<Farmer> returnFarmer = new ArrayList<Farmer>();

		Iterator<Farmer> ma = allfarmers.listIterator();
		while (ma.hasNext()) {
			Farmer m = ma.next();
			if (m.getFarmerID().contains(key) || m.getFarmInfo().getName().contains(key)
					|| m.getFarmInfo().getAdd().contains(key) || m.getFarmInfo().getPhone().contains(key)
					|| m.getFarmInfo().getWeb().contains(key)) {
				returnFarmer.add(m);
				return returnFarmer;
			}
			if (m.getInfo().getPersonalName().contains(key) || m.getInfo().getPersonalPhone().contains(key)
					|| m.getInfo().getPersonalEmail().contains(key)) {
				returnFarmer.add(m);
				return returnFarmer;
			}

		}
		// there is nothing here
		returnFarmer.add(new NullFarmer());
		return (returnFarmer);
	}

	@Override
	public List<Customer> ifTopicisCustomer(String key) {

		List<Customer> allcustomers = ci.getAllCustomers();

		// if empty all
		if (key.equals("")) {
			return allcustomers;
		}

		List<Customer> returnCustomer = new ArrayList<Customer>();

		Iterator<Customer> ma = allcustomers.listIterator();
		while (ma.hasNext()) {
			Customer m = ma.next();
			if (m.getCustomerCID().contains(key) || m.getEmail().contains(key) || m.getName().contains(key)
					|| m.getPhone().contains(key) || m.getStreet().contains(key) || m.getZip().contains(key)) {
				returnCustomer.add(m);
				return returnCustomer;
			}

		}
		// there is nothing here
		returnCustomer.add(new NullCustomer());
		return (returnCustomer);
	}

	@Override
	public List<Presentation> ifTopicisOrder(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
