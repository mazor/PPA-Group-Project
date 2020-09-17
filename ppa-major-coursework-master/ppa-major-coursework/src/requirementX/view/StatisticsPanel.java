package requirementX.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JPanel;

import requirementX.model.StatisticValues;

/**
 * This class is used to present a series of statistics, based on the information from the class StatisticValues
 */
public class StatisticsPanel extends JPanel{
	
	private static final long serialVersionUID = 319449826459391494L;
	private ArrayList<LabelReturnerPanel> statPanels; //represents the JPanels that contain the data from supplied dates in the updatePanel method
	private ArrayList<LRPIndex> mainPanels; //represents the JPanels visible on the StatisticsPanel
	
	//both maxI and i used for the buttons
	private int maxI; //represents the highest index in statPanels
	private int i; //represents the current index
	
	private StatisticValues sv; //used to get all the values for each statistic

	/**
	 * The constructor initialises all fields and creates the panels that will be used for statistics 
	 */
	public StatisticsPanel(){

		//i is initialised as 0
		i=0;
		
		//sv is initialised with a start and end date
		sv=new StatisticValues("2015-01-01 12:12:12", "2016-01-01 12:12:12");
		
		//statPanels and mainPanels are initialised 
		statPanels=new ArrayList<LabelReturnerPanel>();
		mainPanels=new ArrayList<LRPIndex>();
		
		//layout is set as a grid of 2 rows and 2 columns
		setLayout(new GridLayout(2,2));

	
		//The 8 statistics are initialised and added to statPanels by calling initPanels
		initPanels();
		//maxI is initialised as the size of statPanels minus 1 to give the last index in that arraylist
		maxI=statPanels.size()-1;
		
		//the preference for statistics is loaded and saved into an ArrayList indexes
		ArrayList<Integer> indexes=loadPreference();

		//The mainPanels are initialised and added with the preferences from indexes for which statistic they show
		add(initTopLeft(indexes.get(0)));
		add(initTopRight(indexes.get(1)));
		add(initBottomLeft(indexes.get(2)));
		add(initBottomRight(indexes.get(3)));
		
	}
	
	/**
	 * The preferences (that represent what should be the current index value of each of the panels in mainPanels) are loaded from a file
	 * @return indexes: an ArrayList<Integer> which has the preferences inside
	 */
	private ArrayList<Integer> loadPreference(){
		
		//indexes is created and initialised as a new ArrayList
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		//the 4 following additions are done in case loading was not successful so that there is a default of the first 4 statistics in statPanels
		
		//the value 0 is added to index 0
		indexes.add(0,0);
		//the value 1 is added to index 1
		indexes.add(1,1);
		//the value 2 is added to index 2
		indexes.add(2,2);
		//the value 3 is added to index 3
		indexes.add(3,3);
	
		// Tries to load the file "preferences.sav" and assign indexes as the indexes saved there
		try{ 
			FileInputStream saveFile = new FileInputStream("preferences.sav");
			ObjectInputStream restore=new ObjectInputStream(saveFile);
			indexes= (ArrayList<Integer>) restore.readObject();
			restore.close();
		} catch (Exception exc){
			exc.printStackTrace();
		}
		return indexes;
	}
	 
	/**
	 * Preferences are saved as the values in the supplied ArrayList<Integer>
	 * @param indexes
	 */
	private void savePreference(ArrayList<Integer> indexes){
		
		// Tries to save indexes in the file "preferences.sav"
		try{
			FileOutputStream saveFile = new FileOutputStream("preferences.sav");
			ObjectOutputStream save=new ObjectOutputStream(saveFile);
			save.writeObject(indexes);
			save.close();
 
		} catch (Exception exc){
			exc.printStackTrace();
		}
	
	}
	
