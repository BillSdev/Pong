import java.awt.Graphics2D;


public class Player extends BoardObject {

	public Player(double x, double y) {
		image = null;
		//width = cwidth = image.getWidth();
		//height = cwidth = image.getHeight();
		width = cwidth = 20;
		height = cheight = 85; 
		setVectors(0, 0);
		up = false;
		down = false;
		this.x = x;
		this.y = y;
	}



	public void update() {
		if(up == true)
			dy = -10;
		else if(down == true)
			dy = 10;
		else 
			dy = 0;
		calculateFloorCeiling();
		if(top == false && bottom == false) {
			y += dy;
			x += dx;
		}
		if(top == true) 
			y = cheight/2+1;
		if(bottom == true)
			y = GamePanel.HEIGHT - cheight/2-1;
	}

	public void setUp(boolean b) {
		up = b;
	}

	public void setDown(boolean b) {
		down = b;
	}

	public void draw(Graphics2D g) { //overriding superclass
		g.fillRect((int)x - width/2, (int)y - cheight/2, cwidth, cheight);
	}

	//	public void checkBallCollision(Ball ball) {
	//		if(interstects(ball))
	//			ball.calculateVectorAfterPlayerCollision(y, x);
	//	}

}
