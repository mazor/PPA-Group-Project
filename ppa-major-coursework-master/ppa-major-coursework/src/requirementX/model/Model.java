package requirementX.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Map;
import javax.swing.JLabel;
import api.ripley.Incident;
import api.ripley.Ripley;

public class Model {
	ArrayList<Incident> list;
	ArrayList<String> name;
	Ripley ripley1 = new Ripley("90tLI3CStdmyVD6ql2OMtA==","lBgm4pRs8QnVqL46EnH7ew==");
	TreeMap<String,Integer > mapstates = new TreeMap<String,Integer >();
	int number;
	String from;
	String to;
	String userSelection;

	public void add(){
		mapstates.put("WY",0);
		mapstates.put("AL",0);
		mapstates.put("WI",0);
		mapstates.put("WV",0);
		mapstates.put("VA",0);
		mapstates.put("VT",0);
		mapstates.put("UT",0);
		mapstates.put("TX",0);
		mapstates.put("TN",0);
		mapstates.put("SD",0);
		mapstates.put("SC",0);
		mapstates.put("RI",0);
		mapstates.put("OR",0);
		mapstates.put("OH",0);
		mapstates.put("ND",0);
		mapstates.put("NC",0);
		mapstates.put("NY",0);
		mapstates.put("NM",0);
		mapstates.put("NJ",0);
		mapstates.put("NH",0);
		mapstates.put("NV",0);
		mapstates.put("AL",0);
		mapstates.put("AZ",0); 
		mapstates.put("AR",0); 
		mapstates.put("CA",0); 
		mapstates.put("CO",0);
		mapstates.put("CT",0); 
		mapstates.put("DE",0); 
		mapstates.put("FL",0);
		mapstates.put("GA",0);
		mapstates.put("HI",0);
		mapstates.put("ID",0);
		mapstates.put("IL",0); 
		mapstates.put("IN",0);
		mapstates.put("IA",0);
		mapstates.put("NE",0);
		mapstates.put("KS",0);
		mapstates.put("KY",0); 
		mapstates.put("LA",0);
		mapstates.put("ME",0); 
		mapstates.put("MD",0);
		mapstates.put("MA",0); 
		mapstates.put("MI",0); 
		mapstates.put("MN",0); 
		mapstates.put("MS",0); 
		mapstates.put("MO",0);
		mapstates.put("MT",0);
		mapstates.put("PA",0);
		mapstates.put("WA",0);
		mapstates.put("AK", 0);
		mapstates.put("OK", 0);

	}
	
// use the grab data method  ---ALERT: I don't need this method. Only keep it if you need it. I use the setIncidents in range only, (and access data from the TreeMap).
	public ArrayList<String> getStateName(String from, String to){
		this.from=from;
		this.to=to;
		name = new ArrayList<String>();
		list = ripley1.getIncidentsInRange(from , to );
		//System.out.println(list.size());
		try{
			for (Incident i : list) {
				//System.out.println("printing state" + i.getState());
				name.add(i.getState());
				//System.out.println(name);

			}} catch(NullPointerException exception){
				return null;

			}
		return name;

	} 
	
	//---------------------my methods---------------------------------
	public void setListOfIncidentsInRange(String from,String to){
		this.from=from;
		this.to=to;
		list = ripley1.getIncidentsInRange(from , to );
		
	}
	public ArrayList<Incident> getListOfIncidentsInRange(){
		
		return list;
		
	}
	public void setStateClicked(String userMarkerClick) {
		userSelection = userMarkerClick;
	}
	public String getStateClicked(){
		return userSelection;		
	}
	//update model.
	public void updateModel(String from, String to) {

		getStateName(from,to);	
	}	
	//--------------------------------------------end of my methods----
	public void markState(){
		int i;
		int j;
		int occurence;
		//int number;

		for (Map.Entry<String,Integer> mapping :mapstates.entrySet()){
			number=mapping.getValue();
			for(j=0; j<name.size();j++){
				//System.out.println(mapstates.get(i).getText());
				//System.out.println(name.get(j));
				if (mapping.getKey().equals((name.get(j)))){
					
					number+=1;
					mapstates.put(mapping.getKey(), number);
					
				}
			}
		}
	}
	public TreeMap<String, Integer> getStates() {
		
		return mapstates;
		
	}

	public ArrayList<String> getName() {
		return name;
	}


	public Integer getNumber(){
		return number;
	}
	

//	public static void main(String[] args){
//		Model view1 = new Model();
//		view1.add();
//		//System.out.println("1");
//		view1.getStateName();
//		//System.out.println("2");
//		view1.markState();
//		//System.out.println("3");

	}
