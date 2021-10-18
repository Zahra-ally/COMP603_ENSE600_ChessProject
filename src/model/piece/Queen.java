package model.piece;

import model.PieceColor;
public class Queen extends Piece{
	public Queen(PieceColor color,int row, int col){
		super(color,row, col);
		id = "queen";
	}
	
        @Override
	public void getMoves(){
		
		legalVector =  generateLegalMoves(-1, -1);
		legalVector.addAll(generateLegalMoves(-1, 1));
		legalVector.addAll(generateLegalMoves(1, -1));
		legalVector.addAll(generateLegalMoves(1, 1));
		legalVector.addAll(generateLegalMoves(0, -1));
		legalVector.addAll(generateLegalMoves(0, 1));
		legalVector.addAll(generateLegalMoves(-1, 0));
		legalVector.addAll( generateLegalMoves(-1, 0));
	}
        
        
          @Override
    public String toString() {
        //Capital represents White
        if (color == PieceColor.WHITE) {
            return "Q";
        } else {
            return "q";
        }
    }
}