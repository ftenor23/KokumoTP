package TP_Bis.entity;


import TP_Bis.Graphic.BoardGraphics;

import java.util.Scanner;

public class Board {
    private Grid[] matrix;
    private Soldier[] soldiers;
    private final static int BOARD_SIZE = 25;
    private final static int NUMBER_OF_SOLDIERS = 3;
    private final static int LINE_LENGHT = 5;

    public Board() {
        matrix =new Grid[BOARD_SIZE];
        for(int i=0; i< matrix.length; i++){
            matrix[i] = new Grid();
        }
        soldiers = new Soldier[NUMBER_OF_SOLDIERS];
    }


    public int getNumberOfSoldiers() {
        return NUMBER_OF_SOLDIERS;
    }




    public int getSoldierPosition(int id){

        for (int i = 0; i < soldiers.length; i++) {
            if(soldiers[i].getId()==id){
                return soldiers[i].getPosition();
            }
        }
        return -1;
    }

    public Grid[] getMatrix() {
        return matrix;
    }

    public Soldier getSoldier(int id){
        for(int i=0; i<soldiers.length;i++){
            if(soldiers[i].getId()==id){
                return soldiers[i];
            }
        }
        return null;
    }

    public boolean allSoldiersAreDead(){
        boolean areDead = true;
        for(int i=0; i<soldiers.length; i++){
            if(!soldiers[i].isDead()){
                return false;
            }
        }
        return areDead;
    }

    public boolean commanderIsDead(){
        for(int i=0; i<soldiers.length;i++){
            if(soldiers[i].isCommander() && soldiers[i].isDead()){
                return true;
            }
        }
        return false;
    }

    public Soldier[] getSoldiers() {
        return soldiers;
    }

    public int getLineLenght() {
        return LINE_LENGHT;
    }

    public int soldiersAlive(){
        int soldiersAlive=0;
        for(int i=0;i<soldiers.length;i++){
            if(!soldiers[i].isDead()){
                soldiersAlive++;
            }
        }
        return soldiersAlive;
    }
}
