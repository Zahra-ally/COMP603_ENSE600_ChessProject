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
public class QueenTest {
    
    public QueenTest() {
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
     * Test of getMoves method, of class Queen.
     */
    @Test
    public void testGetMoves() {
        System.out.println("getMoves");
        Queen instance = null;
        instance.getMoves();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Queen.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Queen instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
   
	
	@Test
	public void testEmptyMoves() {
		Queen queen = new Queen(PieceColor.WHITE,4,4);
		Game g = initTest(queen);
		g.board.tiles[4][4] = queen; 
		g.board.toString();
		queen.getMoves();		 
		assertTrue(queen.legalVector.contains(new Move(5,5)));
		assertTrue(queen.legalVector.contains(new Move(2,2)));
		assertTrue(queen.legalVector.contains(new Move(4,0)));
                		assertTrue(queen.legalVector.contains(new Move(4,7)));

	}
	
	@Test
	public void testIncorrectMoves() {
		Queen queen = new Queen(PieceColor.WHITE,4,4);
		Game g = initTest(queen);
		g.board.tiles[4][4] = queen; 
		g.board.toString();
		queen.getMoves();		 
		assertTrue(queen.legalVector.contains(new Move(0,4)));
		assertTrue(queen.legalVector.contains(new Move(4,4)));
		assertTrue(queen.legalVector.contains(new Move(7,7)));
                assertTrue(queen.legalVector.contains(new Move(1,4)));
                
	}
	 @Test
	public void testGetMovesCaptured() {
		Queen queen = new Queen(PieceColor.WHITE,4,4);
		Game g = initTest(queen);
		g.board.tiles[4][4] = queen; 
		g.board.toString();
		queen.getMoves();		 
		assertTrue(queen.legalVector.contains(new Move(6,6)));
		assertTrue(queen.legalVector.contains(new Move(6,2)));
		assertTrue(queen.legalVector.contains(new Move(6,4)));
	}
	public Game initTest(Piece p) {
		Game g = new Game();
		g.initGame();
		p.pieces = g.board.tiles;
		return g;
	}
}
