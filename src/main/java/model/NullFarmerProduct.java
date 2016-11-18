package model;

public class NullFarmerProduct extends FarmerProduct {
	
	@Override
    public boolean isNil() {
        return true;
    }

}
