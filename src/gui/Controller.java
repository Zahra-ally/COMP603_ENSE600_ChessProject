package gui;

import model.Move;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import gui.View.ChessBoard;


class Controller implements ActionListener
	{	
		ChessBoard cb = null;
		View view;
		
		
		Controller(ChessBoard cb, View view){
			this.cb = cb;
			this.view = view;
		}
	    
                @Override
	    public void actionPerformed(ActionEvent e)
	    {
	      ImageIcon curPiece = this.cb.getPiece();
	      if (view.movingPieceSelected){
                  if(view.buffered != this.cb) { 
                      if(view.destination!=null)
                          if(!view.destination.contains(cb.move))
                              return;
                      String name = landPieceSwitchTurn();
                      int result = view.turn.noMovesResult();
                      if(result > 0){
                          recoverBlankColor();
                          String prefix = (result == 1)?(name + " is checkmated!"):"Stalemate";
                          endingConfirmDialog(result, prefix);
                          return;
                      }
                  }
                  deselectPiece();
              }
	      else{
                  if(curPiece == null)
                      return;
                  Move move = cb.move;
                  boolean t ;
                  if(view.game.board.tiles[move.row][move.col].color==PieceColor.WHITE)
                      t=true;
                  else
                      t=false;
                  if(t != view.bTurn)  return;
                  view.destination = view.game.board.tiles[move.row][move.col].legalVector;
                  if(view.destination!=null)
                      if(view.destination.size()==0)  return;
                  selectPiece(move);
              }
	    }
	    
	  
		public void deselectPiece() {
			view.movingPieceSelected = false;
    		  view.buffered = null;
    		  recoverBlankColor();
    		  view.destination = null;
    		  view.canUndo = true;
		}
		
		
		public String landPieceSwitchTurn() {
			view.lastCapturedIcon = this.cb.getPiece();
			  this.cb.setPiece(view.buffered.getPiece());
			  view.buffered.setPiece(null); 
			  view.lastCaptured = view.game.board.tiles[cb.move.row][cb.move.col];
			  view.targetPos = cb.move;
			  view.sourcePos = new Move(view.movingPiece.move.row,view.movingPiece.move.col);
			  view.turn.move(view.turn.piecesOwned.indexOf(view.movingPiece) , cb.move.row, cb.move.col);
			  view.lastMoved = view.movingPiece;
			  view.movingPiece = null;
			  view.turn = view.turn.opponent;
			  view.bTurn = !view.bTurn;
			  String name = (view.bTurn)?view.whiteName:view.blackName;
			  view.status.setText(name + "'s turn");
			  int r = view.turn.myKing.getRow(), c = view.turn.myKing.getCol();
			  view.turn.inCheck = view.turn.myKing.inCheck(r, c);
			return name;
		}
		
		
		public void endingConfirmDialog(int result, String prefix) {
			String prompt = " Do you want to start a new game?";
			  int a=JOptionPane.showConfirmDialog(view,prefix + prompt);  
			  if(a==JOptionPane.YES_OPTION){  
				if(result == 1) 
					view.updateScore();
				view.refresh(); 
			  } 
			  else{
				  System.exit(0);
			  }
		}

		public void selectPiece(Move p) {
                     if(view.destination!=null)
			for(Move pd: view.destination){
	    		  view.cb[pd.row][pd.col].setBackground(Color.cyan);
	    	  }
	    	  view.buffered = this.cb;
	    	  view.movingPieceSelected = true;
	    	  view.movingPiece = view.game.board.tiles[p.row][p.col];
	    	  view.canUndo = false;
		}

		
		
		public void recoverBlankColor() {
			for(Move pd: view.destination){
				  view.cb[pd.row][pd.col].setBackground(view.setSpaceColor((7-pd.row)*8+pd.col));
			  }
		}
	}