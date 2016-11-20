package lf2u;

import model.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestNils {

	@Test
	public void TestNills() {

		NullFarmer test = new NullFarmer();
		assertEquals(test.isNil(), true);

		NullPresentation test2 = new NullPresentation();
		assertEquals(test2.isNil(), true);

		NullCustomer test3 = new NullCustomer();
		assertEquals(test3.isNil(), true);

		NullFarm_info test4 = new NullFarm_info();
		assertEquals(test4.isNil(), true);

		NullManager test5 = new NullManager();
		assertEquals(test5.isNil(), true);

		NullOrder test6 = new NullOrder();
		assertEquals(test6.isNil(), true);

		NullFarmerProduct test7 = new NullFarmerProduct();
		assertEquals(test7.isNil(), true);

		NullPersonal_info test8 = new NullPersonal_info();
		assertEquals(test8.isNil(), true);

		NullProduct test9 = new NullProduct();
		assertEquals(test9.isNil(), true);

	}

	@Test
	public void CreateOrderedBy() {
		Ordered_by newOrdereBy = new Ordered_by("Jules", "test", "1234");

		assertEquals(newOrdereBy.getEmail(), "test");
		assertEquals(newOrdereBy.getName(), "Jules");
		assertEquals(newOrdereBy.getPhone(), "1234");

	}

}
