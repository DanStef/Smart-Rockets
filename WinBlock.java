import java.awt.Color;
import java.awt.Graphics2D;

public class WinBlock {
	int x;
	int y;
	int height = 50;
	int width = 50;
	
	WinBlock(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		g2d.fillRect(x, y, height, width);
	}
}
