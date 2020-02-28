
/*
CLASS: AsteroidsGame
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
Original code by Dan Leyzberg and Art Simon
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Asteroids extends Game {
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	public static int count = 100;
	public boolean isCollected = false;
	static int counter = 0;
	public static int lives = 5;
	Star[] stars = createStars(30,4);
	Asteroid tri;
	Asteroid sqr;
	Asteroid plg;
	Ship ship;
	Bullet remove;
	List<Asteroid> asters;
	public Asteroids() {
		super("Asteroids!", SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setFocusable(true);
		this.requestFocus();
		asters = createRandomAsteroids(10,60,30);
		Point b1 = new Point (0,80);
		Point b2 = new Point (60,60);
		Point b3 = new Point (20,0);
		Point[] triangle = {b1,b2,b3};
		Point a1 = new Point (0,50);
		Point a2 = new Point (80,30);
		Point a3 = new Point (60,60);
		Point a4 = new Point (20,0);
		Point[] square = {a1,a3,a2,a4};
		Point c1 = new Point (0,60);
		Point c2 = new Point (40,80);
		Point c3 = new Point (80,60);
		Point c4 = new Point (80,0);
		Point c5 = new Point(0,0);
		Point[] polygon = {c1,c2,c3,c4,c5};
		Point locationT = new Point(50,35);
		Point locationS = new Point(400,420);
		Point locationP = new Point(150,300);

		tri = new Asteroid(triangle,locationT,0.8);
		asters.add(tri);
		sqr = new Asteroid(square,locationS,-0.2);
		asters.add(sqr);
		plg = new Asteroid(polygon,locationP,0.5);
		asters.add(plg);
		ship = coolShip();


		this.addKeyListener(ship);
		
		this.addKeyListener(ship);
	}
	private Ship coolShip() {
        // Look of ship
		Point p1 = new Point(0,30);
		Point p2 = new Point(50,15);
		Point p3 = new Point(0,0);
		Point p4 = new Point(15,15);
        Point[] shipShape = {
        		p1,p2,p3,p4
        };
        // Set ship at the middle of the screen
		Point locationShip = new Point(0,300);
        double startingRotation = 0.9; // Start facing to the right
        return new Ship(shipShape, locationShip, startingRotation);
    }

	private java.util.List<Asteroid> createRandomAsteroids(int numberOfAsteroids, int maxAsteroidWidth,
			int minAsteroidWidth) {
		java.util.List<Asteroid> asteroids = new ArrayList<>(numberOfAsteroids);

		for(int i = 0; i < numberOfAsteroids; ++i) {
			// Create random asteroids by sampling points on a circle
			// Find the radius first.
			int radius = (int) (Math.random() * maxAsteroidWidth);
			if(radius < minAsteroidWidth) {
				radius += minAsteroidWidth;
			}
			// Find the circles angle
			double angle = (Math.random() * Math.PI * 1.0/2.0);
			if(angle < Math.PI * 1.0/5.0) {
				angle += Math.PI * 1.0/5.0;
			}
			// Sample and store points around that circle
			ArrayList<Point> asteroidSides = new ArrayList<Point>();
			double originalAngle = angle;
			while(angle < 2*Math.PI) {
				double x = Math.cos(angle) * radius;
				double y = Math.sin(angle) * radius;
				asteroidSides.add(new Point(x, y));
				angle += originalAngle;
			}
			// Set everything up to create the asteroid
			Point[] inSides = asteroidSides.toArray(new Point[asteroidSides.size()]);
			Point inPosition = new Point(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
			double inRotation = Math.random() * 360;
			asteroids.add(new Asteroid(inSides, inPosition, inRotation));
		}
		return asteroids;
	}

	 // Create a certain number of stars with a given max radius

	 public Star[] createStars(int numberOfStars, int maxRadius) {
	         Star[] stars = new Star[numberOfStars];
	         for(int i = 0; i < numberOfStars; ++i) {
	                 Point center = new Point
	(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
	                 int radius = (int) (Math.random() * maxRadius);
	                 if(radius < 1) {
	                         radius = 1;
	                 }
	                stars[i] = new Star(center, radius);
	         }
	return stars;
	 }
	 
	public void paint(Graphics brush) {
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);

		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted
		counter++;
		
		for(int i = 0; i < stars.length; i++) {
			stars[i].paint(brush, Color.yellow);
		}
		boolean remove = false;
		for(int i = 0; i < asters.size(); i++) {
			asters.get(i).move();
			asters.get(i).paint(brush, Color.white);
			
			if(asters.get(i).collision(ship)) {
				isCollected = true;
			}
		}
		ArrayList<Bullet> removeThis = new ArrayList<>();
		for(int i = 0; i <ship.getBullets().size(); i++) {
			
			ship.getBullets().get(i).move();
			
			if(ship.getBullets().get(i).outOfBounds()) {
				//ship.bullets.remove(i);
				removeThis.add(ship.getBullets().get(i));
				//here we can use i--, but problem didn't solved after test.
			}
			else {
				ship.getBullets().get(i).paint(brush, Color.white);
				for(int j = 0; j < asters.size(); j++) {
					System.out.println(ship.getBullets().size() + " & " + asters.size());
					if(asters.get(j).contains(ship.getBullets().get(i).center)) {
						asters.remove(j);
						remove = true;
					}
					System.out.println(i + " and " + j);
				}
				if(remove == true) {
					//ship.bullets.remove(i);
					removeThis.add(ship.getBullets().get(i));
					remove = false;
					//i--;
				}
				
			}
			
		}
		for (Bullet b : removeThis ) {
			ship.bullets.remove(b);
		}
		
		ship.paint(brush, Color.pink);
		if(isCollected == true) {
		ship.paint(brush, Color.cyan);
		count--;
		}
		else {
			ship.paint(brush, Color.green);
		}
		ship.move();

		brush.setColor(Color.white);
		//brush.drawString("Counter is " + counter,10,10);
		brush.drawString("You still have: " + lives + " lives.",10,10);
		if(count == 0) {
		ship.paint(brush, Color.green);
		count = 100;
		lives--;
		isCollected = false;
		}
		if(asters.isEmpty()) {
			brush.setColor(Color.black);
			brush.fillRect(0,0,width,height);
			brush.setColor(Color.white);
			brush.drawString("YOU WON", 400, 300);
		}
		else if(lives <= 0) {
			brush.setColor(Color.black);
			brush.fillRect(0,0,width,height);
			brush.setColor(Color.white);
			brush.drawString("Good Game", 400, 300);
		}
	}

	public static void main (String[] args) {
		Asteroids a = new Asteroids();
		a.repaint();
		
		
		//Asteroid aster = new Asteroid(null,null,0.3);
	}
}