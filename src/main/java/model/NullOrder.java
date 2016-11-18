package model;

public class NullOrder extends Order {
	
	@Override
	public boolean isNil() {
		return true;
	}

}
