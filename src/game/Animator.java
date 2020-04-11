package game;
/**
 * A class that contains methods necessary for animations
 * 
 * 
 * @author andrew
 *
 */
public class Animator {
	
	/**
	 * 
	 * Map a number to a logistical curve
	 * 
	 * @param t in [0, 1]
	 * @return [0,1]
	 */
	
	public static float logisticalInterpolation(float t) {
		return (float) (Math.round(100000 / (1 + Math.exp(-10*t+5))) / 100000f);
	}
	/**
	 * Linearly interpolate between two numbers
	 * 
	 * @param start
	 * @param end
	 * @param t in [0,1]
	 * @return [start, end]
	 */
	public static int linearInterpolate(int start, int end, float t) {
		return Math.round(start * (1-t) + end*t);
	}
	
	
	/**
	 * Linearly interpolate between two matrices by linearly interpolating between components
	 * 
	 * @param start
	 * @param end
	 * @param t int [0, 1]
	 * @return
	 */
	public static TransformMatrix linearInterpolateMatrix(TransformMatrix start, TransformMatrix end, float t) {
		float invT = 1 - t;
		return new TransformMatrix(
				start.a * invT + end.a * t,
				start.b * invT + end.b * t,
				start.c * invT + end.c * t,
				start.d * invT + end.d * t
				);
	}
	
}
