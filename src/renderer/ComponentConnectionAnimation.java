package renderer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import objects.InternetComponent;

public class ComponentConnectionAnimation extends ComponentAnimation{
	public ComponentConnectionAnimation(InternetComponent a, InternetComponent b, int duration) {
		super(a,b,duration, ComponentAnimationTypes.CONNECT);
	}
	public void render(Graphics2D g, TransformMatrix transform) {
		g.setStroke(new BasicStroke(transform.a));
		float t = Animator.logisticalInterpolation(1f * now / duration);
		float invT = 1 - t;
		Vector atob = Vector.sub(componentB.position, componentA.position);
		Vector btoa = Vector.sub(componentA.position, componentB.position);
		atob.directionalize(10);
		atob.add(componentA.position);
		btoa.directionalize(10);
		btoa.add(componentB.position);
		Vector transformedA = transform.transform(atob);
		Vector transformedB = transform.transform(btoa);
		g.drawLine((int) transformedA.x, (int) transformedA.y, (int) (invT * transformedA.x + t * transformedB.x), (int) (invT * transformedA.y + t * transformedB.y));
	}
}
