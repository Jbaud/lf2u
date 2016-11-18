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
import model.Customer;
import model.Order;
import model.OrderDetail;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CustomerTest extends JerseyTest {

	private CustomerInterface fi = new CustomerManager();

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
		
		Customer newCustomer = new Customer("jules", "1", "1", "1", "1");
		OrderDetail newOrderDetail = new OrderDetail("test", 2.3f);
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		list.add(newOrderDetail);
		Order newOrder = new Order("123", list);
		
		Order order = fi.createOrder("1", newOrder);
		
		
	}
	

}
