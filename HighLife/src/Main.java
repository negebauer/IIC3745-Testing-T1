
import java.awt.Color;

import javax.swing.JFrame;

public class Main {

/**
 * @param args
 * @throws InterruptedException
 * @throws Exception
 */
public static void main(String[] args) throws InterruptedException {


	int width = 30, height = 30;

	HighLifeBoard board = new HighLifeBoard(width, height, true);

	HighLifeGUI gui = new HighLifeGUI(width, height);

	startGUI(gui);
	gui.setDisplayData(board.getData());

	for(int i = 0; i < 600; i++) {
		Thread.sleep(1000);
		board.simulate();
		gui.setDisplayData(board.getData());
	}

}

public static void startGUI(HighLifeGUI gui)
{
	JFrame frame = new JFrame();
	frame.setSize(600, 600);
	frame.getContentPane().add(gui);
	frame.setLocationRelativeTo(null);
	frame.setBackground(Color.lightGray);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
}

}
