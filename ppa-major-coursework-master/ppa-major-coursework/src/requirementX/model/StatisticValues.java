package requirementX.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

import api.ripley.Incident;
import api.ripley.Ripley;

import java.util.Calendar;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * This class is used to get values for statistics based on incidents that occurred in a supplied range.
 */
public class StatisticValues {
	
	private Ripley ripley; //is used to get the incidents in a supplied range
	private TreeMap<String,Integer> mapstates; //represents all the US states mapped with the number of incidents that occurred in that state
	private ArrayList<Incident> incidentsInRange; //represents all the incidents in a given range
	private ArrayList<String> shapes; //represents all the shapes of the Incidents
	private TreeMap<String,Integer> graphic; //represents all the shapes mapped with the number of Incidents that were that shape
	private TreeMap<String,Integer > cities; //represents all the cities that the Incidents in a given range occurred in
	private ArrayList<Incident> incidentsWithKeyWords; //represents all the incidents that had military keywords
	private String startDate; //represents the startDate
	private String endDate; //represents the endDate
	
	//the following fields are all for the season statistic
	private DateValidation dv; //used for conversions of Strings to more suitable formats

	// the following 12 fields are used to represent number of incidents in a given month (one field for each month of the year)
	private int JanuaryCount;
	private int FebruaryCount;
	private int MarchCount;
	private int AprilCount;
	private int MayCount;
	private int JuneCount;
	private int JulyCount;
	private int AugustCount;
	private int SeptemberCount;
	private int OctoberCount;
	private int NovemberCount;
	private int DecemberCount;
	
	// the following 12 fields are used to represent number of incidents in a given season (one field for each season of the year)
	private int SpringCount;
	private int SummerCount;
	private int AutumnCount ;
	private int WinterCount ;
	
	/**
	 * Constructor assigns values for ripley, startDate and endDate and initialises graphic, dv, incidentsInRange and mapstates
	 */
	public StatisticValues(String startDate, String endDate) {
		
		//(Ripley is created using private key and public key)
		ripley = new Ripley("90tLI3CStdmyVD6ql2OMtA==", "lBgm4pRs8QnVqL46EnH7ew==");
		//graphic is initialised
		graphic= new TreeMap<String,Integer>();		
		//dv is initialised
		dv = new DateValidation(null);
		//startDate and endDate are initialised as the parameters
		this.startDate=startDate;
		this.endDate=endDate;
		//incidentsInRange is assigned as the incidents from ripley between the two given dates
		incidentsInRange=ripley.getIncidentsInRange(startDate, endDate);
		
		//mapStates is initialised as a new TreeMap of String and Integer as it will map state abbreviations to number of sightings in that state during the correct range
		 mapstates = new TreeMap<String,Integer >();
		
	}
	
	/**
	 * Updates values for startDate, endDate and incidentsInRange based on supplied startDate and endDate
	 * @param startDate
	 * @param endDate
	 */
	public void updateDatesAndIncidents(String startDate, String endDate){
		
		this.startDate=startDate;
		this.endDate=endDate;
		
		//incidentsInRange is assigned as the result of calling getIncidentsInRange from ripley with the parameters supplieds
		incidentsInRange = ripley.getIncidentsInRange(startDate,endDate);
		
	}
	
	/**
	 * Returns the number of sightings in the database where the sighting was outside the US
	 * (startDate and endDate are fields whose values are assigned in the constructor or 
	 * updated in updateDatesAndIncidents)
	 * @return the number of sightings in the database where the sighting was outside the US
	 */
	public int getNonUSInfo(){ 
		
		//notInUS is initialised as 0
		int notInUS=0; //notInUS represents the number of incidents not in the US

		//tempState will be used to represent the state of the incident being looked at
		String tempState;
		
		//for each incident in the specified range do
		for (Incident incident: incidentsInRange){
			tempState=incident.getState();
			//if the state is in Canada or South Australia or is 'Not Specified.' then notInUS is incremented by one
			if ((tempState.equals("ON")||tempState.equals("AB")
				||tempState.equals("BC")||tempState.equals("MB")
				||tempState.equals("NB")||tempState.equals("NT")
				||tempState.equals("NS")||tempState.equals("NU")
				||tempState.equals("PE")||tempState.equals("QC")
				||tempState.equals("SK")||tempState.equals("YT")
				||tempState.equals("NL")||tempState.equals("NF")
				||tempState.equals("SA")|| (tempState.equals("Not specified.")))){
						notInUS++;
			} 
					

		}
		
		return notInUS; //notInUS is returned
	}
	
