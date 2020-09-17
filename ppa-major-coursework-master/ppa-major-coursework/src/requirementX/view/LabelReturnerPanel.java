package requirementX.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import requirementX.view.StatisticsPanel.LeftButton;
import requirementX.view.StatisticsPanel.RightButton;

public class LabelReturnerPanel extends JPanel{

	private JLabel jlCenter; //represents the label that holds the information in the centre of the panel
	private JLabel jlTitle; //represents the label that holds the information in the title of the panel
	private JButton jbBack; //represents the button that allows a user to go back
	private JButton jbForward; //represents the button that allows a user to go forward

	
	/**
	 * Creates a new panel with a title, two buttons and a centre that says "Please enter dates"
	 * @param title: used to assign the data in jlTitle (the label used for the title)
	 */
	public LabelReturnerPanel(String title) {

		//jlTitle is initialised with the supplied parameter and aligned centre
		jlTitle=new JLabel(title, SwingConstants.CENTER);

		//the layout of the panel is set as a BorderLayout
		setLayout(new BorderLayout());
		
		//jlTitle is added to the north of the panel
		add(jlTitle, BorderLayout.NORTH);
		
		//jbBack is initialised and added to the west
		jbBack =new JButton("<");
		add(jbBack, BorderLayout.WEST);
		
		//jbForward is initialised and added to the east
		jbForward =new JButton(">");
		add(jbForward, BorderLayout.EAST);

		//jlCenter is initialised and aligned centre
		jlCenter = new JLabel("Please enter dates", SwingConstants.CENTER);
		//jlCenter is added to the centre
		add(jlCenter, BorderLayout.CENTER);

	}

	/**
	 * Sets text in jlCenter as the supplied String
	 * @param center
	 */
	public void setCenter(String center) {
		
		this.jlCenter.setText(center);
		
	}

	/**
	 * Adds ActionListeners
	 * @param leftButton: the ActionListener added to jbBack
	 * @param rightButton: the ActionListener added to jbForward
	 */
	public void addActionListeners( LeftButton leftButton, RightButton rightButton){

		jbBack.addActionListener(leftButton);
		jbForward.addActionListener(rightButton);
		
	}

	
	/**
	 * Sets text in jlTitle as the supplied string
	 * @param title
	 */
	public void setTitle(String title) {
		
		this.jlTitle.setText(title);
	
	}

	/**
	 * Returns jlTitle's text
	 * @return jlTitle's text (the title)
	 */
	public String getTitle(){
		
		return jlTitle.getText();

	}

	/**
	 * Returns jlCenter's text
	 * @return jlCenter's text 
	 */
	public String getCenter(){
		
		return jlCenter.getText();
		
	}
	
}
