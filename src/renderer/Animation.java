package renderer;

public class Animation {
	
	int duration, now;
	
	public Animation(int duration) {
		now = 0;
		this.duration = duration;
	}
	
	public float getNow() {
		return Animator.logisticalInterpolation(1f * now / duration);
	}
	
	public void update() {
		if(now <= duration) {
			now ++;
		}
	}
	
	public boolean isFinished() {
		return now > duration;
	}
	
}
