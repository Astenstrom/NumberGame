import java.awt.Point;

/**
 * 
 * @author Stefano
 *
 */
public class Rook extends Player{

	/**
	 * Create a new rook from the given location
	 * 
	 * @param location
	 */
	public Rook(Point location) {
		super(location);
	}

	@Override
	public Player clone(){
		Rook rook = new Rook(new Point(location));
		rook.setMaze(maze);
		return rook;
		
	}
	
}
