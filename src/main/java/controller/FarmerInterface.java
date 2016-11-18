package controller;

import model.*;

import java.util.List;

public interface FarmerInterface {
	
	List<Farmer> getAllFarmers();
	Farmer createFarmer(Farm_info myfarm,Personal_info myinfo,Delivers_to mydestinations);
	Farmer viewFarmer(String fid);
	void updateFarmer(String fid, Farm_info farm, Personal_info info, Delivers_to deliversto);
	List<SimpleFarmer> searchByZip(String zip);
	List<FarmerProduct>  viewFarmerProduct(String fid);
	FarmerProduct createAProduct(FarmerProduct newFarmerProduct);
	FarmerProduct getFarmerProduct(String fspid);
	Report createAreport(String frid, String name);
	List<Report> getAllReport();
	List<FarmerReport> getAllFarmerReports();
	FarmerReport getAFarmerReport(String fid,String frid);
	void updateAFarmerOrder(String fid,String frid,FarmerOrders il);
}
