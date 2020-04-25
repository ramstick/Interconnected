package levels;

import game.Game;
import game.NetworkPanel;
import objects.IPAddress;
import objects.Router;
import renderer.CameraMode;
import renderer.TextRenderer;
import renderer.TransformMatrix;

public class Tutorial extends Level{
	public void update(Game game) {
		int t = game.getT();
		TextRenderer textRenderer = game.getTextRenderer();
		NetworkPanel network = game.getNetwork();
		if(t == 0) {
			game.getCamera().setMode(CameraMode.FREE);
			network.spawnComponent(new Router(0,0,new IPAddress(0,0,0,0)));
			textRenderer.addLine("This is a Router");
			textRenderer.addLine("A Router helps direct network traffic");
			textRenderer.addLine("Lets begin with a simple program");
			game.getCamera().enterAnimation(new TransformMatrix(5,5,0,0), 100);
		}else if(t == 300) {
			game.showConsoleButton();
		}
	}
}
