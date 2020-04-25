package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceLoader {
	public static BufferedImage CONSOLE_ICON;

	public static void loadResources () {
		try {
			CONSOLE_ICON = ImageIO.read(new File("res/Console.png"));
		} catch (IOException e) {
			
		}
	}
}
