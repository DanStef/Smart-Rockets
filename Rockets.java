import java.awt.Graphics2D;
import java.util.Random;

public class Rockets {
	Rocket[] rockets;
	boolean succes = false;
	int generations = 0;
	double minTime = 0;
	double minDist = 0;
	int timeFound = 0;

	Rockets(int n) {
		rockets = new Rocket[n];
		for (int i = 0; i < rockets.length; i++) {
			rockets[i] = new Rocket(470, 930);
		}
	}

	public void draw(Graphics2D g2d) {
		for (int i = 0; i < rockets.length; i++) {
			rockets[i].draw(g2d);
		}
	}

	public void update(int t) {
		for (int i = 0; i < rockets.length; i++) {
			rockets[i].update(t);
		}
	}

	public void checkObs(int x, int y, int w, int h) {
		for (int i = 0; i < rockets.length; i++) {
			if (rockets[i].x > x - 20 && rockets[i].x < x + h && rockets[i].y > y - 20 && rockets[i].y < y + w) {
				// System.out.println();
				rockets[i].hitObs = true;
			}

			if (rockets[i].x > 980 || rockets[i].x < 0 || rockets[i].y > 960 || rockets[i].y < 0) {
				rockets[i].hitObs = true;
			}
		}
	}

	void getMostNear(int x, int y) {
		for (int i = 0; i < rockets.length; i++) {
			rockets[i].grade = Math
					.sqrt((x - rockets[i].x) * (x - rockets[i].x) + (y - rockets[i].y) * (y - rockets[i].y));
			rockets[i].distance = rockets[i].grade;
			if (rockets[i].time != 0) {
				rockets[i].grade = rockets[i].grade - Math.pow(2.0, rockets[i].time);
			}
			if (rockets[i].hitObs) {
				rockets[i].grade *= 2;
			}
		}
		sort();
	}

	void checkSucces(int x, int y, int w, int h, double time) {
		for (int i = 0; i < rockets.length; i++) {
			if (rockets[i].x > x - 20 && rockets[i].x < x + h && rockets[i].y > y - 20 && rockets[i].y < y + w) {
				// System.out.println();
				rockets[i].succes = true;
				succes = true;
				rockets[i].time = time;
				if (timeFound == 0) {
					timeFound = 1;
					minTime = 15-time;
					double aux = minTime*100;
					aux = Math.floor(aux);
					minTime = aux/100;
				}
			}
		}
	}

	void sort() {
		int i, j;
		for (i = 0; i < rockets.length; i++) {
			for (j = i; j < rockets.length; j++) {
				if (rockets[j].grade < rockets[i].grade) {
					Rocket cop = rockets[j];
					rockets[j] = rockets[i];
					rockets[i] = cop;
				}
			}
		}

	}

	public void makeGeneration(int x, int y) {
		generations++;
		getMostNear(x, y);

		//minTime = getMinTime();
		minDist = getMinDistance();

		Rocket parent1, parent2;
		Random r = new Random();
		Integer result;

		Rocket[] mateVector = new Rocket[20100];
		int nrCrt = 0;
		for (int i = 0; i < rockets.length /2; i++) {
			for (int j = 0; j < rockets.length - i; j++) {
				mateVector[nrCrt] = rockets[i];
				nrCrt++;
			}
		}

		for (int i = 0; i < rockets.length; i++) {
			if (rockets[i].succes) {
				for (int j = 0; j < 1000; j++) {
					 mateVector[nrCrt] = rockets[i];
					 nrCrt++;
				}
				break;
			}
		}

		for (int i = 0; i < 200; i++) {
			double[] anglesAux = new double[1000];

			result = r.nextInt();
			result = Math.abs(result);
			parent1 = mateVector[result.intValue() % (nrCrt)];
			result = r.nextInt();
			result = Math.abs(result);
			parent2 = mateVector[result.intValue() % (nrCrt)];

			for (int j = 0; j < 1000; j++) {

				int chance = r.nextInt();
				chance *= 100;
				chance %= 2;
				double mutation = r.nextDouble();

				if (mutation < 0.1) {
					anglesAux[j] = r.nextFloat();
					anglesAux[j] = 2 * Math.PI * anglesAux[j];
				} else {

					// System.out.println(j);
					if (j < 500) {
						anglesAux[j] = parent1.angles[j];
					} else {
						anglesAux[j] = parent2.angles[j];
					}
				}
			}
			rockets[i] = new Rocket(470, 930, anglesAux);
			// rockets[i].angles = anglesAux;
		}
		timeFound = 0;
	}

	public double getMinDistance() {
		double min = rockets[0].distance;
		for (int i = 0; i < rockets.length; i++) {
			if (rockets[i].distance < min) {
				min = rockets[i].distance;
			}
		}

		return min;
	}

	public double getMinTime() {
		double min = rockets[0].time;
		for (int i = 0; i < rockets.length; i++) {
			if (rockets[i].succes) {
				min = rockets[i].time;
				break;
			}
		}
		System.out.println(min);
		for (int i = 0; i < rockets.length; i++) {
			if (rockets[i].time > min && rockets[i].succes) {
				min = rockets[i].time;
			}
		}
		return 15 - min;
	}
}
