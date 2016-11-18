package model;

import java.util.ArrayList;
import java.util.List;

public class Delivers_to {
	
	List<String> Delivers = new ArrayList<String>();
	
	public Delivers_to(){
		
	}

	public Delivers_to(List<String> delivers_to){
		Delivers.addAll(delivers_to);
	}
	
	public List<String> getZip(){
		return this.Delivers;
	}
	
}
