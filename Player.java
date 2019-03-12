import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	int x;
	int y;

	Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update() {
		x+=3;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLUE);
		g2d.setBackground(Color.BLUE);
		g2d.fillRect(x, y, 40, 40);
		
	}
}
