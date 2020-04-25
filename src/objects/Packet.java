package objects;

import java.awt.Graphics2D;

import renderer.TransformMatrix;
import renderer.Vector;

public class Packet {
	private InternetComponent source, end;
	private String data;
	
	private InternetComponent nextDestination;
	
	private Vector position;
	
	private boolean arrived;
	
	public Packet(InternetComponent source, InternetComponent end, String data) {
		this.source = source;
		this.end = end;
		this.data = data;
		nextDestination = end;
		
		Vector v = source.position.directional(nextDestination.position);
		v.multiply(10);
		v.add(source.position);
		position = new Vector(v);
		
		arrived = false;
	}
	
	public void render(Graphics2D g,TransformMatrix camera) {
		
		if(nextDestination != null && !arrived) {
			Vector v = position.directional(nextDestination.position);
			v.multiply(.1f);
			position.add(v);
			arrived = position.distSquared(end.position) < 100;
		}
		
		
		Vector transformed = camera.transform(position);
		
		g.fillOval((int) transformed.x - 10, (int) transformed.y - 10, 20, 20);
	}
	
	public void route(InternetComponent next) {
		nextDestination = next;
	}
	
	public String toString() {
		return "TO - "+source+"\nFOR - "+end+"\nDATA - \n"+data;
	}
	
	public boolean hasArrived() {
		return arrived;
	}
}
