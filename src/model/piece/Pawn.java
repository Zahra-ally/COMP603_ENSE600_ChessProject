package model.piece;
import java.util.ArrayList;

import model.Board;
import model.Game;
import model.Move;
import model.PieceColor;

public class Pawn extends Piece{
	public Pawn(PieceColor color,int row, int col){
		super(color,row, col);
		id = "pawn";
	}
	
	private int baseRow = (color==PieceColor.WHITE)?1:Board.nRows-2;
	private int rowDirection = (color==PieceColor.WHITE)?1:-1;// the forward direction across the row
	
	public void getMoves(){
		if(legalVector==null) legalVector = new ArrayList<Move>(); 
		else legalVector.clear();
		// the first movement can be 2 steps forward
		if(move.row == baseRow &&  pieces[move.row+2*rowDirection][move.col]==null) {
			legalVector.add(new Move(move.row+2*rowDirection, move.col));
		}
		// move forward 1 step
		if(isOnBoard(move.row+rowDirection, move.col)&& 
				pieces[move.row+rowDirection][move.col]==null) {
			legalVector.add(new Move(move.row+rowDirection, move.col));
		}
		//capture in 1 step diagonally
		if(isOnBoard(move.row+rowDirection, move.col+1)&& 
				pieces[move.row+rowDirection][move.col+1]!=null &&
				pieces[move.row+rowDirection][move.col+1].color!=this.color) {
			legalVector.add(new Move(move.row+rowDirection, move.col+1));
		}
		if(isOnBoard(move.row+rowDirection, move.col-1)&& 
				pieces[move.row+rowDirection][move.col-1]!=null &&
				pieces[move.row+rowDirection][move.col-1].color!=this.color) {
			legalVector.add(new Move(move.row+rowDirection, move.col-1));
		}	
	}
        
        
          @Override
    public String toString() {
        //Capital represents White
        if (color == PieceColor.WHITE) {
            return "P";
        } else {
            return "p";
        }
    }
}