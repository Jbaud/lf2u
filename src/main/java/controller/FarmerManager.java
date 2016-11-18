package controller;

import model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FarmerManager implements FarmerInterface, FarmerProductInterface {

	private static List<Farmer> Farmers = new ArrayList<Farmer>();
	private static List<FarmerProduct> FarmerProducts = new ArrayList<FarmerProduct>();
	private static List<Report> Reports = new ArrayList<Report>();
	public static List<FarmerReport> FarmerReports = new ArrayList<FarmerReport>();
	
	@Override
	public List<Farmer> getAllFarmers() {
		
		// TODO Auto-generated method stub
		return Farmers;
	}

	@Override
	public Farmer createFarmer(Farm_info myfarm, Personal_info myinfo, Delivers_to mydestinations) {
		Farmer newFarmer = new Farmer(myfarm, myinfo, mydestinations);
		Farmers.add(newFarmer);
		// we also create a now empty farmer report associated to this fid
		// this object will be completed when an actual order is created
		FarmerReport newFarmerReport1 = new FarmerReport();
		newFarmerReport1.setFid(newFarmer.getFarmerID());
		newFarmerReport1.setFrid("1");
		newFarmerReport1.setName("Orders to deliver today");
		
		FarmerReport newFarmerReport2 = new FarmerReport();
		newFarmerReport2.setFid(newFarmer.getFarmerID());
		newFarmerReport2.setFrid("2");
		newFarmerReport2.setName("Orders to deliver tomorrow");
		
		FarmerReport newFarmerReport3 = new FarmerReport();
		newFarmerReport3.setFid(newFarmer.getFarmerID());
		newFarmerReport3.setFrid("3");
		newFarmerReport3.setName("Revenue report");
		
		FarmerReport newFarmerReport4 = new FarmerReport();
		newFarmerReport4.setFid(newFarmer.getFarmerID());
		newFarmerReport4.setFrid("4");
		newFarmerReport4.setName("Orders delivery report");
		
		FarmerReports.add(newFarmerReport1);
		FarmerReports.add(newFarmerReport2);
		FarmerReports.add(newFarmerReport3);
		FarmerReports.add(newFarmerReport4);
		
		return newFarmer;
	}

	@Override
	public Farmer viewFarmer(String fid) {
		// TODO Auto-generated method stub
		return (findFarmerbyID(fid));
	}

	private Farmer findFarmerbyID(String fid) {
		// TODO Auto-generated method stub
		Iterator<Farmer> ma = Farmers.listIterator();
		while (ma.hasNext()) {
			Farmer m = ma.next();
			if (m.matchesId(fid))
				return (m);
		}
		return (new NullFarmer());
	}

	@Override
	public FarmerProduct getFarmerProduct(String fspid) {
		// TODO Auto-generated method stub
		return (findFarmerProductbyID(fspid));
	}

	private FarmerProduct findFarmerProductbyID(String fspid) {
		// TODO Auto-generated method stub
		Iterator<FarmerProduct> ma = FarmerProducts.listIterator();
		while (ma.hasNext()) {
			FarmerProduct m = ma.next();
			if (m.matchesId(fspid))
				return (m);
		}
		return (new NullFarmerProduct());
	}

	// only called when we know farmer exists
	@Override
	public void updateFarmer(String fid, Farm_info farm, Personal_info info, Delivers_to deliversto) {
		for (Farmer d : Farmers) {
			if (d.getFarmerID() != null && d.getFarmerID().contains(fid)) {
				d.setFarmer_info(farm);
				d.setInfo(info);
				d.setDelivery(deliversto);
			}

		}
	}

	@Override
	public List<SimpleFarmer> searchByZip(String zip) {
		List<SimpleFarmer> returnList = new ArrayList<SimpleFarmer>();
		for (Farmer d : Farmers) {
			if (d.getDelivery().getZip().contains(zip)) {
				returnList.add(new SimpleFarmer(d.getFarmerID(), d.getFarmInfo().getName()));
			}
		}
		return returnList;
	}

	@Override
	public List<FarmerProduct> getAllProducts() {
		// TODO Auto-generated method stub
		return FarmerProducts;
	}

	/*
	 * @Override public FarmerProduct createAProduct(String belongs_to,String
	 * gcpid, String note, String start_date, String end_date, Integer price,
	 * String product_unit, String image) { FarmerProduct newFarmerProduct = new
	 * FarmerProduct(belongs_to,gcpid, note, start_date, end_date, price,
	 * product_unit, image); FarmerProducts.add(newFarmerProduct); return
	 * newFarmerProduct; }
	 */

	@Override
	public FarmerProduct createAProduct(FarmerProduct newFarmerProduct) {
		newFarmerProduct.initFSPID();
		FarmerProducts.add(newFarmerProduct);
		return newFarmerProduct;
	}

	// called when we knwo the farmer exists
	@Override
	public List<FarmerProduct> viewFarmerProduct(String fid) {

		List<FarmerProduct> returnList = new ArrayList<FarmerProduct>();
		for (FarmerProduct d : FarmerProducts) {
			if (d.belongs_to_whom().contains(fid)) {
				returnList.add(d);
			}
		}
		return returnList;
	}

	
	// Farmer's reports 
	
	@Override
	public Report createAreport(String frid, String name) {

		Reports.add(new Report(frid,name));

		return null;
	}

	@Override
	public List<Report> getAllReport() {
		// TODO Auto-generated method stub
		return Reports;
	}

	@Override
	public List<FarmerReport> getAllFarmerReports() {
		// TODO Auto-generated method stub
		return FarmerReports;
	}

	@Override
	public FarmerReport getAFarmerReport(String fid,String frid) {
		
		Iterator<FarmerReport> ma = FarmerReports.listIterator();
		while (ma.hasNext()) {
			FarmerReport m = ma.next();
			if (m.matchesId(fid) && m.matchesFrid(frid))
				return (m);
		}
		
		return null;
	}

	@Override
	public void updateAFarmerOrder(String fid, String frid, FarmerOrders il) {
		FarmerReport found = getAFarmerReport(fid, frid);
		found.addOrders(il);
		
	}

}
