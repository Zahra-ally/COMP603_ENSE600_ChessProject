package model;

import gui.Opponent;
import gui.View;
import gui.Welcome;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Game {

    public Board board = new Board();
    public Player player;
    public Player op;
    public Opponent home = new Opponent();

    
    /*
    Initialise the game
    */
    public void initGame() {

        
            this.player = new Player(this, PieceColor.WHITE);

            this.op = new Player(this, PieceColor.BLACK);
      

    
   
     

    this.player.setOpponent (
    this.op);
         

    this.op.setOpponent (
    this.player);
         

    this.player.noMovesResult ();
}

public void gameLoop() {
        while (true) {
                    this.boardTofile();

            System.out.println("Your turn:");

            player.play();
            System.out.println("You opponent's turn:");
            op.play();
        }
    }

    public static void main(String[] args) {
         Database dbManager = new Database();
        System.out.println(dbManager.connect());
        Welcome welcome=new Welcome();
        welcome.setVisible(true);
       
    }
    
    
    
    public void boardTofile() {
    
    String sBoard = "";
    
    try {
    
    FileWriter boardWriter = new FileWriter("board.txt");
    for (int i = 7; i >= 0; i--) {
    for (int j = 0; j < 8; j++) {
    sBoard += board.tiles[j][i] + " ";
    }
    sBoard += "\n";
    
    boardWriter.write(sBoard);
    
    }
    boardWriter.close();
    
    } catch (IOException e) {
    System.out.println("An error occurred.");
    }
    
    }
static void displayFile() throws FileNotFoundException {
        try {
            File file = new File("Scores.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            for (int k = 0; k < 4; k++) {

                String data = br.readLine();
                if (data != null) {
                    String[] arrOfStr = data.split("#");

                    for (String a : arrOfStr) {
                        System.out.println(a);
                    }

                }
            }
            br.close();
        } catch (IOException ex) {
        }

    }

    static void updateFile(int num1, int num2, int draw) throws FileNotFoundException {

        // String[] file = new String[3];
        String line;
        int[] newFile = new int[4];

        int pos = 0, num = 0;
        String s = "";
        String filename = "Scores.txt";

        try ( BufferedReader br = new BufferedReader(new FileReader(filename))) {

            for (int i = 0; i < 4; i++) {
                line = br.readLine();

                pos = line.indexOf("#");
                s = line.substring(pos + 1);
                if (s.equals("")) {
                    newFile[i] = 0;
                } else {
                    num = Integer.valueOf(s);
                    newFile[i] = num;
                }

            }

            newFile[1] = num1 + newFile[1];
            newFile[2] = num2 + newFile[2];
            newFile[3] = draw + newFile[3];

            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("\n");

            myWriter.write("Player1" + "#" + newFile[1]);
            myWriter.write("\n");
            myWriter.write("Player2" + "#" + newFile[2]);
            myWriter.write("\n");
            myWriter.write("Draw" + "#" + newFile[3]);

            myWriter.close();

        } catch (IOException ex) {
        }

    }

    static void mapToFile(HashMap<String, Double> map1, HashMap<String, Double> map2) {

        File file = new File("bet.txt");

        try {
            FileWriter fr = new FileWriter(file, true);

            BufferedWriter bf = new BufferedWriter(new FileWriter(file));

            // iterate map entries
            for (Map.Entry<String, Double> entry
                    : map1.entrySet()) {

                bf.write(entry.getKey() + ":"
                        + entry.getValue());

                bf.newLine();
            }
            for (Map.Entry<String, Double> entry
                    : map2.entrySet()) {

                bf.write(entry.getKey() + ":"
                        + entry.getValue());

                bf.newLine();
            }

            bf.close();
            fr.close();
        } catch (IOException e) {
        }

    }
}
