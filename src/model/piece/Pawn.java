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
       
        
	private int baseRow = (color==PieceColor.WHITE)?1:6;
	private int rowDirection = (color==PieceColor.WHITE)?1:-1;
	
        @Override
	public void getMoves(){
		if(legalVector==null) 
                    legalVector = new ArrayList<Move>(); 
		else legalVector.clear();
		// can move 2 spots if first turn
		if(move.row == baseRow) {
                if (pieces[move.row+2*rowDirection][move.col]==null) {
                    legalVector.add(new Move(move.row+2*rowDirection, move.col));
                }
		}
                
                 if (color == PieceColor.WHITE) {
                 
                 // move straight one move
		if(isOnBoard(move.row+1, move.col)) {
                if (pieces[move.row+1][move.col]==null) {
                    legalVector.add(new Move(move.row+1, move.col));
                    
                    
                }
		}
		//cut diagonally
		if(isOnBoard(move.row+1, move.col+1)&& 
				pieces[move.row+1][move.col+1]!=null &&
				pieces[move.row+1][move.col+1].color!=this.color) {
			legalVector.add(new Move(move.row+1, move.col+1));
		}
		if(isOnBoard(move.row+1, move.col-1)&& 
				pieces[move.row+1][move.col-1]!=null &&
				pieces[move.row+1][move.col-1].color!=this.color) {
			legalVector.add(new Move(move.row+1, move.col-1));
		}	
                 
                 
                 
                 }
                else if (color == PieceColor.BLACK) {
                
                
              
		// move straight one move
		if(isOnBoard(move.row-1, move.col)) {
                if (pieces[move.row-1][move.col]==null) {
                    legalVector.add(new Move(move.row-1, move.col));
                    
                    
                }
		}
		//cut diagonally
		if(isOnBoard(move.row-1, move.col+1)&& 
				pieces[move.row-1][move.col+1]!=null &&
				pieces[move.row-1][move.col+1].color!=this.color) {
			legalVector.add(new Move(move.row-1, move.col+1));
		}
		if(isOnBoard(move.row-1, move.col-1)&& 
				pieces[move.row-1][move.col-1]!=null &&
				pieces[move.row-1][move.col-1].color!=this.color) {
			legalVector.add(new Move(move.row-1, move.col-1));
		}	
	}}
        
        
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