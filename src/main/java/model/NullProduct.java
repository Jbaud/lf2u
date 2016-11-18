package model;

public class NullProduct extends ManagerProduct {

	@Override
    public boolean isNil() {
        return true;
    }
}
