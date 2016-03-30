import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 
 * @author Stefano
 * 
 */
public abstract class Player {

    protected Point location;
    protected int width, height;
    protected Maze maze;
    protected Point[] moveSet;
    
	/*
	 * A hashmap is just a way of storing information. It uses
	 * key-value pairs to store and then access information. You will be seeing
	 * these a lot in the rest of your cs career, but do not worry about how they 
	 * work right now.
	 * 
	 * Here, we are just using it to store the ways that a chess piece can move.
	 * All of the ways that a rook can move is stored as an array of points, that
	 * is referenced with the string "Rook"
	 */
	private static HashMap<String, Point[]> moveSets;
	
	/*
	 * this is called a static initialization block - you can read the java documentation on it
	 * here: http://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
	 * 
	 * these can be very useful in certain situations, but make sure that you do not overuse them!
	 */
	static{
		
		moveSets = new HashMap<String, Point[]>();
	
		moveSets.put( "Rook",   new Point[]{new Point(0,1),  new Point(1,0),  new Point(-1,0),  new Point(0,-1)  });
	
		moveSets.put( "Queen",  new Point[]{new Point(0,1),  new Point(1,0),  new Point(-1,0),  new Point(0,-1),
										   new Point(1,1),   new Point(1,-1), new Point(-1,-1), new Point(-1,1)});
		
		moveSets.put( "Knight", new Point[]{new Point(1,2),  new Point(2,1),  new Point(-1,2),  new Point(2,-1),
											new Point(1,-2), new Point(-2,1), new Point(-2,-1), new Point(-1,-2)});
		
		moveSets.put( "Bishop", new Point[]{new Point(1,1),  new Point(-1,1), new Point(-1,-1), new Point(1,-1)});
	
	}
	

    /**
     * Creates a new Player objects and gets the correct move set
     * 
     * @param location
     *            starting location of the player
     */
    public Player(Point location) {
        this.location = location;
        moveSet = moveSets.get(getClass().getSimpleName());
        if(null==moveSet)
            moveSet = new Point[0];
    }

    /**
     * Get the valid moves of the player from its current location
     * 
     * @return array list of valid moves
     */
    public ArrayList<Point> getValidMoves() {

        ArrayList<Point> validMoves = new ArrayList<Point>();

        for (Point p : moveSet) {

            Point moveDistance = new Point(p);
            Point possibleLocation = new Point(location);

            moveDistance.x *= maze.getWeight(location);
            moveDistance.y *= maze.getWeight(location);

            possibleLocation.translate(moveDistance.x, moveDistance.y);

            if (possibleLocation.x >= 0 && possibleLocation.x < width
                    && possibleLocation.y >= 0 && possibleLocation.y < height) {
                validMoves.add(possibleLocation);
            }
        }

        return validMoves;
    }

    /**
     * Creates a copy of the player
     */
    public abstract Player clone();

    /**
     * Moves the player to the specified location. If the move is valid, the
     * player is moved. If it is not, the player remains in its original
     * location
     * 
     * @param x
     *            x location to move the player to
     * @param y
     *            y location to move the player to
     * @return whether the move was executed or not
     */
    public boolean move(int x, int y) {

        if (maze != null && getValidMoves().contains(new Point(x, y))) {
            location = new Point(x, y);
            return true;
        }

        return false;

    }

    /**
     * Get the current location of the player
     * 
     * @return the current location of the player
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Set the current location of the player
     * 
     * @param location
     *            the new location of the player
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * Set the maze that the player is referencing
     * 
     * @param maze
     */
    public void setMaze(Maze maze) {
        this.maze = maze;
        width = maze.getWidth();
        height = maze.getHeight();
    }

    /**
     * Get the maze that the player is referencing
     * 
     * @return
     */
    public Maze getMaze() {
        return maze;
    }

}
