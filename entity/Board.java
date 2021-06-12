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

    /*public void setSoldiers(int positionOne, int positionTwo, int positionThree, boolean firstTurn){
        //ingresar las posiciones de los soldados
        soldiers=new Soldier[NUMBER_OF_SOLDIERS];
        Scanner in = new Scanner(System.in);
        int id;
        positionOne--;
        positionTwo--;
        positionThree--;
        for(int i=0; i<NUMBER_OF_SOLDIERS;i++){
            id=i+1;
            if(i==0) {
                while(matrix[positionOne].isOccupied() || arrayOutOfBounds(positionOne)){
                    if(matrix[positionOne].isOccupied()) {
                        System.out.println("La posicion en la que quiere ubicar al ninja "+ id + " esta ocupada. Ingrese otra posicion:");
                        positionOne = in.nextInt() - 1;
                    }
                    if(arrayOutOfBounds(positionOne)){
                        System.out.println("La posicion en la que quiere ubicar al ninja " + id + " esta fuera de los limites. Ingrese otra posicion:");
                        positionOne= in.nextInt() - 1;
                    }
                }
                soldiers[i]=new Soldier(true, id, positionOne);
            }
            if(i==1){
                while(matrix[positionTwo].isOccupied() || arrayOutOfBounds(positionTwo)){
                    if(matrix[positionTwo].isOccupied()) {
                        System.out.println("La posicion en la que quiere ubicar al ninja "+id+" esta ocupada. Ingrese otra posicion:");
                        positionTwo = in.nextInt() - 1;
                    }
                    if(arrayOutOfBounds(positionTwo)){
                        System.out.println("La posicion en la que quiere ubicar al ninja "+id+"esta fuera de los limites. Ingrese otra posicion:");
                        positionTwo = in.nextInt() - 1;
                    }
                }
                soldiers[i]=new Soldier(false, id, positionTwo);
            }
            if(i==2){
                while(matrix[positionThree].isOccupied() || arrayOutOfBounds(positionThree)){
                    if(matrix[positionThree].isOccupied()) {
                        System.out.println("La posicion en la que quiere ubicar al ninja "+id+" esta ocupada. Ingrese otra posicion:");
                        positionThree = in.nextInt() - 1;
                    }
                    if(arrayOutOfBounds(positionThree)){
                        System.out.println("La posicion en la que quiere ubicar al ninja "+id+" esta fuera de los limites. Ingrese otra posicion:");
                        positionThree = in.nextInt() - 1;
                    }
                }
                soldiers[i]=new Soldier(false, id, positionThree);
            }
            switch (i) {
                case 0:
                    setSoldierPosition(positionOne, soldiers[i], firstTurn); //restamos uno porque el tablero arranca en cero
                break;
                case 1:
                    setSoldierPosition(positionTwo, soldiers[i], firstTurn);
                break;
                case 2:
                    setSoldierPosition(positionThree, soldiers[i], firstTurn);
                break;
                default:
                    break;
            }


        }
    }

    private boolean arrayOutOfBounds(int position){
        return position<0 || position>= matrix.length;
    }
    public void setSoldierPosition(int position, Soldier soldier, boolean firstTurn) {

        if(matrix[position].isOccupied()){
            if(firstTurn) {
                System.out.println("Posicion ocupada.");
            } else {
                System.out.println("Perdiste tu turno, la posicion esta ocupada.");
            }
        } else if(matrix[position].isImpassable()){
            System.out.println("Perdiste tu turno, el terreno esta inhabilitado");
        } else {
            soldiers[soldier.getId()-1].setPosition(position);
            matrix[position].occupyGrid(soldiers[soldier.getId()-1]);
        }
    }*/

    public int getNumberOfSoldiers() {
        return NUMBER_OF_SOLDIERS;
    }

   /* public void printOwnBoard(){
        int counter=0;
        for(int i = 0; i< matrix.length; i++){
            if(counter==LINE_LENGHT){
                BoardGraphics.printLine(); //damos un salto de linea cuando llegamos a 5
                counter=0;
            }

            if(matrix[i].isOccupied()) {
                if(matrix[i].hasDeadSoldier()){
                    BoardGraphics.printDeadSoldier();
                }
                else if (matrix[i].hasCommander()) {
                    BoardGraphics.printCommander();
                }else{
                    BoardGraphics.printSoldier();
                }
            } else if(matrix[i].isImpassable()){
                BoardGraphics.printImpassableZone();
            } else if(!matrix[i].isOccupied()){
                BoardGraphics.printPosition(i);
            }
            counter++;

        }
        BoardGraphics.printLine();
    }

    public void showEnemyBoard(){
        int counter=0;
        for(int i = 0; i< matrix.length; i++) {
            if (counter == LINE_LENGHT) {
                BoardGraphics.printLine(); //damos un salto de linea cuando llegamos a 5
                counter = 0;
            }

            if (matrix[i].hasDeadSoldier()) {
                BoardGraphics.printDeadSoldier();
            } else if (matrix[i].isImpassable()) {
                BoardGraphics.printImpassableZone();
            }else {
                BoardGraphics.printPosition(i);
            }
            counter++;
        }
        BoardGraphics.printLine();
    }*/

    /*public void attackReceived(int position) {
        int id = -1;
        for (int i = 0; i < soldiers.length; i++) {
            if (soldiers[i].getPosition() == position) {
                id = soldiers[i].getId() - 1;
            }
        }
        if (id > -1) {
            soldiers[id].substractALife();
            matrix[position].attackReceived(soldiers[id]);
        } else {
            matrix[position].attackReceived();
        }
    }*/



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
