package TP_Bis.entity;

public class Data {
    /*Esta clase es la que utilizamos para intercambiar datos entre cliente y servidor*/

    private Board hostBoard;
    private Board clientBoard;
    private String hostName;
    private String clientName;
    private boolean gameOver;
    private boolean connectionLost;

    public boolean isGameOver() {
        return this.gameOver;
    }

    public boolean connectionLost() {
        return connectionLost;
    }

    public void setConnectionLost(boolean connectionLost) {
        this.connectionLost = connectionLost;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Board getHostBoard() {
        return hostBoard;
    }

    public void setHostBoard(Board hostBoard) {
        this.hostBoard = hostBoard;
    }

    public Board getClientBoard() {
        return clientBoard;
    }

    public void setClientBoard(Board clientBoard) {
        this.clientBoard = clientBoard;
    }
}
