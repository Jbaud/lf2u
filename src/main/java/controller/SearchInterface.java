package controller;

import java.util.List;

import model.Customer;
import model.Farmer;
import model.Presentation;

public interface SearchInterface {
	
	List<Farmer> ifTopicisFarm(String key);
	List<Customer> ifTopicisCustomer(String key);
	List<Presentation> ifTopicisOrder(String key);

}
