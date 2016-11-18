package org.iit.lf2u;

import controller.*;
import model.*;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sun.org.apache.xerces.internal.util.Status;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.json.*;

@Path("/managers")
public class Managers {

	private ManagerInterface mi = new ManagerManager();

	
	
	// GET /managers/accounts
	@GET
	@Path("/accounts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllManagers() {
		// calls the "Get All Lamps" use case
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s = gson.toJson(mi.getAllManagers());
		return Response.status(200).entity(s).build();
	}

	// GET /managers/accounts/{mid}
	@Path("/accounts/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAManager(@PathParam("id") String mid) {
		Manager m = mi.viewManager(mid);
		if (m.isNil()) {
			// return a 404
			return Response.status(404).build();
		} else {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(m);
			return Response.status(200).entity(s).build();
		}
	}

	// GET /managers/catalog
	@Path("/catalog")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCatalog() {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s = gson.toJson(mi.getAllManagersProducts());
		return Response.status(Response.Status.OK).entity(s).build();
	}

	// POST /managers/catalog
	@POST
	@Path("/catalog")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAProduct(String name) {
		JSONObject obj = new JSONObject(name);
		String n = obj.getString("name");
		ManagerProduct mp = mi.createAProduct(n);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("gcpid", mp.getGcpid());
		
		return Response.status(Response.Status.CREATED).entity(jsonObject.toString()).build();
	}

	// POST /managers/catalog/{gcpid}
	@POST
	@Path("/catalog/{gcpid}")
	@Produces(MediaType.APPLICATION_JSON)
	public void modifyProduct(@PathParam("gcpid") String gcpid, String name) {
		// get the gcpid value
		// check if value exists.
		JSONObject obj = new JSONObject(name);
		String n = obj.getString("name");
		ManagerProduct mp = mi.updateAproduct(gcpid, n);
	}

	// check if a product exists
	@GET
	@Path("/catalog/{gcpid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkifGCPIDexist(@PathParam("gcpid") String gcpid) {
		// check if value exists.
		boolean ispresent = mi.doeGcpidExists(gcpid);
		if (ispresent) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	// get a product name
	// internal method
	@GET
		@Path("/catalog/{gcpid}/getname")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getNameOfProduct(@PathParam("gcpid") String gcpid){
			
			// check if value exists.
			ManagerProduct retour = mi.findAProductbyID(gcpid);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(retour.getName());
			return Response.status(Response.Status.OK).entity(s).build();
			
		}

	@PostConstruct
	public void postConstruct() {
		// This method gets executed exactly once, after the servlet container
		// has been created
		// A good place to place code that needs to be executed once, at startup
		List<Manager> Managers = mi.getAllManagers();
		if (Managers.isEmpty())
			mi.createManager("Jean", "0635593172", "jeanTheManager@manager.com", "root", "01/01/2016");
		
	
	}
}