	/**
	 * Returns the number of suspected hoaxes during the date range 
	 * (startDate and endDate are fields whose values are assigned in the constructor or 
	 * updated in updateDatesAndIncidents)
	 * @return number of suspected hoaxes
	 */
	public int getHoaxInfo(){ 
		
		//numberOfHoaxes is used to represent the number of suspected hoaxes in incidentsInRange
		int numberOfHoaxes = 0; //initially assigned as 0 as no suspected hoaxes have been found yet
		
		String[] parts; //will be used when splitting the Incidents around "hoax"

		//for each Incident in incidentsInRange
		for (Incident incident: incidentsInRange){
			
			//part is assigned as the result of splitting the (lower case) summary of incident around "hoax"
			parts= incident.getSummary().toLowerCase().split("hoax");
			
			//if parts has a length of more than one (meaning incident summary contained the word hoax and so is a suspected hoax)
				if (parts.length>1){
		
					//numberOfHoaxes is incremented by one
					numberOfHoaxes++;
				}
				
		}
			
		return numberOfHoaxes;
		
	}

	/**
	 * All US states are put into the TreeMap mapstates with a value of 0. 
	 * This may override values if TreeMap already has values mapped to those states.
	 */
	private void addUSStates(){
		mapstates.put("WY",0);
		mapstates.put("AK",0); 
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
		mapstates.put("OK",0);

	}
	
