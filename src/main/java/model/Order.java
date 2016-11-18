package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.annotations.Expose;

public class Order {

	private transient String belongs_to;

	@Expose(deserialize = false)
	private String oid;
	@Expose
	private String fid;
	@Expose(deserialize = false)
	private String order_date;
	@Expose
	private String planned_delivery_date;
	@Expose
	private String actual_delivery_date;
	@Expose
	private String status;

	private List<OrderDetail> order_detail = new ArrayList<OrderDetail>();

	private String delivery_note;

	public String getDelivery_note() {
		return delivery_note;
	}

	public void setDelivery_note(String delivery_note) {
		this.delivery_note = delivery_note;
	}

	// add
	private Farm_info farm_info;
	private float products_total;
	private float delivery_charge;
	private float order_total;
	
	public Order() {

	}

	public Order(String fid, List<OrderDetail> newOrderDetail) {
		this.fid = fid;
		this.order_detail = newOrderDetail;
	}

	public Order(String fid, List<OrderDetail> newOrderDetail, String note) {
		this.fid = fid;
		this.order_detail = newOrderDetail;
		this.delivery_note = note;

	}

	public Farm_info getFarm_info() {
		return farm_info;
	}
	
	public void setFarm_info(Farm_info farm_info) {
		this.farm_info = farm_info;
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

	public void setDelivery_charge(float farmer_delivery_charge) {
		this.delivery_charge = farmer_delivery_charge;
	}

	public float getOrder_total() {
		return order_total;
	}

	public void setOrder_total(float f) {
		this.order_total = f;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOID() {
		return this.oid;
	}

	public String getFid() {
		return this.fid;
	}

	public List<OrderDetail> getOrderDetail() {
		return this.order_detail;
	}

	public void setBelongs_to(String cid) {
		this.belongs_to = cid;
	}

	public String getBelongs_to() {
		return this.belongs_to;
	}

	public void setOID() {
		this.oid = UUID.randomUUID().toString();
	}

	public boolean matchesId(String oid) {
		return (oid.equals(this.oid));
	}

	public boolean isNil() {
		return false;
	}

}
