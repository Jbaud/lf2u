package model;

public class order_detail_presentation {

	/*
	 * "fspid": "345", "name": "Potatoes", "amount": "1.5 lb", "price":
	 * "0.29 per lb", "line_item_total": 0.43
	 */

	private String fspid;
	private String name;
	private String amount;
	private String price;
	private float line_item_total;

	public order_detail_presentation() {

	}

	public order_detail_presentation(String fspid,String name,String amount,String price,float line_item_total) {
		this.fspid=fspid;
		this.name= name;
		this.amount=amount;
		this.price=price;
		this.line_item_total=line_item_total;
	}

	public String getFspid() {
		return fspid;
	}

	public void setFspid(String fspid) {
		this.fspid = fspid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public float getLine_item_total() {
		return line_item_total;
	}

	public void setLine_item_total(float line_item_total) {
		this.line_item_total = line_item_total;
	}
	
}
