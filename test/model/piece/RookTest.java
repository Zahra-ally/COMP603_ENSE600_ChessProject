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
public class RookTest {

    public RookTest() {
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
     * Test of getMoves method, of class Rook.
     */
    @Test
    public void testGetMoves() {
        System.out.println("getMoves");
        Rook instance = null;
        instance.getMoves();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Rook.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Rook instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetMovesCaptured () {
        Rook rook = new Rook(PieceColor.WHITE, 4, 4);
        Game g = initTest(rook);
        g.board.tiles[4][4] = rook;
        g.board.toString();
        rook.getMoves();
        assertTrue(rook.legalVector.contains(new Move(4, 6)));
    }

    @Test
    public void testEmptyMoves() {
        Rook rook = new Rook(PieceColor.WHITE, 4, 4);
        Game g = initTest(rook);
        g.board.tiles[4][4] = rook;
        g.board.toString();
        rook.getMoves();
        assertTrue(rook.legalVector.contains(new Move(4, 0)));
        assertTrue(rook.legalVector.contains(new Move(4, 7)));
        assertTrue(rook.legalVector.contains(new Move(2, 4)));

    }

   

    @Test
    public void testIncorrectMoves() {
       Rook rook = new Rook(PieceColor.WHITE, 4, 4);
        Game g = initTest(rook);
        g.board.tiles[4][4] = rook;
        g.board.toString();
        rook.getMoves();
        assertTrue(rook.legalVector.contains(new Move(4, 4)));
                assertTrue(rook.legalVector.contains(new Move(3, 2)));

    }

    public Game initTest(Piece p) {
        Game g = new Game();
        g.initGame();
        p.pieces = g.board.tiles;
        return g;
    }

}
