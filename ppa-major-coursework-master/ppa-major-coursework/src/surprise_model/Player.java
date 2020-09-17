package surprise_model;

import java.util.Observable;


public class Player extends Observable {

	private int lives;
	private Room currentRoom;
	private Room firstRoom;
	private boolean hasBeenRestored;
	
	
	public Player(Room room) {
		lives = 3;
		this.currentRoom = room;
		this.firstRoom = room;
	}
	
/**
 * Method returns the number of lives the Player currently has.
 */
    public int getLives() {
    	return lives;
    }
    
  
/**
 * Method restores all of the Player's lives.    
 */
    public void refuelLife() {
    	lives = 3;
    	hasBeenRestored = true;
    }
    
/**
 * Method returns the Room the Player is currently in.
 */
    public Room getCurrentRoom() {
    	return currentRoom;
    }
      
    
/**
 * Method puts the Player into the first room on the spaceship.     
 */
    public void putInFirstRoom() {
    	currentRoom = firstRoom;
    	setChanged();
    	notifyObservers();
    }
    
   
/**
 * Method returns a boolean value indicating if the Player has been restored
 * to full health.
 */
    public boolean hasBeenRestored() {
    	return hasBeenRestored;
    }
   
    
/**
 * Method returns a boolean value indicating if the Player is dead or not.
 * The Player is considered dead if the number of lives are <= 0.
 */
    public boolean isPlayerDead() {
    	return (lives <= 0);
    }
    
    
/**
 * Method returns a boolean value indicating if the Room the Player is currently
 * in contains a hoax or a true sighting.
 */
    public boolean currentRoomContainsHoax() {
    	if (currentRoom instanceof DoorRoom) {
    		return ((DoorRoom)currentRoom).isHoax();
    	}
    	else return false;
    }


/**
 * Method returns a boolean value indicating if the Room the Player is currently in
 * is the last Room on the spaceship.    
 * @return
 */
    public boolean isInFinalRoom() {
    	return currentRoom.isLastRoom();
    }
    
    
/**
 * Method returns UFO sighting from the Player's currentRoom.
 */
     public String getUfoObservation() {
	 return ((DoorRoom)currentRoom).getUfoObservation();
     }
		
	
/**
 * Method changes the situation of the Player based on whether or not they guessed 
 * a UFO sighting correctly.	
 * @param isHoax
 */
	public void guessedHoax(boolean isHoax) {
		hasBeenRestored = false;
		if (currentRoom instanceof DoorRoom) {
			//If user guessed correctly...
			if (currentRoomContainsHoax() == isHoax) {
			    //then the user is moved into the next Room which is not an AlienRoom.
			    if (!(((DoorRoom)currentRoom).getRoom1() instanceof AlienRoom)) currentRoom = ((DoorRoom)currentRoom).getRoom1();
			    else {
			    	  currentRoom = ((DoorRoom)currentRoom).getRoom2(); 
			    }
			}
			//If the user guessed wrong.. 
			else {
				  lives--;
				  //then the Player looses one life.  
			}
		}
		setChanged();
		notifyObservers();
    }
	
}
