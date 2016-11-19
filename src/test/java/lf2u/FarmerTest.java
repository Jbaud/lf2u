package lf2u;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.iit.lf2u.Farmers;
import org.iit.lf2u.Managers;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.FarmerInterface;
import controller.FarmerManager;
import model.Delivers_to;
import model.Farm_info;
import model.Farm_info_Presentation;
import model.Farmer;
import model.FarmerProduct;
import model.FarmerReport;
import model.Personal_info;
import model.SimpleFarmer;

public class FarmerTest extends JerseyTest {
	
	private FarmerInterface fi = new FarmerManager();
	
	@Override
	public Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(Farmers.class);
	}
	
	
	@Test
	public void testFetchAll() {
		final Response response = target("/farmers").request().get();
        assertEquals("should return status 200", 200, response.getStatus());
	}
	
	@Test
	public void testGetNonExistingFarmer() {
		Response output = target("/farmers/123").request().get();
		assertEquals("should return status 404", 404, output.getStatus());
	}

	
	/*
	@Test
	public void CreateFarmer(){
		Farm_info test = new Farm_info("test", "123", "123","test");
		Personal_info test2 = new Personal_info("test", "test", "test");
		List<String> list = new ArrayList<String>();
		list.add("123");
		Delivers_to test3 = new Delivers_to(list);
		Farmer newFarmer = new Farmer(test, test2, test3);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s = gson.toJson(newFarmer);
		
		System.out.println(s);
		
		Response output = target("/farmers").request().post(Entity.entity(s, MediaType.APPLICATION_JSON));

		assertEquals("Should return status 201", 201, output.getStatus());
	
	}
	*/
	
	
	@Test
	public void CreateAFarmer(){
		Farm_info test = new Farm_info("test", "123", "123");
		Personal_info test2 = new Personal_info("test", "test", "test");
		List<String> list = new ArrayList<String>();
		list.add("123");
		Delivers_to test3 = new Delivers_to(list);
		Farmer newFarmer = fi.createFarmer(test, test2,test3 );
		
		assertEquals(newFarmer.getFarmInfo().getName(), "test");
		
	}
	@Test 
	public void ViewFarmer(){
		Farm_info test = new Farm_info("test", "123", "123");
		Personal_info test2 = new Personal_info("test", "test", "test");
		List<String> list = new ArrayList<String>();
		list.add("123");
		Delivers_to test3 = new Delivers_to(list);
		Farmer newFarmer = fi.createFarmer(test, test2,test3 );
		
		assertEquals(newFarmer.getFarmInfo().getName(), "test");
		
		Farmer b = fi.viewFarmer(newFarmer.getFarmerID());
		
		assertEquals(newFarmer.getFarmerID(),b.getFarmerID());
	}
	
	@Test
	public void CreateProduct(){
		
		Farm_info test = new Farm_info("test2", "124", "124");
		Personal_info test2 = new Personal_info("test2", "test2", "test2");
		List<String> list = new ArrayList<String>();
		list.add("124");
		Delivers_to test3 = new Delivers_to(list);
		Farmer newFarmer = fi.createFarmer(test, test2,test3 );
		
		FarmerProduct test4 = new FarmerProduct(newFarmer.getFarmerID(), "124", "please2", "now2", "never2", 2, "lb2", "url2");
		
		FarmerProduct test5 = fi.createAProduct(test4);
		assertEquals(test5.getBelongs_to(), newFarmer.getFarmerID());
		
		List<FarmerProduct> test6 = fi.viewFarmerProduct(newFarmer.getFarmerID());
		
		assertEquals(test5.getBelongs_to(),test6.get(0).getBelongs_to());
		
		FarmerReport test7 = fi.getAFarmerReport(newFarmer.getFarmerID(), "1");
		assertNotNull(test7);
		
		// fetch an order
		
		FarmerProduct test8 = fi.getFarmerProduct(test4.getFspid());
		
		assertEquals(test8.getEnd_date(), test4.getEnd_date());
		
		// search by zip
		
		List<SimpleFarmer> test9 = fi.searchByZip("124");
		
		assertEquals(test9.get(0).getFid(),newFarmer.getFarmerID());
		
		fi.updateFarmer(newFarmer.getFarmerID(), test, test2, test3);
		
		assertEquals(newFarmer.getFarmInfo().getName(), "test2");
		
		
		Response output = target("/farmers/"+newFarmer.getFarmerID()+"/products").request().get();
		assertEquals("should return status 200", 200, output.getStatus());
		
		Response output2 = target("/farmers/"+newFarmer.getFarmerID()+"/products/"+test5.getFspid()).request().get();
		assertEquals("should return status 200", 200, output2.getStatus());
		
		Response output3 = target("/farmers/"+newFarmer.getFarmerID()+"/reports/1").request().get();
		assertEquals("should return status 200", 200, output3.getStatus());
		
		Response output4 = target("/farmers/"+newFarmer.getFarmerID()+"/reports").request().get();
		assertEquals("should return status 200", 200, output4.getStatus());
		
		
	}
	 
	@Test
	public void Farm_info_presentation(){
		//Farm_info_presentation test = new Farm_info_Presentation("123", "Jules", address, phone, web)
	}
	
}
