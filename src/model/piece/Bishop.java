package model.piece;
import java.util.ArrayList;

import model.Move;
import model.PieceColor;
public class Bishop extends Piece{
	public Bishop( PieceColor color,int row, int col){
		super(color,row, col);
		id = "bishop";
	}
	
        @Override
	public void getMoves(){
		 /*
                 *
                 *Bishop moves diagonally: 
                 *North East, North West, South West and South East
                 *
         */
		legalVector = generateLegalMoves(-1, -1);;
		legalVector.addAll(generateLegalMoves(-1, 1));
		legalVector.addAll(generateLegalMoves(1, -1));
		legalVector.addAll(generateLegalMoves(1, 1));
	}
        
          @Override
    public String toString() {
        //Capital represents White
        if (color == PieceColor.WHITE) {
            return "B";
        } else {
            return "b";
        }
    }
}