	/**
	 * The panel is updated using the supplied two Strings that represent the start and end date
	 * @param startDate: represents the start date
	 * @param endDate: represents the end date
	 * @return this StatisticsPanel
	 */
	public StatisticsPanel updatePanel(String startDate, String endDate) {

		//sv is updated with the supplied startDate and endDate
		sv.updateDatesAndIncidents(startDate,endDate);	

		//for each panel in statPanels the centre is set with the corresponding statistic value.
		/*
		 * index 0 - hoaxes
		 * index 1 - non US sightings number
		 * index 2 - Likeliest state (US)
		 * index 3 - sightings via other platform
		 * index 4 - likeliest city
		 * index 5 - likeliest shape
		 * index 6 - season values
		 * index 7 - military references 
		 * */
		statPanels.get(0).setCenter(""+sv.getHoaxInfo());
		statPanels.get(1).setCenter(""+sv.getNonUSInfo());
		statPanels.get(2).setCenter(""+sv.getLikeliestState());
		try {
				statPanels.get(3).setCenter("Number of results for searching for UFO Sightings France in Youtube: "+sv.getViaOther(startDate, endDate));
		} catch (IOException e1) {
				statPanels.get(3).setCenter("0"); //if sighting search was unsuccessful the centre is set as 0
		}
		statPanels.get(4).setCenter(""+sv.getLikeliestCity());
		statPanels.get(5).setCenter(""+sv.getLikeliestShape());
		
		//month and season counts updated in sv
		sv.determineIncidentMonth();
		sv.determineTotalIncidentSeasons();
		statPanels.get(6).setCenter(sv.getSeasons());
		statPanels.get(7).setCenter(""+sv.getNumberOfIncidentsWhichReferencesTheMilitary());
		
		LabelReturnerPanel currentPanel; //represents one of the mainPanels
		String title; //represents currentPanel's title
		
		//for mainPanel.size() number of times
		for (int i=0; i<mainPanels.size(); i++){
			
			//currentPanel assigned as a panel from mainPanels with the index i in the ArrayList 
			currentPanel=mainPanels.get(i);
			
			//title is assigned as currentPanel's title
			title=currentPanel.getTitle();
		
			//a switch statement is done based on title
			switch (title){
			
				//for each case the currentPanel's centre is set as the corresponding panel from statPanels' centre 
				case "Hoaxes": 
					currentPanel.setCenter(statPanels.get(0).getCenter());	
					break;
				case "Non US Sightings":
					currentPanel.setCenter(statPanels.get(1).getCenter());
					break;
				case "Likeliest State":
					currentPanel.setCenter(statPanels.get(2).getCenter());
					break;
				case "Sightings Via Other Platforms":
					currentPanel.setCenter(statPanels.get(3).getCenter());
					break;
				case "Likliest City":
					currentPanel.setCenter(statPanels.get(4).getCenter());
					break;
				case "Most Likely Shape":
					currentPanel.setCenter(statPanels.get(5).getCenter());
					break;
				case "Sightings Per Season":
					currentPanel.setCenter(statPanels.get(6).getCenter());
					break;
				case "Number of Sightings Mentioning The Military":
					currentPanel.setCenter(statPanels.get(7).getCenter());
					break;
				default: break;
			}
			
		}
		
		//a new ArrayList<Integer> is created to represent the preferences the user has
		ArrayList<Integer> indexes =new ArrayList<Integer>();

		//the indexes of the 4 mainPanels are added to indexes 
		indexes.add(mainPanels.get(0).getIndex());
		indexes.add(mainPanels.get(1).getIndex());
		indexes.add(mainPanels.get(2).getIndex());
		indexes.add(mainPanels.get(3).getIndex());
		
		//savePreference is called with indexes to save these preferences
		savePreference(indexes);
		
		//panel is revalidated and repainted
		revalidate();
		repaint();
		
		//the panel is returned
		return this;
	}

	/**
	 * each panel that needs to be in statPanels is initialised and added to statPanels 
	 */
	private void initPanels(){
		
		//jpHoaxes represents the panel that will show number of suspected hoaxes during a given range
		LabelReturnerPanel jpHoaxes= new LabelReturnerPanel("Hoaxes");
		jpHoaxes.setCenter("Please note it may take a while for data to load");
		statPanels.add(jpHoaxes);
		
		//jpNonUS represents the panel that will show number of non-US sightings during a given range
		LabelReturnerPanel jpNonUS = new LabelReturnerPanel("Non US Sightings");
		jpNonUS.setCenter("Please note it may take a while for data to load");
		statPanels.add(jpNonUS);
		
		//jpLikeliestState represents the panel that will show the state with the most sightings during a given range
		LabelReturnerPanel jpLikeliestState = new LabelReturnerPanel("Likeliest State");
		jpLikeliestState.setCenter("Please note it may take a while for data to load");
		statPanels.add(jpLikeliestState);
			
		//jpViaOther represents the panel that will show the number of results for searching for "UFO",
		//"Sightings" and "France" at the same time in YouTube during a given range
		LabelReturnerPanel jpViaOther = new LabelReturnerPanel("Sightings Via Other Platforms");
		jpViaOther.setCenter("Please note it may take a while for data to load");
		statPanels.add(jpViaOther);
		
		//jpLikelyCity represents the panel that will show the city with the most sightings
		LabelReturnerPanel jpLikelyCity = new LabelReturnerPanel("Likliest City");
		jpLikelyCity.setCenter("Please note it may take a while for data to load");
		statPanels.add(jpLikelyCity);
		
		//jpLikelShape represents the panel that will show the shape with the most sightings
		LabelReturnerPanel jpLikelyShape = new LabelReturnerPanel("Most Likely Shape");
		jpLikelyShape.setCenter("Please note it may take a while for data to load");
		statPanels.add(jpLikelyShape);
		
		//jpSeasons represents the panel that will show the total number of sightings per season
		LabelReturnerPanel jpSeasons = new LabelReturnerPanel("Sightings Per Season");
		jpSeasons.setCenter("Please note it may take a while for data to load");
		statPanels.add(jpSeasons);
		
		//jpMilitary represents the panel that will show the number of sightings that refer to the military
		LabelReturnerPanel jpMilitary = new LabelReturnerPanel("Number of Sightings Mentioning The Military");
		jpMilitary.setCenter("Please note it may take a while for data to load");
		statPanels.add(jpMilitary);
		
	}
	
