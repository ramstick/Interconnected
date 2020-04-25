package renderer;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TextRenderer {

	Queue<String> lines;
	public static final int LINE_UPDATE_SPEED = 100;
	private int time;
	private boolean scrolling;
	
	private String previous, current, next;

	public TextRenderer() {
		lines = new LinkedList<String>();
		time = 0;
		scrolling = true;
	}

	public void render(Graphics2D g, int x, int y) {
		FontMetrics metrics = g.getFontMetrics();
		int height = metrics.getHeight();
		int height4 = height / 4;
		if(scrolling) {
			float a = Animator.logisticalInterpolation(1f * time / LINE_UPDATE_SPEED);
			int b = height - (int) (height * a);
			
			
			if(previous != null) {
				int width = metrics.stringWidth(previous);
				g.setColor(new Color(0,0,0,.1f - .1f * a));
				g.fillRect(x - width / 2 - 10, y - 2 * height + b, width + 20, height);
				g.setColor(new Color(1f,1f,1f,1f - a));
				g.drawString(previous, x - width / 2, y - height + b - height4);
			}
			
			if(current != null) {
				int width = metrics.stringWidth(current);
				g.setColor(new Color(0,0,0,.1f));
				g.fillRect(x - width / 2 - 10, y - height + b, width + 20, height);
				g.setColor(new Color(1f,1f,1f,.7f * a + .3f));
				g.drawString(current, x - width / 2, y + b - height4);
			}
			
			if(next != null) {
				int width = metrics.stringWidth(next);
				g.setColor(new Color(0,0,0,.1f * a));
				g.fillRect(x - width / 2 - 10, y + b, width + 20, height);
				g.setColor(new Color(1f,1f,1f,.3f * a));
				g.drawString(next, x - width / 2, y + height + b - height4);
			}
			
			time ++;
			if(LINE_UPDATE_SPEED == time) {
				time = 0;
				scrolling = false;
			}
		}else {
			
			if(current != null) {
				int width = metrics.stringWidth(current);
				g.setColor(new Color(0,0,0,.1f));
				g.fillRect(x - width / 2 - 10, y - height, width + 20, height);
				g.setColor(new Color(1f,1f,1f, 1f));
				g.drawString(current, x - width / 2, y - height4);
			}
			
			if(next != null) {
				int width = metrics.stringWidth(next);
				g.setColor(new Color(0,0,0,.1f));
				g.fillRect(x - width / 2 - 10, y, width + 20, height);
				g.setColor(new Color(1f,1f,1f, .3f));
				g.drawString(next, x - width / 2, y + height - height4);
			}
			
			time ++;
			if(LINE_UPDATE_SPEED == time) {
				time = 0;
				scrolling = true;
				previous = current;
				current = next;
				next = lines.poll();
			}
		}
	}

	public boolean hasNext() {
		return !lines.isEmpty();
	}

	public void addLine(String line) {
		if(current == null) {
			scrolling = true;
			time = 0;
			current = line;
		}else if(next == null) {
			next = line;
		}else {
			lines.add(line);
		}
	}
}
