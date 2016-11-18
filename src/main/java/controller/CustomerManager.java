package controller;

import model.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CustomerManager implements CustomerInterface {

	private static List<Customer> Customers = new ArrayList<Customer>();
	private static List<Order> Orders = new ArrayList<Order>();
	private static List<Presentation> Presentations = new ArrayList<Presentation>();

	private FarmerInterface fi = new FarmerManager();

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return Customers;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return Orders;
	}

	@Override
	public Customer createCustomer(String name, String zip, String phone, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer createCustomer(Customer newcustomer) {
		newcustomer.initCID();
		Customers.add(newcustomer);
		return newcustomer;
	}

	@Override
	public List<Order> viewOrder(String cid) {
		return (findOrderByID(cid));
	}

	private List<Order> findOrderByID(String cid) {
		// TODO Auto-generated method stub
		List<Order> foundOrders = new ArrayList<Order>();
		Iterator<Order> ma = Orders.listIterator();
		while (ma.hasNext()) {
			Order m = ma.next();
			if (m.getBelongs_to().equals(cid))
				foundOrders.add(m);
		}
		return foundOrders;
	}

	@Override
	public Customer viewCustomer(String cid) {
		return (findCostumerID(cid));
	}

	private Customer findCostumerID(String cid) {
		// TODO Auto-generated method stub
		Iterator<Customer> ma = Customers.listIterator();
		while (ma.hasNext()) {
			Customer m = ma.next();
			if (m.matchesId(cid))
				return (m);
		}
		return (new NullCustomer());
	}

	@Override
	public void updateCustomer(String cid, Customer newcustomer) {
		String oldcid;

		Iterator<Customer> ma = Customers.listIterator();
		while (ma.hasNext()) {
			Customer m = ma.next();
			if (m.matchesId(cid)) {
				Customers.remove(m);
				createAnupdatedCustomer(cid, newcustomer);
			}

		}
	}

	private void createAnupdatedCustomer(String cid, Customer newcustomer) {
		newcustomer.setCID(cid);
		Customers.add(newcustomer);
	}

	@Override
	public Order createOrder(String cid, Order newOrder) {

		float accumulator = 0.0f;

		// create object for farmer report
		Ordered_by order_by = new Ordered_by();
		FarmerOrders newFarmerOrder = new FarmerOrders();

		//

		LocalDate localDate = LocalDate.now();
		LocalDate tomorrow = LocalDate.now().plusDays(1);

		newOrder.setBelongs_to(cid);
		newOrder.setOID();
		newOrder.setOrder_date(DateTimeFormatter.ofPattern("yyyMMdd").format(localDate));
		newOrder.setActual_delivery_date("");
		newOrder.setStatus("open");
		newOrder.setPlanned_delivery_date(DateTimeFormatter.ofPattern("yyyMMdd").format(tomorrow));

		String farm = newOrder.getFid();

		// we get the info from the farm
		Client client = Client.create();

		WebResource webResource = client.resource("http://localhost:8080/lf2u/farmers/" + farm);

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		String output = response.getEntity(String.class);

		Gson gson = new GsonBuilder().registerTypeAdapter(Farm_info.class, new FarmDeserializer()).create();
		Farm_info a = gson.fromJson(output, Farm_info.class);
		a.setFidOfFarmInfo(farm);
		newOrder.setFarm_info(a);

		List<OrderDetail> order_details = newOrder.getOrderDetail();
		List<order_detail_presentation> order_detail_presentations = new ArrayList<order_detail_presentation>();

		Iterator<OrderDetail> ma = order_details.listIterator();
		while (ma.hasNext()) {
			OrderDetail m = ma.next();
			// get FarmerProduct
			Client client2 = Client.create();
			WebResource fetchFarmerProduct = client2.resource(
					"http://localhost:8080/lf2u/farmers/" + farm + "/products/" + m.getFspidFromOrderDetail());

			ClientResponse details = fetchFarmerProduct.accept("application/json").get(ClientResponse.class);
			String infoExtracedFromResponse = details.getEntity(String.class);

			System.out.println(infoExtracedFromResponse);

			Gson gsonGetFarmerProduct = new Gson();
			FarmerProduct fp = gsonGetFarmerProduct.fromJson(infoExtracedFromResponse, FarmerProduct.class);
			// get name of product
			WebResource fetchname = client
					.resource("http://localhost:8080/lf2u/managers/catalog/" + fp.getGcpid() + "/getname");

			ClientResponse nameOfCorrespondingProduct = fetchname.accept("application/json").get(ClientResponse.class);
			String infoExtracedFromGcpid = nameOfCorrespondingProduct.getEntity(String.class);

			Gson extractname = new Gson();
			String nameOfProduct = extractname.fromJson(infoExtracedFromGcpid, String.class);

			System.out.println("price ->" + fp.getPrice());
			System.out.println("amount ->" + m.getAmount());

			float amount = m.getAmount();

			m.setName(nameOfProduct);
			m.setProductUnit(fp.getProduct_unit());
			m.setPrice(fp.getPrice());
			m.setline_item_total(fp.getPrice() * m.getAmount());
			accumulator = accumulator + fp.getPrice() * m.getAmount();

			String amount_to_string = Float.toString(amount) + " " + fp.getProduct_unit();
			String price = Float.toString(fp.getPrice()) + " per " + fp.getProduct_unit();

			order_detail_presentations.add(new order_detail_presentation(m.getFspidFromOrderDetail(), nameOfProduct,
					amount_to_string, price, fp.getPrice() * amount));
		}
		newOrder.setProducts_total(accumulator);

		// fetch delivery charge
		WebResource fetchFarmer = client
				.resource("http://localhost:8080/lf2u/farmers/" + farm + "/delivery_charge");

		ClientResponse details = fetchFarmer.accept("application/json").get(ClientResponse.class);
		String infoExtracedFromResponse = details.getEntity(String.class);

		JSONObject obj = new JSONObject(infoExtracedFromResponse);
		float farmer_delivery_charge = BigDecimal.valueOf(obj.getDouble("delivery_charge")).floatValue();

		newOrder.setDelivery_charge(farmer_delivery_charge);
		newOrder.setOrder_total(farmer_delivery_charge + accumulator);

		Farm_info_Presentation tempFarm = new Farm_info_Presentation(farm, a.getName(), a.getAdd(), a.getPhone(),
				a.getWeb());

		Presentations.add(new Presentation(newOrder.getOID(), newOrder.getOrder_date(),
				newOrder.getPlanned_delivery_date(), newOrder.getActual_delivery_date(), newOrder.getStatus(), tempFarm,
				order_detail_presentations, newOrder.getDelivery_note(), newOrder.getProducts_total(),
				newOrder.getDelivery_charge(), newOrder.getOrder_total()));

		// complete the FarmerReports object

		Customer theclient = findCostumerID(cid);

		order_by.setName(theclient.getName());
		order_by.setEmail(theclient.getEmail());
		order_by.setPhone(theclient.getPhone());

		String delivery_add = theclient.getStreet() + " , " + theclient.getZip();

		newFarmerOrder.setOid(newOrder.getOID());
		newFarmerOrder.setProducts_total(newOrder.getProducts_total());
		newFarmerOrder.setDelivery_charge(newOrder.getDelivery_charge());
		newFarmerOrder.setOrder_total(newOrder.getOrder_total());
		newFarmerOrder.setStatus(newOrder.getStatus());
		newFarmerOrder.setOrder_date(newOrder.getOrder_date());
		newFarmerOrder.setPlanned_delivery_date(newOrder.getPlanned_delivery_date());
		newFarmerOrder.setActual_delivery_date(newOrder.getActual_delivery_date());
		newFarmerOrder.setOrdered_by(order_by);
		newFarmerOrder.setDelivery_address(delivery_add);
		newFarmerOrder.setNote(newOrder.getDelivery_note());
		newFarmerOrder.setOrder_detail(order_detail_presentations);

		FarmerManager test = new FarmerManager();
		test.updateAFarmerOrder(farm, "2", newFarmerOrder);

		Orders.add(newOrder);
		// Presentations.add(new Presentation())
		return newOrder;

	}

	@Override
	public Presentation viewOrderByOid(String oid) {
		Iterator<Presentation> ma = Presentations.listIterator();
		while (ma.hasNext()) {
			Presentation m = ma.next();
			if (m.matchesId(oid))
				return (m);
		}
		return (new NullPresentation());
	}

	@Override
	public void cancelOrder(String oid, String newvalue) {
		// TODO Auto-generated method stub
		Iterator<Presentation> ma = Presentations.listIterator();
		while (ma.hasNext()) {
			Presentation m = ma.next();
			if (m.matchesId(oid)) {
				m.setStatus(newvalue);
			}
		}

		Iterator<Order> mb = Orders.listIterator();
		while (mb.hasNext()) {
			Order m = mb.next();
			if (m.matchesId(oid))
				m.setStatus(newvalue);
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
