package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import quartz.VirtualMachine;
import renderer.TransformMatrix;
import renderer.Vector;

public class InternetComponent {

	public IPAddress address;
	public Vector position;
	public int packetCooldown;
	ArrayList<InternetComponent> connections;

	VirtualMachine machine;
	
	public InternetComponent(int x, int y, IPAddress address) {
		position = new Vector(x,y);
		connections = new ArrayList<InternetComponent> ();
		this.address = address;
		resetCooldown();
	}

	public void render(Graphics2D g, TransformMatrix transform, Color c) {

	}

	public void render(Graphics2D g, TransformMatrix transform, Color c, float scale) {
		render(g,transform, c);
	}

	public Connection connect(InternetComponent b) {
		connections.add(b);
		b.connections.add(this);
		return new Connection(this,b);
	}
	
	public boolean updateCooldown() {
		return  0 > packetCooldown--;
	}
	
	public String toString() {
		return address.toString();
	}
	
	public void resetCooldown() {
		packetCooldown = (int) (300 * Math.random()) + 200;
	}
	
	public boolean isConnected() {
		return !connections.isEmpty();
	}
	
	public Packet createPacket(String s) {
		return new Packet(this, connections.get((int) (connections.size() * Math.random())), s);
	}
	
	public VirtualMachine getVirtualMachine() {
		return machine;
	}
}
