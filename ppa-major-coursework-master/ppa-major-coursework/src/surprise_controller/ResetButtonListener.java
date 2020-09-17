package surprise_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import surprise_model.Player;


/*
 * This class is an ActionListener for the reset buttons on the LoosePanel,
 * and the WinPanel.
 */


public class ResetButtonListener implements ActionListener {

	
	private Player player;
	
	
	public ResetButtonListener(Player player) {
		this.player = player;
	}
	
	
/**
 * Method orders Player to restore all its lives and put itself in the
 * first room of the game.	
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		player.refuelLife();
		player.putInFirstRoom();		
	}

	
	
	
}
