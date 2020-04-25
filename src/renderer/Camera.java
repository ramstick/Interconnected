package renderer;

public class Camera {
	
	private CameraMode cameraMode, last;
	private TransformMatrix camera;
	private MatrixAnimation animation;

	public Camera(CameraMode mode, TransformMatrix camera) {
		cameraMode = mode;
		this.camera = camera;
		animation = null;
		last = cameraMode;
	}
	
	public void update() {
		if (CameraMode.ANIMATION == cameraMode) {
			camera = animation.getTransform();
			animation.update();
			
			if(animation.isFinished()) {
				cameraMode = last;
			}
		}
	}
	
	public TransformMatrix getCameraMatrix() {
		return camera;
	}
	
	public void translate(float x, float y) {
		camera.translate(x, y);
	}
	
	public void scale(float s, float x, float y) {
		camera.scale(s, x, y);
		camera.a = Math.min(10, Math.max(.1f, camera.a));
		camera.b = camera.a;
	}
	
	public void enterAnimation(TransformMatrix end, int duration) {
		last = cameraMode;	
		cameraMode = CameraMode.ANIMATION;
		animation = new MatrixAnimation(camera, end, duration);
	}
	
	public void setAnimation(MatrixAnimation animation) {
		cameraMode = CameraMode.ANIMATION;
		this.animation = animation;
	}
	
	public boolean isFree() {
		return cameraMode == CameraMode.FREE;
	}
	
	public void setMode(CameraMode mode) {
		this.cameraMode = mode;
	}
}

