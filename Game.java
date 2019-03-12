import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener {

	Timer timer;
	Player p;
	Obstacle ob;
	WinBlock wb;
	Rockets rockets;
	int life = 1300;
	int generations = 1;
	String generationsString;
	String succesString;
	long start, stop;
	String time;
	String closestDistance;
	String minimumTime;

	public Game() {
		timer = new Timer(10, this);
		timer.start();
		setFocusable(true);
		setBackground(Color.BLACK);

		p = new Player(60, 60);
		ob = new Obstacle(275, 450);
		wb = new WinBlock(475, 10);
		// rockets = new Rocket[200];
		// for (int i = 0; i < rockets.length; i++) {
		// rockets[i] = new Rocket(470, 930);
		// }
		rockets = new Rockets(200);
		generationsString = "Generations:";
		succesString = "Succes:";
		time = "Time:";
		closestDistance = "Closest distance:";
		minimumTime = "Fastest time:";
		start = System.currentTimeMillis();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		life--;
		//System.out.println((stop - start) / 1000.0);
		// Stopwatch s;

		Double aux4 = 15 - ((stop - start) / 1000.0);
		Double aux5 = Math.floor(aux4*100);
		aux5 = aux5/100;
		String aux3 = time + aux5;
		
		// p.draw(g2d);
		ob.draw(g2d);
		wb.draw(g2d);
		rockets.draw(g2d);
		rockets.checkObs(275, 450, 50, 450);
		rockets.checkSucces(475, 10, 50, 50,aux5);
		
		stop = System.currentTimeMillis();
		if ((stop - start) / 1000.0 >= 15) {
			generations++;
			rockets.makeGeneration(475, 10);
			start = System.currentTimeMillis();
		}
		
		g2d.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		String aux1 = generationsString + generations;
		String aux2 = succesString + rockets.succes;
		g2d.drawString(aux1, 850, 40);
		g2d.drawString(aux2, 100, 40);
		g2d.drawString(aux3, 850, 60);
		
		
		if(!rockets.succes) {
			String aux6 = closestDistance+Math.floor(rockets.minDist);
			g2d.drawString(aux6, 100, 60);
		} else {
			String aux6 = minimumTime+rockets.minTime;
			g2d.drawString(aux6, 100, 60);
		}
		
		rockets.update(life);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		// System.out.println("hello");
		// System.out.println(generations);
	}
}
