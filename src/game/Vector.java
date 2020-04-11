package game;

public class Vector {
	public float x,y;
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public double dist(Vector b) {
		float dx = x - b.x;
		float dy = y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	public float distSquared(Vector b) {
		float dx = x - b.x;
		float dy = y - b.y;
		return dx * dx + dy * dy;
	}
}
