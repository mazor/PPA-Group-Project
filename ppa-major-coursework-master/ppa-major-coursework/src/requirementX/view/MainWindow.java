package requirementX.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import api.ripley.Ripley;
import requirementX.Mapp;
import requirementX.Surprise;
import requirementX.model.Model;
import requirementX.view.MainWindow;
import requirementX.view.Panel1;
import requirementX.view.StatisticsPanel;
import requirementX.model.DateValidation;
import requirementX.model.ListOfSightings;

public class MainWindow extends JFrame{
	
		private DateValidation dateVal; //used for validating date
		private JSpinner jsFrom; //used to represent the selection for a start date
		private JSpinner jsTo;//used to represent the selection for an end date
		private Panel1 panel1; //represents the introduction panel
		private StatisticsPanel stats; //represents the statistics panel
		private Mapp map; //represents the US map
		private JPanel surprisePanel; //represents the surprise panel (a game)
		private Ripley ripley; //used to get acknowledgement String and last updated 
		private static final long serialVersionUID = 898813246359564715L;
		private int i; //used to represent an index in the ArrayList of panels in initWidgits and the ActionListener inner classes
		private ListOfSightings los;
		private JButton jbPrevious; //jbPrevious represents the button that makes the previous panel appear
		private JButton jbNext; //jbNext represents the button that makes the next panel appear
		private Model model; //used for map
		
		/**
		 * The constructor calls initWidgits()
		 */
		public MainWindow(){
			
			initWidgits();
			
		}
		
		/**
		 * Initialises the Mainwindow
		 */
		private void initWidgits(){

			//ArrayList panels will be used to represent the JPanels that the buttons navigate through
			ArrayList<JPanel> panels = new ArrayList<JPanel>();
			
			//ripley is initialised
			ripley = new Ripley("90tLI3CStdmyVD6ql2OMtA==", "lBgm4pRs8QnVqL46EnH7ew==");
			
			//acknowledgement String is output
			System.out.println(ripley.getAcknowledgementString());
			
			//panel1 is initialised
			panel1 = new Panel1();

			//a new Surprise class is created
			Surprise surprise = new Surprise();
			
			//surprise panel is initialised as the result of calling getGame from surprise
			surprisePanel = surprise.getGame();
			
			//stats is initialised
			stats = new StatisticsPanel();
			
			//model is initialsied
			model = new Model();
			
			//add, getStateName and getStates called from model
			model.add();
			model.getStateName("2015-12-12 12:12:12", "2016-12-12 12:12:12");
			model.getStates();
			los = new ListOfSightings(model);
			los.getTreeMap();
			//instantiates the State/Incident TMap  //calls sort sightings. 
			map = new Mapp(model,los);
			
			//panel1, map, stats and surprisePanel are added to panels
			panels.add(panel1);
			panels.add(map);
			panels.add(stats);
			panels.add(surprisePanel);
			
			//default close operation is set
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//i is initialised as 0
			i = 0;
			
			//layout is made into a BorderLayout
			getContentPane().setLayout(new BorderLayout());
			
			//new JPanels are created for the centre and south	
			JPanel jpCenter = new JPanel();
			JPanel jpSouth = new JPanel();

			//jpCenter is made to have a BorderLayout
			jpCenter.setLayout(new BorderLayout());

			//jsFrom and jsTo initialised with new SpinnerDateModels
			jsFrom = new JSpinner(new SpinnerDateModel());	
			jsTo = new JSpinner(new SpinnerDateModel());
			
			//dateVal initialised
			dateVal = new DateValidation(this);	
			
			//jsFrom and jsTo have changeListeners added to them
			jsFrom.addChangeListener(new DateChangeListener(this, dateVal));
			jsTo.addChangeListener(new DateChangeListener(this, dateVal));
			
	
			//jbPrevious initialised and has an ActionListener added to it
			jbPrevious = new JButton("<");
			jbPrevious.addActionListener(new ActionListener() {

				/**
				 * When the button is clicked
				 */
				@Override
				public void actionPerformed(ActionEvent e) {
					//if i is greater than 0 (if there is a panel to its left)
					if (i>0)	{
						
						//i is decreased by one
						i--;
						
						//jpCenter has the current panel removed from it
						jpCenter.remove(panels.get(i+1));
						//jpCenter has the new panel added to it (in the centre)
						jpCenter.add(panels.get(i), BorderLayout.CENTER);
				
						//pack is called from MainWindow
						pack();

					}

				}
				
			});
			
			//jbPrevious enabled set as false
			jbPrevious.setEnabled(false);
			
			//jbNext initialised and has an ActionListener added to it
			jbNext = new JButton(">");
			jbNext.addActionListener(new ActionListener() {

				/**
				 * When the button is clicked
				 */
				@Override
				public void actionPerformed(ActionEvent e) { 
					
					//if panels size is not equal to i+1 then 
					if (panels.size()!=(i+1))	{
						
						//increment i by one
						i++;
						
						//jpCenter has the current panel removed from it
						jpCenter.remove(panels.get(i-1));
						//jpCenter has the new panel added to it (in the centre)
						jpCenter.add(panels.get(i), BorderLayout.CENTER);
			
						pack();

					}				
				}
			});

			//jbNext enabled set as false
			jbNext.setEnabled(false);
			
			//jpCenter has the panel at index i in panels added to it
			jpCenter.add(panels.get(i),BorderLayout.CENTER);

			
			//jpNorth is initialised and added to the north
			getContentPane().add(initNorth(), BorderLayout.NORTH);
			//jpSouth and jpCenter added to south and centre respectively
			getContentPane().add(jpSouth, BorderLayout.SOUTH);
			getContentPane().add(jpCenter, BorderLayout.CENTER);
			
			//jlLastSeen created and initialised as a JLabel with the text that is the result of calling getLastUpdated from ripley
			JLabel jlLastSeen = new JLabel (ripley.getLastUpdated());
			
			//jbPrevious, jlLastSeen and jbNext added to jpSouth
			jpSouth.add(jbPrevious);
			jpSouth.add(jlLastSeen);
			jpSouth.add(jbNext);

			//pack called from MainWindow
			pack();
			
		}
		
