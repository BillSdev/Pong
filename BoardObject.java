import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//a super class of all objects that need to interact with the board or with one another.

public class BoardObject {

	protected BufferedImage image;
	protected double x;
	protected double y;
	protected double dx; 
	protected double dy;
	protected int width;
	protected int height;
	protected int cwidth;
	protected int cheight;
	protected double speed;
	protected int yceiling;
	protected int yfloor;
	protected boolean top;
	protected boolean bottom;
	protected boolean up;
	protected boolean down;
	
	public BoardObject() {
		yceiling = 0;
		yfloor = GamePanel.HEIGHT;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle((int)x - cwidth/2, (int)y - cheight/2, cwidth, cheight);
	}
	
	public boolean intersects(BoardObject o) {
		Rectangle r1 = this.getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}
	
	public void calculateFloorCeiling() { //the +dy and +dx is to prevent the object from going out of bounds one step before it does
		top = ((y + dy) - cheight/2 <= yceiling);
		bottom = ((y + dy) + cheight/2 >= yfloor);
	}
	
	
	public void draw(Graphics2D g) {
		g.drawImage(image, (int)x - width/2, (int)y - cheight/2, null);
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setVectors(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
		speed = Math.sqrt(dx*dx + dy*dy);
	}
	
	public double getDX() { 
		return dx;
	}
	
	public double getDY() {
		return dy;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getCWidth() {
		return cwidth;
	}
	
	public int getHeight () {
		return height;
	}

	public int getCHeight() {
		return cheight;
	}
	
}

