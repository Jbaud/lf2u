package org.iit.lf2u;


import controller.*;
import model.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.*;

import javax.annotation.PostConstruct;
import javax.swing.text.AbstractDocument.Content;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.*;

@Path("/farmers")
public class Farmers {

	private FarmerInterface fi = new FarmerManager();

	// GET /farmers
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllManagers(@Context UriInfo info) {

		String zip = info.getQueryParameters().getFirst("zip");
		if (zip != null && !zip.isEmpty() && zip.length() == 5) {
			List<SimpleFarmer> returnList = new ArrayList<SimpleFarmer>();
			returnList = fi.searchByZip(zip);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String succed = gson.toJson(returnList);
			return Response.status(Response.Status.OK).entity(succed).build();
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s = gson.toJson(fi.getAllFarmers());
		return Response.status(Response.Status.OK).entity(s).build();
	}

	// GET /farmer/{fid}
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAFarmer(@PathParam("id") String fid) {
		Farmer m = fi.viewFarmer(fid);
		if (m.isNil()) {
			// return a 404
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + fid).build();
		} else {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(m);
			return Response.status(201).entity(s).build();
		}
	}

	// create farmer
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createFarmer(@Context UriInfo uriInfo, String json) {

		Gson gson = new GsonBuilder().registerTypeAdapter(Farm_info.class, new FarmDeserializer()).create();

		Farm_info a = gson.fromJson(json, Farm_info.class);

		Gson gson2 = new GsonBuilder().registerTypeAdapter(Personal_info.class, new InfoDeserializer()).create();

		Personal_info b = gson2.fromJson(json, Personal_info.class);

		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject) jsonParser.parse(json);
		JsonArray jsonArr = jo.getAsJsonArray("delivers_to");
		Gson googleJson = new Gson();
		List<String> jsonObjList = googleJson.fromJson(jsonArr, ArrayList.class);

		Delivers_to c = new Delivers_to(jsonObjList);

		Farmer farmer = fi.createFarmer(a, b, c);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("fid", farmer.getFarmerID());

