package TP_Bis.entity;



public class Soldier {
    private boolean commander;
    private int remainingLives;
    private boolean canMove;
    private boolean isDead;
    private int id;
    private int position;

    public Soldier(boolean commander, int id, int position) {
        this.commander = commander;
        if(this.commander){
            this.remainingLives=2;
        } else{
            this.remainingLives=1;
        }
        this.canMove=true;
        this.isDead=false;
        this.id=id;
        this.position=position;
    }
    public void substractALife(){

        this.remainingLives--;
        if(this.remainingLives<1){
            this.isDead=true;
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isCommander() {
        return commander;
    }

    public int getId() {
        return id;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean canMove() {
        return canMove;
    }

    public int getPosition() {
        return position;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
