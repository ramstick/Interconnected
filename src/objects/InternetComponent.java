package objects;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.TransformMatrix;
import game.Vector;

public class InternetComponent {

	public Vector position;
	ArrayList<InternetComponent> connections;
	
	public InternetComponent(int x, int y) {
		position = new Vector(x,y);
		connections = new ArrayList<InternetComponent> ();
	}
	
	public void render(Graphics2D g, TransformMatrix transform) {
		
	}
	
	public Connection connect(InternetComponent b) {
		connections.add(b);
		b.connections.add(this);
		return new Connection(this,b);
	}
}
