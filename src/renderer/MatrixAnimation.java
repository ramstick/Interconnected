package renderer;

public class MatrixAnimation {
	
	
	private TransformMatrix start, end;
	private int duration, currentT;
	
	public MatrixAnimation(TransformMatrix start, TransformMatrix end, int duration) {
		this.start = start;
		this.end = end;
		this.duration = duration;
		currentT = 0;
	}
	/**
	 * Get the TransformMatrix
	 * associated with the current time
	 * 
	 * @return The current TransformMatrix
	 */
	public TransformMatrix getTransform() {
		float now = Animator.logisticalInterpolation(1f * currentT / duration);
		return Animator.linearInterpolateMatrix(start,end,now);
	}
	/**
	 * Update the MatrixAnimation
	 */
	public void update() {
		if(duration >= currentT) {
			currentT ++;
		}
	}
	/**
	 * Checks if the MatrixAnimation is finished
	 * 
	 * @return true if the MatrixAnimation, false otherwise
	 */
	public boolean isFinished() {
		return duration < currentT;
	}
}
