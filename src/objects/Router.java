package objects;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Main;
import game.TransformMatrix;
import game.Vector;

public class Router extends InternetComponent{
	
	public Router(int x, int y) {
		super(x,y);
	}
	
	public void render(Graphics2D g, TransformMatrix transform) {
		Vector transformedPosition = transform.transform(position);
		g.setColor(Main.BACKGROUND);
		g.fillRect(Math.round(transformedPosition.x - 10*transform.a), Math.round(transformedPosition.y - 10*transform.a), (int) (20*transform.a), (int) (20*transform.a));
		g.setColor(Color.WHITE);
		g.drawRect(Math.round(transformedPosition.x - 10*transform.a), Math.round(transformedPosition.y - 10*transform.a), (int) (20*transform.a), (int) (20*transform.a));
	}
}
