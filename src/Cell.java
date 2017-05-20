import java.util.ArrayList;

public class Cell {
	public boolean mur[] = new boolean[2]; // 0 : droite 	1 : bas
	public Cell[][] maze;
	public int x;
	public int y;
	public boolean state;
	
	public Cell(Cell[][] maze,int x,int y){
		this.mur[0] = true;
		this.mur[1] = true;
		this.maze = maze;
		this.x = x;
		this.y = y;
		this.state = false;
	}
	
	public Cell getTopCell(){
		if (y>0){
			return maze[y-1][x];
		}
		return null;
	}
	
	public Cell getBottomCell(){
		if (y<maze.length-1){
			return maze[y+1][x];
		}
		return null;
	}
	
	public Cell getRightCell(){
		if (x<maze[0].length-1){
			return maze[y][x+1];
		}
		return null;
	}
	
	public Cell getLeftCell(){
		if (x>0){
			return maze[y][x-1];
		}
		return null;
	}
	
	public boolean getTop(){
		if (this.getTopCell()!=null){
			return this.getTopCell().getBottom();
		}
		return true;
	}
	
	public boolean getBottom(){
		return this.mur[1];
	}
	
	public boolean getRight(){
		return this.mur[0];
	}
	
	public boolean getLeft(){
		if (this.getLeftCell()!=null){
			return this.getLeftCell().getRight();
		}
		return true;
	}

	public void destroyRight(){
		this.mur[0] = false;
	}
	
	public void destroyLeft(){
		if (this.getLeftCell()!=null){
			this.getLeftCell().destroyRight();
		}
	}
	
	public void destroyBottom(){
		this.mur[1] = false;
	}

	public void destroyTop(){
		if (this.getTopCell()!=null){
			this.getTopCell().destroyBottom();
		}
	}
	
	public String getStr(){

		String s;
		if (this.getBottom()){
			s = "B";
			if (this.getRight()){
				s = "T";
			}
		}
		else {
			if (this.getRight()){
				s = "D";
			}
			else {
				s = "R";
			}
		}
		return s;
	}

	public boolean getState(){
		return this.state;
	}
	
	public int getNbSol(){
		int cpt = 0;
		if (this.getTopCell()!=null && this.getTop() && !this.getTopCell().getState()){
			cpt+=1;
		}
		if (this.getRightCell()!=null && this.getRight() && !this.getRightCell().getState()){
			cpt+=1;
		}
		if (this.getLeftCell()!=null && this.getLeft() && !this.getLeftCell().getState()){
			cpt+=1;
		}
		if (this.getBottomCell()!=null && this.getBottom() && !this.getBottomCell().getState()){
			cpt+=1;
		}
		return cpt;
	}
	
	public Cell action(){
		boolean top = false;
		boolean bottom = false;
		boolean right = false;
		boolean left = false;
		ArrayList<String> list = new ArrayList();
		if (this.getTopCell()!=null && this.getTop() && !this.getTopCell().getState()){
			top = true;
			list.add("top");
		}
		if (this.getRightCell()!=null && this.getRight() && !this.getRightCell().getState()){
			right = true;
			list.add("right");
		}
		if (this.getLeftCell()!=null && this.getLeft() && !this.getLeftCell().getState()){
			left = true;
			list.add("left");
		}
		if (this.getBottomCell()!=null && this.getBottom() && !this.getBottomCell().getState()){
			bottom = true;
			list.add("bottom");
		}
		if (list.size()>0){
			int rand = (int)(Math.random()*(list.size()));
			String resp = list.get(rand);
			
			switch (resp){
			case "top":
				this.destroyTop();
				return this.getTopCell();
			case "right":
				this.destroyRight();
				return this.getRightCell();
			case "left":
				this.destroyLeft();
				return this.getLeftCell();
			case "bottom":
				this.destroyBottom();
				return this.getBottomCell();
			}
		}
		return null;
	}
	
	public void setState(boolean state){
		this.state = state;
	}
}
