/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.piece;

import model.Game;
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
public class KingTest {
    
    public KingTest() {
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
     * Test of getMoves method, of class King.
     */
    @Test
    public void testGetMoves() {
        System.out.println("getMoves");
        King instance = null;
        instance.getMoves();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inCheck method, of class King.
     */
    @Test
    public void testInCheck() {
        System.out.println("inCheck");
      	King king = new King(PieceColor.WHITE,5,4);
		Game g = initTest(king);
		g.board.tiles[5][4] = king; 
              
		assertTrue(king.inCheck(5,4));
		assertTrue(king.inCheck(5,3));
		assertTrue(king.inCheck(6,4));
    }

    /**
     * Test of getNearPieces method, of class King.
     */
    @Test
    public void testGetNearPieces() {
        System.out.println("getNearPieces");
      	King king = new King(PieceColor.WHITE,5,4);
		Game g = initTest(king);
		g.board.tiles[5][4] = king; 
		System.out.println(king.getNearPieces(5, 4, 1, 1));
		assertTrue(king.getNearPieces(5, 4, 1, 1).equals("pawn"));
		assertTrue(king.getNearPieces(5, 4, 1, -1).equals("pawn"));
    }

    /**
     * Test of toString method, of class King.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        King instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    public Game initTest(Piece p) {
		Game g = new Game();
		g.initGame();
		p.pieces = g.board.tiles;
		return g;
	}
	
}
