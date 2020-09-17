package requirementX;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Sorted {
	JComboBox<String> listStrings;
	
	JPanel panel;
	JFrame frame1;
	
/**
 * Method creates a JComboBox.
 */
	public void setSorted() {
	listStrings = new JComboBox<String>();
	listStrings.addItem("-");
	listStrings.addItem("Date");
	listStrings.addItem("City");
	listStrings.addItem("Shape");
	listStrings.addItem("Duration");
	listStrings.addItem("Posted");
	}
	
/**
 * Method creates a JPanel which contains listStrings.
 */
	public void setStuff() {
	panel = new JPanel();
	panel.setLayout(new BorderLayout());
	panel.add(listStrings, BorderLayout.NORTH);
	panel.setSize(200, 200);
	frame1= new JFrame();
	frame1.add(panel);
	frame1.setLayout(new BorderLayout());
	frame1.setSize(200, 200);
	frame1.setVisible(true);
	
	}
   class Listner implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		
		
	}
	   
   }
	
	
	public static void main(String[] args) {
		Sorted sorted = new Sorted();
		sorted.setSorted();
		sorted.setStuff();
		
		
	}
	
}
