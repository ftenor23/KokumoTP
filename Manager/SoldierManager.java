package TP_Bis.Manager;

import TP_Bis.entity.Board;
import TP_Bis.entity.Soldier;

import java.util.Scanner;

public class SoldierManager {
    /*public boolean save(Soldier soldiers[], int positionOne, int positionTwo, int positionThree, Board myBoard){
        Scanner in = new Scanner(System.in);
        int id;
        positionOne--;
        positionTwo--;
        positionThree--;
        for(int i=0; i<soldiers.length;i++){
            id=i+1;
            if(i==0) {
                while(myBoard.getMatrix()[positionOne].isOccupied() || arrayOutOfBounds(positionOne, myBoard)){
                    if(myBoard.getMatrix()[positionOne].isOccupied()) {
                        System.out.println("La posicion en la que quiere ubicar al ninja "+ id + " esta ocupada. Ingrese otra posicion:");
                        positionOne = in.nextInt() - 1;
                    }
                    if(arrayOutOfBounds(positionOne, myBoard)){
                        System.out.println("La posicion en la que quiere ubicar al ninja " + id + " esta fuera de los limites. Ingrese otra posicion:");
                        positionOne= in.nextInt() - 1;
                    }
                }
                soldiers[i]=new Soldier(true, id, positionOne);
            }
            if(i==1){
                while(myBoard.getMatrix()[positionTwo].isOccupied() || arrayOutOfBounds(positionTwo, myBoard)){
                    if(myBoard.getMatrix()[positionTwo].isOccupied()) {
                        System.out.println("La posicion en la que quiere ubicar al ninja "+id+" esta ocupada. Ingrese otra posicion:");
                        positionTwo = in.nextInt() - 1;
                    }
                    if(arrayOutOfBounds(positionTwo, myBoard)){
                        System.out.println("La posicion en la que quiere ubicar al ninja "+id+"esta fuera de los limites. Ingrese otra posicion:");
                        positionTwo = in.nextInt() - 1;
                    }
                }
                soldiers[i]=new Soldier(false, id, positionTwo);
            }
            if(i==2){
                while(myBoard.getMatrix()[positionThree].isOccupied() || arrayOutOfBounds(positionThree)){
                    if(myBoard.getMatrix()[positionThree].isOccupied()) {
                        System.out.println("La posicion en la que quiere ubicar al ninja "+id+" esta ocupada. Ingrese otra posicion:");
                        positionThree = in.nextInt() - 1;
                    }
                    if(arrayOutOfBounds(positionThree,myBoard)){
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

    private boolean arrayOutOfBounds(int position, Board myBoard){
        return position<0 || position>= myBoard.getMatrix().length;
    }*/
}
