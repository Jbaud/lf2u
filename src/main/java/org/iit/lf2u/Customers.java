package org.iit.lf2u;

import controller.*;
import model.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import com.google.gson.reflect.TypeToken;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.math.BigDecimal;

@Path("/customers")
public class Customers {

	private CustomerInterface ci = new CustomerManager();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createACustomer(String json) {
		Gson gson = new Gson();
		Customer mp = gson.fromJson(json, Customer.class);
		Customer newCustomer = ci.createCustomer(mp);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("cid", newCustomer.getCustomerCID());

		return Response.status(Response.Status.CREATED).entity(jsonObject.toString()).build();
	}

	// GET /customers/{cid}
	@GET
	@Path("/{cid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getACostumer(@PathParam("cid") String cid) {
		Customer cu = ci.viewCustomer(cid);
		if (cu.isNil()) {
			// return a 404
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + cid).build();
		} else {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(cu);
			return Response.ok(s).build();
		}
	}

	@PUT
	@Path("/{cid}")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateACostumer(@PathParam("cid") String cid, String json) {
		Customer m = ci.viewCustomer(cid);
		// if fid does not exist
		if (!m.isNil()) {

			// the put is always complete
			Gson gson = new Gson();
			Customer cu = gson.fromJson(json, Customer.class);

			ci.updateCustomer(cid, cu);

		}
	}

	// ===================================
	// ORDERS=================================

	// POST /customers/{cid}/orders
	@POST
	@Path("/{cid}/orders")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAnOrder(@Context UriInfo uriInfo, @PathParam("cid") String cid, String json) {
		// check if order exists
		String id;
		Customer c = ci.viewCustomer(cid);
		if (c.isNil()) {
			// return a 404
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for cid: " + cid).build();
		} else {

			Gson gson = new Gson();
			Order cud = gson.fromJson(json, Order.class);

			// we must check if fspid exists
			List<OrderDetail> tocheck = cud.getOrderDetail();

			for (OrderDetail d : tocheck) {
				Client client = Client.create();

				WebResource webResource = client.resource("http://localhost:8080/lf2u/farmers/" + cud.getFid()
						+ "/products/" + d.getFspidFromOrderDetail());

				ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
				if (response.getStatus() == 404) {
					return Response.status(Response.Status.NOT_FOUND)
							.entity("Entity not found for fspid: " + d.getFspidFromOrderDetail()).build();
				}

			}
			// we know that all fspid do exist:
			Order createdOrder = ci.createOrder(cid, cud);
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("oid", createdOrder.getOID());

			id = createdOrder.getOID();
			Gson gson2 = new Gson();
			String s = gson2.toJson(createdOrder.getOID());
			// Build the URI for the "Location:" header
			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			builder.path(id);

			// The response includes header and body data
			return Response.created(builder.build()).entity(jsonObject.toString()).build();
		}
	}

	// GET /customers/{cid}/orders
	@GET
	@Path("/{cid}/orders")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewOrder(@PathParam("cid") String cid) {

		Customer c = ci.viewCustomer(cid);
		if (c.isNil()) {
			// return a 404
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for cid: " + cid).build();
		} else {

			List<Order> allorders = ci.viewOrder(cid);

			Gson gson = new GsonBuilder().registerTypeAdapter(Order.class, new OrderSerializer()).setPrettyPrinting()
					.create();
			Type listOfTestObject = new TypeToken<List<Object>>() {
			}.getType();

			// Gson gson = new GsonBuilder().setPrettyPrinting().create();
			// String s = gson.toJson(allorders);
			String s = gson.toJson(allorders, listOfTestObject);
			return Response.status(Response.Status.OK).entity(s).build();
		}
	}

	// GET /customers/{cid}/orders/{oid}
	@GET
	@Path("/{cid}/orders/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewOrderByOID(@PathParam("cid") String cid, @PathParam("oid") String oid) {
		Customer c = ci.viewCustomer(cid);
		if (c.isNil()) {
			// return a 404
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for cid: " + cid).build();
		} 
		else {
			System.out.println(ci.viewOrder(oid).size());
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(ci.viewOrderByOid(oid));
			return Response.status(Response.Status.OK).entity(s).build();
		}

	}
	
	//delete an order
	// POST /customers/{cid}/orders/{oid}
	@POST
	@Path("/{cid}/orders/{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response DeleteOrderByOID(@Context UriInfo uriInfo,@PathParam("cid") String cid, @PathParam("oid") String oid,String json) {
		Customer c = ci.viewCustomer(cid);
		Presentation o = ci.viewOrderByOid(oid);
		if (c.isNil()) {
			// return a 404
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for cid: " + cid).build();
		} 
		else {
			
			if(o.isNil()){
				return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for oid: " + oid).build();
			}
			else{
				// delete the correponding element
				JSONObject obj = new JSONObject(json);
				// Integer newvalue = obj.getInt("delivery_charge");
				String newvalue = obj.getString("status");
				
				if(!newvalue.equals("cancelled")){
					return Response.status(Response.Status.NOT_FOUND).entity("The data is malformed").build();
				}
				System.out.println("cancelled oid");
				
				ci.cancelOrder(oid,newvalue);
				UriBuilder builder = uriInfo.getAbsolutePathBuilder();
				return Response.status(204).contentLocation(builder.build()).build();
				
			}
			
		}

	}
}	

class OrderSerializer implements JsonSerializer<Order> {
	@Override
	public JsonElement serialize(Order src, Type typeOfSrc, JsonSerializationContext context) {
		// This method gets involved whenever the parser encounters the Dog
		// object (for which this serializer is registered)
		JsonObject object = new JsonObject();

		String oid = src.getOID();
		String order_date = src.getOrder_date();
		String planned = src.getPlanned_delivery_date();
		String actual = src.getActual_delivery_date();
		String status = src.getStatus();
		String fid = src.getFid();

		object.addProperty("oid", oid);
		object.addProperty("order_date", order_date);
		object.addProperty("planned_delivery_date", planned);
		object.addProperty("actual_delivery_date", actual);
		object.addProperty("status", status);
		object.addProperty("fid", fid);

		// we create the json object for the dog and send it back to the
		// Gson serializer
		return object;
	}
}	
    