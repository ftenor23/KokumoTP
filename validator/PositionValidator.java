package TP_Bis.validator;

import TP_Bis.entity.Board;


public class PositionValidator {
    private final static int BOARD_LENGHT = Board.getBoardSize();


    public static boolean arrayOutOfBounds(int positionOne, int positionTwo, int positionThree){
        if(positionOne<1 || positionOne>BOARD_LENGHT || positionTwo<1 || positionTwo>BOARD_LENGHT || positionThree<1 || positionThree>BOARD_LENGHT){
            return true;
        }
        return false;
    }

    //si el valor ingresado es menor a 1 o mayor al tamaño de la matriz
    //se le informa al jugador para que vuelva a ingresar los datos
    public static boolean arrayOutOfBounds(int position) {

        return position<1 || position>BOARD_LENGHT;
    }

}
