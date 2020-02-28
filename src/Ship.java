import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Ship extends Polygon implements KeyListener{
	int[] xs;
	int[] ys;
	boolean UP = false;
	boolean LEFT = false;
	boolean RIGHT = false;
	boolean DOWN = false;
	boolean SPACE = false;
	Bullet bullet;
	ArrayList bullets = new ArrayList();
	
	public Ship(Point[] shape, Point position, double rotation) {
		super(shape, position, rotation);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void paint(Graphics brush, Color color) {
		
		Point[] points = getPoints();
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];
		int nPoints = points.length;
		for(int i = 0; i < nPoints; ++i) {
			xPoints[i] = (int) points[i].x;
			yPoints[i] = (int) points[i].y;
		}
		
		
		if(SPACE==true) {
			bullet.paint(brush, color);
		}
		brush.setColor(color);
		brush.fillPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		//Bullet bullet = new Bullet(getPoints()[1],rotation);
			if(SPACE == true) {
				//bullet.move();
			}
			if(UP == true) {
				position.x += 3 * Math.cos(Math.toRadians(rotation));
				position.y += 3 * Math.sin(Math.toRadians(rotation));
			}
			else if(RIGHT == true) {
				rotate(3);
			}
			else if (LEFT == true) {
				rotate(-3);
			}
			
			else if (DOWN == true){
				position.x -= 3 * Math.cos(Math.toRadians(rotation));
				position.y -= 3 * Math.sin(Math.toRadians(rotation));	
			}
			
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



	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			UP = true;
			// up arrow key

			}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			RIGHT = true;

			// right arrow key

			}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			LEFT = true;
					// left arrow key
			}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			DOWN = true;
					// left arrow key
			}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			SPACE = true;
			bullet = new Bullet(getPoints()[1],rotation);
			bullets.add(bullet);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			UP = false;
			// up arrow key

			}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			RIGHT = false;

			// right arrow key

			}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			LEFT = false;
					// left arrow key
			}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			DOWN = false;
					// left arrow key
			}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			SPACE = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}



}
