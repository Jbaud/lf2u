package lf2u;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;


import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.iit.lf2u.Customers;
import org.iit.lf2u.Farmers;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.CustomerInterface;
import controller.CustomerManager;
import controller.FarmerInterface;
import controller.FarmerManager;
import controller.ManagerInterface;
import controller.ManagerManager;
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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CustomerTest extends JerseyTest {

	private CustomerInterface fi = new CustomerManager();
	private ManagerInterface mi = new ManagerManager();
	private FarmerInterface farmerint = new FarmerManager(); 

	@Override
	public Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(Customers.class);
	}

	@Test
	public void TestCreateACustomer() {
		
		Customer newCustomer = new Customer("jules", "1", "1", "1", "1");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s = gson.toJson(newCustomer);

		Response output = target("/customers").request().post(Entity.entity(s, MediaType.APPLICATION_JSON));
		
		assertEquals("Should return status 201", 201, output.getStatus());

	}
	
	
	@Test
	public void CreateOrder(){
		
		Farm_info test = new Farm_info("test", "123", "123","test");
		Personal_info test2 = new Personal_info("test", "test", "test");
		List<String> list = new ArrayList<String>();
		list.add("123");
		Delivers_to test3 = new Delivers_to(list);
		//Farmer newFarmer = new Farmer(test, test2, test3);
		
		ManagerProduct managerProduct = mi.createAProduct("potatoes");
		Farmer newFarmer = farmerint.createFarmer(test, test2, test3);
		FarmerProduct d_farmerProduct = new FarmerProduct(newFarmer.getFarmerID(), managerProduct.getGcpid(), "test", "20160101", "20160102", 2, "lb", "url");
		FarmerProduct farmerProduct = farmerint.createAProduct(d_farmerProduct);
		Customer newCustomer = new Customer("jules", "1", "1", "1", "1");
		Customer sendCustomer = fi.createCustomer(newCustomer);
		OrderDetail newOrderDetail = new OrderDetail("test", 2.3f);
		List<OrderDetail> list2 = new ArrayList<OrderDetail>();
		list2.add(newOrderDetail);
		
		Order newOrder = new Order(newFarmer.getFarmerID(), list2);
		
		Order order = fi.createOrder(sendCustomer.getCustomerCID(), newOrder);
		
		assertEquals(order.getFid(), newFarmer.getFarmerID());
		
		
		Presentation newPresentation = fi.viewOrderByOid(order.getOID());
		
		assertEquals(newPresentation.getOid(), order.getOID());
		
		List<Order> getOrders = fi.viewOrder(newCustomer.getCustomerCID());
		
		assertEquals(getOrders.get(0).getOID(), order.getOID());
		
		
	
	}
	

}
