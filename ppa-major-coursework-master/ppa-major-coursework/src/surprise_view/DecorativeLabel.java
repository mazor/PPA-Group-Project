package surprise_view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/*
 * This class is used to easily create a JLabel whit a supplied image and size.
 */

public class DecorativeLabel extends JLabel {


	private static final long serialVersionUID = 1L;

	
/*
 * The constructor takes an image address, in string, and integers to 
 * determine the width and height of the image to be shown on the label.	
 */
	public DecorativeLabel(String imageAddress, int width, int height) {
		BufferedImage image = null;
	
	try {
	      image = ImageIO.read(new File(imageAddress));
	} catch (IOException e) {
		e.printStackTrace();
	}
	Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	ImageIcon finalImage = new ImageIcon(scaledImage);
	
	setIcon(finalImage);
	}
	
}
