/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author zahra
 */
public class PlayerInfo {
/*
    Storing data of the player
    */
    private String firstName;
    private String lastName;

    private String username;
    private String email;
    private String password;
    private String scoreRatio;
    private PieceColor color;
    private String opName;

    PlayerInfo(String username, String sRatio) {
        this.scoreRatio = sRatio;
        this.username = username;
    }

   
    public PlayerInfo(String opName) {
        this.opName = opName;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public PlayerInfo(String firstName, String lastName, String username, String email, String password, String sRatio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.scoreRatio = sRatio;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PlayerInfo(PieceColor color) {
        this.color = color;

    }

    public PlayerInfo(String username, String opName, String scoreRatio) {
        this.username = username;
        this.opName = opName;
        this.scoreRatio = scoreRatio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getScoreRatio() {
        return scoreRatio;
    }

    public void setScore(String score, String opScore) {
        this.scoreRatio = score + ":" + opScore;
    }

}
