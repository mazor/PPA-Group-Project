package surprise_model;

/*
 * A DoorRoom is a type of Room which contains two other Room, and an alien sighting.
 */

public class DoorRoom extends Room {

	private Room nextRoom1;
	private Room nextRoom2;
	private String ufoObservation;
	private boolean isHoax;

	
	public DoorRoom(Room nextRoom1, Room nextRoom2, String alienSighting, boolean isHoax) {
		super(false, false);
		this.nextRoom1 = nextRoom1;
		this.nextRoom2 = nextRoom2;
		this.ufoObservation = alienSighting;
		this.isHoax = isHoax;
		
	}

/**
 * Method returns the first Room inside the DoorRoom.	
 * @return
 */
	public Room getRoom1() {
		return nextRoom1;
	}
	
	
/**
 * Method returns the second Room inside the DoorRoom.	
 * @return
 */
	public Room getRoom2() {
		return nextRoom2;
	}
	

/**
 * Method returns the UFO sighting from the DoorRoom.	
 * @return
 */
	public String getUfoObservation() {
		return ufoObservation;	
    }
	
	
/**
 * Method returns a boolean value indicating if the sighting in this DoorRoom 
 * is a hoax or not.	
 * @return
 */
	public boolean isHoax() {
		return isHoax;
	}
	
}
