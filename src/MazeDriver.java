import javax.swing.*;
import java.awt.*;
/**
 * The MazeDriver class represents the main method
 * for the maze
 *
 * creates an instance of the maze
 *
 * @author Anna Stenstrom
 * @version 1.0 04/09/13
 */
public class MazeDriver {
   public static void main(String[] args){
      JFrame frame = new JFrame("Rook's tour");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MazePanel());
		frame.setPreferredSize((new Dimension(500,500)));
		frame.pack();
		frame.setVisible(true);
   }
}