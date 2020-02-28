import java.awt.Color;
import java.awt.Graphics;

public class Asteroid extends Polygon{
	Point[] shape;
	Point position;
	double rotation;
	int[] xs;
	
	int[] ys;
	//Graphics brush;
	//Color color;
	
	
	public Asteroid(Point[] shape, Point position, double rotation) {
		super(shape, position, rotation);
		this.shape = shape;
		this.position = position;
		this.rotation = rotation;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics brush, Color color) {
		// TODO Auto-generated method stub
		xs = new int[shape.length];
		for(int i = 0; i < shape.length; i++) {
			xs[i] = (int) ((int)position.x + shape[i].x);
		}
		ys = new int[shape.length];
		for(int j = 0; j < shape.length; j++) {
			ys[j] = (int) ((int)position.y + shape[j].y);
		}
		brush.setColor(color);
		brush.drawPolygon(xs,ys,shape.length);
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		position.x += Math.cos(Math.toRadians(rotation));
		position.y += Math.sin(Math.toRadians(rotation));

		/**
		 * If the asteroid moves off of the screen from the 
		 * x or y axis, have it re-appear from the other side.
		 */
		if(position.x > Asteroids.SCREEN_WIDTH) {
            position.x -= Asteroids.SCREEN_WIDTH;
        } else if(position.x < 0) {
            position.x += Asteroids.SCREEN_WIDTH;
        }
        
        if(position.y > Asteroids.SCREEN_HEIGHT) {
            position.y -= Asteroids.SCREEN_HEIGHT;
        } else if(position.y < 0) {
            position.y += Asteroids.SCREEN_HEIGHT;
        }
	}
		


}
