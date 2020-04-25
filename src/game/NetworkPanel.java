package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import objects.*;
import renderer.Camera;
import renderer.CameraMode;
import renderer.ComponentAnimation;
import renderer.ComponentAnimationTypes;
import renderer.ComponentConnectionAnimation;
import renderer.ComponentSpawnAnimation;
import renderer.MatrixAnimation;
import renderer.TextRenderer;
import renderer.TransformMatrix;
import renderer.Vector;

import java.util.Scanner;
import java.awt.RenderingHints;

public class NetworkPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener, MouseWheelListener{
	
	Game game;
	
	ArrayList<ComponentAnimation> componentanimations;
	
	boolean dragging;
	int oldX, oldY;
	int mouseX, mouseY;


	Camera camera;
	
	TransformMatrix modelView;
	
	GamePanel gamePanel;
	
	TextRenderer textRenderer;

	int te;
	
	InternetComponent focusedComponent;
	
	public NetworkPanel(GamePanel game) {
		
		this.gamePanel = game;
		this.game = game.game;
		
		setBackground(GameSettings.BACKGROUND);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		
		componentanimations = new ArrayList<ComponentAnimation> ();
	
		
		

		camera = new Camera(CameraMode.FIXED, new TransformMatrix());
		camera.setAnimation(new MatrixAnimation(new TransformMatrix(.4f,.4f,0,0), new TransformMatrix(), 200));
		
		modelView = new TransformMatrix(1,-1,400, 300);
		
		dragging = false;
		
		textRenderer = this.game.textRenderer;
		
		setFont(new Font("sans", Font.PLAIN, 20));
		
		camera.enterAnimation(new TransformMatrix(5,5,0,0), 200);
		
		te = 0;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Calculate the transformation for this frame
		TransformMatrix cameraMatrix = camera.getCameraMatrix();
		camera.update();
		TransformMatrix transform = TransformMatrix.multiplyMatrix(modelView, cameraMatrix);
		
		g2d.setStroke(new BasicStroke(cameraMatrix.a));

		ArrayList<Connection> connections = game.getConnections();
		ArrayList<InternetComponent> components = game.getComponents();
		ArrayList<Packet> packets = game.getPackets();
		for(int i = 0; i < connections.size(); i ++) {
			connections.get(i).render(g2d, transform);
		}

		g2d.setStroke(new BasicStroke(transform.a * 2));

		for(int i = 0; i < components.size(); i ++) {
			InternetComponent ic = components.get(i);
			ic.render(g2d, transform, Color.white);
			
			if(!GameSettings.PACKETS_DISABLED && ic.updateCooldown() && ic.isConnected()) {
				ic.resetCooldown();
				packets.add(ic.createPacket("Lol"));
			}
		}
		
		for(int i = packets.size() - 1; i >= 0; i --) {
			packets.get(i).render(g2d, transform);
			if(packets.get(i).hasArrived()) {
				packets.remove(i);
			}
		}
		
		for(int i = componentanimations.size() - 1; i >= 0; i --) {
			ComponentAnimation animation = componentanimations.get(i);
			animation.render(g2d, transform);
			animation.update();
			if(animation.isFinished()) {
				if(animation.type == ComponentAnimationTypes.SPAWN) {
					components.add(animation.componentA);
				}else if(animation.type == ComponentAnimationTypes.CONNECT) {
					connections.add(animation.componentA.connect(animation.componentB));
				}
				componentanimations.remove(i);
			}
		}
		
		textRenderer.render(g2d, getWidth()/2, getHeight()/7);
		te ++;
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
		if(camera.isFree()) {
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
		Vector mouseRelative = TransformMatrix.multiplyMatrix(modelView, camera.getCameraMatrix()).getUntransformed(mx, my);

		
		
		InternetComponent index = game.getClosestComponent(mouseRelative, 400);
		if (index != null) {
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
		if (camera.isFree()) {
			Vector v = TransformMatrix.multiplyMatrix(modelView, camera.getCameraMatrix()).getUntransformed(mouseX, mouseY);
			float scale = (float) (Math.exp(e.getWheelRotation() * 0.01));
			camera.scale(scale, -0.1f * v.x, -0.1f * v.y);
		}

	}
	
	public void spawnComponent(InternetComponent component) {
		componentanimations.add(new ComponentSpawnAnimation(component, 200));
	}
	public void createConnection(InternetComponent s, InternetComponent e) {
		componentanimations.add(new ComponentConnectionAnimation(s, e, 200));
	}
	
	public void enterAnimationTo(TransformMatrix end, int duration) {
		camera.enterAnimation(end, duration);
	}
	
	public void recalculateModelView() {
		modelView = new TransformMatrix(1,-1,getWidth() / 2, getHeight() / 2);
	}
	
	public void setGame(Game g) {
		game = g;
	}
	
	public void focusOnComponent(InternetComponent component) {
		focusedComponent = component;
		enterAnimationTo(new TransformMatrix(5,5,-5 * component.position.x,-5 * component.position.y), 100);
	}
}
