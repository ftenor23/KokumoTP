package TP_Bis.validator;

import TP_Bis.entity.Board;
import TP_Bis.entity.Player;

public class MoveValidator {
    private final static int BOARD_LENGHT=Board.getBoardSize();
    public boolean isValid(int position){
        return !(position<0);
    }


    public boolean validPosition(int newPosition){
        return newPosition>-1 && newPosition<BOARD_LENGHT;
    }

    public boolean moveIsValid(int move, int position){
        final int LEFT=3;
        final int RIGHT=4;
        final int UP=1;
        final int DOWN=2;

        if((position)%5==0 && move==LEFT){ //si esta del lado izq, no puede moverse a la izq
            return false;
        }
        if((position+1)%5==0 && move == RIGHT){ //si esta del lado derecho, no puede moverse a la derecha
            return false;
        }
        if((position+1 == 1 || position+1 == 2 || position+1 == 3
        || position+1 == 4 || position+1 == 5) && move == UP){
            return false; //si esta arriba, no puede moverse mas arriba
        }
        if((position+1 == BOARD_LENGHT || position+1 == BOARD_LENGHT-1 || position+1 == BOARD_LENGHT-2
                || position+1 == BOARD_LENGHT-3 || position+1 == BOARD_LENGHT-4) && move == DOWN){
            return false; //si esta en la ultima fila, no puede ir mas abajo
        }
        if(move==-1){
            return false;
        }
        return true;
    }

    public boolean soldierCanMove(Player me, int id){
        return me.getBoard().getSoldier(id).canMove();
    }
}
