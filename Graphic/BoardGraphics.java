package TP_Bis.Graphic;

import TP_Bis.entity.Board;

public class BoardGraphics extends Graphics{

    //BOARDMANAGER
    public void printOwnBoard(Board board){
        int counter=0;
        for(int i = 0; i< board.getMatrix().length; i++){
            if(counter==board.getLineLenght()){
                System.out.println(" "); //damos un salto de linea cuando llegamos a 5
                counter=0;
            }

            if(board.getMatrix()[i].isOccupied()) {
                if(board.getMatrix()[i].hasDeadSoldier()){
                    System.out.print("X  ");
                }
                else if (board.getMatrix()[i].hasCommander()) {
                    System.out.print("C  ");
                }else{
                    System.out.print("S  ");
                }
            } else if(board.getMatrix()[i].isImpassable()){
                System.out.print("*  ");
            } else if(!board.getMatrix()[i].isOccupied()){
                if (i < 9) {
                    System.out.print(i + 1 + "  ");
                } else {
                    System.out.print(i+1 + " ");
                }
            }
            counter++;

        }
        System.out.println("");
    }

    public void showEnemyBoard(Board enemyBoard){
        System.out.println("\nTablero enemigo");
        int counter=0;
        for(int i = 0; i< enemyBoard.getMatrix().length; i++) {
            if (counter == enemyBoard.getLineLenght()) {
                System.out.println(" "); //damos un salto de linea cuando llegamos a 5
                counter = 0;
            }

            if (enemyBoard.getMatrix()[i].hasDeadSoldier()) {
                System.out.print("X  ");
            } else if (enemyBoard.getMatrix()[i].isImpassable()) {
                System.out.print("*  ");
            }else {
                if (i < 10) {
                    System.out.print(i + 1 + "  ");
                } else {
                    System.out.print(i+1 + " ");
                }
            }
            counter++;
        }
        System.out.println("\n\n");
    }

    public void positionOccupied(int id){
        System.out.println("La posicion en la que quiere ubicar al ninja "+ id + " esta ocupada. Ingrese otra posicion:");

    }

    public void positionOutOfBounds(int id){
        System.out.println("La posicion en la que quiere ubicar al ninja " + id + " esta fuera de los limites. Ingrese otra posicion:");
    }

    public void occupiedPosition(){
        System.out.println("Posicion ocupada.");
    }

    public void lostTurnPositionOccupied(){
        System.out.println("Perdiste tu turno, la posicion esta ocupada.");
    }

    public void lostTurnImpassableZone(){
        System.out.println("Perdiste tu turno, el terreno esta inhabilitado");
    }
}