		return Response.status(Response.Status.CREATED).entity(jsonObject.toString()).build();
	}

	// PUT /farmers/{fid}
	@Path("/{fid}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAManager(@PathParam("fid") String fid, String json) {
		Farmer f = fi.viewFarmer(fid);
		if (f.isNil()) {
			// return a 404
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + fid).build();
		} else {
			// PUT so must be complete
			Gson gson = new GsonBuilder().registerTypeAdapter(Farm_info.class, new FarmDeserializer()).create();
			Farm_info a = gson.fromJson(json, Farm_info.class);
			Gson gson2 = new GsonBuilder().registerTypeAdapter(Personal_info.class, new InfoDeserializer()).create();
			Personal_info b = gson2.fromJson(json, Personal_info.class);
			JsonParser jsonParser = new JsonParser();
			JsonObject jo = (JsonObject) jsonParser.parse(json);
			JsonArray jsonArr = jo.getAsJsonArray("delivers_to");
			Gson googleJson = new Gson();
			List<String> jsonObjList = googleJson.fromJson(jsonArr, ArrayList.class);
			Delivers_to c = new Delivers_to(jsonObjList);

			// Farmer farmer = fi.createFarmer(a, b, c);
			fi.updateFarmer(fid, a, b, c);

			return Response.ok().build();
		}
	}

	// ============================delivery_charge============================

	// GET /farmers/{fid}/delivery_charge
	@Path("/{fid}/delivery_charge")
	@GET
	public Response viewDeliveryCharge(@PathParam("fid") String fid) {

		Farmer m = fi.viewFarmer(fid);
		if (m.isNil()) {
			// return a 404
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + fid).build();
		} else {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(m);

			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("delivery_charge", Float.toString(m.viewDeliveryCharge()));

			return Response.status(Response.Status.CREATED).entity(jsonObject.toString()).build();
		}

	}

	// POST /farmers/{fid}/delivery_charge
	@Path("/{fid}/delivery_charge")
	@POST
	public Response changeDeliveryCharge(@PathParam("fid") String fid, String json) {

		Farmer m = fi.viewFarmer(fid);
		if (m.isNil()) {
			// return a 404
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + fid).build();
		} else {
			JSONObject obj = new JSONObject(json);
			// Integer newvalue = obj.getInt("delivery_charge");
			float newvalue = BigDecimal.valueOf(obj.getDouble("delivery_charge")).floatValue();
			m.updateDeliveryCharge(newvalue);
			return Response.status(Response.Status.OK).build();

		}
	}

	// ============================FarmerProduct============================

	// View Farmer store
	// GET /farmers/{fid}/products
	@Path("/{fid}/products")
	@GET
	public Response viewCatalog(@PathParam("fid") String fid) {
		Farmer m = fi.viewFarmer(fid);
		if (m.isNil()) {
			// return a 404
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + fid).build();
		} else {

			String farmerID = m.getFarmerID();
			List<FarmerProduct> returnList = new ArrayList<FarmerProduct>();
			returnList = fi.viewFarmerProduct(fid);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String succed = gson.toJson(returnList);
			return Response.status(Response.Status.OK).entity(succed).build();

		}

	}

	// Create a product
	// POST /farmers/{fid}/products
	@Path("/{fid}/products")
	@POST
	public Response CreateaProduct(@PathParam("fid") String fid, String json) {
		Farmer m = fi.viewFarmer(fid);
		if (m.isNil()) {
			// return a 404
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + fid).build();
		} else {

			Gson gson = new Gson();
			FarmerProduct mp = gson.fromJson(json, FarmerProduct.class);

			// ==========

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/lf2u/managers/catalog/" + mp.getGcpid());

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if (response.getStatus() != 404) {
				// ==========

				mp.setBelongs_to_whom(fid);
				FarmerProduct newProduct = fi.createAProduct(mp);
				System.out.println(newProduct.getFspid());

				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("fspid", newProduct.getFspid());

				return Response.status(Response.Status.CREATED).entity(jsonObject.toString()).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for gcpid: " + mp.getGcpid())
						.build();
			}
		}
	}

	// POST /farmers/{fid}/products/{fspid}
	@Path("/{fid}/products/{fspid}")
	@POST
	public Response updateAProduct(@PathParam("fid") String fid, @PathParam("fspid") String fspid, String json) {
		Farmer m = fi.viewFarmer(fid);
		// if fid does not exist
		if (m.isNil()) {
			// return a 404
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for fid: " + fid).build();
		} else {
			FarmerProduct fp = fi.getFarmerProduct(fspid);
			// if fspid does not exist
			if (fp.isNil()) {
				// return a 404
				return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for fspid: " + fspid)
						.build();
			}
			// both are valid we need to update the values
			else {
				Gson gson = new Gson();
				FarmerProduct mp = gson.fromJson(json, FarmerProduct.class);
				System.out.println(Float.toString(mp.getprice()));
				// we fetch the existing object
				FarmerProduct old_product = fi.getFarmerProduct(fspid);

				if (mp.getGcpid() != null) {
					old_product.setGcpid(mp.getGcpid());
				}
				if (mp.getNote() != null) {
					old_product.setNote(mp.getNote());
				}
				if (mp.getStart_date() != null) {
					old_product.setStart(mp.getStart_date());
				}
				if (mp.getEnd_date() != null) {
					old_product.setEnd(mp.getEnd_date());
				}
				if (mp.getprice() != 0.0f) {
					old_product.setPrice(mp.getprice());
				}
				if (mp.getProduct_unit() != null) {
					old_product.setUnit(mp.getProduct_unit());
				}
				if (mp.getImage() != null) {
					old_product.setImage(mp.getImage());
				}
				return Response.status(Response.Status.OK).build();
			}

		}
	}

	// GET /farmers/{fid}/products/{fspid}
	@Path("/{fid}/products/{fspid}")
	@GET
	public Response seeAProduct(@PathParam("fid") String fid, @PathParam("fspid") String fspid) {

		Farmer m = fi.viewFarmer(fid);
		// if fid does not exist
		if (m.isNil()) {
			// return a 404
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for fid: " + fid).build();
		} else {
			FarmerProduct fp = fi.getFarmerProduct(fspid);
			// if fspid does not exist
			if (fp.isNil()) {
				// return a 404
				return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for fspid: " + fspid)
						.build();
			}
			// the product exists
			else {

			}
			FarmerProduct selected = fi.getFarmerProduct(fspid);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(selected);
			return Response.status(Response.Status.OK).entity(s).build();
		}
	}

	// GET reports
	// GET /farmers/{fid}/reports
	@GET
	@Path("/{fid}/reports")
	public Response getAFarmerReport(@PathParam("fid") String fid) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s = gson.toJson(fi.getAllReport());

		return Response.status(200).entity(s).build();
	}

	// GET /farmers/{fid}/reports/{frid}[?start_date=YYYYMMDD&end_date=YYYYMMDD]
	@GET
	@Path("/{fid}/reports/{frid}")
	public Response getAFarmerReportByFrid(@PathParam("fid") String fid, @PathParam("frid") String frid) {

		Farmer m = fi.viewFarmer(fid);

		// if fid does not exist
		if (m.isNil()) {
			// return a 404
			return Response.status(404).build();
		} else {
			if (Integer.parseInt(frid) > 0 && Integer.parseInt(frid) <= 4) {

				FarmerReport fr = fi.getAFarmerReport(fid, frid);

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String s = gson.toJson(fr);
				return Response.status(200).entity(s).build();

			} else {
				return Response.status(404).build();
			}

		}

	}

	// extra method
	// POST /farmers/{fid}/reports/{frid}
	@POST
	@Path("/{fid}/reports/{frid}")
	public Response UpdateFarmerReportByFrid(@PathParam("fid") String fid, @PathParam("frid") String frid,String json) {

		Gson gson = new Gson();
		FarmerOrders il = gson.fromJson(json, FarmerOrders.class);
		
		fi.updateAFarmerOrder(fid,frid,il);
		
		
		return null;
	}

	@PostConstruct
	public void postConstruct() {
		List<Report> Reports = fi.getAllReport();
		if (Reports.isEmpty()) {
			fi.createAreport("1", "Orders to deliver today");
			fi.createAreport("2", "Orders to deliver tomorrow");
			fi.createAreport("3", "Revenue report");
			fi.createAreport("4", "Orders delivery report");
		}

	}
}

class FarmDeserializer implements JsonDeserializer<Farm_info> {
	@Override
	public Farm_info deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
		// Get the "content" element from the parsed JSON
		JsonElement content = je.getAsJsonObject().get("farm_info");
		return new Gson().fromJson(content, Farm_info.class);

	}
}

class InfoDeserializer implements JsonDeserializer<Personal_info> {
	@Override
	public Personal_info deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
			throws JsonParseException {
		// Get the "content" element from the parsed JSON
		JsonElement content = je.getAsJsonObject().get("personal_info");

		return new Gson().fromJson(content, Personal_info.class);

	}

}

class DeliverDeserializer implements JsonDeserializer<Delivers_to> {
	@Override
	public Delivers_to deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
			throws JsonParseException {
		// Get the "content" element from the parsed JSON
		JsonElement content = je.getAsJsonObject().get("delivers_to");

		return new Gson().fromJson(content, Delivers_to.class);

	}
	
	

}


class ordered_byDeserializer implements JsonDeserializer<Ordered_by> {
	@Override
	public Ordered_by deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
		// Get the "content" element from the parsed JSON
		JsonElement content = je.getAsJsonObject().get("ordered_by");
		return new Gson().fromJson(content, Ordered_by.class);

	}

}