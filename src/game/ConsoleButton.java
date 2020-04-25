package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import renderer.IntegerAnimation;

public class ConsoleButton extends JButton implements MouseListener, ActionListener{
	
	int x = 0;
	IntegerAnimation animation;
	GamePanel gamePanel;
	Polygon boundaryShape;
	
	public ConsoleButton (GamePanel gamePanel) {
		super("Console");
		this.gamePanel = gamePanel;
		setSize(148,64);
		x = -148;
		setLocation(x, 20);
	
		int [] xPoints = {0,128,148,128,0};
		int [] yPoints = {0,0,32,64,64};
		
		boundaryShape = new Polygon(xPoints, yPoints, 5);

		addMouseListener(this);
		addActionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(GameSettings.MENU_OVERLAY_COLOR);
		g2d.fillPolygon(boundaryShape);
		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(4,BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		g2d.drawLine(74, 20, 84, 32);
		g2d.drawLine(74, 44, 84, 32);
		g2d.drawLine(89, 44, 109, 44);
	}
	
	public void update() {
		if(animation != null) {
			x = animation.getNow();
			animation.update();
			if(animation.isFinished()) {
				animation = null;
			}
			setLocation(x, 20);
		}
	}
	
	public void show() {
		animation = new IntegerAnimation(x,-20,100);
	}
	
	public void hide() {
		animation = new IntegerAnimation(x,-148,100);
	}

	public void mouseClicked(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {
		animation = new IntegerAnimation(x,0,20);
	}

	public void mouseExited(MouseEvent e) {
		animation = new IntegerAnimation(x,-20,20);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("CONSOLE OPENED");
		gamePanel.toggleConsole();
	}
}
