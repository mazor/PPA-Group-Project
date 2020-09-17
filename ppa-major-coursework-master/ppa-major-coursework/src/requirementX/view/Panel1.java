package requirementX.view;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import api.ripley.Ripley;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.concurrent.TimeUnit;


/*
 * This class is the first panel which is presented to the user upon opening the application.
 * Panel1 works as a welcome panel, and encourages the user to select a date.
 */

public class Panel1 extends JPanel {

	
	private static final long serialVersionUID = 1L;

	private Ripley ripley;
	private JPanel centrePanel;

	
	
	public Panel1() { 
	setLayout(new BorderLayout()); 
	setPreferredSize(new Dimension(800, 600));
    
	centrePanel = new JPanel(new GridLayout(4, 1));
	centrePanel.setOpaque(false);
	
	ripley = new Ripley("90tLI3CStdmyVD6ql2OMtA==", "lBgm4pRs8QnVqL46EnH7ew==");   
	
	add(introductionPanel(), BorderLayout.NORTH); 
	}

	
/**
  * This method updates the JTexFields and JPanels on Panel1.
  * This method will be called by the MainWindow when the values of the
  * JSliders change.
  */
	public void updatePanel1(String from, String to) {
    //The centre part of Panel1 needs a reset.
	centrePanel.removeAll();
	
	//Add first two widgets.
	centrePanel.add(dataSelected(from, to));
	centrePanel.add(grabbingData()); 	
	
	repaint();
	revalidate();
	
	//The current time is stored.
	long startTime = System.currentTimeMillis();
	//Data is grabbed, but not stored.
	ripley.getIncidentsInRange(from, to);
	
	//The total time it took to gather data is calculated.
	long timeTakenToGrabData = System.currentTimeMillis() - startTime;	
	
	//The time it took to grab the data is converted from milliseconds to minutes and seconds.
	int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(timeTakenToGrabData);	
	int secondsInMinutes = (int) TimeUnit.MINUTES.toSeconds(minutes);	
	int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(timeTakenToGrabData - secondsInMinutes);			
	
	//The remaining widgets are added.
	centrePanel.add(countdown(minutes, seconds));
	centrePanel.add(explanationPanel());
    add(centrePanel, BorderLayout.CENTER);
    
    repaint();
    revalidate();
	}
	

	
/**
 * This method creates a JPanel which welcomes the user to the program,
 * and displays the acknowledgement string from Ripley. 		
 * @return
 */
	private JPanel introductionPanel() {	
	   JPanel introductionPanel = new JPanel(new GridLayout(4, 1));      
	   
	   //Fetch acknowledgement string from the Ripley library.
	   String acknowledgmentString = ripley.getAcknowledgementString();
	   
	   //Create and design the four JTextAreas which are to be added to the JPanel.
	   JTextField textField1 = new JTextField("Welcome to Ripley v." + ripley.getVersion());
	   JTextField textField2 = new JTextField(acknowledgmentString);
	   JTextField textField3 = new JTextField("Please use the dates above to select a time period, ");
	   JTextField textField4 = new JTextField("in order to begin analysing UFO sighting data"); 
	   textField1.setHorizontalAlignment(JTextField.CENTER);
	   textField2.setHorizontalAlignment(JTextField.CENTER);
	   textField3.setHorizontalAlignment(JTextField.CENTER);
	   textField4.setHorizontalAlignment(JTextField.CENTER);  
	   textField1.setBackground(null);	   
	   textField2.setBackground(null);
	   textField3.setBackground(null);
	   textField4.setBackground(null);   
	   textField1.setBorder(null);
	   textField2.setBorder(null);
	   textField3.setBorder(null);
	   textField4.setBorder(null);
	   
	   //Add all JTextFields to the JPanel.
	   introductionPanel.add(textField1);
	   introductionPanel.add(textField2);
	   introductionPanel.add(textField3);
	   introductionPanel.add(textField4);
	
	   return introductionPanel;
	}
	

/**
 * This method creates a JPanel which encourages the user to start utilising
 * the program.		
 * @return
 */	
	private JPanel explanationPanel() {		
		JPanel explanationPanel = new JPanel(new GridLayout(2, 1));
		
		//Design JTextFields which are to be added to explanationPanel/
		JTextField textField1 = new JTextField("Please now interact with this data using the ");
		JTextField textField2 = new JTextField("buttons to the left and the right.");		    			
		textField1.setFont(new Font("BoldText", Font.BOLD, 12));
		textField2.setFont(new Font("BoldText", Font.BOLD, 12));		
		textField1.setHorizontalAlignment(JTextField.CENTER);
	    textField2.setHorizontalAlignment(JTextField.CENTER);
        textField1.setBackground(null);
	    textField2.setBackground(null);		    
	    textField1.setBorder(null);
	    textField2.setBorder(null);	
	    
	    explanationPanel.add(textField1);
	    explanationPanel.add(textField2);		    
	    return explanationPanel;
	}


	/**
	 * This method creates a JTextField which displays the time period the user has selected. 
	 * @return
	 */
	private JTextField dataSelected(String from, String to) {
	JTextField dataSelected = new JTextField("Data range selected, " + from + " - " + to + ".");
	dataSelected.setHorizontalAlignment(JTextField.CENTER);
	dataSelected.setBackground(null);
	dataSelected.setBorder(null); 
	return dataSelected;
	}

	
	/**
	 * This method creates a JLabel which displays the message: "Grabbing data..." 
	 * @return
	 */
	private JLabel grabbingData() {
	JLabel grabbingData = new JLabel("Grabbing data...");
	grabbingData.setHorizontalAlignment(JLabel.CENTER);
	grabbingData.setOpaque(false);
	return grabbingData;
	}
	
	
	/**
	 * This method creates a JTextField which informs
	 * the user how long it took to load the data from the database. 
	 * @return
	 */
	private JTextField countdown(int minutes, int seconds) {
	JTextField countdown = new JTextField("Data grabbed in " + minutes + " minutes, and " + seconds + " seconds.");
	countdown.setHorizontalAlignment(JTextField.CENTER);
	countdown.setBackground(null);
    countdown.setBorder(null);
	return countdown;
	}
	

}

