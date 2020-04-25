package renderer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import objects.InternetComponent;

public class ComponentSpawnAnimation extends ComponentAnimation{
	
	public ComponentSpawnAnimation(InternetComponent component, int duration) {
		super(component, duration, ComponentAnimationTypes.SPAWN);
	}
	
	public void render(Graphics2D g, TransformMatrix transform) {
		g.setStroke(new BasicStroke(transform.a * 2));
		float t = Animator.logisticalInterpolation(1f * now / duration);
		componentA.render(g, transform, new Color(1f,1f,1f,t),t);
	}
}
