import javax.swing.JFrame;

public class Game
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(3);
		frame.setContentPane(new GamePanel());
		frame.pack();
		//frame.setSize(1366, 768);
		frame.setTitle("TFDGHYRSMS");
		frame.setResizable(false);
		frame.setVisible(true);
	}
}