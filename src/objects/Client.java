package objects;

import java.awt.Color;
import java.awt.Graphics2D;

import game.GameSettings;
import game.Main;
import renderer.TransformMatrix;
import renderer.Vector;

public class Client extends InternetComponent{
	public Client(int x, int y, IPAddress address) {
		super(x,y,address);
	}
	
	public void render(Graphics2D g, TransformMatrix transform, Color c) {
		Vector transformedPosition = transform.transform(position);
		g.setColor(GameSettings.BACKGROUND);
		g.fillOval(Math.round(transformedPosition.x - 10 * transform.a) , Math.round(transformedPosition.y - 10 * transform.a), Math.round(20 * transform.a), Math.round(20 * transform.a));
		g.setColor(c);
		g.drawOval(Math.round(transformedPosition.x - 10 * transform.a), Math.round(transformedPosition.y - 10 * transform.a), Math.round(20 * transform.a), Math.round(20 * transform.a));
	}
	
	public void render(Graphics2D g, TransformMatrix transform, Color c, float s) {
		Vector transformedPosition = transform.transform(position);
		g.setColor(GameSettings.BACKGROUND);
		g.fillOval(Math.round(transformedPosition.x - 10 * s * transform.a) , Math.round(transformedPosition.y - 10 * s * transform.a), Math.round(20 * s * transform.a), Math.round(20 * s * transform.a));
		g.setColor(c);
		g.drawOval(Math.round(transformedPosition.x - 10 * s * transform.a), Math.round(transformedPosition.y - 10 * s * transform.a), Math.round(20 * s * transform.a), Math.round(20 * s * transform.a));
	}
}
