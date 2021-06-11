package TP_Bis.validator;

import TP_Bis.entity.Board;

import java.util.Scanner;

public class PositionValidator {
    private Board board;

    public PositionValidator() {
        board=new Board();
    }

    public boolean isValid(int position, int ninjaId){
        Scanner in=new Scanner(System.in);
        while(arrayOutOfBounds(position)){
            System.out.println("La posicion en la que quiere ubicar al ninja " + ninjaId + " esta fuera de los limites. Ingrese otra posicion:");
            position= in.nextInt() - 1;
        }
        return true;
    }



    public boolean arrayOutOfBounds(int positionOne, int positionTwo, int positionThree){
        if(positionOne<1 || positionOne>25 || positionTwo<1 || positionTwo>25 || positionThree<1 || positionThree>25){
            return true;
        } //iria en validator
        return false;
    }

    public boolean arrayOutOfBounds(int position) {

        return position<0 || position>board.getMatrix().length;
    }

}
