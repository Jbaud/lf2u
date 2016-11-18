package model;

import java.util.ArrayList;
import java.util.List;

public class FarmerOrders {

	
	private String oid;
	private float products_total;
	private float delivery_charge;
	private float order_total;
	private String status;
	private String order_date;
	private String planned_delivery_date;
	private String actual_delivery_date;
	private Ordered_by ordered_by;
	private String delivery_address;
	private String note; 
	private List<order_detail_presentation> order_detail = new ArrayList<order_detail_presentation>(); 
	
	public FarmerOrders(){
		
	}

	public FarmerOrders(String oid, float products_total, float delivery_charge, float order_total, String status,
			String order_date, String planned_delivery_date, String actual_delivery_date, Ordered_by ordered_by,
			String delivery_address, String note, List<order_detail_presentation> order_detail) {
		super();
		this.oid = oid;
		this.products_total = products_total;
		this.delivery_charge = delivery_charge;
		this.order_total = order_total;
		this.status = status;
		this.order_date = order_date;
		this.planned_delivery_date = planned_delivery_date;
		this.actual_delivery_date = actual_delivery_date;
		this.ordered_by = ordered_by;
		this.delivery_address = delivery_address;
		this.note = note;
		this.order_detail = order_detail;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public float getProducts_total() {
		return products_total;
	}

	public void setProducts_total(float products_total) {
		this.products_total = products_total;
	}

	public float getDelivery_charge() {
		return delivery_charge;
	}

	public void setDelivery_charge(float delivery_charge) {
		this.delivery_charge = delivery_charge;
	}

	public float getOrder_total() {
		return order_total;
	}

	public void setOrder_total(float order_total) {
		this.order_total = order_total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getPlanned_delivery_date() {
		return planned_delivery_date;
	}

	public void setPlanned_delivery_date(String planned_delivery_date) {
		this.planned_delivery_date = planned_delivery_date;
	}

	public String getActual_delivery_date() {
		return actual_delivery_date;
	}

	public void setActual_delivery_date(String actual_delivery_date) {
		this.actual_delivery_date = actual_delivery_date;
	}

	public Ordered_by getOrdered_by() {
		return ordered_by;
	}

	public void setOrdered_by(Ordered_by ordered_by) {
		this.ordered_by = ordered_by;
	}

	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<order_detail_presentation> getOrder_detail() {
		return order_detail;
	}

	public void setOrder_detail(List<order_detail_presentation> order_detail) {
		this.order_detail.addAll(order_detail);
	}
	
	
	
	
}
