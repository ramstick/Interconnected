package game;

import java.awt.Color;

import javax.swing.JFrame;

import ide.ProgramPanel;
/**
 * This one literally just contains the main method.
 * Thats literally it
 * 
 * @author andrew
 *
 */
public class Main {
	
	public static Color BACKGROUND = new Color(245, 91, 214);
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Interconnected");
		NetworkPanel np = new NetworkPanel();
		//ProgramPanel pp = new ProgramPanel();
		frame.add(np);
		//frame.add(pp);
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(np);

	}

}
