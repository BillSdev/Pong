import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable, KeyListener{

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public final double FPS = 65;
	Thread thread;
	Graphics2D g;
	BufferedImage image;
	GameStateManager gsm;
	long startTime;
	int fpsCounter;
	long fpsStartTime;

	public GamePanel() {
		super();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		this.requestFocus();
		if(this.thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	public void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		gsm = new GameStateManager();
		startTime = System.nanoTime();
		fpsStartTime = System.nanoTime();
		fpsCounter = 0;
	}

	public void run() {
		init();
		while(true) {
			if((System.nanoTime() - startTime) >= (1/FPS)*1000000000) {
				update();
				draw(g);
				drawToScreen();
				fpsCounter++;
				startTime = System.nanoTime();
				if(System.nanoTime() - fpsStartTime > 1000000000) {
					System.out.print("FPS = " + fpsCounter);
					fpsCounter = 0;
					fpsStartTime = System.nanoTime();
				}
			}
		}
	}

	public void update() {
		gsm.update();
	}

	public void draw(Graphics2D g) {
		gsm.draw(g);
	}

	public void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		//g2.dispose();
	}

	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}

	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}

	public void keyTyped(KeyEvent arg0) {	}
}
