package surprise_view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import surprise_controller.ResetButtonListener;


/*
 * This class is a DecorativePanel which will be shown to the user if and when they loose Spaceship Escape.
 */

public class LoosePanel extends DecorativePanel {

	
	private static final long serialVersionUID = 1L;

	private ResetButtonListener controller;

	
	public LoosePanel(ResetButtonListener controller) {
		super("/surprise_view/it-s-probing-time.png");
		this.controller = controller;
		setLayout(new BorderLayout());
		JPanel northPanel = new JPanel(new GridLayout(2, 1));
		northPanel.setOpaque(false);
		northPanel.add(youLooseLabel());
		northPanel.add(resetButton());
		add(northPanel, BorderLayout.NORTH);
	}
	

	
/**
 * Method creates a JLabel which informs the user that she has lost.
 * @return
 */
	private JLabel youLooseLabel() {
		JLabel youLooseLabel = new JLabel("The aliens are thoroughly offended. You loose.");
		youLooseLabel.setHorizontalAlignment(JLabel.HORIZONTAL);
		youLooseLabel.setFont(new Font("LooseLabelFont", Font.BOLD + Font.ITALIC, 18));
		youLooseLabel.setOpaque(false);
		youLooseLabel.setForeground(Color.WHITE);
		return youLooseLabel;
	}
	
	
/**
 * Method creates the reset button. The button is given the ActionListener ResetButtonListener.
 * When the button is clicked the game restarts.  	
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
