package model;

public class OrderDetail {
	
	private String fspid;
	private String name;
	private float amount;
	private float price;
	private float line_item_total;
	private String product_unit;
	
	public OrderDetail(){
		
	}
	
	public OrderDetail(String fspid,float amount){
		this.fspid=fspid;
		this.amount=amount;
	}
	
	public String getFspidFromOrderDetail(){
		return this.fspid;
	}

	public void setName(String name){
		this.name=name;
	}
	
	public void setProductUnit(String unit){
		this.product_unit=unit;
	}
	public String getProductUnit(){
		return this.product_unit;
	}
	public float getAmount(){
		return this.amount;
	}
	public void setAmount(float newAmount){
		this.amount=newAmount;
	}
	public float getPrice(){
		return this.price;
	}
	public void setPrice(float newPrice){
		this.amount=newPrice;
	}
	public void setline_item_total(float newprice){
		this.line_item_total=newprice;
	}
	public float getline_item_total(){
		return this.line_item_total;
	}
}
