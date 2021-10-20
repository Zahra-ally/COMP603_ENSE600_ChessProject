package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import model.Database;

import model.Game;
import model.Player;
import model.Move;
import model.PlayerInfo;
import model.piece.Piece;

public class View extends JFrame {

    public ChessBoard[][] cb = new ChessBoard[8][8];
    public Game game;
    Database db = new Database();
    public ChessBoard buffered = null;
    public Piece movingPiece = null;
    public boolean movingPieceSelected = false;
    public ArrayList<Move> destination;

    public boolean canUndo = false;
    public Piece lastCaptured = null;
    public Move targetPos = null;
    public Move sourcePos = null;
    public Piece lastMoved = null;
    public ImageIcon lastCapturedIcon = null;

    Opponent op = new Opponent();

    /*  public String getWhiteName() {
    SignInFrame signIn = new SignInFrame();
    SignUpFrame signUp = new SignUpFrame();
    String whiteName;
    
    if (signUp.getUsername() != null) {
    whiteName = signUp.getUsername();
    } else {
    whiteName = signIn.getUsername();
    }
    
    return whiteName;
    }*/
    public String blackName = "Black";
//op.getOpName();

    public String whiteName = "White";
// getWhiteName();

    public int blackScore = 0, whiteScore = 0;

    public JLabel status = new JLabel(whiteName + "'s turn");
    public JLabel whitescoreLabel = new JLabel(whiteName
            + " score:" + whiteScore + "\t\t\t");
    public JLabel blackscoreLabel = new JLabel(blackName
            + " score:" + blackScore + "\t\t\t");

    private final int FRAME_WIDTH = 560, FRAME_HEIGHT = 600;
    private final Color LIGHT = Color.LIGHT_GRAY;
    private final Color DARK = Color.DARK_GRAY;
    private final String IMAGE_FOLDER = "src/images/";
    private final String[] pieceFormat = new String[]{"R", "N", "B",
        "Q", "K", "B", "N", "R"};

    public JMenuItem restart;
    public JMenuItem forfeit;
    public JMenuItem undo;
    public JMenuItem back;

    public Player turn;
    public boolean bTurn;

    public View() {
        super();
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Chess Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(setMenuBar());
        this.setLayout(new BorderLayout());
        this.add(setPanel(), BorderLayout.NORTH);
        this.add(setChessPanel(), BorderLayout.CENTER);
        this.game = new Game();
        this.game.initGame();
        this.turn = game.player;
        this.bTurn = true;

    }

    /*
        Collection of buttons to imitate a chessboard
     */
    public class ChessBoard extends JButton {

        public final Move move;
        private ImageIcon piece = null;

        ChessBoard(Color color, Move move, ImageIcon image) {
            this.setOpaque(true);
            this.setBorderPainted(false);
            this.setBackground(color);
            this.move = move;
            this.piece = image;
            this.addActionListener(new Controller(this, View.this));
            if (image != null) {
                this.setIcon(image);
            }
        }

        public ImageIcon getPiece() {
            return this.piece;
        }

        public void setPiece(ImageIcon imagePiece) {
            this.piece = imagePiece;
            this.setIcon(imagePiece);
        }

    }

    public JMenuBar setMenuBar() {
        JMenuBar mb = new JMenuBar();
        restart = new JMenuItem("Restart");
        forfeit = new JMenuItem("Forfeit");
        undo = new JMenuItem("Undo");
        back = new JMenuItem("Back");
        setUndo(undo);
        setRestart(restart);
        setBack(back);
        setForfeit(forfeit);
        mb.add(restart);
        mb.add(forfeit);
        mb.add(undo);
        mb.add(back);
        return mb;
    }

    public void setRestart(JMenuItem restart) {
        restart.addActionListener((ActionEvent e) -> {
            refresh();
            blackScore = 0;
            whiteScore = 0;
            whitescoreLabel.setText(whiteName
                    + " score:\t\t" + whiteScore + "\t\t\t");
            blackscoreLabel.setText(blackName
                    + " score:\t\t" + blackScore + "\t\t\t");
        });
    }

    public void refresh() {
        Game game = new Game();
        game.initGame();
        this.game = game;
        turn = game.player;
        bTurn = true;
        status.setText(whiteName + "'s turn");
        setToNull();
        for (int i = 0; i < 64; i++) {
            int row = 7 - i / 8;
            int col = i % 8;
            ImageIcon icon = initPieceIcon(i);
            cb[row][col].setPiece(icon);
        }
    }

    public void setForfeit(JMenuItem restart) {
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateScore();
                refresh();
            }
        });
    }

    public void updateScore() {
        if (!bTurn) {
            whiteScore++;
            whitescoreLabel.setText(whiteName
                    + " score:" + whiteScore + "\t\t\t");
        } else {
            blackScore++;
            blackscoreLabel.setText(blackName
                    + " score:" + blackScore + "\t\t\t");
        }
        String ratio = whiteScore + ":" + blackScore;
        db.updateScoreRatio(whiteName, ratio);
    }

    public void setBack(JMenuItem back) {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Opponent home = new Opponent();
                home.setVisible(true);
                View view = new View();
                view.setVisible(false);
            }
        });
    }

    public void setUndo(JMenuItem undo) {
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!canUndo) {
                    return;
                }
                turn.opponent.undo(lastMoved, sourcePos.row, sourcePos.col, targetPos, lastCaptured);
                ImageIcon curPiece = cb[targetPos.row][targetPos.col].getPiece();
                cb[targetPos.row][targetPos.col].setPiece(lastCapturedIcon);
                cb[sourcePos.row][sourcePos.col].setPiece(curPiece);
                turn = turn.opponent;
                bTurn = !bTurn;
                String name;
                if (bTurn) {
                    name = whiteName;
                } else {
                    name = blackName;
                }
                status.setText(name + "'s turn");
                canUndo = false;
            }
        });
    }

    public JPanel setChessPanel() {
        JPanel chessPanel = new JPanel(new GridLayout(8, 8));
        chessPanel.setSize(FRAME_WIDTH, FRAME_WIDTH);

        for (int i = 0; i < 64; i++) {
            int row = 7 - i / 8;
            int col = i % 8;
            ImageIcon icon = initPieceIcon(i);
            ChessBoard bt = new ChessBoard(setSpaceColor(i), new Move(row, col), icon);
            chessPanel.add(bt, i);
            cb[row][col] = bt;
        }
        return chessPanel;
    }

    public ImageIcon initPieceIcon(int i) {
        ImageIcon icon = null;
        String color;
        String piece;
        if (!(i >= 16 && i < 48)) {
            if (i < 16) {
                color = "B";
            } else {
                color = "W";
            }
            if (i >= 8 && i < 56) {
                piece = "P";
            } else {
                piece = pieceFormat[i % 8];
            }
            try {
                Image im = ImageIO.read(new File(IMAGE_FOLDER + color + piece + ".gif"));
                icon = new ImageIcon(im);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return icon;
    }

    public JPanel setPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(status, BorderLayout.CENTER);
        panel.add(blackscoreLabel, BorderLayout.EAST);
        panel.add(whitescoreLabel, BorderLayout.WEST);
        return panel;
    }

    public Color setSpaceColor(int i) {
        int row = i / 8, col = i % 8;
        if (row % 2 == col % 2) {
            return LIGHT;
        } else {
            return DARK;
        }
    }

    public void setToNull() {
        buffered = null;
        movingPieceSelected = false;
        destination = null;
        movingPiece = null;
        canUndo = false;
        lastCaptured = null;
        targetPos = null;
        sourcePos = null;
        lastMoved = null;
        lastCapturedIcon = null;
    }
}
