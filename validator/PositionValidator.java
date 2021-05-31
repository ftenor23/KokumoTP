package TP_Bis.validator;

import TP_Bis.entity.Board;

import java.util.Scanner;

public class PositionValidator {
    private Board board;
    public boolean isValid(int position, int ninjaId){
        Scanner in=new Scanner(System.in);
        while(outOfBounds(position)){
            System.out.println("La posicion en la que quiere ubicar al ninja " + ninjaId + " esta fuera de los limites. Ingrese otra posicion:");
            position= in.nextInt() - 1;
        }
        return true;
    }



    private boolean outOfBounds(int position){
        return position<0||position>board.getMatrix().length;
    }


}
