package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import model.piece.King;
import model.piece.Piece;

public class Player {

    Game game;
    PieceColor color; //color of piece
    public boolean inCheck;
    int bottomRow, pawnRow;
    public ArrayList<Piece> piecesOwned; // store the pieces owned by each player
    public King myKing; //the king of the player
    public Player opponent; //the opponent of the player

    /**
     *
     * @param game
     * @param color
     */
    public Player(Game game, PieceColor color) {
        this.game = game;
        this.color = color;
        inCheck = false;
        if (this.color == PieceColor.WHITE) {
            bottomRow = 0;
            pawnRow = 1;
        } else {
            bottomRow = 7;
            pawnRow = 6;
        }

        piecesOwned = new ArrayList<Piece>();
        for (int i = 0; i < 8; i++) {
            piecesOwned.add(this.game.board.tiles[bottomRow][i]);
            piecesOwned.add(this.game.board.tiles[pawnRow][i]);
        }
        myKing = (King) this.game.board.tiles[bottomRow][4];
    }

    /**
     * set the opponent of the player
     */
    public void setOpponent(Player op) {
        opponent = op;
    }

    /**
     * 
     * Move piece to specified position (i,j)
     * 
     */
    public void move(int index, int i, int j) {
        int curRow = piecesOwned.get(index).getRow(), curCol = piecesOwned.get(index).getCol();
        game.board.tiles[curRow][curCol] = null;
        piecesOwned.get(index).move(new Move(i, j));
        if (game.board.tiles[i][j] != null) {
            opponent.piecesOwned.remove(game.board.tiles[i][j]);
        }
        game.board.tiles[i][j] = piecesOwned.get(index);
    }

    /**
     * display the legal moves of the piece and allow moves to be made
     */
    void play() {
        noMovesResult();
        int index = 0, i = 0, j = 0;
        move(index, i, j);
        int r = opponent.myKing.getRow();
        int c = opponent.myKing.getCol();
        opponent.inCheck = opponent.myKing.inCheck(r, c);
        //print the board
        game.board.toString();
        boardTofile(game.board.toString());
    }
/*
    Get the game outcome is no moves are avalible i.e. checkmate or stalemate
    */
    public int noMovesResult() {
        boolean isMovable = canMove();
        if (!isMovable) {
            if (!inCheck) {
                System.out.println("Stalemate!");
                return 2;
            } else {
                System.out.println("Checkmate!");
                return 1;
            }
        }
        return 0;
    }


    /**
     *Check if a legal move exists to escape check
     */
    private boolean canEscapeCheck() {
        boolean bCanMove = false;
        for (Piece piece : piecesOwned) {
            System.out.println(piecesOwned.indexOf(piece) + "\t" + piece.id);
            piece.getMoves();
            int curRow = piece.getRow();
            int curCol = piece.getCol();
            for (Iterator<Move> it = piece.legalVector.iterator(); it.hasNext();) {
                Move pos = it.next();
                Piece temp = game.board.tiles[pos.row][pos.col];
                move(piecesOwned.indexOf(piece), pos.row, pos.col);
                if (myKing.inCheck(myKing.getRow(), myKing.getCol())) {
                    undo(piece, curRow, curCol, pos, temp);
                    it.remove(); // remove move that wont help escape check
                } else {
                    undo(piece, curRow, curCol, pos, temp);
                    bCanMove = printLegalMoves(pos);
                }
            }
            System.out.println();
        }
        return bCanMove;
    }
    
    /**
     * reverse move
     */
  
  public void undo(Piece p, int curRow, int curCol, Move pos, Piece temp) {
        if (temp != null) {
            opponent.piecesOwned.add(temp);
            temp.isOnBoard = true;
        }
        move(piecesOwned.indexOf(p), curRow, curCol);
        game.board.tiles[pos.row][pos.col] = temp;
    }
    /**
     print moves that are possible and legal
     */
    public boolean printLegalMoves(Move move) {
        boolean canMove;
        System.out.print(move.row + ", " + move.col + "\t");
        canMove = true; //found a legitimate movement
        return canMove;
    }

    
    /**
     * check if there are possible legal moves 
     */
    public boolean canMove() {
        boolean bCanMove = false; //if player has a legal move to play
        if (inCheck) {
            bCanMove = canEscapeCheck();
        } else { 
            for (Piece piece : piecesOwned) {
                System.out.println(piecesOwned.indexOf(piece) + "\t" + piece.id);
                piece.getMoves();
                for (Move pos : piece.legalVector) {
                    bCanMove = printLegalMoves(pos);
                }
                System.out.println();
            }
        }
        return bCanMove;
    }
    
    
    public void boardTofile(String board) {
    
    String sBoard = "";
    
    try {
    
    FileWriter boardWriter = new FileWriter("possiblemoves.txt");

    
    boardWriter.write(board);
    
    
    boardWriter.close();
    
    } catch (IOException e) {
    System.out.println("An error occurred.");
    }
    
    }

}
