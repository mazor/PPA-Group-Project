package requirementX;

import javax.swing.JPanel;
import surprise_controller.GamePanelController;
import surprise_controller.ResetButtonListener;
import surprise_model.AlienRoom;
import surprise_model.DoorRoom;
import surprise_model.Player;
import surprise_model.Room;
import surprise_view.GamePanel;
import surprise_view.SpaceshipEscape;
import surprise_view.StartPanel;


/*
 * This class hold variables for all the different parts of the surprise part of the application.
 * By making an object of this class, it is possible to return a JPanel from which the game can be played.
 */

public class Surprise {


	private SpaceshipEscape game;
		
	
	public Surprise() {
	
		//All the rooms on the Spaceship.
		Room finalRoom = new Room(true, false);
		AlienRoom alienRoom = new AlienRoom();
		DoorRoom room10 = new DoorRoom(finalRoom, alienRoom, "We had a close incounter, then heard a really loud noise and saw a UFO.", true);
		DoorRoom room9 = new DoorRoom(alienRoom, room10, "Large bright light near Arcturus in the sky visibly as bright as sun.", false);
		DoorRoom room8 = new DoorRoom(alienRoom, room9, "Long, cone-shaped, object with white, red and green lights seen by 2 people.", false);
		DoorRoom room7 = new DoorRoom(room8, alienRoom, "Was searching for evidence of Bigfoot, experienced possible abduction.", true);
		DoorRoom room6 = new DoorRoom(alienRoom, room7, "Sighting of V-shaped UFO less then 100 from my deck.", true);
		DoorRoom room5 = new DoorRoom(room6, alienRoom, "Hovering object above tree line, definitely not a plane or drone, very large.", false);
		DoorRoom room4 = new DoorRoom(room5, alienRoom, "Vary fast streak across sky 25degrees of sky in 1:2 sec.", true);
		DoorRoom room3 = new DoorRoom(alienRoom, room4, "3 flying objects resembling shooting stars, low flying at constant speed.", false);
		DoorRoom room2 = new DoorRoom(alienRoom, room3, "A SHOOTING BALL OF FIRE DOWNTOWN SOUTH BEND ON FRIDAY NIGHT AT 11:30PM.", false);
		DoorRoom room1 = new DoorRoom(room2, alienRoom, "It looked like a star but cruised like a helicopter.", false);
		DoorRoom room0 = new DoorRoom(alienRoom, room1, "Followed by ufo.", true);
		
		//The Player.
		Player player = new Player(room0);   
	    
		//StartPanel
		StartPanel startPanel = new StartPanel();  
		
		//Controller for the GamePanel.
		GamePanelController gamePanelController = new GamePanelController(player);
		ResetButtonListener resetButtonListener = new ResetButtonListener(player);
		
		//GamePanel.
		GamePanel gamePanel = new GamePanel(player, gamePanelController, resetButtonListener);
		
		//GamePanel is added to GamePanelController.
		gamePanelController.addGamePanel(gamePanel);
		
		//GamePanel is added as an observer of the Player.
		player.addObserver(gamePanel);
		
		//The GamePanel and the StartPanel are added to the SpaceshipEscape class.
		game = new SpaceshipEscape(gamePanel, startPanel);
}
	

/**
 * Method returns the JPanel game. The Spaceship Escape game is accessed trough this JPanel.	
 * @return
 */
	public JPanel getGame() {
		return game;
	}
	
	
}
