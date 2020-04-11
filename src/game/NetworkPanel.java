package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.awt.event.MouseWheelEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import objects.Client;
import objects.Connection;
import objects.InternetComponent;
import objects.Router;
import java.util.Scanner;

enum CameraMode {
	FREE, ANIMATION, FIXED
}

public class NetworkPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener, MouseWheelListener, ActionListener{

	ArrayList<InternetComponent> components;
	ArrayList<Connection> connections;

	boolean dragging;
	int oldX, oldY;
	int mouseX, mouseY;

	float scale = 1;

	Timer t;

	CameraMode cameraMode;
	TransformMatrix camera;
	MatrixAnimation animation;
	
	TransformMatrix modelView;

	public NetworkPanel() {
		setBackground(Main.BACKGROUND);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);

		components = new ArrayList<InternetComponent> ();
		connections = new ArrayList<Connection> ();
		
		
		// Hard Coded stuff
		components.add(new Router(-200,-100));
		components.add(new Router(100,-100));
		components.add(new Client(300,-100));
		components.add(new Client(300,0));
		components.add(new Client(300,-200));

		connections.add(components.get(0).connect(components.get(1)));
		connections.add(components.get(1).connect(components.get(2)));
		connections.add(components.get(1).connect(components.get(3)));
		connections.add(components.get(1).connect(components.get(4)));

		t = new Timer(14, this);
		t.start();

		cameraMode = CameraMode.FREE;
		camera = new TransformMatrix();
		animation = null;
		
		modelView = new TransformMatrix(1,-1,400, 300);

		dragging = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;


		g2d.setStroke(new BasicStroke(camera.a));
		

		if(CameraMode.ANIMATION == cameraMode) {
			camera = animation.getTransform();
			animation.update();
			if(animation.isFinished()) {
				cameraMode = CameraMode.FREE;
				animation = null;
			}
		}
		// Caculate the tranformation for this frame
		TransformMatrix transform = TransformMatrix.multiplyMatrix(modelView, camera);

		for(int i = 0; i < connections.size(); i ++) {
			connections.get(i).render(g2d, transform);
		}

		g2d.setStroke(new BasicStroke(transform.a * 2));

		for(int i = 0; i < components.size(); i ++) {
			components.get(i).render(g2d, transform);
		}

		g2d.setColor(Color.BLACK);
		drawGrid(g2d, 0, 0, transform);
	}
	/**
	 * Draw a grid, what did you expect
	 * 
	 * @param g - Graphics2D context
	 * @param centerX - the center of the grid
	 * @param centerY - the center of the grid
	 * @param transform - the current camera transform.
	 */
	public void drawGrid(Graphics2D g, float centerX, float centerY, TransformMatrix transform) {
		Vector orgin = transform.transform(new Vector(centerX, centerY));
		Vector xaxis = transform.transform(new Vector(centerX + 300,centerY));
		Vector yaxis = transform.transform(new Vector(centerX, centerY + 300));
		Vector negxaxis = transform.transform(new Vector(centerX - 300,centerY));
		Vector negyaxis = transform.transform(new Vector(centerX, centerY - 300));

		g.drawLine((int) orgin.x, (int) orgin.y, (int) xaxis.x, (int) xaxis.y);
		g.drawLine((int) orgin.x, (int) orgin.y, (int) yaxis.x, (int) yaxis.y);
		g.drawLine((int) orgin.x, (int) orgin.y, (int) negxaxis.x, (int) negxaxis.y);
		g.drawLine((int) orgin.x, (int) orgin.y, (int) negyaxis.x, (int) negyaxis.y);
	}


	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_T) {
			Scanner in = new Scanner(System.in);
			System.out.print("Transform matrix:\nA = ");
			float a = in.nextFloat();
			System.out.print("B = ");
			float b = in.nextFloat();
			System.out.print("C = ");
			float c = in.nextFloat();
			System.out.print("D = ");
			float d = in.nextFloat();
			enterAnimationTo(new TransformMatrix(a,b,c,d), 200);
		}
	}
	public void keyReleased(KeyEvent e) {}

	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if(cameraMode == CameraMode.FREE) {
			if(dragging) {
				camera.translate(mouseX - oldX, -(mouseY - oldY));

				oldX = mouseX;
				oldY = mouseY;
			}
		}
	}
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}


	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		int mx = e.getX(), my = e.getY();
		Vector mouseRelative = TransformMatrix.multiplyMatrix(modelView, camera).getUntransformed(mx, my);

		InternetComponent index = null;
		float dist = 100000000;
		for(int i = 0; i < components.size(); i ++) {
			InternetComponent component = components.get(i);
			float d = component.position.distSquared(mouseRelative);
			if(d < dist) {
				index = component;
				dist = d;
			}
		}
		System.out.println("("+mouseRelative.x+", "+mouseRelative.y+")");
		System.out.println(dist);
		if (dist < 400) {;
			enterAnimationTo(TransformMatrix.multiplyMatrix(TransformMatrix.scaleMatrix(5),TransformMatrix.translationMatrix(-index.position.x, -index.position.y)), 100);
		}else {
			oldX = mx;
			oldY = my;
			dragging = true;
		}
	}
	public void mouseReleased(MouseEvent e) {
		if(dragging) {
			dragging = false;
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if (cameraMode == CameraMode.FREE) {
			Vector v = TransformMatrix.multiplyMatrix(modelView, camera).getUntransformed(mouseX, mouseY);
			float scale = (float) (Math.exp(e.getWheelRotation() * 0.01));
			camera.scale(scale, -0.1f * v.x, -0.1f * v.y);
			camera.a = Math.max(Math.min(camera.a, 10), 1);
			camera.b = Math.max(Math.min(camera.b, 10), 1);
		}

	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void enterAnimationTo(TransformMatrix end, int duration) {
		System.out.println(end.a +" 0 "+end.c);
		System.out.println("0 "+end.b+" "+end.d);
		animation = new MatrixAnimation(camera, end, duration);
		cameraMode = CameraMode.ANIMATION;
	}
}
