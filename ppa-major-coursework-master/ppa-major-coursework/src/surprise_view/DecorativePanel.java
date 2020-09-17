package surprise_view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/*
 * This class allows us to easily create a JPanel with a decorative background image.
 */

public class DecorativePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private String imageAddress;	
	

/**
 * When creating an object of this class it must be given an address to a background image.	
 * @param imageAddress
 */
	public DecorativePanel(String imageAddress) {
		this.imageAddress = imageAddress;
	}
	

/**
 * We override the paintComponent method to paint an image of our choosing to the
 * background of the panel.
 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		URL resource = getClass().getResource(imageAddress);
		BufferedImage backgroundImage = null;
		try { 
			backgroundImage = ImageIO.read(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		Image image = backgroundImage.getScaledInstance(600, 600, Image.SCALE_DEFAULT);
		g.drawImage(image, 0, 0, this);
	}
	
}
