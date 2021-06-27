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

    public static boolean arrayOutOfBounds(int position) {

        return position<0 || position>BOARD_LENGHT;
    }

}