	/**
	 * This initialises and returns the top left stat box
	 * @param index: the index the panel will initially have
	 * @return jpTopLeft: The top left stat box
	 */
	private LabelReturnerPanel initTopLeft(int index){
		
		//jpTopLeft is created as a new LRPIndex with the title that matches the title of the panel with the index 'index' in statPanels
		LRPIndex jpTopLeft=new LRPIndex(statPanels.get(index).getTitle(), index);
		//centre is set as the center of the panel in statPanels with the same index
		jpTopLeft.setCenter(statPanels.get(index).getCenter());
		
		//jpTopLeft is given new ActionListeners 
		jpTopLeft.addActionListeners(new LeftButton(jpTopLeft), new RightButton(jpTopLeft));
	
		//jpTopLeft is added to mainPanels
		mainPanels.add(jpTopLeft);
		
		return jpTopLeft;
		
	}

	/**
	 * This initialises and returns the top right stat box
	 * @param index: the index the panel will initially have
	 * @return jpTopLeft: The top right stat box
	 */
	private LabelReturnerPanel initTopRight(int index){
		
		//jpTopRight is created as a new LRPIndex with the title that matches the title of the panel with the index 'index' in statPanels
		LRPIndex jpTopRight=new LRPIndex(statPanels.get(index).getTitle(), index);
		//centre is set as the center of the panel in statPanels with the same index
		jpTopRight.setCenter(statPanels.get(index).getCenter());
		
		//jpTopRight is given new ActionListeners 
		jpTopRight.addActionListeners(new LeftButton(jpTopRight), new RightButton(jpTopRight));
	
		//jpTopRight is added to mainPanels
		mainPanels.add(jpTopRight);
		
		return jpTopRight;

	}
	
	/**
	 * This initialises and returns the bottom left stat box
	 * @param index: the index the panel will initially have
	 * @return jpTopLeft: The bottom left stat box
	 */
	private LabelReturnerPanel initBottomLeft(int index){
		
		//jpBottomLeft is created as a new LRPIndex with the title that matches the title of the panel with the index 'index' in statPanels
		LRPIndex jpBottomLeft=new LRPIndex(statPanels.get(index).getTitle(), index);
		//centre is set as the centre of the panel in statPanels with the same index
		jpBottomLeft.setCenter(statPanels.get(index).getCenter());
		
		//jpBottomLeft is given new ActionListeners 
		jpBottomLeft.addActionListeners(new LeftButton(jpBottomLeft), new RightButton(jpBottomLeft));
	
		//jpBottomLeft is added to mainPanels
		mainPanels.add(jpBottomLeft);
		
		return jpBottomLeft;
	
	}

	/**
	 * This initialises and returns the bottom right stat box
	 * @param index: the index the panel will initially have
	 * @return jpTopLeft: The bottom right stat box
	 */
	private LabelReturnerPanel initBottomRight(int index){
	
		//jpBottomRight is created as a new LRPIndex with the title that matches the title of the panel with the index 'index' in statPanels
		LRPIndex jpBottomRight =new LRPIndex(statPanels.get(index).getTitle(), index);
		//centre is set as the centre of the panel in statPanels with the same index
		jpBottomRight.setCenter(statPanels.get(index).getCenter());
		
		//jpBottomRight is given new ActionListeners 
		jpBottomRight.addActionListeners(new LeftButton(jpBottomRight), new RightButton(jpBottomRight));
	
		//jpBottomRight is added to mainPanels
		mainPanels.add(jpBottomRight);
		
		return jpBottomRight;
	
	}
	

	/**
	 * Used for the StatisticsPanel's left buttons to allow the ability to choose between different statistics
	 *  and ensure the same statistic does not show up more than once
	 */
	public class LeftButton implements ActionListener{
		
