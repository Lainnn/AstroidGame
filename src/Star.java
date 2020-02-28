import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Star extends Circle {
	Point center;
	public Star(Point center, int radius) {
		super(center, radius);
		this.center = center;
		}

	@Override
	public void paint(Graphics brush, Color color) {
		// TODO Auto-generated method stub
		Random random = new Random();
		int r = random.nextInt(this.radius);
		brush.setColor(color);
		brush.fillOval((int)center.x, (int)center.y, r, r);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
}
