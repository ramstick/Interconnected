package objects;

import java.awt.Color;
import java.awt.Graphics2D;

import game.TransformMatrix;
import game.Vector;

public class Connection {
	InternetComponent a, b;
	public Connection(InternetComponent a, InternetComponent b) {
		this.a = a;
		this.b = b;
	}
	
	public void render(Graphics2D g, TransformMatrix transform) {
		Vector transformedPositionA = transform.transform(a.position);
		Vector transformedPositionB = transform.transform(b.position);
		g.setColor(Color.WHITE);
		g.drawLine(Math.round(transformedPositionA.x), Math.round(transformedPositionA.y), Math.round(transformedPositionB.x), Math.round(transformedPositionB.y));
	}
}
