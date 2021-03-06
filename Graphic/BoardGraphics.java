package TP_Bis.Graphic;

import TP_Bis.entity.Board;

public abstract class BoardGraphics extends Graphics{


    public static void printOwnBoard(Board board){
        System.out.println("Mi tablero");
        int counter=0;
        for(int i = 0; i< board.getMatrix().length; i++){
            if(counter==board.getLineLenght()){
                printLine(); //damos un salto de linea cuando llegamos a 5
                counter=0;
            }

            if(board.getMatrix()[i].isImpassable()){
                if(board.getMatrix()[i].hasDeadSoldier()){
                    printDeadSoldier();
                } else {
                    printImpassableZone();
                }
            } else if(board.getMatrix()[i].isOccupied()){
                if(board.getMatrix()[i].hasCommander()){
                    printCommander();
                } else {
                    printSoldier();
                }
            } else {
                printPosition(i);
            }
            counter++;

        }
        printLine();
    }

    public static void showEnemyBoard(Board enemyBoard){
        System.out.println("\nTablero enemigo");
        int counter=0;
        for(int i = 0; i< enemyBoard.getMatrix().length; i++) {
            if (counter == enemyBoard.getLineLenght()) {
                BoardGraphics.printLine(); //damos un salto de linea cuando llegamos a 5
                counter = 0;
            }

            if (enemyBoard.getMatrix()[i].hasDeadSoldier()) {
                printDeadSoldier();
            } else if (enemyBoard.getMatrix()[i].isImpassable()) {
                printImpassableZone();
            }else {
                printPosition(i);
            }
            counter++;
        }
        BoardGraphics.printLine();
        BoardGraphics.printLine();
    }

    public static void positionOccupied(int id){
        System.out.println("La posicion en la que quiere ubicar al soldado "+ (id-1) + " esta ocupada. Ingrese otra posicion:");

    }

    public static void positionOutOfBounds(int id){

        System.out.println("La posicion en la que quiere ubicar al soldado " + (id-1) + " esta fuera de los limites. Ingrese otra posicion:");
    }

    public static void occupiedPosition(){
        System.out.println("Posicion ocupada.");
    }

    public static void lostTurnPositionOccupied(){
        System.out.println("Perdiste tu turno, la posicion esta ocupada.");
    }

    public static void lostTurnImpassableZone(){
        System.out.println("Perdiste tu turno, el terreno esta inhabilitado");
    }

    private static void printDeadSoldier(){
        System.out.print("X  ");
    }

    private static void printImpassableZone(){
        System.out.print("*  ");
    }

    private static void printCommander(){
        System.out.print("C  ");
    }

    private static void printSoldier(){
        System.out.print("S  ");
    }

    private static void printPosition(int i){
        if (i < 9) {
            System.out.print(i + 1 + "  ");
        } else {
            System.out.print(i+1 + " ");
        }
    }


}

