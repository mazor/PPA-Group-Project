package surprise_model;

/*
 * This class represents a Room which contains aliens.
 * An AlienRoom cannot be a final room. Also, an AlienRoom
 * does not lead to any other rooms.
 */

public class AlienRoom extends Room {

	public AlienRoom() {
		super(false, true);
	}

	
}
