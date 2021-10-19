package model;
public class Move{
	public int row, col;
	public Move(int r, int c){
		row = r;
		col = c;
	}
	
	public boolean equals(Object p){
		return this.row == ((Move)p).row && this.col == ((Move)p).col;
	}
}