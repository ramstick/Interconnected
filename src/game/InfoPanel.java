package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import renderer.IntegerAnimation;

public class InfoPanel extends JPanel{
	
	int x = 0;
	IntegerAnimation animation;
	
	public InfoPanel() {
		setSize(200,400);
		setLocation(x, 100);
		setBackground(GameSettings.MENU_OVERLAY_COLOR);
		x = -200;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void update() {
		if(animation != null) {
			x = animation.getNow();
			animation.update();
			if(animation.isFinished()) {
				animation = null;
			}
			setLocation(x, 100);
		}
	}
	
	public void show() {
		animation = new IntegerAnimation(x,0,100);
	}
	
	public void hide() {
		animation = new IntegerAnimation(x,-200,100);
	}
}
