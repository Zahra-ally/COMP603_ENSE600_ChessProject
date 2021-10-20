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
public class KnightTest {
    
    public KnightTest() {
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
     * Test of getMoves method, of class Knight.
     */
    @Test
    public void testGetMoves() {
        System.out.println("getMoves");
        Knight instance = null;
        instance.getMoves();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Knight.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Knight instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
	@Test
	public void testGetCaptureMoves() {
		Knight knight1 = new Knight(PieceColor.WHITE,4,5);
		Knight knight2 = new Knight(PieceColor.WHITE,3,3);
		Game g = initTest(knight1, knight2);
		g.board.tiles[4][5] = knight1; 
		g.board.tiles[3][3] = knight2; 
		g.board.toString();
		knight1.getMoves();	
		knight2.getMoves();
		assertTrue(knight1.legalVector.contains(new Move(3,3)));
		assertTrue(knight1.legalVector.contains(new Move(6,6)));
		assertTrue(knight2.legalVector.contains(new Move(4,5)));
		assertTrue(knight2.legalVector.contains(new Move(1,4)));
	}
	
	

	
	@Test
	public void testGetIncorrectMove() {		
		Knight knight1 = new Knight(PieceColor.WHITE,4,5);
		Knight knight2 = new Knight(PieceColor.WHITE,3,3);
		Game g = initTest(knight1, knight2);
		g.board.tiles[4][5] = knight1; 
		g.board.tiles[3][3] = knight2; 
		knight1.getMoves();	
		knight2.getMoves();
		assertFalse(knight1.legalVector.contains(new Move(4,4)));
		assertFalse(knight1.legalVector.contains(new Move(5,4)));
		assertFalse(knight2.legalVector.contains(new Move(3,3)));
		assertFalse(knight2.legalVector.contains(new Move(3,5)));
	}
public Game initTest(Piece p1, Piece p2) {
		Game g = new Game();
		g.initGame();
		p1.pieces = g.board.tiles;
		p2.pieces = g.board.tiles;
		return g;
	}
}
