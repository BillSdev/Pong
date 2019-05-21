import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class PlayState extends GameState {

	private Ball ball;
	private Player playerLeft;
	private Player playerRight;
	private BufferedImage background;
	private int leftScore;
	private int rightScore;                                                                    
	private BufferedImage backgroundPostGame;
	private boolean playing;
	String[] options = {"Play again", "Menu"};
	int currentChoice;
	Font font;
	GameStateManager gsm;
	int playTo;
	String winner;

	public PlayState(GameStateManager gsm) {
		this.gsm = gsm;
		background = null;
		backgroundPostGame = null;
		font = new Font("Arial", Font.PLAIN, 40);
		init();
	}

	public void init() {
		playTo = MenuState.playToSetting;
		leftScore = rightScore = 0;
		playing = true;
		initPlay();
	}

	public void initPlay() {
		playerLeft = new Player(50, GamePanel.HEIGHT/2);
		playerRight = new Player(GamePanel.WIDTH - 50, GamePanel.HEIGHT/2);
		ball = new Ball();
		System.out.println("GAME STARTED");
	}

	public void update() {
		if(playing) {
			playerLeft.update();
			playerRight.update();
			ball.update();

			if(ball.intersects(playerLeft)) {
				ball.calculateVectorAfterPlayerCollision(playerLeft);
			}
			if(ball.intersects(playerRight)) {
				ball.calculateVectorAfterPlayerCollision(playerRight);
			}

			if(ball.getX() - ball.getCWidth() <= 0) {
				rightScore++;
				initPlay();
			}
			if(ball.getX() + ball.getCWidth() >= GamePanel.WIDTH) {
				leftScore++;
				initPlay();
			}

			if(leftScore == playTo || rightScore == playTo) {
				winner = "right";
				if(leftScore == playTo)
					winner = "left";
				playing = false;

			}
		}

		else {

		}
	}

	public void draw(Graphics2D g) {
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT); //instead for background image.
		g.setColor(Color.GREEN);
		g.drawString("Score : " + leftScore, 20, 40);
		g.drawString("" + rightScore, GamePanel.WIDTH-50, 40);
		g.setColor(Color.ORANGE);
		playerLeft.draw(g);
		g.setColor(Color.YELLOW);
		playerRight.draw(g);
		g.setColor(Color.RED);
		ball.draw(g);

		if(!playing) {
			g.setColor(Color.gray);
			g.fillRect(150, 100, 500, 400);
			g.setColor(Color.YELLOW);
			if(winner.equals("left"))
				g.setColor(Color.ORANGE);
			g.drawString(winner + " player won!", 260, 140);
			g.setColor(Color.WHITE);
			for(int i = 0 ; i < options.length ; i++) {
				g.drawString(options[i], 300, 180 + (i+1) * 180/options.length);
			}
			arrow(235, 265 + currentChoice * 90, g);
		}

	}

	public void select() {

	}

	public void keyPressed(int key) {
		if(playing) {
			if(key == 'W')
				playerLeft.setUp(true);
			if(key == 'S')
				playerLeft.setDown(true);
			if(key == KeyEvent.VK_UP)
				playerRight.setUp(true);
			if(key == KeyEvent.VK_DOWN)
				playerRight.setDown(true);
		}
		
		else {
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
		
	}

	public void keyReleased(int key) {
		if(playing) {
			if(key == 'W')
				playerLeft.setUp(false);
			if(key == 'S')
				playerLeft.setDown(false);
			if(key == KeyEvent.VK_UP) 
				playerRight.setUp(false);
			if(key == KeyEvent.VK_DOWN)
				playerRight.setDown(false);
		}
	}
	
	public void choose() {
		if(currentChoice == 0)
			init();
		if(currentChoice == 1)
			gsm.setState(GameStateManager.MENUSTATE);
	}
	
	public void arrow(int x, int y, Graphics2D g) {
		
		int[] x1 = {x, x+20, x+20, x+45, x+20, x+20, x};
		int[] y1 = {y, y, y+15, y-10, y-35, y-20, y-20};
		g.fillPolygon(x1, y1, y1.length);
		
	}


}
