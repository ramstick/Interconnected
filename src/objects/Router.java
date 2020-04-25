package objects;

import java.awt.Color;
import java.awt.Graphics2D;

import game.GameSettings;
import game.Main;
import renderer.TransformMatrix;
import renderer.Vector;

public class Router extends InternetComponent{
	
	public Router(int x, int y, IPAddress address) {
		super(x,y, address);
	}
	
	public void render(Graphics2D g, TransformMatrix transform, Color c) {
		Vector transformedPosition = transform.transform(position);
		g.setColor(GameSettings.BACKGROUND);
		g.fillRect(Math.round(transformedPosition.x - 10*transform.a), Math.round(transformedPosition.y - 10*transform.a), (int) (20*transform.a), (int) (20*transform.a));
		g.setColor(c);
		g.drawRect(Math.round(transformedPosition.x - 10*transform.a), Math.round(transformedPosition.y - 10*transform.a), (int) (20*transform.a), (int) (20*transform.a));
	}
	
	public void render(Graphics2D g, TransformMatrix transform, Color c, float scale) {
		Vector transformedPosition = transform.transform(position);
		g.setColor(GameSettings.BACKGROUND);
		g.fillRect(Math.round(transformedPosition.x - 10*scale*transform.a), Math.round(transformedPosition.y - 10*scale*transform.a), (int) (20*scale*transform.a), (int) (20*scale*transform.a));
		g.setColor(c);
		g.drawRect(Math.round(transformedPosition.x - 10*scale*transform.a), Math.round(transformedPosition.y - 10*scale*transform.a), (int) (20*scale*transform.a), (int) (20*scale*transform.a));
	}
}
