package game;


/**
 * TransformMatrix
 * 
 * This is matrix.
 * Its either you know it or you dont
 * shut up
 * 
 * [
 * 	[ a 0 c ]
 * 	[ 0 b d ]
 * ]
 * 
 * @author andrew
 *
 */
public class TransformMatrix {
	
	public float a, b, c, d;
	
	public TransformMatrix() {
		this.a = 1;
		this.b = 1;
		this.c = 0;
		this.d = 0;
	}
	public TransformMatrix(float a, float b, float c, float d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	/**
	 * 
	 * mA.compose(mB) -> mB * mA
	 * 
	 * @param b - The next transformation to concatenate.
	 */
	public void compose(TransformMatrix mB) {
		a = mB.a * a;
		b = mB.b * b;
		c = mB.a * c + mB.c;
		d = mB.b * d + mB.d;
	}
	/**
	 * 
	 * mA.compose(vB) -> mA * vB
	 * 
	 * @param v - The vector to transform
	 * @retrun The transformed vector
	 */
	public Vector transform(Vector v) {
		return new Vector(a * v.x + c, b * v.y + d);
	}
	/**
	 * 
	 * scale the matrix by "scale" from x and y; 
	 * 
	 * @param scale
	 * @param x
	 * @param y
	 */
	public void scale(float scale, float x, float y) {
		compose(new TransformMatrix(scale,scale, x-scale * x,y- scale * y));
	}
	public void translate(float x, float y) {
		c += x;
		d += y;
	}
	public Vector getUntransformed(float x, float y) {
		return new Vector((x - c) / a, (y - d) / b);
	}
	
	public static TransformMatrix scaleMatrix(float scale) {
		return new TransformMatrix(scale,scale,0,0);
	}
	/**
	 * Returns a newly created TransformMatrix that translates thing according to vector [x,y]
	 * 
	 * @param x - this is the x coord (its not that hard)
	 * @param y - this is the y coord (also not that hard)
	 * @return A new Matrix that represents a translation by [x,y]
	 */
	public static TransformMatrix translationMatrix(float x, float y) {
		return new TransformMatrix(1,1,x,y);
	}
	/**
	 * Multiply two matrices together
	 * If you dont know how that works, go search it up on wikipedia
	 * </br>
	 * mC = mA * mB
	 * 
	 * @param a - mA
	 * @param b - mB
	 * @return  - mC
	 */
	public static TransformMatrix multiplyMatrix(TransformMatrix a, TransformMatrix b) {
		return new TransformMatrix(
				a.a * b.a,
				a.b * b.b,
				a.a * b.c + a.c,
				a.b * b.d + a.d
				);
	}
}
