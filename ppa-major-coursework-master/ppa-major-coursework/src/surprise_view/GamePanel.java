package surprise_view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import surprise_controller.GamePanelController;
import surprise_controller.ResetButtonListener;
import surprise_model.Player;


/*
 * This class is the panel for the main game. While playing (not at the start)
 * this is the panel which the user will see.
 */
public class GamePanel extends JPanel implements Observer {


	private static final long serialVersionUID = 1L;

	
	private JButton hoaxButton;
	private JButton notHoaxButton;		
	private JButton continueButton;
	
	private JPanel floor;
	private JPanel wall;
	
	private Player player;	
		
	private boolean isHoax;
	
	private WinPanel winPanel;
	private LoosePanel loosePanel;
	

	
	public GamePanel(Player player, GamePanelController gamePanelController, ResetButtonListener resetController) {	
		this.player = player;	
		setLayout(new GridLayout(3, 1));
		
		//Initialise buttons.
		notHoaxButton = new JButton("Not a hoax");
		hoaxButton = new JButton("Hoax");
		continueButton = new JButton("Continue");
		
		//Add ActionListener to continueButton.
		continueButton.addActionListener(gamePanelController);
		
		//Initialise WinPanel and LoosePanel.
		winPanel = new WinPanel(resetController);
		loosePanel = new LoosePanel(resetController);
	
		//Initialise wall and floor.
		floor = new JPanel(new FlowLayout(FlowLayout.CENTER, 140, 70));		
		wall = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, -40));
		
		//Set up floor and wall panels.
		floor();
		wall(door(), door());
		
		//Add widgets to GamePanel.
		add(roof());
		add(wall);
		add(floor);
	}
	
	
/**
 * Method returns a boolean value indicating if the Player guessed a sighting 
 * was a hoax or not.	
 * @return
 */
	public boolean isHoax() {
		return isHoax;
	}
	
	
/**
 * Method initialises the JPanel which is added to the northern part of the GamePanel.
 * The JPanel contains information about the UFO sighting in the currentRoom and
 * the number of lives the Player has left.	
 * @param descriptionOfSighting
 * @return
 */
	private JPanel roof() {
		JPanel roof = new JPanel(new GridLayout(2, 1));
		roof.setBackground(Color.BLUE);	
		
		//JLabel with UFO sighting.
		JLabel description = new JLabel(player.getUfoObservation());
		description.setHorizontalAlignment(JLabel.HORIZONTAL);
		description.setFont(new Font("DescriptionFont", Font.BOLD, 12));
		description.setForeground(Color.WHITE);	
		roof.add(description);
		
		//JLabel with Player's remaining lives.
		JLabel livesLeft = new JLabel("Lives: " + player.getLives());
		livesLeft.setHorizontalAlignment(JLabel.HORIZONTAL);
		livesLeft.setFont(new Font("LivesLeftFont", Font.BOLD, 16));
		livesLeft.setForeground(Color.WHITE);	
		roof.add(livesLeft);
		
		return roof;
	}
	
	
/**
 * Method changes the floor panel after the user has guessed if a sighting was
 * a hoax or not.	
 */
	private void changedFloor() {
		floor.removeAll();
		floor.add(continueButton);
		floor.repaint();
		floor.revalidate();
	}
	
	
/**
 * Method designs the JPanel which is added to the southern part of the GamePanel, to represent the floor.
 * This panel has two buttons which allow the user to guess if a sighting is a hoax or not.	
 * @return
 */
	private void floor() {
		floor.removeAll();
        floor.setBackground(Color.BLACK);		
		floor.add(hoaxButton);
		floor.add(notHoaxButton);
		
		//ActionListener for the hoaxButton.
		hoaxButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isHoax = true;	
				//If user guessed correctly...
				if (player.currentRoomContainsHoax()) {
						   //Then there is no alien behind the door.
					       wall(alienDoor(false), door());
					}
				//If the user guessed incorrectly,
				//then there is an alien behind the door.
				else {wall(alienDoor(true), door());}	
             changedFloor();
			}
			}
		);				
		
		//ActionListener for the notAHoaxButton.
		notHoaxButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isHoax = false;
				//If user guessed correctly...
				if (!player.currentRoomContainsHoax()) {
					//Then there is no alien behind the door.
					wall(door(), alienDoor(false));
				}
				//If the user guessed incorrectly,
				//then there is an alien behind the door.
				else {wall(door(), alienDoor(true));}
			changedFloor();
			}        	
        }); 
	}
	
	
	
	
