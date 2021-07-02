package TP_Bis.entity;


public class Player {
    private Board myBoard;
    private String playerName;
    private boolean firstTurn;
    private boolean commanderIsDead;
    private boolean informCommanderIsDead;
    private boolean informCommanderWasAttacked;
    private boolean[] informSoldierIsDead;

    public Player(String name) {
        this.playerName = name;
        this.myBoard = new Board();
        this.firstTurn=true;
        this.commanderIsDead=false;
        this.informCommanderIsDead=true;
        this.informCommanderWasAttacked=true;
        this.informSoldierIsDead=inicializeArrayAsTrue();
    }

    public boolean informCommanderWasAttacked() {
        return informCommanderWasAttacked;
    }

    public void setInformCommanderWasAttacked(boolean informCommanderWasAttacked) {
        this.informCommanderWasAttacked = informCommanderWasAttacked;
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

    public boolean getInformSoldierIsDead(int id) {
        return informSoldierIsDead[id];
    }

    public boolean[] getInformSoldierIsDead() {
        return informSoldierIsDead;
    }

    public void setInformSoldierIsDead(boolean informSoldierIsDead, int id) {
        this.informSoldierIsDead[id] = informSoldierIsDead;
    }

    private boolean[] inicializeArrayAsTrue(){
        final int CANT_OF_COMMANDERS=1;
        //LE RESTAMOS UNO AL ARRAY PORQUE EL MENSAJE PARA INFORMAR
        //QUE EL COMANDANTE ESTA MUERTO SE REALIZA POR OTRO MEDIO
        boolean[] array=new boolean[Board.getNumberOfSoldiers()-CANT_OF_COMMANDERS];
        for(int i=0;i<array.length;i++){
            array[i]=true;
        }
        return array;
    }
}
