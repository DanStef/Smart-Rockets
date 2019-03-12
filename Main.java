import javax.swing.JFrame;

public class Main{

	// public Main() {
	// setTitle("Simple example");
	// setSize(500,500);
	// //setDefaultCloseOperation(EXIT_ON_CLOSE);
	// setResizable(false);
	// }
	//

	static int height=1000;
	static int width=1000;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.pack();
		frame.setSize(height,width);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Game());
		frame.setVisible(true);
	}

}
