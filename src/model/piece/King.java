package model.piece;
import java.util.ArrayList;

import model.Move;
import model.PieceColor;
public class King extends Piece{
	public King(PieceColor color,int row, int col){
		super(color,row, col);
		id = "king";
               
	}
        
	
	private static final int[] legalDirection = new int[] {-1, 1, 0};
	
         /*
        Adding all possible vectors for the king the arraylist
       King can move in one step in any direction
        
     */
        @Override
	public void getMoves(){
		if(legalVector==null)
                    legalVector = new ArrayList<>(); 
		else 
                    legalVector.clear();
		for(int rowDirection: legalDirection)
			for (int colDirection: legalDirection) {
				boolean legal = true;
				if(legal) {
					int newRow = move.row+rowDirection;
						int newCol = move.col+colDirection;
					if (isOnBoard(newRow, newCol)) { 
                                    } else {
                                            continue;
                                    }
					Piece nearPiece = pieces[newRow][newCol];
                                        if(nearPiece == null&&!inCheck(newRow, newCol))
                                             legalVector.add(new Move(newRow, newCol));
                                        else if(nearPiece != null)
                                                if((this.getColor()!=nearPiece.getColor())&&!inCheck(newRow, newCol)) {
                                            legalVector.add(new Move(newRow, newCol));
                                        }
				}
			}
	}
	/**
	 * check if king is in check
     * @param row
     * @param col
     * @return boolean value
	 */
	public boolean inCheck(int row, int col) {
              boolean checkedByRook=false;
              boolean checkedByBishop=false;
              boolean checkedByQueen=false;
              boolean checkedByKnight=false;
              boolean checkedByPawn =  false;
              
		  if (getNearPieces(row, col, 1, 0).equals("rook")
								|| getNearPieces(row, col, -1, 0).equals("rook")
								|| getNearPieces(row, col, 0, 1).equals("rook")
								|| getNearPieces(row, col, 0, -1).equals("rook"))
                       checkedByRook =  true;
                  
                    
		if(  getNearPieces(row, col, 1, 1).equals("bishop")
								|| getNearPieces(row, col, -1, 1).equals("bishop")
								|| getNearPieces(row, col, 1, -1).equals("bishop")
								|| getNearPieces(row, col, -1, -1).equals("bishop"))
                    checkedByBishop=true;
                    
		if(   getNearPieces(row, col, 1, 0).equals("queen")
								|| getNearPieces(row, col, -1, 0).equals("queen")
								|| getNearPieces(row, col, 0, 1).equals("queen")
								|| getNearPieces(row, col, 0, -1).equals("queen")
								|| getNearPieces(row, col, 1, 1).equals("queen")
								|| getNearPieces(row, col, -1, 1).equals("queen")
								|| getNearPieces(row, col, 1, -1).equals("queen")
								|| getNearPieces(row, col, -1, -1).equals("queen"))
                    checkedByQueen=true;
                    
		if(getNearPieces(row, col, 1, 2).equals("knight")
								|| getNearPieces(row, col, 1, -2).equals("knight")
								|| getNearPieces(row, col, 2, 1).equals("knight")
								|| getNearPieces(row, col, 2, -1).equals("knight")
								|| getNearPieces(row, col, -1, 2).equals("knight")
								|| getNearPieces(row, col, -1, -2).equals("knight")
								|| getNearPieces(row, col, -2, -1).equals("knight")
								|| getNearPieces(row, col, -2, 1).equals("knight"))
                    checkedByKnight=true;
                
                
                
		int pawnMove ;
                if(this.getColor()==PieceColor.WHITE)
                    pawnMove=1;
                else
                    pawnMove=-1;
                
                
		if(  getNearPieces(row, col, pawnMove, 1).equals("pawn")
								|| getNearPieces(row, col, pawnMove, -1).equals("pawn"))
                    checkedByPawn=true;
                
                
                
		if( checkedByRook||checkedByBishop||checkedByQueen||checkedByKnight||checkedByPawn)
                    return true;
                else
                    return false;
                           
		
	}
	
	/*
        Get pieces surrounding the king to check if it is in check
        */

    /**
     *
     * @param row
     * @param col
     * @param rDirection
     * @param colDirection
     * @return id of piece that could put it in check
     */

	public String getNearPieces(int row, int col, int rDirection, int colDirection) {
            
            boolean oneStepKill=false;
            if(pieces[row][col]!=null)
            if("knight".equals(pieces[row][col].id)||"pawn".equals(pieces[row][col].id)){
            oneStepKill=true;
            
            }
		row+=rDirection; 
                col+=colDirection; 
                
		if(!isOnBoard(row, col)) {
			return "";
		}
		if(oneStepKill) {
                    if(pieces[row][col]!=null&&pieces[row][col].getColor()!=this.getColor())
                        return pieces[row][col].id;
		}
		for(int i = row, j = col; isOnBoard(i,j); i+=rDirection, j+=colDirection) {
			if(pieces[i][j] == null) 
                            continue;
			if(pieces[i][j]!=null && pieces[i][j].getColor()!=this.getColor())
				return pieces[i][j].id;
			break;
		}
		return "";
	}
        
        
        
          @Override
    public String toString() {
        //Capital represents White
        if (color == PieceColor.WHITE) {
            return "K";
        } else {
            return "k";
        }
    }
}