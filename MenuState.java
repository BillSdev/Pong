import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


public class MenuState extends GameState {


	private GameStateManager gsm;
	private String[] options = {"Play", "Settings", "Exit"};
	private int currentChoice;
	private boolean inSettings;
	public static int playToSetting; //was lazy to take care of this var properly lol
	private Font font;

	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		font = new Font("Arial", Font.PLAIN, 50);
		init();
	}

	public void init() {
		currentChoice = 0;
		inSettings = false;
		playToSetting = 10;
	}

	public void update() {



	}

	public void draw(Graphics2D g) {
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.RED);
		g.drawString("PONG!", 315, 40);
		g.setColor(Color.WHITE);
		if(!inSettings) {
			g.drawString("Menu", 330, 120);
			for(int i = 0 ; i < options.length ; i++) {
				g.drawString(options[i], 200, 220 + 90 * i);
			}
			arrowRight(100, 215 + 90 * currentChoice, g);
		}
		else {
			
			g.drawString("Settings", 305, 120);
			arrowUp(370, 230, g);
			g.drawString("" + playToSetting, 370, 310);
			g.drawString("Play to : ", 20, 310);
			arrowDown(370, 380, g);

		}
	}

	public void keyPressed(int key) {

		if(!inSettings) {
			if(key == KeyEvent.VK_UP) {
				currentChoice--;
				if(currentChoice < 0)
					currentChoice = options.length-1;
			}
			if(key == KeyEvent.VK_DOWN) {
				currentChoice++;
				if(currentChoice > options.length-1)
					currentChoice = 0;
			}
			if(key == KeyEvent.VK_ENTER) {
				choose();
			}
		}
		else {
			if(key == KeyEvent.VK_UP) {
				playToSetting++;
				if(playToSetting > 24)
					playToSetting = 24;
			}
			if(key == KeyEvent.VK_DOWN) {
				playToSetting--;
				if(playToSetting < 0)
					playToSetting = 0;
			}
			if(key == KeyEvent.VK_ENTER) {
				inSettings = false;
			}
		}
	}


	public void keyReleased(int key) {

	}

	public void choose() {

		if(currentChoice == 0)
			gsm.setState(GameStateManager.PLAYSTATE);
		if(currentChoice == 1)
			inSettings = true;
		if(currentChoice == 2)
			System.exit(0);

	}

	public void arrowRight(int x, int y, Graphics2D g) {
		int[] x1 = {x, x+20, x+20, x+45, x+20, x+20, x};
		int[] y1 = {y, y, y+15, y-10, y-35, y-20, y-20};
		g.fillPolygon(x1, y1, y1.length);
	}

	public void arrowDown(int x, int y, Graphics2D g) {
		g.rotate(Math.PI/2, x + 22, y - 10);
		arrowRight(x, y, g);
		g.rotate(-Math.PI/2, x + 22, y - 10);
	}

	public void arrowUp(int x, int y, Graphics2D g) {
		g.rotate(-Math.PI/2, x + 22, y - 10);
		arrowRight(x, y, g);
		g.rotate(Math.PI/2, x + 22, y - 10);
	}

}
