package game;

import java.util.ArrayList;

import renderer.Camera;
import renderer.TextRenderer;
import renderer.Vector;

import levels.Level;
import objects.*;

public class Game {
	ArrayList<InternetComponent> components;
	ArrayList<Connection> connections;
	ArrayList<Packet> packets;
	
	Level currentLevel;
	
	TextRenderer textRenderer;
	
	NetworkPanel network;
	
	GamePanel gamePanel;
	
	int t;
	
	public Game(Level level) {
		components = new ArrayList<InternetComponent>();
		connections  = new ArrayList<Connection>();
		packets = new ArrayList<Packet>();
		
		currentLevel = level;
		
		textRenderer = new TextRenderer();
	
		t = 0;
	}
	
	public void setGamePanel(GamePanel gp) {
		gamePanel = gp;
	}
	
	public void showConsoleButton() {
		gamePanel.showConsoleButton();
	}
	
	public void updateLevel() {
		if(currentLevel != null) {
			currentLevel.update(this);
			t ++;
		}
	}
	
	public int getT() {
		return t;
	}
	
	public TextRenderer getTextRenderer() {
		return textRenderer;
	}
	
	public NetworkPanel getNetwork() {
		return network;
	}
	
	public void addComponent(InternetComponent component) {
		components.add(component);
	}
	
	public void addConnection(Connection connection) {
		connections.add(connection);
	}
	
	public void addPacket(Packet packet) {
		packets.add(packet);
	}
	
	public ArrayList<InternetComponent> getComponents(){
		return components;
	}
	
	public ArrayList<Connection> getConnections(){
		return connections;
	}
	
	public ArrayList<Packet> getPackets(){
		return packets;
	}
	
	public InternetComponent getClosestComponent(Vector position) {
		InternetComponent index = null;
		float dist = Float.MAX_VALUE;
		for(int i = 0; i < components.size(); i ++) {
			InternetComponent component = components.get(i);
			float d = component.position.distSquared(position);
			if(d < dist) {
				index = component;
				dist = d;
			}
		}
		
		return index;
	}
	
	public Camera getCamera() {
		return gamePanel.getCamera();
	}
	
	public InternetComponent getClosestComponent(Vector position, float threshold) {
		InternetComponent index = null;
		float dist = Float.MAX_VALUE;
		for(int i = 0; i < components.size(); i ++) {
			InternetComponent component = components.get(i);
			float d = component.position.distSquared(position);
			if(d < dist) {
				index = component;
				dist = d;
			}
		}
		if(dist < threshold) {
			return index;
		}
		return null;
	}
}
