package model;

import java.util.UUID;

import com.google.gson.annotations.Expose;

public class FarmerProduct {

	private transient String belongs_to;
	@Expose(deserialize = false)
	private String fspid;
	private String gcpid;
	private String note;
	private String start_date;
	private String end_date;
	private float price;
	private String product_unit;
	private String image;

	public FarmerProduct() {

	}

	public FarmerProduct(String belongs_to, String gcpid, String note, String start_date, String end_date,
			Integer price, String product_unit, String image) {
		// this.fspid=UUID.randomUUID().toString();
		this.belongs_to = belongs_to;
		this.gcpid = gcpid;
		this.note = note;
		this.start_date = start_date;
		this.end_date = end_date;
		this.price = price;
		this.product_unit = product_unit;
		this.image = image;
	}

	public void initFSPID() {
		this.fspid = UUID.randomUUID().toString();
	}

	// setters
	public void setBelongs_to_whom(String belongs_to) {
		this.belongs_to = belongs_to;
	}

	public void setGcpid(String newGcpid) {
		this.gcpid = newGcpid;
	}

	public void setNote(String newnote) {
		this.note = newnote;
	}

	public void setStart(String newStart) {
		this.start_date = newStart;
	}

	public void setEnd(String newEnd) {
		this.end_date = newEnd;
	}

	public void setPrice(float newPrice) {
		this.price = newPrice;
	}

	public void setUnit(String unit) {
		this.product_unit = unit;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// matches

	public boolean matchesId(String fspid) {
		return (fspid.equals(this.fspid));
	}
	// getters

	public String getFspid() {
		return this.fspid;
	}

	public String getBelongs_to() {
		return belongs_to;
	}

	public String getNote() {
		return note;
	}

	public String getStart_date() {
		return start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public float getPrice() {
		return price;
	}

	public String getProduct_unit() {
		return product_unit;
	}

	public String getImage() {
		return image;
	}

	public float getprice() {
		return this.price;
	}

	public String belongs_to_whom() {
		return this.belongs_to;
	}

	public String getGcpid() {
		return this.gcpid;
	}

	// nil

	public boolean isNil() {
		return false;
	}
}
