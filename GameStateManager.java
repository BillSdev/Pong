import java.awt.Graphics2D;

//This class is the class connecting all the different states (current menu and play states)
//Its job is to update , draw and direct the input to the current running state and easily switch between states.


public class GameStateManager {

	private GameState[] gameStates;
	private int currentState;
	
	public static final int NUMOFGAMESTATES = 2;
	public static final int MENUSTATE = 0;
	public static final int PLAYSTATE = 1;
	
	public GameStateManager() {
		gameStates = new GameState[NUMOFGAMESTATES];
		currentState = MENUSTATE;
		loadState(currentState);
	}
	
	public void loadState(int state) {
		if(state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		if(state == PLAYSTATE)
			gameStates[state] = new PlayState(this);
	}
	
	public void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
	
	public void update() {
		gameStates[currentState].update();
	}
	
	public void draw(Graphics2D g) {
		gameStates[currentState].draw(g);
	}
	
	public void keyPressed(int key) {
		gameStates[currentState].keyPressed(key);
	}
	
	public void keyReleased(int key) {
		gameStates[currentState].keyReleased(key);
	}
	
}
