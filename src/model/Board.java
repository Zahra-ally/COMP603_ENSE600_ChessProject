package model;
import model.piece.*;

public class Board{
	public static int nRows = 8;
	public static int nColumns = 8;
	public Piece[][] tiles = new Piece[nRows][nColumns];
	
	Board(){
		tiles[0][0] = new Rook(PieceColor.WHITE,0,0);
		tiles[0][1] = new Knight(PieceColor.WHITE,0,1);
		tiles[0][2] = new Bishop(PieceColor.WHITE,0,2);
		tiles[0][3] = new Queen(PieceColor.WHITE,0,3);
		tiles[0][4] = new King(PieceColor.WHITE,0,4);
		tiles[0][5] = new Bishop(PieceColor.WHITE,0,5);
		tiles[0][6] = new Knight(PieceColor.WHITE,0,6);
		tiles[0][7] = new Rook(PieceColor.WHITE,0,7);
                 for (int i = 0; i < nRows; i++) {
            tiles[1][i] = new Pawn(PieceColor.WHITE,1,i);
        }
		
		tiles[7][0] = new Rook(PieceColor.BLACK,7,0);
		tiles[7][1] = new Knight(PieceColor.BLACK,7,1);
		tiles[7][2] = new Bishop(PieceColor.BLACK,7,2);
		tiles[7][3] = new Queen(PieceColor.BLACK,7,3);
		tiles[7][4] = new King(PieceColor.BLACK,7,4);
		tiles[7][5] = new Bishop(PieceColor.BLACK,7,5);
		tiles[7][6] = new Knight(PieceColor.BLACK,7,6);
		tiles[7][7] = new Rook(PieceColor.BLACK,7,7);
                
               for (int i = 0; i < nRows; i++) {
            tiles[6][i] = new Pawn(PieceColor.BLACK,6,i);
        } 
                
		

		for (int i:new int[] {0,1,6,7}){
			for (int j=0; j<8; j++){
				tiles[i][j].pieces = tiles;
			}
		}
	}
	
	 @Override
    public String toString() {
        String sBoard = "";
        for (int i = nRows-1; i >= 0; i--) {
            for (int j = 0; j < nColumns; j++) {
                sBoard += tiles[j][i] + " ";
            }
            sBoard += "\n";

        }
        return sBoard;
    }

}