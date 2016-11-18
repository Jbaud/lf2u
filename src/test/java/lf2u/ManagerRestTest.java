package lf2u;

import org.iit.lf2u.Managers;
import org.json.simple.JSONObject;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

import org.junit.Test;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import model.Farm_info;
import model.Farmer;
import model.Manager;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ManagerRestTest extends JerseyTest {

	private WebResource ws;

	@Override
	public Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(Managers.class);
	}

	@Test
	public void testFetchAll() {
		Response output = target("/managers/catalog").request().get();
		assertEquals("should return status 200", 200, output.getStatus());
	}

	@Test
	public void testGetAllManager() {
		Response output = target("/managers/accounts").request().get();
		assertEquals("should return status 200", 200, output.getStatus());
	}

	@Test
	public void SeeManagerAccount() {

		Client client = Client.create();

		WebResource webResource = client.resource("http://localhost:9998/managers/accounts");

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		String output = response.getEntity(String.class);
		

		Type listType = new TypeToken<ArrayList<Manager>>() {
		}.getType();

		List<Manager> a = new Gson().fromJson(output, listType);

		assertEquals(a.get(0).getName(), "Jean");
		WebResource webResource2 = client.resource("http://localhost:9998/managers/accounts/" + a.get(0).getMid());

		ClientResponse response2 = webResource2.accept("application/json").get(ClientResponse.class);
		
		assertEquals("should return status 200", 200, response2.getStatus());

	}

	@Test
	public void TestWithManagersNonExistent() {

		Response output = target("/managers/catalog/123").request().get();
		assertEquals("should return status 404", 404, output.getStatus());

	}

	@Test
	public void testCreate() {

		JSONObject obj = new JSONObject();
		obj.put("name", "Potatoes");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s = gson.toJson(obj);

		Response output = target("/managers/catalog").request().post(Entity.entity(s, MediaType.APPLICATION_JSON));

		assertEquals("Should return status 200", 200, output.getStatus());
	}

}
