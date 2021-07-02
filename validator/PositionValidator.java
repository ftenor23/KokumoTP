package TP_Bis.validator;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.BoardGraphics;
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
        //le sumamos uno a position para evaluar lo ingresado por el usuario
        //que son posiciones de 1 a 25 y no de 0 a 24 como en la matriz
        return position<1 || position>BOARD_LENGHT;
    }

    public static boolean positionIsValid(Board board, int position, int i){
        int id=i+1;

        while (board.getMatrix()[position].isOccupied() || arrayOutOfBounds(position+1)) {
            if (board.getMatrix()[position].isOccupied()) {
                //si la posicion esta ocupada por otro soldado pedimos que ingrese una nueva
                BoardGraphics.positionOccupied(id);
                position = EnterData.nextInt();
            }
            while (arrayOutOfBounds(position)) {
                //si la nueva posicion esta fuera de los limites, pedimos
                //al usuario qe ingrese una nueva hasta que cumpla con ese requisito
                BoardGraphics.positionOutOfBounds(id);
                position = EnterData.nextInt();
            }
        }

        return true;
    }

}
