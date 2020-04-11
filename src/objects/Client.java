package objects;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Main;
import game.TransformMatrix;
import game.Vector;

public class Client extends InternetComponent{
	public Client(int x, int y) {
		super(x,y);
	}
	
	public void render(Graphics2D g, TransformMatrix transform) {
		Vector transformedPosition = transform.transform(position);
		g.setColor(Main.BACKGROUND);
		g.fillOval(Math.round(transformedPosition.x - 10 * transform.a) , Math.round(transformedPosition.y - 10 * transform.a), Math.round(20 * transform.a), Math.round(20 * transform.a));
		g.setColor(Color.WHITE);
		g.drawOval(Math.round(transformedPosition.x - 10 * transform.a), Math.round(transformedPosition.y - 10 * transform.a), Math.round(20 * transform.a), Math.round(20 * transform.a));
	}
}
