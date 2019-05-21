import java.awt.Graphics2D;


public abstract class GameState {

	private GameStateManager gsm;
	
	abstract public void init();
	abstract public void update();
	abstract public void draw(Graphics2D g);
	abstract public void keyPressed(int key);
	abstract public void keyReleased(int key);
	
}
