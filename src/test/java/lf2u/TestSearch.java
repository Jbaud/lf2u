package lf2u;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import controller.CustomerInterface;
import controller.CustomerManager;
import controller.FarmerInterface;
import controller.FarmerManager;
import controller.Search;
import controller.SearchInterface;
import model.Customer;
import model.Delivers_to;
import model.Farm_info;
import model.Farmer;
import model.Personal_info;

public class TestSearch {
	
	private FarmerInterface fi = new FarmerManager();
	private CustomerInterface ci = new CustomerManager();
	private SearchInterface si = new Search();
	
	@Test
	public void TestSearchFarm(){
		
		Farm_info test = new Farm_info("test3", "125", "125");
		Personal_info test2 = new Personal_info("test4", "test5", "test6");
		List<String> list = new ArrayList<String>();
		list.add("125");
		Delivers_to test3 = new Delivers_to(list);
		Farmer newFarmer = fi.createFarmer(test, test2,test3 );
		
		List<Farmer> foundFarmers = si.ifTopicisFarm("test3");
		
		assertEquals("should be the same",foundFarmers.get(0).getFarmInfo().getName(), newFarmer.getFarmInfo().getName());
		
	}
	
	@Test
	public void TestSearchCustomer(){
		
		Customer newCustomer = new Customer("jules", "1", "1", "1", "1");
		Customer newCust = ci.createCustomer(newCustomer);
		
		List<Customer> foundCustomers = si.ifTopicisCustomer("jules");
		
		assertEquals("should be the same",foundCustomers.get(0).getName(), newCustomer.getName());
		
	}
	
	

}