/**
 * Method returns a JPanel which looks like a door with an alien warning sign.	
 * @param iWannaBeADoor
 * @return
 */
	private JPanel door() {
		 JPanel iWannaBeADoor = new JPanel(new BorderLayout());
		 iWannaBeADoor.setPreferredSize(new Dimension(100, 300));
		 iWannaBeADoor.setBackground(Color.LIGHT_GRAY);
		 //The panel is given a doorknob and an alien warning sign.
		 DecorativeLabel doorknob = new DecorativeLabel("src/surprise_view/Doorknob.png", 20, 20);
		 DecorativeLabel alienWarning = new DecorativeLabel("src/surprise_view/aliens-ahead.png", 80, 80);				 
		 iWannaBeADoor.add(alienWarning, BorderLayout.CENTER);
		 iWannaBeADoor.add(doorknob, BorderLayout.EAST);
		 return iWannaBeADoor;
		}
		
	
	
/**
 * Method return a DecorativePanel. The method takes a boolean value as input. If the input is true,
 * then a label containing an angry, fierce alien will be added to the panel.	
 * @return
 */
	private DecorativePanel alienDoor(boolean alienBehindDoor) {
		DecorativePanel alienDoor = new DecorativePanel("behind-door.png");
		alienDoor.setPreferredSize(new Dimension(100, 300));
		alienDoor.setLayout(new BorderLayout());
		//If there is an alien behind the door, a label of an alien will be added to the panel.
		if (alienBehindDoor) {
		          DecorativeLabel scaryAlienLabel = new DecorativeLabel("src/surprise_view/fierce-alien.png", 150, 150);
		          alienDoor.add(scaryAlienLabel, BorderLayout.CENTER);
		}
		return alienDoor;		
	}
	
	
	
	
/**
 * Method changes the wall panel based on the two JPanels it takes as input. The
 * supplied JPanels become the two doors.	
 * @param leftDoor
 * @param rightDoor
 */
		private void wall(JPanel leftDoor, JPanel rightDoor) {
			wall.removeAll();
			wall.setBackground(Color.BLUE);		
			wall.add(leftDoor);
			wall.add(rightDoor);
			repaint();
			revalidate();
		}

		
		
		
/**
 * Method updates the GamePanel based on the situation of the game. If the Player is dead, the
 * LoosePanel is shown. If the Player is in the final room of the spaceship, then the WinPanel 
 * is shown. If none of those are the case, then the game is still going and the GamePanel 
 * simply updates its doors.
 */
	@Override
	public void update(Observable o, Object arg) {
	//If Player is dead...
		if (player.isPlayerDead()) {
			//Show looser panel.
			removeAll();
			setLayout(new BorderLayout());
			add(loosePanel, BorderLayout.CENTER);
			repaint();
			revalidate();
		}
	//If Player has won...	
		else if (player.isInFinalRoom()) {
			//Show winner panel.
			removeAll();
			setLayout(new BorderLayout());
			add(winPanel, BorderLayout.CENTER);
			repaint();
			revalidate();
		}
	//If none of the above..	
		else {
			  //Keep playing.
			  removeAll();
			  setLayout(new GridLayout(3, 1));
			  wall(door(), door());
			  floor();
			  
		      add(roof());
			  add(wall);
			  add(floor);
			  repaint();
			  revalidate();
		}
	}
	
	}