	/**
	 * Returns the likeliest state in full (not in abbreviation) based on which state had the most sightings (Incidents) during the range. 
	 * (startDate and endDate are fields whose values are assigned in the constructor or 
	 * updated in updateDatesAndIncidents)
	 * @return the likeliest state
	 */
	public String getLikeliestState(){

		//states are added to mapstates
		addUSStates();
		
		//tempState will be used to represent the state of the incident being looked at
		String tempState;
		
		//tempSightingNo is initialised as 0 and is used to represent the number of sightings so far for the state that tempState represents
		int tempSightingNo=0;
		
		//for every incident in the range
		for (Incident incident: incidentsInRange){
			//tempStates is assigned as the incident's state using the getState() method
			tempState=incident.getState();
			
			//if tempState is not in Canada or South Australia and tempState is not "Not specified." then
				if (!(tempState.equals("ON")||tempState.equals("AB")
					||tempState.equals("BC")||tempState.equals("MB")
					||tempState.equals("NB")||tempState.equals("NT")
					||tempState.equals("NS")||tempState.equals("NU")
					||tempState.equals("PE")||tempState.equals("QC")
					||tempState.equals("SK")||tempState.equals("YT")
					||tempState.equals("NF")||tempState.equals("SA")
					||tempState.equals("NL")
					||tempState.equals("Not specified."))){
					
						//tempSightingNo is reassigned as 0 to ensure the wrong state's sighting is not inserted with tempState
						tempSightingNo=0;
						
						//if tempState is a key in mapstates and is not mapped to null then
						if (mapstates.get(tempState)!=null){
							
							//tempSightingNo is assigned as the value currently mapped to tempState
							tempSightingNo=mapstates.get(tempState);
							
						}
						
						//tempSightingNo+1 is mapped with tempState and the entry is put into the TreeMap
						mapstates.put(tempState, tempSightingNo+1 );
						
				}
				
		} 
		
		//tempMostState is used to represent the State with the most sightings in the TreeMap found so far
		//it is initialised as "TX" which is a US state abbreviation representing Texas
		String tempMostState="TX";
		
		//tempMostValue is assigned as the value associated with the tempMostState. It represents the highest value found so far
		int tempMostValue=mapstates.get(tempMostState);
		
		//currentState is used to represent the state that is compared with the tempMostState. 
		//it is initialised as "TX" which is a US state abbreviation representing Texas
		String currentState="TX";
		
		//currentValue is assigned as the value associated with the currentState. It represents the value that is compared with the tempMostValue. 
		int currentValue=mapstates.get(currentState);
		
		//for every entry in the mapstates entryset 
		for (java.util.Map.Entry<String,Integer> mapping :mapstates.entrySet()){
			
			//currentState is assigned as the key of mapping
			currentState=mapping.getKey();
			//currentValue is assigned as the value of mapping
			currentValue=mapping.getValue();
			
			//if currentValue is greater than tempMostValue then
			if (currentValue>tempMostValue){
				
				//tempMostState is assigned as currentState as currentState had a greater number of sightings than the previous tempMostState
				tempMostState=currentState;
				
				//tempMostValue is assigned as currentValue as currentValue was higher and so is the highest value found so far
				tempMostValue=currentValue;
			}
			
		}
		
		//a switch statement is done best on tempMostState to get the full state name rather than the abbreviation
		switch(tempMostState){
			
			case 	"WY":   tempMostState="Wyoming";
							break;
							
			case 	"AK":   tempMostState="Alaska";
							break;
							
			case 	"WI":   tempMostState="Wisconsin";
							break;
							
			case 	"WV":   tempMostState="West Virginia";
							break;
							
			case	"VA":	tempMostState="Virginia";
							break;
							
			case 	"VT":	tempMostState="Vermont";
							break;
							
			case 	"UT":	tempMostState="Utah";
							break;
							
			case 	"TX":	tempMostState="Texas";
							break;
							
			case 	"SD":	tempMostState="South Dakota";
							break;
							
			case 	"SC":	tempMostState="South Carolina";
							break;
							
			case 	"RI":	tempMostState="Rhode Island";
							break;
							
			case 	"TN":	tempMostState="Tennessee";
							break;
							
			case 	"OR":	tempMostState="Oregon";
							break;
							
			case 	"OH":	tempMostState="Ohio";
							break;
							
			case 	"ND":	tempMostState="North Dakota";
							break;
							
			case 	"NC":	tempMostState="North Carolina";
							break;
							
			case 	"NY":	tempMostState="New York";
							break;
							
			case 	"NM":	tempMostState="New Mexico";
							break;
							
			case 	"NJ":	tempMostState="New Jersey";
							break;
							
			case 	"NH":	tempMostState="New Hampshire";
							break;
							
			case 	"NV":	tempMostState="Nevada";
							break;
							
			case 	"AL":	tempMostState="Alabama";
							break;
							
			case 	"AZ":	tempMostState="Arizona";
							break;
							
			case 	"AR":	tempMostState="Arkansas";
							break;
							
			case	"CA":	tempMostState="California";
							break;
							
			case 	"CO":	tempMostState="Colorado";
							break;
							
			case 	"CT":	tempMostState="Conneticut";
							break;
							
			case 	"DE": 	tempMostState="Delaware";
							break;
							
			case 	"FL":	tempMostState="Florida";
							break;
							
			case 	"GA":	tempMostState="Georgia";
							break;
							
			case 	"HI":	tempMostState="Hawaii";
							break;
							
			case 	"ID":	tempMostState="Idaho";
							break;
							
			case 	"IL":	tempMostState="Illinois";
							break;
							
			case 	"IN":	tempMostState="Indiana";
							break;
							
			case 	"IA":	tempMostState="Iowa";
							break;
							
			case 	"NE":	tempMostState="Nebraska";
							break;
							
			case 	"KS": 	tempMostState="Kansas";
							break;
							
			case 	"KY": 	tempMostState="Kentucky";
							break;
							
			case	"LA":	tempMostState="Louisiana";
							break; 
							
			case 	"ME":	tempMostState="Maine";
							break;
							
			case 	"MD":	tempMostState="Maryland";
							break;
							
			case 	"MA":	tempMostState="Massachusetts";
							break;
							
			case	"MI":	tempMostState="Michigan";
							break;
						
			case 	"MN": 	tempMostState="Minnesota";
							break;
							
			case 	"MS":	tempMostState="Mississippi";
							break;
							
			case 	"MO":	tempMostState="Missouri";
							break;
							
			case 	"MT":	tempMostState="Montana";
							break;
							
			case	"PA":	tempMostState="Pennsylvania";
							break;
							
			case	"WA": 	tempMostState="Washington";
							break;
							
			case 	"OK":	tempMostState="Oklahoma";
							break;
			
			default: break;
			
		}
			return("Most likely state="+tempMostState);

	}

