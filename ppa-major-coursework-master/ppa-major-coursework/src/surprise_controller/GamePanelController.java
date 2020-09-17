package surprise_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import surprise_model.Player;
import surprise_view.GamePanel;

/*
 * This class is an ActionListener for the GamePanel. When changes happen in the
 * GamePanel which effect the Player, this class makes the Player update itself.
 */


public class GamePanelController implements ActionListener {

	
	private Player player;
	private GamePanel gamePanel;
	
	
	
	public GamePanelController(Player player) {
		this.player = player;
	}

/**
 * Method adds a GamePanel to the GamePanelController.	
 * @param gamePanel
 */
	public void addGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	
/**
 * When the user guesses if a sighting is a hoax or not by clicking a
 * button on the GamePanel, this method tells the Player to change based 
 * on the answer given by the user.	
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		player.guessedHoax(gamePanel.isHoax());
	}
	
}
