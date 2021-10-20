/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.piece;

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
public class PawnTest {
    
    public PawnTest() {
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
     * Test of getMoves method, of class Pawn.
     */
    @Test
    public void testGetMoves() {
        System.out.println("getMoves");
        Pawn instance = null;
        instance.getMoves();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Pawn.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Pawn instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    	@Test
	public void testGetCaptureMoves() {		
		Pawn pawn1 = new Pawn(PieceColor.BLACK,4,4);
		Pawn pawn2 = new Pawn(PieceColor.WHITE,3,3);
		Game g = initTest(pawn1, pawn2);
		g.board.tiles[4][4] = pawn1; 
		g.board.tiles[3][3] = pawn2; 
		g.board.toString();
		pawn1.getMoves();	
		pawn2.getMoves();
		assertTrue(pawn1.legalVector.contains(new Move(3,3)));
		assertTrue(pawn2.legalVector.contains(new Move(4,4)));
	}
	
	
	
	@Test
	public void testGetFirstMoves() {
		Game g = new Game();
		Piece p1 = g.board.tiles[6][2];
		Piece p2 = g.board.tiles[1][2];
		p1.getMoves();
		assertTrue(p1.legalVector.contains(new Move(5,2)));
		assertTrue(p1.legalVector.contains(new Move(4,2)));
		p2.getMoves();
		assertTrue(p2.legalVector.contains(new Move(2,2)));
		assertTrue(p2.legalVector.contains(new Move(3,2)));
		
	}
	
	public Game initTest(Piece p1, Piece p2) {
		Game g = new Game();
		g.initGame();
		p1.pieces = g.board.tiles;
		p2.pieces = g.board.tiles;
		return g;
	}
    
}
