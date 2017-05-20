
public class Generation {
	public static void main(String args[]){
		long debut = System.currentTimeMillis();
		int width = 50;
		int height = 50;
		
		Cell[][] maze = new Cell[height][width];
		
		initMaze(maze);
		launchGen(maze);
		System.out.println((System.currentTimeMillis()-debut)/1000);
		//displayMaze(maze);
		
		FenetreMaze fenetre = new FenetreMaze(maze);
		
	}
	
	public static void initMaze(Cell[][] maze){
		for (int i=0;i<maze.length;i++){
			for (int j=0;j<maze[i].length;j++){
				maze[i][j] = new Cell(maze,j,i);
			}
		}
	}
	
	public static void displayMaze(Cell[][] maze){
		for (int i=0;i<maze.length;i++){
			for (int j=0;j<maze[i].length;j++){
				System.out.print(maze[i][j].getStr());
			}
			System.out.println();
		}
	}

	public static void launchGen(Cell[][] maze){
		int randY = (int)(Math.random()*(maze.length));
		int randX = (int)(Math.random()*(maze[0].length));
		
		Cell beginCell = maze[randY][randX];
		gen(beginCell);
	}
	
	public static void gen(Cell cell){
		Cell nextCell = cell.action();
		if(nextCell != null){
			nextCell.setState(true);
			if (nextCell.getNbSol()>0){
				gen(nextCell);
				gen(cell);
			}
		}
	}
}
