package surprise_view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import surprise_controller.ResetButtonListener;


/*
 * This class is a DecorativePanel which will be shown to the user if and when they win Spaceship Escape.
 */
public class WinPanel extends DecorativePanel {

	private static final long serialVersionUID = 1L;

	
	private ResetButtonListener controller;
	
	
	public WinPanel(ResetButtonListener controller) {
    	super("/surprise_view/mother-earth.png");
    	this.controller = controller;
    	setLayout(new BorderLayout());
    	add(youWin(), BorderLayout.NORTH);
    	add(resetButton(), BorderLayout.SOUTH);
    }

	
/**
 * Method initialises a JTextArea which informs the player that she has won the game.	
 * @return
 */
	private JTextArea youWin() {
		JTextArea youWin = new JTextArea();
		youWin.append("The aliens are pleasantly surprised with you." + "\n");
		youWin.append("You may return to your precious mother earth." + "\n");
		youWin.append("Have a nice trip home!" + "\n");
		youWin.append("You win.");	
		youWin.setFont(new Font("YouWinLabelFont", Font.BOLD + Font.ITALIC, 18));
		youWin.setOpaque(false);
		youWin.setForeground(Color.WHITE);
		return youWin;
	}

	
		
/**
 * Method initialises the reset button.	
 * @return
 */
	private JButton resetButton() {
		JButton resetButton = new JButton("CLICK HERE TO TRY AGAIN");
		resetButton.setContentAreaFilled(false);
		resetButton.setBorderPainted(false);
		resetButton.setForeground(Color.WHITE);
		resetButton.setFont(new Font("ResetButtonFont", Font.BOLD + Font.ITALIC, 16));
		resetButton.addActionListener(controller);
		return resetButton;
	}

}
