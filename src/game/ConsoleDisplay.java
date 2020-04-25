package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import quartz.VirtualMachine;
import renderer.Animation;
import renderer.FloatAnimation;
import renderer.IntegerAnimation;
/**
 * @author andrew
 *	
 *A Panel that contains console output.
 */
public class ConsoleDisplay extends JPanel{
	
	VirtualMachine machine;
	
	boolean opened;
	float y;
	FloatAnimation animation;
	
	InnerConsoleDisplay consoleDisplay;
	
	public ConsoleDisplay () {
		setSize(300,300);
		setLocation(500,600);
		y = 0;
		opened = false;
		setBackground(GameSettings.MENU_OVERLAY_COLOR);
		
		setLayout(new BorderLayout());
		
		consoleDisplay = new InnerConsoleDisplay();
		
		setBorder(new EmptyBorder(10,10,10,10));
		
		add(consoleDisplay);
	}
	
	/**
	 * Updates the animation the console is in.
	 */
	
	public void update() {
		if(animation != null) {
			y = animation.getNow();
			animation.update();
			if(animation.isFinished()) {
				animation = null;
			}
		}
		setLocation(GameSettings.WIDTH - getWidth() - 30, GameSettings.HEIGHT - ((int) (y * getHeight())));
		consoleDisplay.repaint();
	}
	
	/**
	 * Sets the machine
	 * 
	 * @param machine Machine to output the console of.
	 */
	
	public void setMachine(VirtualMachine machine) {
		this.machine = machine;
	}
	
	/**
	 * Shows the console.
	 */
	
	public void show() {
		opened = true;
		animation = new FloatAnimation(y,1,50);
	}
	
	/**
	 * Hides the console.
	 */
	
	public void hide() {
		opened = false;
		animation = new FloatAnimation(y,0,50);
	}
	
	/**
	 * Toggles the state of the console
	 */
	
	public void toggle() {
		if(opened) {
			hide();
		}else {
			show();
		}
	}
}

class InnerConsoleDisplay extends JScrollPane {
	
	public InnerConsoleDisplay() {
		add(new JLabel("Yay"));
	}
	
}
