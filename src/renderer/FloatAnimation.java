package renderer;

public class FloatAnimation {
	float start, end;
	int duration;
	int t;
	
	public FloatAnimation(float start, float end, int duration) {
		this.start = start;
		this.end = end;
		this.duration = duration;
		this.t = 0;
	}
	
	public float getNow() {
		float now = Animator.logisticalInterpolation(1f * t / duration);
		return Animator.linearInterpolate(start, end, now);
	}
	
	public void update() {
		t ++;
	}
	
	public boolean isFinished() {
		return t > duration;
	}
}
