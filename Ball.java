import java.awt.Graphics2D;


public class Ball extends BoardObject {
	
	public Ball() {
//		image = null;
//		height = cheight = image.getHeight();
//		width = cwidth = image.getWidth();
		height = cheight = 30;
		width = cwidth = 30;
		speed = 10;
		dx = (Math.random()*6 + 3) * (((int)(Math.random()*2))*2 - 1);
		dy = Math.sqrt(speed*speed - dx*dx);
		y = GamePanel.HEIGHT/2;
		x = GamePanel.WIDTH/2;
	}
	
	public void calculateVectorAfterPlayerCollision(Player player) { //unique ball return physics.  updated velocity vector is based on ball position relative to the player rather than on its previous velocity vector.
		double slope = (player.getY() - y)/(player.getX() - x);
		double angle = Math.atan(slope); 
		dx = speed * Math.cos(angle) * (-(player.getX() - x)/Math.abs((player.getX() - x))); 
		dy = speed * Math.sin(angle) * (-(player.getX() - x)/Math.abs((player.getX() - x)));
	}                   																
																																									 
	public void update() {
		calculateFloorCeiling();
		if(top == true || bottom == true)
			dy = -dy;
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g) { 
		g.fillOval((int)x - width/2, (int)y - cheight/2, cwidth, cheight);
	}
	
}
