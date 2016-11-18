package controller;

import model.*;

import java.util.List;

public interface CustomerInterface {
	
	List<Customer> getAllCustomers();
	List<Order> getAllOrders();
	Customer createCustomer(String name,String zip,String phone,String email);
	Customer createCustomer(Customer newcustomer);
	Customer viewCustomer(String cid);
	void updateCustomer(String cid,Customer newcustomer);
	Order createOrder(String cid,Order newOrder);
	List<Order> viewOrder(String cid);
	Presentation viewOrderByOid(String oid);
	void cancelOrder(String oid,String newvalue);

}
