/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.PlayerInfo;


/**
 *
 * @author zahra
 */
public class Database {
      // Database Attributes
    private final String dbURL = "jdbc:derby://localhost:1527/Players";
    private final String dbUsername = "chess";
    private final String dbPassword = "chess";
    private Connection conn;
    private Statement statement;

    /**
     * Constructs a Database
     */
    public Database() {
        if (!connect()) {
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(null, "A connection to the database could not be established.", "", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
            System.exit(0);
        }
        dbSetup();
    }

   
    protected boolean connect() {
        boolean success = false;
        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println("DATABASE: Connected");
            statement = conn.createStatement();
            success = true;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return success;
    }

   
    protected void dbSetup() {
        try {
            // Create Player table if it doesn't exist
            if (!checkTableExists("Players")) {
                //statement.executeUpdate("DROP TABLE Players"); // --> Can be used here to reset 'Players' table.
                statement.executeUpdate("CREATE TABLE Players (Username VARCHAR(50), FirstName VARCHAR(50), LastName VARCHAR(50), Email VARCHAR(100), "
                    + "Password VARCHAR(50), OpponentName VARCHAR(50),  ScoreRatio VARCHAR(50)");
                System.out.println("Players table was created.");

                // Load sample data into Players table
                statement.executeUpdate("INSERT INTO Players (Username, FirstName, LastName, Email,Password,OpponentName,ScoreRatio) VALUES "
                        + "('Em',Emily', 'emily@example.com', 'example','Quinn','9:1'), "
                        + "('TT',Thomas Tester', 'test@example.com', 'test','Peter','7:3'), "
                        + "('JJ',Jane Doe', 'doe@example.com', 'jane','Love,'4:10')");
                System.out.println("Sample data was added to 'Players' table.");
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    /**
     * Checks if chosen table exists in database
     *
     * @param tableName the table to check for
     * @return true if table exists, else false
     */
    protected boolean checkTableExists(String newTableName) {
         boolean flag = false;
        try {

            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);//types);
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + "  is there");
                    flag = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
        }
        return flag;
    }

   
    public boolean validLogin(String username, String password) {
        boolean validLogin = false;
        try {
            ResultSet rs = statement.executeQuery("SELECT FirstName FROM Players WHERE Username = '" + username + "' AND Password = '" + password + "'");
            if (rs.next()) {
                // Login is Valid
                validLogin = true;
                System.out.println("success login");
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return validLogin;
    }

   
    public boolean registerUser(String username,String firstName,String lastName,String email, String password) {
        boolean success = false;
        try {
            if (!validLogin(username, password)) {
             
                  statement.executeUpdate("INSERT INTO Players "
                        + "VALUES('" + username + "', '" +firstName+ "', '" +lastName+ "', '" +email+ "', '" + password + "', '" + "" + ",'0:0')");
                success = true;
                System.out.println("User " + firstName + " was successfully added into the database");
            } else if (validLogin(username, password)) {
                System.out.println("User " + firstName + " already exists, choose other credentials.");
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return success;
    }

    
    public boolean addOpponent(String opName,PlayerInfo player) {
        boolean added=false;
    
     try {
            statement.executeUpdate("INSERT INTO Players" +"VALUES"+ player.getOpName() + " WHERE Username = '" + player.getUsername() + "'");
             added = true;
                System.out.println("User " + opName + " was successfully added into the database");
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return added;
    
    }
    
    protected void updateHighscore(PlayerInfo player) {
        try {
            statement.executeUpdate("UPDATE Players SET Score = " + player.getScoreRatio() + " WHERE Username = '" + player.getUsername() + "'");
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Retrieves high scores for ranking table
     *
     * @return the list of players' high scores ratios
     */
    protected ArrayList<PlayerInfo> getHighScores() {
        ArrayList<PlayerInfo> players = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM Players");
            while (rs.next()) {
                players.add(new PlayerInfo(rs.getString("UserName"), rs.getString("ScoreRation")));
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return players;
    }

    /**
     * Loads the player from the database
     *
     * @param email the player's email address
     * @param pass the player's password
     * @return a Player object of the current user
     */
    protected PlayerInfo loadPlayer(String username, String pass) {
        PlayerInfo player = null;
        if (validLogin(username, pass)) {
            try {
                ResultSet rs = statement.executeQuery("SELECT * FROM Players WHERE Username = '" + username + "' AND password = '" + pass + "'");
                if (rs.next()) {
                    // Retrieve Player Information and Construct the player
                    player = new PlayerInfo(rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Username"), rs.getString("Email"), rs.getString("Password"), rs.getString("ScoreRatio"));
                }
                rs.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return player;
    }
}
