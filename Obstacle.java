import java.awt.Color;
import java.awt.Graphics2D;

public class Obstacle {
	int x;
	int y;
	int height = 450;
	int width = 50;
	
	Obstacle(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.RED);
		g2d.fillRect(x, y, height, width);
	}
}
