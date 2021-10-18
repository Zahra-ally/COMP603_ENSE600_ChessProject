package model.piece;
import java.util.ArrayList;

import model.Game;
import model.Move;
import model.PieceColor;

public class Knight extends Piece{
	public Knight(PieceColor color,int row, int col){
		super(color,row, col);
		id = "knight";
	}
	
	private static int[] moveDirection = new int[] {-2, -1, 1, 2};
	
	public void getMoves(){
		if(legalVector==null) legalVector = new ArrayList<Move>(); 
		else legalVector.clear();
		for(int rowDirection: moveDirection)
			for (int colDirection: moveDirection) {
				boolean isValidMove = (Math.abs(rowDirection)!=Math.abs(colDirection));
				if(isValidMove) {
					int nextRow = move.row+rowDirection,
						nextCol = move.col+colDirection;
					if(isOnBoard(nextRow, nextCol) &&
							(pieces[nextRow][nextCol] ==null ||
							this.color!=pieces[nextRow][nextCol].color)  ) {
						legalVector.add(new Move(nextRow, nextCol));
					}
				}
			}
	}
        
          @Override
    public String toString() {
        //Capital represents White
        if (color == PieceColor.WHITE) {
            return "N";
        } else {
            return "n";
        }
    }
}