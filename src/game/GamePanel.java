package game;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.Timer;

import levels.Tutorial;
import objects.InternetComponent;
import renderer.Animation;
import renderer.Camera;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
/**
 * Holds the entire game, including the network, ide and other panels.
 * 
 * @author andrew
 *
 */

public class GamePanel extends JLayeredPane implements ActionListener, ComponentListener{
	
	NetworkPanel network;
	InfoPanel information;
	Game game;
	
	ConsoleButton consoleButton;
	ConsoleDisplay consoleDisplay;
	
	Timer t;
	
	InternetComponent focusedComponent;
	
	public GamePanel() {
		setLayout(null);
		game = new Game(new Tutorial());
		game.setGamePanel(this);
		
		consoleButton = new ConsoleButton(this);
		consoleDisplay = new ConsoleDisplay();
		
		network = new NetworkPanel(this);
		game.network = network;
		information = new InfoPanel();
		
		t = new Timer(14, this);
		t.start();
		

		add(network, 1);
		add(consoleButton, 0);
		add(information, 0);
		add(consoleDisplay, 0);
		network.setSize(300,300);
		addComponentListener(this);
		addKeyListener(network);
		requestFocus();
		information.hide();
	}
	
	public void enterAnimation(Animation a) {
		
	}
	
	public void showConsoleButton() {
		consoleButton.show();
	}
	
	public void hideConsoleButton() {
		consoleButton.hide();
	}

	public void showConsole() {
		consoleDisplay.show();
	}
	
	public void hideConsole() {
		consoleDisplay.hide();
	}
	
	public void toggleConsole() {
		consoleDisplay.toggle();
	}
	
	public void update() {
		consoleButton.update();
		information.update();
		consoleDisplay.update();
		repaint();
	}
	
	public void setFocusedComponent(InternetComponent component) {
		focusedComponent = component;
	}
	
	public InternetComponent getFocusedComponent() {
		return focusedComponent;
	}
	
	public Camera getCamera() {
		return network.camera;
	}
	
	public void actionPerformed(ActionEvent e) {
		update();
		game.updateLevel();
	}

	public void componentResized(ComponentEvent e) {
		network.setSize(getSize());		
		network.recalculateModelView();
		consoleDisplay.setSize(300, 300);
		GameSettings.HEIGHT = getHeight();
		GameSettings.WIDTH = getWidth();
	}

	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
