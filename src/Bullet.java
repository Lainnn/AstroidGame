import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Bullet extends Circle{
	private static final int RADIUS = 5;
	private double rotation;
	Point center;
	
	public Bullet(Point center, double rotation) {
		super(center, RADIUS); // define RADIUS in Bullet class
		this.rotation = rotation;
		this.center = center;
		}
	
	@Override
	public void paint(Graphics brush, Color color) {
		// TODO Auto-generated method stub
		Random random = new Random();
		brush.setColor(color);
		brush.fillOval((int)center.x, (int)center.y, RADIUS, RADIUS);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		center.x += 2 * Math.cos(Math.toRadians(rotation));
		center.y += 2 * Math.sin(Math.toRadians(rotation));
	}
	
	public boolean outOfBounds() {
		if(center.y >= Asteroids.SCREEN_HEIGHT || center.x >= Asteroids.SCREEN_WIDTH) {
			return true;
		}
		return false;
	}
	public Point getCenter() {
		return center;
	}

}
