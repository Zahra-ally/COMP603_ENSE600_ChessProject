package model.piece;
import java.util.ArrayList;

import model.Game;
import model.Move;
import model.Board;
import model.PieceColor;

public class Piece{
	public String id;
	
	public boolean isOnBoard = true;
	public Move move;
	public PieceColor color;
	public Piece[][] pieces;
	/**
	 * Class constructor
	 * @param row		initial row position
	 * @param col		initial column position
     * @param color
	 */
	public Piece( PieceColor color,int row, int col){
		this.move = new Move(row, col);
		this.color = color;
                
              
	}
	 public Piece(PieceColor pieceColor) {
        this.color = pieceColor;
    }
	public int getRow() {
		return this.move.row;
	}
	
	public int getCol() {
		return this.move.col;
	}

    public PieceColor getColor() {
        return color;
    }
	
	/**
	 * move the piece to a position
	 */
	public void move(Move pos){
		this.move.row = pos.row;
		this.move.col = pos.col;
	}
	
	/**
	 *Storing legal moves
	 */
	public ArrayList<Move> legalVector;
	
	
	
	public ArrayList<Move> generateLegalMoves(int rowDirection, int colDirection){
		ArrayList<Move> moveable = new ArrayList<Move>();
		for(int i = move.row + rowDirection,
				j = move.col + colDirection;
			isOnBoard(i,j); 
			i+=rowDirection, j+=colDirection){
				if (pieces[i][j] == null){
					moveable.add(new Move(i, j));//store blank tiles
				}
				else if(this.getColor() == pieces[i][j].getColor()){
					break; 
				}
				else{//store opponent occupied spaces
					moveable.add(new Move(i, j));
					break;
				}
		}
		return moveable;
	}
	
	
	public void getMoves(){}
	
	/**
	 * Ensuring that play is on an 8x8 chess board
	 */
	public static boolean isOnBoard(int row, int col){
		 
        return !(row < 0 || row > 7 || col < 0 || col > 7);
    }	}
