package model;

import java.util.List;

public class Presentation {

	/*
	 * { "oid": "97", "order_date": "20161021", "planned_delivery_date":
	 * "20161022", "actual_delivery_date": "", "status": "open", "farm_info": {
	 * "fid": "123", "name": "Tall Trees", "address":
	 * "25550 W Cuba Rd, Barrington, IL 60010", "phone": "847-651-2140", "web":
	 * "" }, "order_detail": [{ "fspid": "345", "name": "Potatoes", "amount":
	 * "1.5 lb", "price": "0.29 per lb", "line_item_total": 0.43 } ],
	 * "delivery_note": "Please leave with building attendant.",
	 * "products_total": 22.43, "delivery_charge": 5.00, "order_total": 27.43 }
	 */

	private String oid;
	private String order_date;
	private String planned_delivery_date;
	private String actual_delivery_date;
	private String status;
	private Farm_info_Presentation farm_info;
	private List<order_detail_presentation> order_detail;
	private String delivery_note;
	private float products_total;
	private float delivery_charge;
	private float order_total;

	public Presentation() {

	}

	public Presentation(String oid, String order_date, String planned_delivery_date, String actual_delivery_date,
			String status, Farm_info_Presentation farm_info, List<order_detail_presentation> order_detail,
			String delivery_note, float products_total, float delivery_charge, float order_total) {

		this.oid = oid;
		this.order_date = order_date;
		this.planned_delivery_date = planned_delivery_date;
		this.actual_delivery_date = actual_delivery_date;
		this.status = status;
		this.farm_info = farm_info;
		this.order_detail = order_detail;
		this.delivery_note = delivery_note;
		this.products_total = products_total;
		this.delivery_charge = delivery_charge;
		this.order_total = order_total;
	}
	
	public boolean isNil() {
		return false;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
	
	public boolean matchesId(String oid) {
        return(oid.equals(this.oid));
    }
	
	public void setStatus (String newStatus){
		this.status=newStatus;
	}

}
