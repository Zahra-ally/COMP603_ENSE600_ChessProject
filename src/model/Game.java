package model;

import gui.Home;
import gui.View;
import gui.Welcome;

public class Game {

    public Board board = new Board();
    public Player player;
    public Player op;
    public Home home = new Home();

    public void initGame() {

        
            this.player = new Player(this, PieceColor.WHITE);

            this.op = new Player(this, PieceColor.BLACK);
      

    
   
     

    this.player.setOpponent (
    this.op);
         

    this.op.setOpponent (
    this.player);
         

    this.player.checkEndingCondition ();
}

public void gameLoop() {
        while (true) {
            
            System.out.println("Your turn:");

            player.play();
            System.out.println("You opponent's turn:");
            op.play();
        }
    }

    public static void main(String[] args) {
        Welcome welcome=new Welcome();
        welcome.setVisible(true);
        /*View view = new View();
        view.setVisible(true);*/
    }

}
