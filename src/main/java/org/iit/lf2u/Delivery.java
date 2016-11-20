package org.iit.lf2u;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import controller.CustomerInterface;
import controller.CustomerManager;
import model.Customer;
import model.FarmerProduct;
import model.Order;
import model.OrderDetail;
import model.Presentation;

@Path("/delivery")
public class Delivery {

	private CustomerInterface ci = new CustomerManager();
	
	@POST
	@Path("/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAnOrder(@Context UriInfo uriInfo, @PathParam("oid") String oid, String json) {
	
		JSONObject obj = new JSONObject(json);
		String newvalue = obj.getString("status");
	
		Presentation updatedPresentation = ci.updateOrderStatus(oid, newvalue);
		
		if(updatedPresentation.isNil()){
			return Response.status(404).build();
		}
		// the value was successfully updated
		return Response.status(200).build();
	}
}
