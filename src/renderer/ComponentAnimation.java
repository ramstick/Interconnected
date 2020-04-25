package renderer;

import java.awt.Graphics2D;

import objects.InternetComponent;

public class ComponentAnimation {
	
	public ComponentAnimationTypes type;
	public InternetComponent componentA, componentB;
	int now, duration;
	
	public ComponentAnimation(InternetComponent component, int duration, ComponentAnimationTypes type) {
		now = 0;
		this.componentA = component;
		this.duration = duration;
		this.type = type;
	}
	
	public ComponentAnimation(InternetComponent componentA, InternetComponent componentB, int duration, ComponentAnimationTypes type) {
		now = 0;
		this.componentA = componentA;
		this.componentB = componentB;
		this.duration = duration;
		this.type = type;
	}
	
	public void render(Graphics2D g, TransformMatrix transform) {
		
	}
	
	public void update() {
		if(!isFinished())
		now ++;
		
	}
	public boolean isFinished() {
		return now > duration;
	}
}
