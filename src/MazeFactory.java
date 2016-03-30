import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.awt.Point;
import java.util.*;

/**
 * 
 * This is what you'll be using to generate
 * your mazes. Take a good look, there's some
 * interesting stuff going on here.
 * 
 * 
 * @author Stefano
 *
 */
public class MazeFactory {
	
	private static final Random rand = new Random();


	/**
	 * 
	 * Get a maze valid for a rook.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Maze generateRookMaze(int x, int y){
		return generateMaze(x,y, new Rook(new Point(rand.nextInt(x),rand.nextInt(y))));
	}
	
	/**
	 * Generate a maze for the given player 
	 * 
	 * Width and Height define how large the maze should be
	 * 
	 * @param width 
	 * @param height 
	 * @param player 
	 * @return
	 */
	private static Maze generateMaze(int width, int height, Player player){
		
		Point goal = new Point(rand.nextInt(width), rand.nextInt(height));
		Maze maze = null;
		ArrayList<Point> solution = new ArrayList<Point>();
		
		while(solution.size() < 1){
			maze = new Maze(generateMap(width, height), goal, player);
			solution = solve(maze);
		}
		
		
		return maze;
	}
	
	/**
	 * Here we make a map of weights by randomly selecting
	 * valid weights.
	 * 
	 * No weight should move you out of the maze.
	 * 
	 * @param width 
	 * @param height
	 * @return
	 */
	private static int[][] generateMap(int width, int height){
		int[][] map = new int[width][height];

		
		for(int i =0; i < map.length; i++)
			for(int j = 0, max =-1; j < map[i].length; j++){
				for(int y = 0, x[] = {width-i-2, height-j-2, i-1, j-1}; y < x.length;y++)
					max = x[y]>max? x[y] : max;	
					
				map[i][j] = rand.nextInt(max-1)+1;
			}

		/*
         * ternarys are cool to know, but not the most readable code unless you are familiar with them
         * here is a useful explanation of them: http://alvinalexander.com/java/edu/pj/pj010018
		 */
		
		return map;
	}
	
	/**
	 * BFS through the maze to get to the goal.
	 * We just want to make sure there is a way
	 * to get the end from the starting position.
	 * Usually you'd invert all the weights, 
	 * and do a bidirectional search.
	 * 
	 * @param maze
	 * @return
	 */
	private static ArrayList<Point> solve(Maze maze){
		
		if(null == maze)
			return new ArrayList<Point>();
		
		Point goal = maze.getGoal();
		Player player = maze.getPlayer();
		
		HashMap<Point, Point> parents = new HashMap<Point, Point>();
		ArrayList<Point> closed = new ArrayList<Point>();
		ArrayList<Point> open = new ArrayList<Point>();
		
		Player test = player.clone();
		open.add(test.getLocation());
		
		while(!open.isEmpty()){
			Point probe = open.remove(0);
			test.setLocation(probe);
			closed.add(probe);
			List<Point> fringe = test.getValidMoves();
			
			for(Point p : fringe)
				if(!closed.contains(p) && !open.contains(p)){
					open.add(p);
					parents.put(p, probe);
				}
		}
		
		ArrayList<Point> path = new ArrayList<Point>();
		
		if(parents.containsKey(goal))
			path.add(goal);
			
		while(path.size()!=0 && !path.contains(player.getLocation()))
			path.add(parents.get(path.get(path.size()-1)));
		
		path.remove(player.getLocation());
		maze.setSolution(path);
		
		return path;
	}
}
