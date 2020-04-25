package game;

import java.awt.Color;
import java.awt.Dimension;

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
	
	// 2373 lines of code lol
	public static void main(String[] args) {
		
		ResourceLoader.loadResources();
		
		JFrame frame = new JFrame("Interconnected");
		GamePanel gp = new GamePanel();
		
		frame.setContentPane(gp);
		
		frame.setMinimumSize(new Dimension(1000, 800));
		
		frame.setSize(1000,800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
