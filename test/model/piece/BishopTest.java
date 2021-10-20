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
import model.Move;

/**
 *
 * @author zahra
 */
public class BishopTest {
        Bishop bishop;

    public BishopTest() {
        this.bishop = new Bishop(PieceColor.WHITE,4,4);
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
     * Test of getMoves method, of class Bishop.
     */
    @Test
    public void testGetMoves() {
        System.out.println("getMoves");
        Bishop instance = null;
        instance.getMoves();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Bishop.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Bishop instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
	
	@Test
	public void testCapture() {
		Game g = initTest(bishop);
		g.board.tiles[4][4] = bishop; 
		g.board.toString();
		bishop.getMoves();
		assertTrue(bishop.legalVector.contains(new Move(6,6)));
		assertTrue(bishop.legalVector.contains(new Move(6,2)));
	}
	
	
	
	@Test
	public void testNotCaptureItself() {
            Game g = initTest(bishop);
		g.board.tiles[4][4] = bishop;
		bishop.getMoves();
		assertTrue(bishop.legalVector.contains(new Move(1,7)));
		assertTrue(bishop.legalVector.contains(new Move(1,1)));
		
	}
	
	@Test
	public void testGetIncorrectMove() {
            Game g = initTest(bishop);
		g.board.tiles[4][4] = bishop;
		bishop.getMoves();
		assertTrue(bishop.legalVector.contains(new Move(4,4)));
		assertTrue(bishop.legalVector.contains(new Move(4,5)));
		
	}

	public Game initTest(Piece p) {
		Game g = new Game();
		g.initGame();
		p.pieces = g.board.tiles;
		return g;
	}
	
}