	/**
	 *  Adds entries to graphic (each Shape name mapped with a value of 0)
	 */
	private void addShapes(){

			graphic.put("Flash",0);
			graphic.put("Rectangle",0);
			graphic.put("Cone",0);
			graphic.put("Egg",0);
			graphic.put("Teardrop",0);
			graphic.put("Fireball",0);
			graphic.put("Other",0);
			graphic.put("Light",0);
			graphic.put("Circle",0);
			graphic.put("Triangle",0);
			graphic.put("Fireball",0);
			graphic.put("Sphere", 0);
			graphic.put("Diamond",0);
			graphic.put("Field", 0);   
			graphic.put("Disk", 0);
			graphic.put("Cigar", 0);
			graphic.put("Cylinder", 0);
			graphic.put("Oval", 0);
			graphic.put("Chevron", 0);
			graphic.put("Changing",0);
			graphic.put("Unknown",0);
			graphic.put("Formation",0);
	}

	/**
	 * Used to return an ArrayList of type String to represent the shapes of the incidents
	 * @return ArrayList<String> representing all the shapes of the incidents
	 */
	private ArrayList<String> getShapes(){
		
		//addShapes is called to add entries to graphic
		addShapes();

		//shapes is initialised 
		shapes = new ArrayList<String>();

		//for every incident in incidentsInRange
		for (Incident incident: incidentsInRange){
			
			//incident's shape is added to the ArrayList shapes 
			shapes.add(incident.getShape());

		}
			return shapes;
	}

	/**
	 * Returns the likeliest shape (most commonly appearing) for the incidentsInRange ArrayList
	 * (incidentsInRangeis a field whose values is assigned in the constructor or 
	 * updated in updateDatesAndIncidents)
	 * @return the likeliest shape
	 */
	public Object getLikeliestShape(){
		
		int j; //used as an index for a for loop that is likely to be repeated multiple times
		int number; //used to represent the number of incidents with a particular shape

		//the ArrayList shapes is assigned a value by calling getShapes
		getShapes();
		
		//for every entry in the graphic entryset
		for (Map.Entry<String,Integer> mapping :graphic.entrySet()){
			
			//number is assigned as the mapping's value
			number=mapping.getValue();
				
			//for shapes size number of times 
			for(j=0; j<shapes.size();j++){
				
				//if the mapping's key equals index j of shapes then
					if (mapping.getKey().equals((shapes.get(j)))){

						//number is incremented by one
						number+=1;
						
						//number is mapped with the current mapping's key and put into graphic
						graphic.put(mapping.getKey(), number);
						
					}
					
			}   
					
		}
			
		Object max=null; //used to represent the likeliest shape in graphic
		
		//the maximum value in the map is worked out by calling the max method on graphic.values()
		int maxValueInMap=(Collections.max(graphic.values()));  
		
		//for every entry in the graphic entryset
		for (Entry<String, Integer> entry : graphic.entrySet()) {  
			
			//if the entry's value is the maximum value in the map then
			if (entry.getValue()==maxValueInMap) {
				
				//max is assigned to the entry's key
				max=entry.getKey();     
				
		    }
		}

		return max;
						
	}
			
