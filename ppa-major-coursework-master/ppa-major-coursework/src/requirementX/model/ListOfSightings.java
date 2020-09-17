package requirementX.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import api.ripley.Incident;
import api.ripley.Ripley;
import requirementX.Mapp;
import requirementX.model.Model;

public class ListOfSightings {

	private ArrayList<Incident> modelIncidentsInRange;
	private TreeMap<String,ArrayList<Incident>> incidentsByState; 		
	private Model model;

	private String tempState = null;
	private ArrayList<Incident> tempIncidentList;	//temp array of the list of States, with incidents.
	
	//to pass into the Mapp view.
	
	private DefaultListModel<Incident> dflAllIncidentList;
	
/*	private ArrayList<String> tempStates;
	private Incident single_incident;*/
	
	public ListOfSightings(Model model2 ) {
		// TODO Auto-generated constructor stub
		this.model = model2;
		
		tempIncidentList = new  ArrayList<Incident>();
		
		modelIncidentsInRange = model2.getListOfIncidentsInRange();	//all of model's incidents here....
																 	//loop could extract data based on key.		
		//items for the Mapp view. 
		
		dflAllIncidentList = new DefaultListModel<>();
		incidentsByState = new TreeMap<String,ArrayList<Incident>> ();
		
	}
	private void addUSStates(){
		
		incidentsByState.put("WY",new ArrayList<Incident>());
		incidentsByState.put("AK",new ArrayList<Incident>()); 
		incidentsByState.put("WI",new ArrayList<Incident>());
		incidentsByState.put("WV",new ArrayList<Incident>());
		incidentsByState.put("VA",new ArrayList<Incident>());
		incidentsByState.put("VT",new ArrayList<Incident>());
		incidentsByState.put("UT",new ArrayList<Incident>());
		incidentsByState.put("TX",new ArrayList<Incident>());
		incidentsByState.put("TN",new ArrayList<Incident>());
		incidentsByState.put("SD",new ArrayList<Incident>());
		incidentsByState.put("SC",new ArrayList<Incident>());
		incidentsByState.put("RI",new ArrayList<Incident>());
		incidentsByState.put("OR",new ArrayList<Incident>());
		incidentsByState.put("OH",new ArrayList<Incident>());
		incidentsByState.put("ND",new ArrayList<Incident>());
		incidentsByState.put("NC",new ArrayList<Incident>());
		incidentsByState.put("NY",new ArrayList<Incident>());
		incidentsByState.put("NM",new ArrayList<Incident>());
		incidentsByState.put("NJ",new ArrayList<Incident>());
		incidentsByState.put("NH",new ArrayList<Incident>());
		incidentsByState.put("NV",new ArrayList<Incident>());
		incidentsByState.put("AL",new ArrayList<Incident>());
		incidentsByState.put("AZ",new ArrayList<Incident>()); 
		incidentsByState.put("AR",new ArrayList<Incident>()); 
		incidentsByState.put("CA",new ArrayList<Incident>()); 
		incidentsByState.put("CO",new ArrayList<Incident>());
		incidentsByState.put("CT",new ArrayList<Incident>()); 
		incidentsByState.put("DE",new ArrayList<Incident>()); 
		incidentsByState.put("FL",new ArrayList<Incident>());
		incidentsByState.put("GA",new ArrayList<Incident>());
		incidentsByState.put("HI",new ArrayList<Incident>());
		incidentsByState.put("ID",new ArrayList<Incident>());
		incidentsByState.put("IL",new ArrayList<Incident>()); 
		incidentsByState.put("IN",new ArrayList<Incident>());
		incidentsByState.put("IA",new ArrayList<Incident>());
		incidentsByState.put("NE",new ArrayList<Incident>());
		incidentsByState.put("KS",new ArrayList<Incident>());
		incidentsByState.put("KY",new ArrayList<Incident>()); 
		incidentsByState.put("LA",new ArrayList<Incident>());
		incidentsByState.put("ME",new ArrayList<Incident>()); 
		incidentsByState.put("MD",new ArrayList<Incident>());
		incidentsByState.put("MA",new ArrayList<Incident>()); 
		incidentsByState.put("MI",new ArrayList<Incident>()); 
		incidentsByState.put("MN",new ArrayList<Incident>()); 
		incidentsByState.put("MS",new ArrayList<Incident>()); 
		incidentsByState.put("MO",new ArrayList<Incident>());
		incidentsByState.put("MT",new ArrayList<Incident>());
		incidentsByState.put("PA",new ArrayList<Incident>());
		incidentsByState.put("WA",new ArrayList<Incident>());
		incidentsByState.put("OK",new ArrayList<Incident>());
		incidentsByState.put("PR",new ArrayList<Incident>());		//new added state Puerto Rico
		incidentsByState.put("DC",new ArrayList<Incident>());		//new added state Washington DC
	
	
	}
	//only call respond to click.	
	//Searches through incidents, and checks if matches users choice. 	
	//then call's method to map State's incident.
	public void respondToClick(){
		
		addUSStates(); //step1
		//System.out.println(incidentsByState.keySet());																		//could have used .containsKey()
																		//				  .keySet(); -- Returns a Set view of the keys contained in this map.
	
		for (String s: incidentsByState.keySet() ){		//go through the incidents KEY. (e.g. the states)
			//System.out.println(model);
			if(s.equals(model.getStateClicked())){		//if the current state matches the user choice.
					
					tempState = s;		//store the incident state
					System.out.println(s);
					System.out.println(model.getStateClicked());
					sortIncidents(  tempState   );		//pass the current state.
					
			}
			
		}
	}
	public void sortIncidents(String tempState){	//could rename to mapStateToIncidents()
		//System.out.println(modelIncidentsInRange);
		//this maps incidents into it's states.
		for (Incident incident: modelIncidentsInRange){		//For all incidents, and if the state is NOT in Canada.
			
			if (!(incident.getState().equals("NF")||incident.getState().equals("SA")||incident.getState().equals("YK")||incident.getState().equals("PQ")|| incident.getState().equals("ON")||incident.getState().equals("AB")|| incident.getState().equals("BC")||incident.getState().equals("MB") ||incident.getState().equals("NB")||incident.getState().equals("NT") ||incident.getState().equals("NS")||incident.getState().equals("NU") ||incident.getState().equals("PE")	||incident.getState().equals("QC") 	||incident.getState().equals("SK")||incident.getState().equals("YT") ||incident.getState().equals("NL") || (incident.getState().equals("Not specified.")))){
				 	//stores the ALL Incident states from API (in an array list)

				this.tempState = tempState;		//get the current incident state.
				tempIncidentList=incidentsByState.get(tempState);		//get the value, of the state. (i.e. the incident array)	
				//System.out.println(tempState);
				//System.out.println(tempIncidentList);
				//incidentsByState.get(incident.getState()).add(incident); //here add the incident.
				tempIncidentList.add(incident);	//add incident to ArrayList.
				
				incidentsByState.put(tempState,tempIncidentList);
				//here, each state is mapped to its incident. 
				
			}
		}
		addIncidentsToJList();
	}
	public void addIncidentsToJList(){
		
		System.out.println("TESTING123123123");
		
		System.out.println("ABCABCABCABC");
		for (Incident i:tempIncidentList){
			System.out.println("oooooooooooooooo");
			dflAllIncidentList.addElement(i);		//adding the incidents, to the DefaultListModel.
		}
		
		/*NOTES: Default List Model - api
		
		+void add(int index, E element) 
		Inserts the specified element at the specified position in this list. 
		+void removeAllElements() 
		Removes all components from this list and sets its size to zero. 
	
		+Object[] toArray() 
		Returns an array containing all of the elements in this list in the correct order. 
	*/
		
	}
	//return current state
	public String getTempState(){
		return tempState;
	}
	//return current list of Incidents
	public ArrayList<Incident> getIncidents(){
		return tempIncidentList;
	}
	//return TreeMap (state, mapped to incidents)
	public DefaultListModel<Incident> getJListModel(){
		return dflAllIncidentList;
	}
	public TreeMap<String,ArrayList<Incident>> getTreeMap(){
		
		respondToClick();	//step2
		return incidentsByState;
	}
	
	
}