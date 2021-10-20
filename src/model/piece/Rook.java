package model.piece;

import java.util.*;

import model.Move;
import model.PieceColor;

public class Rook extends Piece {

    public Rook(PieceColor color, int row, int col) {
        super(color, row, col);
        id = "rook";//Storing the ID of each piece
    }

    /*
        Adding all possible vectors for the rook the arraylist
        Bishop moves diagonally: 
        North East, North West, South West and South East
                 
        
     */
    @Override
    public void getMoves() {

        legalVector = generateLegalMoves(0, -1);
        legalVector.addAll(generateLegalMoves(0, 1));
        legalVector.addAll(generateLegalMoves(-1, 0));
        legalVector.addAll(generateLegalMoves(1, 0));
    }

    @Override
    public String toString() {
        //Capital represents White
        if (color == PieceColor.WHITE) {
            return "R";
        } else {
            return "r";
        }
    }
}
