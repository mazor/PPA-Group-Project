package surprise_view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class StartPanel extends DecorativePanel {

	private static final long serialVersionUID = 1L;
	private JButton startButton;
	private JLabel titleLabel;
	private JLabel introLabel;
    private JTextArea explanation;
    private JButton continueButton;



	public StartPanel() {
		super("/surprise_view/space-is-amazing.png");
		setPreferredSize(new Dimension(600, 600));
		setLayout(new BorderLayout());
        
		//Decorative alien label is prepared.
		DecorativeLabel alienLabel = new DecorativeLabel("src/surprise_view/fierce-alien.png", 150, 200);
		alienLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//Title and introduction messages are prepared.
		JPanel northPanel = new JPanel(new GridLayout(2, 1));
		northPanel.setOpaque(false);
		northPanel.setPreferredSize(new Dimension(600, 200));
		northPanel.add(titleLabel());
		northPanel.add(introLabel());
		
		//Continue button is prepared, but not added to the StartPanel.
		continueButton = new JButton("Continue");
		continueButton.setFont(new Font("ContinueButtonFont", Font.ITALIC + Font.BOLD, 20));
			
		//Title widgets (title and introductory message), and a decorative alien label are added to the StartPanel.
		add(northPanel, BorderLayout.NORTH);		
		add(startButton(), BorderLayout.CENTER);
        add(alienLabel, BorderLayout.SOUTH);
		}
		

/**
 * Method initialises a JLabel with the title of the game, Spaceship Escape.	
 * @return
 */
	private JLabel titleLabel() {	
		titleLabel = new JLabel("Spaceship Escape");
		titleLabel.setFont(new Font("TitleFont", Font.BOLD + Font.ITALIC, 27));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		return titleLabel;
	}
	
	
/**
 * Method creates a JLabel with an introductory message for the user.	
 * @return
 */
	private JLabel introLabel() {	
		introLabel = new JLabel("Welcome to Spaceship Escape! Ready to play?");
		introLabel.setFont(new Font("IntroFont", Font.BOLD + Font.ITALIC, 18));
		introLabel.setBackground(null);
		introLabel.setForeground(Color.WHITE);
		introLabel.setHorizontalAlignment(JLabel.CENTER);
		return introLabel;
	}
	
		
/**
 * Method initialises a start button. When pressed the start button removes the title
 * widgets and instead show the backstory for the game.
 * @return
 */
	private JButton startButton() {
		startButton = new JButton("START");
		startButton.setFont(new Font("ButtonFont", Font.BOLD, 20));
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setForeground(Color.WHITE);		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeAll();
				add(explanation(), BorderLayout.CENTER);
				add(continueButton, BorderLayout.SOUTH);
				repaint();
				revalidate();
			}			
		});		
		return startButton;
	}
	
	
/**
 * Method initialises a JTextArea which contains a short backstory for the game.	
 * @return
 */
	private JTextArea explanation() {
		explanation = new JTextArea();
		explanation.setFont(new Font("ExplanationFont", Font.ITALIC + Font.BOLD, 16));
		explanation.setOpaque(false);
		explanation.setForeground(Color.WHITE);
		explanation.append(" Welcome to Spaceship Escape, or should I say unwelcome?" + "\n");
		explanation.append(" You are here because you have been kidnapped by aliens!" + "\n");
		explanation.append(" They may have kidnapped you, but make no mistake, " + "\n");
		explanation.append(" they do not want you here, and you are far from welcome..." + "\n");
		explanation.append("\n");
		explanation.append("\n");
		explanation.append("\n");
		explanation.append(" The extra-terestials have heard of an earthly database" + "\n"); 
		explanation.append(" called Ripley, which humans use to study alien activity." + "\n");
		explanation.append(" The aliens are VERY offended. True, many of these" + "\n");
		explanation.append(" sightings are real." + "\n");
		explanation.append(" However, there is also a shameful amount of hoaxes." + "\n");
		explanation.append(" The saturnian community is outraged by these filthy lies," + "\n");
		explanation.append(" ignorance, they call it." + "\n");
		explanation.append(" A group of fanatic fundamentalists have gone so far as" + "\n");
		explanation.append(" to start kidnapping humans to educate them on these" + "\n");
		explanation.append(" matters. That is how, and why you are here." + "\n");
		explanation.append("\n");
		explanation.append("\n");
		explanation.append("\n");
		explanation.append(" The aliens will now test if you can tell the difference" + "\n");
		explanation.append(" between a hoax, and a real sighting." + "\n");
		explanation.append(" If you can, you will be allowed to return to mother earth." + "\n"); 
		explanation.append(" If not? Well...");
		return explanation;		
	}


/**
 * Method returns the continueButton.	
 * @return
 */
public JButton getContinueButton() {
	return continueButton;
}

	
}
