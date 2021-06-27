package TP_Bis.entity;


public class Player {
    private Board myBoard;
    private String playerName;
    private boolean firstTurn;
    private boolean commanderIsDead;
    private boolean informCommanderIsDead;
    private boolean informCommanderWasAttacked;
    private boolean informSoldierOneIsDead;
    private boolean informSoldierTwoIsDead;

    public Player(String name) {
        this.playerName = name;
        this.myBoard = new Board();
        this.firstTurn=true;
        this.commanderIsDead=false;
        this.informCommanderIsDead=true;
        this.informCommanderWasAttacked=true;
        this.informSoldierOneIsDead=true;
        this.informSoldierTwoIsDead=true;
    }

    public boolean informCommanderWasAttacked() {
        return informCommanderWasAttacked;
    }

    public void setInformCommanderWasAttacked(boolean informCommanderWasAttacked) {
        this.informCommanderWasAttacked = informCommanderWasAttacked;
    }

    public boolean informSoldierOneIsDead() {
        return informSoldierOneIsDead;
    }

    public void setInformSoldierOneIsDead(boolean informSoldierOneIsDead) {
        this.informSoldierOneIsDead = informSoldierOneIsDead;
    }

    public boolean informSoldierTwoIsDead() {
        return informSoldierTwoIsDead;
    }

    public void setInformSoldierTwoIsDead(boolean informSoldierTwoIsDead) {
        this.informSoldierTwoIsDead = informSoldierTwoIsDead;
    }

    public boolean informCommanderIsDead() {
        return informCommanderIsDead;
    }

    public void setInformCommanderIsDead(boolean informCommanderIsDead) {
        this.informCommanderIsDead = informCommanderIsDead;
    }

    public Board getBoard(){
        return this.myBoard;
    }

    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
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
