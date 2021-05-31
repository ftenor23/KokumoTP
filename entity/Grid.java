package TP_Bis.entity;



public class Grid {
    private boolean occupied;
    private boolean impassable;
    private boolean deadSoldier;
    private boolean hasCommander;

    public Grid() {
        this.occupied=false;
        this.impassable=false;
        this.deadSoldier=false;
        this.hasCommander=false;
    }

    protected void occupyGrid(Soldier soldier){
        if(soldier.isCommander()){
            this.hasCommander=true;
        }
        this.occupied=true;
    }

    protected void attackReceived(Soldier soldier){
        if(soldier.isDead()) {
            this.deadSoldier = true;
            this.impassable = true;
            this.occupied=false;
        }
    }

    protected void attackReceived(){
        this.impassable=true;
    }

    protected void emptyGrid(){
        this.occupied=false;
        this.hasCommander=false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isImpassable() {
        return impassable;
    }

    public boolean hasDeadSoldier() {
        return deadSoldier;
    }

    public boolean hasCommander() {
        return hasCommander;
    }
}
