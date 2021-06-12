package TP_Bis.entity;

import TP_Bis.Manager.PlayerManager;

import java.util.Scanner;


public class Player {
    private Board myBoard;
    private String playerName;
    private Scanner in;
    private boolean firstTurn;
    private boolean commanderIsDead;

    public Player(String name) {
        this.playerName = name;
        this.myBoard = new Board();
        this.firstTurn=true;
        this.commanderIsDead=false;
        in = new Scanner(System.in);
    }

    public Board getBoard(){
        return this.myBoard;
    }

    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }

    public Board getMyBoard() {
        return myBoard;
    }

    public void setMyBoard(Board myBoard) {
        this.myBoard = myBoard;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isFirstTurn() {
        return firstTurn;
    }

    public String getPlayerName() {
        return playerName;
    }

}
