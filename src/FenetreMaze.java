
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreMaze extends JFrame {
	
	Cell[][] maze;
	
	public FenetreMaze(Cell[][] maze){
		this.maze = maze;
		this.setTitle("Maze");
		this.setSize(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		JPanel panel = new JPanel(new GridLayout(maze.length,maze[0].length));
		for (int i=0;i<maze.length;i++){
			for (int j=0;j<maze[0].length;j++){
				panel.add(new JLabel(new ImageIcon("img/"+maze[i][j].getStr()+".png")));
			}
		}
		
		this.add(panel);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
