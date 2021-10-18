package model;
public class Move{
	public int row, col;
	public Move(int a, int b){
		row = a;
		col = b;
	}
	
	public boolean equals(Object p){
		return this.row == ((Move)p).row && this.col == ((Move)p).col;
	}
}