	/**
	 * Returns the likeliest city (most commonly appearing) for the incidentsInRange ArrayList
	 * (incidentsInRangeis a field whose values is assigned in the constructor or 
	 * updated in updateDatesAndIncidents)
	 * @return the likeliest city
	 */
	public String getLikeliestCity(){
		
		//cities is initialised as a new TreeMap of types String and Integer
		cities=new TreeMap<String,Integer >();
		
		//tempCity is used to represent the current incident's city 
		String tempCity;
		
		//tempInt is used to represent the tempCity's value so far. It is initially 0.
		int tempInt=0;
		
		//a city called "Fresno" is put in the TreeMap with the value 0 to ensure that it will be in the TreeMap as it is later used when searching for the most likely city
		cities.put("Fresno", 0);
		
		//for each Incident in incidentsInRange
		for (Incident incident: incidentsInRange){
			
			//tempCity is assigned the value of the incident's city
			tempCity=incident.getCity();
			
			//if the tempCity is not "Not specified." then
			if (!(incident.getCity().equals("Not specified."))){
				
				//tempInt is assigned the value 0
				tempInt=0;
				
				//if tempCity is a key in cities and its value is not null then
				if (cities.get(tempCity)!=null){
					
					//tempInt is assigned the value of the value mapped to tempCity in cities 
					tempInt=cities.get(tempCity);
				
				}
				
				//tempCity is put in cities with the value of tempInt+1
				cities.put(tempCity, tempInt+1 );
		
			}
					
		} 
				
		//tempMostCity represents the city with the most sightings so far. Initially assigned as "Fresno", which is a key in cities
		String tempMostCity="Fresno";
		
		//tempMostValue is the highest number of sightings so far. Initially assigned as the value mapped to tempMostCity.
		int tempMostValue=cities.get(tempMostCity);
		
		//currentCity is the current city being looked at (initially "Fresno")
		String currentCity="Fresno";
		//currentValue is the value of the currentCity
		int currentValue=cities.get(currentCity);
		
		//for each entry in the cities entryset
		for (Map.Entry<String,Integer> mapping :cities.entrySet()){
			
			//currentCity is assigned as the key of mapping
			currentCity=mapping.getKey();
			
			//currentValue is assigned as the value of mapping
			currentValue=mapping.getValue();
					
			//if currentValue is greater than tempMostValue then
			if (currentValue>tempMostValue){
				
				//tempMostCity is assigned as the currentCity as currentCity has a higher sighting number than the previous tempMostCity
				tempMostCity=currentCity;
				
				//tempMostValue is assigned as currentValue as currentValue was greater
				tempMostValue=currentValue;
				
			}
				
		}
				
		return "Most likely city="+tempMostCity+" as it had "+ tempMostValue+" sightings during the given range";
				
					
				
	}
			
	
	/**
	 * Method returns the size of the ArrayList holding all cases with the chosen keywords.	
	 * @return number of incidents that refer to "military", "navy" or "airforce"
	 */
	public int getNumberOfIncidentsWhichReferencesTheMilitary() {		
	
		incidentsWithKeyWords = new ArrayList<Incident>();
						
		//For each incident in range
		for (Incident incident : incidentsInRange) {
			//look for: military, navy, or airforce.
			if (incident.getSummary().toLowerCase().contains("military") ||
				incident.getSummary().toLowerCase().contains("navy") ||
				incident.getSummary().toLowerCase().contains("airforce")) {
					
					//Add incidents with keywords to incidentsWithKeyWords ArrayList.
					incidentsWithKeyWords.add(incident);
			}	
		}
		//Return size of ArrayList containing the chosen incidents. Size gives us number of incidents.
		return incidentsWithKeyWords.size();
	}

	/**
	 * Returns number of results received when searching YouTube for "UFO", "Sightings" and "France" together published after startDate and before endDate
	 * @param startDate
	 * @param endDate
	 * @return number of results received
	 * @throws IOException: if connection could not be formed
	 */
	public int getViaOther(String startDate, String endDate) throws IOException{

		//startDate and endDate both have their spaces replaced with "T" and have ".000" added to them 
		//this is to ensure they are in te correct format
		startDate=startDate.replaceAll(" ", "T");
		startDate+=".000";
		endDate=endDate.replaceAll(" ", "T");
		endDate+=".000";
		
		//apiKey is used to access youtube api
		String apikey="AIzaSyAdPcgDYn0ST4HoP2vWsMCOH_Ra8sZYdW0";
		
		//the URL url is initially set as null
		URL url = null;
		
		//tries to get url in to the correct format using startDate, endDate and apiKey. Uses search and snippet from youtube api as well as setting correct publish date range
		try {
					
			url = new URL ("https://www.googleapis.com/youtube/v3/search?part=snippet&publishedBefore="+endDate+"Z&publishedAfter="+startDate+"Z&type=video&q=UFO+Sightings+France&key="+apikey);

		} catch (MalformedURLException e) {
				e.printStackTrace();
		}

		//connection is initialised with calling openConnection from url
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		
		//connection's request method is set as "GET"
		connection.setRequestMethod("GET");
		      
		//a new BufferedReader (brRead) is created with the parameter of a new InputStreamReader (with the parameter of the input stream from connection)
		BufferedReader brRead = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		//line will be used when reading lines from brRead
		String line;
		
		//new StringBuffer created
		StringBuffer response = new StringBuffer();
		
		//line is assigned as result from calling readLine() from brRead and while that is not null then
		while ((line=brRead.readLine())!=null){
		
			//response has line appended to it
			response.append(line);
		  
		}
		
		//brRead is closed
		brRead.close();
		
		//a new JSONObject is created with response's toString representation as the parameter
		JSONObject json = new JSONObject(response.toString());
		
		//number of total results returned
		return (json.getJSONObject("pageInfo").getInt("totalResults"));
	}


