/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.piece;

import java.util.ArrayList;
import model.Game;
import model.Move;
import model.PieceColor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author zahra
 */
public class PieceTest {
    
    public PieceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getRow method, of class Piece.
     */
    @Test
    public void testGetRow() {
      Piece p1 = new Piece(PieceColor.BLACK,3, 2);
		assertEquals(p1.getRow(),3);
    }

    /**
     * Test of getCol method, of class Piece.
     */
    @Test
    public void testGetCol() {
         Piece p1 = new Piece(PieceColor.BLACK,3, 2);
		assertEquals(p1.getCol(),2);
    }

    /**
     * Test of getColor method, of class Piece.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Piece instance = null;
        PieceColor expResult = null;
        PieceColor result = instance.getColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of move method, of class Piece.
     */
    @Test
    public void testMove() {
         Piece p1 = new Piece(PieceColor.BLACK,3, 2);
		p1.move (new Move(3, 0));
		assertEquals(p1.getCol(),0);
		assertEquals(p1.getRow(),3);
    }

    /**
     * Test of generateLegalMoves method, of class Piece.
     */
    @Test
    public void testGenerateLegalMoves() {
       Game g = new Game();
		g.board.toString();
		Piece p1 = g.board.tiles[1][1];
		ArrayList<Move> r = p1.generateLegalMoves(1, 1);
		assertTrue(r.contains(new  Move(2,2)));
		assertTrue(r.contains(new Move(6,6)));
		assertFalse(r.contains(new Move(7,7)));
	}
    

   
    /**
     * Test of isOnBoard method, of class Piece.
     */
    @Test
    public void testIsOnBoard() {
       assertFalse(Piece.isOnBoard(8,0));
		assertFalse(Piece.isOnBoard(-1,3));
		assertFalse(Piece.isOnBoard(5,8));
		assertTrue(Piece.isOnBoard(0,0));
		assertTrue(Piece.isOnBoard(7,7));	
    }
    
}
