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
    protected void substractALife(){

        this.remainingLives--;
        if(this.remainingLives<1){
            this.isDead=true;
        }
    }

    protected boolean isDead() {
        return isDead;
    }

    protected boolean isCommander() {
        return commander;
    }

    public int getId() {
        return id;
    }

    protected void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    protected boolean canMove() {
        return canMove;
    }

    protected int getPosition() {
        return position;
    }

    protected void setDead(boolean dead) {
        isDead = dead;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