	/**
	 * Assigns values for each month count of incidents that occurred in them
	 */

	public void determineIncidentMonth(){
	
		//new Calendar is created
		Calendar cal_currentIncidentDateTime = Calendar.getInstance();	 
		
		//each month count is initialised as 0 to reset any previous values they had
		JanuaryCount=0;
		FebruaryCount=0;
		MarchCount=0;
		AprilCount = 0;
		MayCount = 0;
		JuneCount = 0;
		JulyCount = 0;
		AugustCount = 0;
		SeptemberCount = 0;
		OctoberCount = 0;
		NovemberCount = 0;
		DecemberCount = 0;
					
		//each season count is initialised as 0 to reset any previous values they had
		SpringCount = 0;
		SummerCount = 0;
		AutumnCount = 0;
		WinterCount = 0;
					
		//for all incidents....
		for (Incident incident: incidentsInRange){
						
			//store the current incident date and time in a variable. (Converted in Calendar format)
			cal_currentIncidentDateTime = dv.convertStringToCalendar(incident.getDateAndTime());		
			//converting the date and time of the current incident, to Calendar. 
					
			//Determine the incident month. Increment the number of incidents recorded per month.
			if (cal_currentIncidentDateTime.get(Calendar.MONTH) == 1){
				JanuaryCount++;
			}else if (cal_currentIncidentDateTime.get(Calendar.MONTH) == 2){
				FebruaryCount++;
			}else if (cal_currentIncidentDateTime.get(Calendar.MONTH) == 3){
				MarchCount++;
			}else if (cal_currentIncidentDateTime.get(Calendar.MONTH) == 4){
				AprilCount++;
			}else if (cal_currentIncidentDateTime.get(Calendar.MONTH) == 5){
				MayCount++;
			}else if (cal_currentIncidentDateTime.get(Calendar.MONTH) == 6){
				JuneCount++;
			}else if (cal_currentIncidentDateTime.get(Calendar.MONTH) == 7){
				JulyCount++;
			}else if (cal_currentIncidentDateTime.get(Calendar.MONTH) == 8){
				AugustCount++;
			}else if (cal_currentIncidentDateTime.get(Calendar.MONTH) == 9){
				SeptemberCount++;
			}else if (cal_currentIncidentDateTime.get(Calendar.MONTH) == 10){
				OctoberCount++;
			}else if (cal_currentIncidentDateTime.get(Calendar.MONTH) == 11){
				NovemberCount++;
			}else if (cal_currentIncidentDateTime.get(Calendar.MONTH) == 12){
				DecemberCount++;
			}
					
		}
	}
	
	/**
	 * Assigns values for each season count
	 */
	public void determineTotalIncidentSeasons(){
					
					SpringCount = FebruaryCount + MarchCount + AprilCount;
					SummerCount = MayCount + JuneCount + JulyCount;
					AutumnCount = AugustCount + SeptemberCount + OctoberCount;
					WinterCount = NovemberCount + DecemberCount + JanuaryCount;
					
	}
				
	/**
	 * Returns total incidents for each season
	 * @return  total incidents for each season as a string
	 */
	public String getSeasons(){
					
		return ("Spring Total: " + SpringCount + ". \n Summer Total: " + SummerCount + ". \n Autumn Total: " + AutumnCount + ". \n Winter Total: " + WinterCount+".");
							
	}
		
}
