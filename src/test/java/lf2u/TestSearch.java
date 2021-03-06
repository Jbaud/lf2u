package lf2u;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.iit.lf2u.Customers;
import org.iit.lf2u.SearchFunction;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import controller.CustomerInterface;
import controller.CustomerManager;
import controller.FarmerInterface;
import controller.FarmerManager;
import controller.ManagerInterface;
import controller.ManagerManager;
import controller.Search;
import controller.SearchInterface;
import model.Customer;
import model.Delivers_to;
import model.Farm_info;
import model.Farmer;
import model.FarmerProduct;
import model.ManagerProduct;
import model.Order;
import model.OrderDetail;
import model.Personal_info;
import model.Presentation;


import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public class TestSearch extends JerseyTest {
	
	private FarmerInterface fi = new FarmerManager();
	private CustomerInterface ci = new CustomerManager();
	private SearchInterface si = new Search();
	private ManagerInterface mi = new ManagerManager();
	
	
	
	@Override
	public Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(SearchFunction.class);
	}
	
	@Test
	public void TestSearchFarm(){
		
		Farm_info test = new Farm_info("test3", "xyz", "125");
		Personal_info test2 = new Personal_info("test4", "test5", "test6");
		List<String> list = new ArrayList<String>();
		list.add("125");
		Delivers_to test3 = new Delivers_to(list);
		Farmer newFarmer = fi.createFarmer(test, test2,test3 );
		
		List<Farmer> foundFarmers = new ArrayList<Farmer>();
		foundFarmers = si.ifTopicisFarm("xyz");
		
		System.out.println("here : "+foundFarmers.get(0).getFarmerID());
		System.out.println("compare to : "+newFarmer.getFarmerID());
		
		assertEquals("should be the same",foundFarmers.get(0).getFarmerID(), newFarmer.getFarmerID());
		
	}
	
	@Test
	public void TestSearchCustomer(){
		
		Customer newCustomer = new Customer("jules", "1", "1", "1", "1");
		Customer newCust = ci.createCustomer(newCustomer);
		
		List<Customer> foundCustomers = si.ifTopicisCustomer("jules");
		
		assertEquals("should be the same",foundCustomers.get(0).getName(), newCustomer.getName());
		
	}
	
	@Test
	public void TestSearchOrder(){
		Farm_info test = new Farm_info("aFarm", "12", "112","1112");
		Personal_info test2 = new Personal_info("test12", "test12", "test12");
		List<String> list = new ArrayList<String>();
		list.add("1223");
		Delivers_to test3 = new Delivers_to(list);
		//Farmer newFarmer = new Farmer(test, test2, test3);
		
		ManagerProduct managerProduct = mi.createAProduct("apples");
		Farmer newFarmer = fi.createFarmer(test, test2, test3);
		FarmerProduct d_farmerProduct = new FarmerProduct(newFarmer.getFarmerID(), managerProduct.getGcpid(), "test", "20160101", "20160102", 2, "lb", "url");
		FarmerProduct farmerProduct = fi.createAProduct(d_farmerProduct);
		Customer newCustomer = new Customer("Robert", "5", "6", "7", "8");
		Customer sendCustomer = ci.createCustomer(newCustomer);
		OrderDetail newOrderDetail = new OrderDetail("test", 2.3f);
		List<OrderDetail> list2 = new ArrayList<OrderDetail>();
		list2.add(newOrderDetail);
		
		Order newOrder = new Order(newFarmer.getFarmerID(), list2);
		
		Order order = ci.createOrder(sendCustomer.getCustomerCID(), newOrder);
		
		List<Presentation> presList = si.ifTopicisOrder(order.getOID());
		
		assertEquals(presList.get(0).getOid(), order.getOID());
				
	}

}
