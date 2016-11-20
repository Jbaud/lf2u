package org.iit.lf2u;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.CustomerInterface;
import controller.CustomerManager;
import controller.Search;
import controller.SearchInterface;

@Path("/search")
public class SearchFunction {
	
	private SearchInterface si = new Search();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(@QueryParam("topic") String topic, @QueryParam("key") String key) {

		if(topic == null){
			return Response.status(200).build();
		}
		
		System.out.println(topic);
		System.out.println(key);

		if (topic.equals("farm")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(si.ifTopicisFarm(key));
			return Response.status(Response.Status.OK).entity(s).build();
		}
		if (topic.equals("customer")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(si.ifTopicisCustomer(key));
			return Response.status(Response.Status.OK).entity(s).build();
		}
		if (topic.equals("order")) {

		} else {
			return Response.status(404).build();
		}

		return Response.status(200).build();

	}

}