		private LRPIndex panelLeft; //represents the panel that will be changed to make it show the correct information
		
		/**
		 * Assigns a value for panelLeft 
		 * @param panelLeft: the panel that will be affected by this ActionListener
		 */
		public LeftButton(LRPIndex panelLeft){
			
			this.panelLeft=panelLeft;
			
		}
		
		/**
		 * Performs the action. 
		 * If i is greater than 0 then
		 * It will reassign the value of i until the panel from statPanels associated with it's information is not already in a mainPanels.
		 * Once an appropriate index has been found, the leftPanel's title and centre are updated to reflect the new information they should hold.
		 * This information is retrieved from the panel with the index i in statPanels 
		 */
		public void actionPerformed(ActionEvent e) {

			//if i is greater than 0
			if (i>0){
				
				//i is decreased by one
				i--;
				
				//while panel at index i from statPanels is not already being used 
				while ((statPanels.get(i).getTitle().equals(mainPanels.get(0).getTitle()))
						||(statPanels.get(i).getTitle().equals(mainPanels.get(1).getTitle()))
						||(statPanels.get(i).getTitle().equals(mainPanels.get(2).getTitle()))
						||(statPanels.get(i).getTitle().equals(mainPanels.get(3).getTitle()))){
					
							//if i is less than or equal to 0 then set i as maxI, otherwise decrease i by one
							if (i<=0){
								i=maxI;
							} else i--;
				}

				//panelLeft's title, centre and index are updated using i. 
				//The centre and title are updated using the panel with index i from statPanels
				//the index is assigned as i
				panelLeft.setTitle(statPanels.get(i).getTitle());
				panelLeft.setCenter(statPanels.get(i).getCenter());
				panelLeft.setIndex(i);
				
				//a new ArrayList of Integers is created (indexes)
				ArrayList<Integer> indexes= new ArrayList<Integer>();
				
				//for a mainPanels size number of times 
				for (int j=0; j<mainPanels.size(); j++){
					
					//the panel with index j in mainPanels is added to index
					indexes.add(mainPanels.get(j).getIndex());
					
				}
				
				//user preferences are saved
				savePreference(indexes);

				//panel is revalidated and repainted
				revalidate();
				repaint();
			
			}
		}

	}
	
	/**
	 * Used for the StatisticsPanel's right buttons to allow the ability to choose between different statistics
	 *  and ensure the same statistic does not show up more than once
	 */
	public class RightButton implements ActionListener{
		
			private LRPIndex panelRight; //represents the panel that will be changed to make it show the correct information

			/**
			 * Assigns a value for panelRight 
			 * @param panelRight: the panel that will be affected by this ActionListener
			 */
			public RightButton(LRPIndex panelRight){
				
				this.panelRight=panelRight;

			}
			
			/**
			 * Performs the action. 
			 * If i is not maxI then
			 * It will reassign the value of i until the panel from statPanels associated with it's information is not already in a mainPanels.
			 * Once an appropriate index has been found, the rightPanel's title and centre are updated to reflect the new information they should hold.
			 * This information is retrieved from the panel with the index i in statPanels 
			 */
			public void actionPerformed(ActionEvent e) { 
				
				//if maxI is not equal to i
				if (maxI!=i)	{
					
					//i is incremented by one
					i++;
					
					//while panel at index i from statPanels is not already being used 
					while ((statPanels.get(i).getTitle().equals(mainPanels.get(0).getTitle()))
							||(statPanels.get(i).getTitle().equals(mainPanels.get(1).getTitle()))
							||(statPanels.get(i).getTitle().equals(mainPanels.get(2).getTitle()))
							||(statPanels.get(i).getTitle().equals(mainPanels.get(3).getTitle()))){
						
								//if i equals maxI
								if (i==maxI){
									//i is assigned as 0
									i=0;
								} else i++; //otherwise it is incremented by one
					}
					
					//panelRight's title, centre and index are updated using i. 
					//The centre and title are updated using the panel with index i from statPanels
					//the index is assigned as i
					panelRight.setTitle(statPanels.get(i).getTitle()); 
					panelRight.setCenter(statPanels.get(i).getCenter());
					panelRight.setIndex(i); 
					
					//a new ArrayList of Integers is created (indexes)
					ArrayList<Integer> indexes= new ArrayList<Integer>();
					
					//for a mainPanels size number of times 
					for (int j=0; j<mainPanels.size(); j++){
						
						//the panel with index j in mainPanels is added to index
						indexes.add(mainPanels.get(j).getIndex());
						
					}
					
					//user preferences are saved
					savePreference(indexes);

					//panel is revalidated and repainted
					revalidate();
					repaint();
				
				}
		
		}
			
	}	
	
}


