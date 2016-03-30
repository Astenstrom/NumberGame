import java.awt.Point;
import java.util.*;


/**
 * This is a jumping maze.
 * It's got some chess piece on it,
 * and the player can move it according to some values.
 * 
 * If he gets to the goal he wins.
 * 
 * Winning can matter because it gives us direction in life,
 * however misguided it may be.
 * 
 * @author Stefano
 *
 */

public class Maze {

	private int[][] weights;
	private Player player;
	private Point goal;
	private List<Point> solution;

	/**
	 * 
	 * Constructs a maze. 
	 * The goal state has a value of zero.
	 * 
	 * @param weights
	 * @param goal
	 * @param player
	 */
	public Maze(int[][] weights, Point goal, Player player){
		this.weights = weights;
		this.goal = goal;
		this.player = player;
		
		player.setMaze(this);
		weights[goal.x][goal.y]= 0;
	}

	/**
	 * Returns the goal point
	 * @return
	 */
	public Point getGoal(){
		return goal;
	}
	
	/**
	 * Returns the weight for the specified location
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int getWeight(int x, int y){
		return weights[x][y];
	}
	
	/**
	 * Returns the weight for the specified point
	 * @param p
	 * @return
	 */
	public int getWeight(Point p){
		return getWeight(p.x, p.y);
	}
	
	/**
	 * Get the width of the map
	 * 
	 * @return
	 */
	public int getWidth(){
		return weights.length;
	}
	
	/**
	 * Get the height of the map
	 * @return
	 */
	public int getHeight(){
		return weights[0].length;
	}
	
	/**
	 * Get the player
	 * @return
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * Prime determinant of your success.
	 * @return
	 */
	public boolean checkWin(){
		return player.getLocation().equals(goal);
	}
	
	/**
	 * Player's got to move somehow.
	 * 
	 * If the move was a success, returns true
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean movePlayer(int x, int y){
		return player.move(x, y);
	}
	
	public void setSolution(ArrayList<Point> solution){
		this.solution = solution;
	}
	
	public int getSolutionLength(){
		return solution.size();
	}
}
