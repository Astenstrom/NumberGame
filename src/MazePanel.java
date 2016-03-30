import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * This class creates the gui for the rook jumping
 * game. 
 * 
 * @author Anna
 *
 */
public class MazePanel extends JPanel{
	private int counter = 0;
	private final int row = 6;
	private final int col = 6;
	private JButton buttons[][] = new JButton[row][col];
	private Maze maze;
   private Player player;
   private Point point;
   private Point goalPoint;
	private ArrayList<Point> validMoves;
	/**
	 * 
	 * Constructs a maze panel 
	 * set all colors and numbers and player
	 *
	 */
	public MazePanel(){
		maze =  MazeFactory.generateRookMaze(6,6);
		setLayout(new GridLayout(6,6));
		for(int x = 0; x < row; x++){
			for(int y = 0; y < col; y++){
				buttons[x][y] = new JButton("");
            buttons[x][y].setBackground(Color.LIGHT_GRAY);
            buttons[x][y].setOpaque(true);
				buttons[x][y].setText(Integer.toString(maze.getWeight(x,y)));
				buttons[x][y].addActionListener(new buttonListener(x,y));
				add(buttons[x][y]);			
			}	
		}
      player = maze.getPlayer();
      getTheLocation();
      getTheGoal();
      getTheValidMoves();

	}
   /**
	 * Method that gets the location of the player
	 * 
	 * sets button to green and sets text to here
	 * 
	 */
   public void getTheLocation(){
      point = player.getLocation();
      buttons[(int)point.getX()][(int)point.getY()].setText
         ("Here: "+ Integer.toString(maze.getWeight((int)
         point.getX(),(int)point.getY())));
      buttons[(int)point.getX()][(int)point.getY()].setBackground(Color.GREEN);
      buttons[(int)point.getX()][(int)point.getY()].setOpaque(true);
   } 
   /**
	 * Method that gets the location of the goal
	 * 
	 * sets button to white
	 * 
	 */
	public void getTheGoal(){
      goalPoint = maze.getGoal();
      buttons[(int)goalPoint.getX()][(int)goalPoint.getY()].
      setBackground(Color.WHITE);
      buttons[(int)goalPoint.getX()][(int)goalPoint.getY()].setOpaque(true);
   }
   /**
	 * gets the valid moves for the player
	 * 
	 * sets them to yellow
	 * 
	 * @return returns array list of valid moves
	 */
   public ArrayList<Point> getTheValidMoves(){
      validMoves = player.getValidMoves();
      for(Point p : validMoves){
         buttons[(int)p.getX()][(int)p.getY()].setBackground(Color.YELLOW);
         buttons[(int)p.getX()][(int)p.getY()].setOpaque(true);
      }
		return validMoves;
   }
   /**
	 * Method that resets the board after
    * moving
	 * 
	 * sets new color for valid moves and 
    * players location
	 * 
	 */
   public void reset(){
      for(int x = 0; x < row; x++){
			for(int y = 0; y < col; y++){
            buttons[x][y].setBackground(Color.LIGHT_GRAY);
            buttons[x][y].setOpaque(true);
            buttons[(int)point.getX()][(int)point.getY()].
            setText(Integer.toString(maze.getWeight((int)point.
            getX(),(int)point.getY())));
            getTheLocation();
            getTheGoal();
            getTheValidMoves();
         }
      }          
   }
   /**
    * Action Listener that check to see if a valid
    * move button has been pressed and if the win button 
    * is pressed.
    *
    */		
	private class buttonListener implements ActionListener {
	   private int x;
      private int y;
       /**
    * constructor for action listener
    *
    * @param int x x value of button
    * @param int y y value of button
    */        
	   public buttonListener(int x, int y){
         this.x = x; 
         this.y = y;
      }
      public void actionPerformed(ActionEvent e){
         for(Point p : validMoves){
            if(x == (int)p.getX() && y== (int)p.getY()){
					if(x == (int)goalPoint.getX() && y == (int)goalPoint.getY()){
						JOptionPane pane = new JOptionPane();
              		pane.showMessageDialog(null, "You win!\nIt took you " +
                  	counter + " moves to reach the goal. \nA solution " +
                  	"was available in "+ maze.getSolutionLength() + " moves", 
                  	"You're awesome!",pane.PLAIN_MESSAGE);
               	System.exit(0);
					}
					else{
               maze.movePlayer(x,y);
               reset();
               counter++;
					}
            }
  
         }
      }

   
	}
   
	
	
}					