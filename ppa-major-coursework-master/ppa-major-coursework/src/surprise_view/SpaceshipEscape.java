package surprise_view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import surprise_model.Player;


/*
 * This class is the JPanel which hold all the other JPanels involved in the
 * Spaceship Escape game.
 */

public class SpaceshipEscape extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel;
	private StartPanel startPanel;
	
	
	public SpaceshipEscape(GamePanel gamePanel, StartPanel startPanel) {
		this.gamePanel = gamePanel;		
		this.startPanel = startPanel;
		setPreferredSize(new Dimension(600, 600));
		setLayout(new BorderLayout());
		addListenerToContinueButton();
		//At the beginning of the game, the StartPanel is shown to the user.
		add(startPanel, BorderLayout.CENTER);		
	}

	
/**
 * Method adds an ActionListener to continueButton from StartPanel. When continueButton
 * is clicked, the StartPanel will be replaced by the GamePanel.
 */
	public void addListenerToContinueButton() {
		//When the continueButton is clicked SpaceshipEscape will replace the StartPanel with the GamePanel.
		startPanel.getContinueButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(gamePanel, BorderLayout.CENTER);
				repaint();
				revalidate();
			}		
		});
	}
	

/**
 * Method restarts the game when the resetButton is pressed. 	
 */
@Override
public void update(Observable arg0, Object arg1) {
	if (((Player)arg0).hasBeenRestored()) {
		removeAll();
		add(startPanel, BorderLayout.CENTER);
	}
	
}	
	
}
