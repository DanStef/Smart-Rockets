import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Rocket {
	Double x;
	Double y;
	double[] angles;
	int width = 20;
	int height = 20;
	int currentVector = 0;
	boolean hitObs = false;
	double grade = 0;
	boolean succes = false;
	double time;
	double distance;

	public Rocket(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		generateRandomAngles();
	}

	public Rocket(double x, double y, double[] angles) {
		super();
		this.x = x;
		this.y = y;
		this.angles = angles;
		time = 0;
	}

	private void generateRandomAngles() {
		angles = new double[1000];
		int i;
		Random r = new Random();
		for (i = 0; i < angles.length; i++) {
			angles[i] = r.nextFloat();
			angles[i] = 2 * Math.PI * angles[i];
			// System.out.println(angles[i]);
		}
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.YELLOW);
		// g2d.fillRect(x, y, width, height);
		g2d.fillRoundRect(x.intValue(), y.intValue(), width, height, 1000, 1000);
	}

	public void update(int t) {

		if (hitObs || succes) {
			return;
		}

		Double result = Math.sin(angles[currentVector]);
		x = x + 4 * result;
		// System.out.println(x);
		result = Math.cos(angles[currentVector]);
		y = y + 4 * result;

		if (t % 7 == 0) {
			currentVector++;
		}

		if (currentVector == 200) {
			currentVector = 0;
		}
	}

	public void setTime(double time) {
		this.time = time;
	}
}
