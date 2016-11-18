package model;

public class NullCustomer extends Customer {

	@Override
	public boolean isNil() {
        return true;
    }
}
