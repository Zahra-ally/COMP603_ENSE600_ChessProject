package gui;

import model.Move;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.*;
import model.piece.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import gui.View.Space;
import gui.View;

/**
 * The action listener class of blocks on chess panel
 * @author Zijian Yao
 */
class Controller implements ActionListener
	{	
		Space space = null;
		View view;
		
		/**
		 * class constructor: seting the game frame and block to which the listener attached
		 * @param s	block
		 * @param v	chess game frame
		 */
		Controller(Space space, View view){
			this.space = space;
			this.view = view;
		}
	    
		// The interface method to receive button clicks
	    public void actionPerformed(ActionEvent e)
	    {
	      ImageIcon curPiece = this.space.getPiece();
	      if (!view.isSelected){
	    	  if(curPiece == null)  return;
	    	  Move p = space.pos;
                   boolean t ;
                  if(view.g.board.tiles[p.row][p.col].color==PieceColor.WHITE)
	    	 t=true;
                  else
                      t=false;
	    	  if(t != view.boolTurn)  return;
	    	  view.destination = view.g.board.tiles[p.row][p.col].legalVector;
                  if(view.destination!=null)
	    	  if(view.destination.size()==0)  return;
	    	  selectPiece(p);
	      }
	      else{
	    	  if(view.buffered != this.space) {
                       if(view.destination!=null)
	    		  if(!view.destination.contains(space.pos)) return;
	    		  String name = landPieceSwitchTurn();
		    	  int result = view.turn.checkEndingCondition();
		    	  if(result > 0){
		    		  recoverBlankColor();
		    		  String prefix = (result == 1)?(name + " is checkmated!"):"Stalemate";
		    		  endingConfirmDialog(result, prefix);
		    		  return;
		    	  } 
	    	  }
	    	  deselectPiece();
	      }
	    }
	    
	    /**
	     * deselect the chozen piece to be moved
	     * allows the player in turn to move another piece
	     */
		public void deselectPiece() {
			view.isSelected = false;
    		  view.buffered = null;
    		  recoverBlankColor();
    		  view.destination = null;
    		  view.canUndo = true;
		}
		
		/**
		 * land the selected piece on the current block
		 * switched turn to the other plauer
		 * @return	the name of nexr player in turn
		 */
		public String landPieceSwitchTurn() {
			view.lastCapturedIcon = this.space.getPiece();
			  this.space.setPiece(view.buffered.getPiece());
			  view.buffered.setPiece(null); 
			  view.lastCaptured = view.g.board.tiles[space.pos.row][space.pos.col];
			  view.targetPos = space.pos;
			  view.sourcePos = new Move(view.movingPiece.move.row,view.movingPiece.move.col);
			  view.turn.move(view.turn.piecesOwned.indexOf(view.movingPiece) , space.pos.row, space.pos.col);
			  view.lastMoved = view.movingPiece;
			  view.movingPiece = null;
			  view.turn = view.turn.opponent;
			  view.boolTurn = !view.boolTurn;
			  String name = (view.boolTurn)?view.whiteName:view.blackName;
			  view.status.setText(name + "'s turn");
			  int r = view.turn.myKing.getRow(), c = view.turn.myKing.getCol();
			  view.turn.isChecked = view.turn.myKing.inCheck(r, c);
			return name;
		}
		
		/**
		 * Display a pop-up window when the game end
		 * show the result (checkmate or stalemate)
		 * add 1 to the score of winner
		 * allow palyers to start a new game 
		 * @param result	1 = checkmate, 2 = stalemate
		 * @param prefix	text of the ending condition
		 */
		public void endingConfirmDialog(int result, String prefix) {
			String prompt = " Do you want to start a new game?";
			  int a=JOptionPane.showConfirmDialog(view,prefix + prompt);  
			  if(a==JOptionPane.YES_OPTION){  
				if(result == 1) 
					view.addWinnerScore();
				view.reloadGameAndGui(); 
			  } 
			  else{
				  System.exit(0);
			  }
		}

		/**
		 * select the piece to be moved 
		 * display all possible destination blocks in green
		 * @param p	the location of the piece
		 */
		public void selectPiece(Move p) {
                     if(view.destination!=null)
			for(Move pd: view.destination){
	    		  view.blanks[pd.row][pd.col].setBackground(Color.cyan);
	    	  }
	    	  view.buffered = this.space;
	    	  view.isSelected = true;
	    	  view.movingPiece = view.g.board.tiles[p.row][p.col];
	    	  view.canUndo = false;
		}

		
		/**
		 * recover the original color of each block 
		 */
		public void recoverBlankColor() {
			for(Move pd: view.destination){
				  view.blanks[pd.row][pd.col].setBackground(view.setSpaceColor((7-pd.row)*8+pd.col));
			  }
		}
	}