		/**
		 * This method initialises and returns the panel that goes in the north of MainWindow 
		 * @return
		 */
		private JPanel initNorth(){
			
			//jpNorth created and initialised
			JPanel jpNorth = new JPanel(); 
			//jpNorth is given a FlowLayout
			jpNorth.setLayout(new FlowLayout(FlowLayout.RIGHT)); 
			
			//labels and the JSpinners added to jpNorth
			jpNorth.add(new JLabel("From:"));
			jpNorth.add(jsFrom);
			jpNorth.add(new JLabel("To:"));	
			jpNorth.add(jsTo);
			
			//jpNorth is returned
			return jpNorth;
			
		}	
		
		/**
		 * Updates panel1
		 * @param from: the start date
		 * @param to: the end date
		 */
		public void updatePanel1(String from, String to) {

			panel1.updatePanel1(from, to);
			
		}
		
		/**
		 * Updates stats
		 * @param from: the start date
		 * @param to: the end date
		 */
		public void updateStatistics(String from, String to) {
		
			stats.updatePanel(from, to);

		}
		/**
		 * Updates model
		 * @param from: the start date
		 * @param to: the end date
		 */
		public void updateModel(String from, String to) {
		
			model.updateModel(from, to);

		}
		
		
		/**
		 * Returns date from jsFrom 
		 * @return date from jsFrom 
		 */
		public Date getFromDate(){
		
			return (Date) jsFrom.getValue();	
			
		}
		
		/**
		 * Returns date from jsTo 
		 * @return date from jsTo 
		 */
		public Date getToDate(){

			return (Date) jsTo.getValue();
			
		}
		

		/**
		 * Converts the date from jsFrom to String
		 * @return the date from jsFrom as a String in the Ripley format
		 */
		public String fromDateString(){
			
			return dateVal.convertDateToRipleyString(getFromDate());
					
		}
		
		/**
		 * Converts the date from jsTo to String
		 * @return the date from jsTo as a String in the Ripley format
		 */
		public String toDateString(){
			
			return dateVal.convertDateToRipleyString(getToDate());
			
		}	
		
		/**
		 * Returns dateVal
		 * @return dateVal
		 */
		public DateValidation getDateVal(){
			
			return dateVal;
			
		}


		/**
		 * Used to check dates from MainWindow and update MainWindow accordingly every time date is changed
		 */
		class DateChangeListener implements ChangeListener {

			private static final long serialVersionUID = 1L;
			private MainWindow mainWindow; 
			private DateValidation dateValidation;
			private JPanel output;	
			
			/**
			 * Assigns values for mainWindow and dateValidation from the supplied parameters. The JPanel output is initialised.
			 * @param mainWindow
			 * @param dateValidation
			 */
			public DateChangeListener(MainWindow mainWindow, DateValidation dateValidation){

				this.mainWindow = mainWindow;
				this.dateValidation=dateValidation;
				output = new JPanel();
				
			}

			/**
			 * Whenever a change is made
			 */
			@Override
			public void stateChanged(ChangeEvent userChangeDate) {

				//if the dates are valid (to date is after from date) and the span between them is not too large (less than 50 years)
				if ((dateValidation.spanFine() && dateValidation.orderFine())){
					
					//panel1, map and statistics are updated
					mainWindow.updatePanel1(mainWindow.fromDateString(), mainWindow.toDateString());
					mainWindow.updateModel(mainWindow.fromDateString(), mainWindow.toDateString());
					mainWindow.updateStatistics(mainWindow.fromDateString(),mainWindow.toDateString());
					
					//buttons are enabled
					jbPrevious.setEnabled(true);
					jbNext.setEnabled(true);
					
					//pack is called from mainWindow
					mainWindow.pack();
					
				}else{ //otherwise
			
					//if the dates were more than 50 years apart
					if (!dateValidation.spanFine()){

						//dialogue  shown with an error message
						JOptionPane.showMessageDialog(output, "Please input dates less than 50 years apart", "Error", JOptionPane.ERROR_MESSAGE);
						
					}
					
					//if to date is earlier than from date
					if (!dateValidation.orderFine()){

						//dialogue  shown with an error message
						JOptionPane.showMessageDialog(output, "Please ensure your from date does not exceed your to date", "Error", JOptionPane.ERROR_MESSAGE);
							
					}
					
				}

			}
			
		}
		
}
