package surprise_model;


/*
 * This class represents a generic Room.
 */

public class Room {

	
	private boolean isFinalRoom;
	private boolean hasAlien;
	
	
	public Room(boolean isFinalRoom, boolean isAlienRoom) {
		this.isFinalRoom = isFinalRoom;
	}
	

/**
 * Method returns a boolean value indicating if this Room is the last 
 * Room on the spaceship or not.
 * @return
 */
	public boolean isLastRoom() {
		return isFinalRoom;
	}

/**
 * Method returns a boolean value indicating if this room contains
 * an alien.	
 * @return
 */
	public boolean hasAlien() {
		return hasAlien;
	}

}