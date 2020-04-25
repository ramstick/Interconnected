package renderer;

public class Vector {
	public float x,y;
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public Vector(Vector b) {
		this.x = b.x;
		this.y = b.y;
	}
	public double dist(Vector b) {
		float dx = x - b.x;
		float dy = y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	public double dist() {
		return Math.sqrt(x*x + y*y);
	}
	public float distSquared(Vector b) {
		float dx = x - b.x;
		float dy = y - b.y;
		return dx * dx + dy * dy;
	}
	public Vector directional(Vector b) {
		float dist = (float) dist(b);
		return new Vector((b.x - x) / dist,(b.y - y) / dist);
	}
	public void add(Vector b) {
		this.x += b.x;
		this.y += b.y;
	}
	public void sub(Vector b) {
		this.x -= b.x;
		this.y -= b.y;
	}
	public void multiply(float s) {
		this.x *= s;
		this.y *= s;
	}
	public void unitize() {
		float dist = (float) dist();
		this.x /= dist;
		this.y /= dist;
	}
	public void directionalize(float s) {
		float dist = (float) dist() / s;
		this.x /= dist;
		this.y /= dist;
	}
	public static Vector add(Vector a, Vector b) {
		return new Vector(a.x+b.x,a.y+b.y);
	}
	public static Vector sub(Vector a, Vector b) {
		return new Vector(a.x-b.x,a.y-b.y);
	}
	public static Vector multiply(Vector a, float s) {
		return new Vector(a.x*s, a.y*s);
	}
}
