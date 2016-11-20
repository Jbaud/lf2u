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

	
}
