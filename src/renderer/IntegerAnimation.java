package renderer;

public class IntegerAnimation {
	
	int start, end, duration;
	int t;
	
	public IntegerAnimation(int start, int end, int duration) {
		this.start = start;
		this.end = end;
		this.duration = duration;
		this.t = 0;
	}
	
	public int getNow() {
		float now = Animator.logisticalInterpolation(1f * t / duration);
		return (int) Animator.linearInterpolate(start, end, now);
	}
	
	public void update() {
		t ++;
	}
	
	public boolean isFinished() {
		return t > duration;
	}
}
