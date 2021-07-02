package TP_Bis.validator;

import TP_Bis.entity.Board;
import TP_Bis.entity.Player;

public class MoveValidator {
    private final static int BOARD_LENGHT=Board.getBoardSize();
    private static final int LEFT=3;
    private static final int RIGHT=4;
    private static final int UP=1;
    private static final int DOWN=2;
    private static final int INVALID_OPTION=-1;
    public boolean isValid(int position){
        return !(position<0);
    }


    public boolean validPosition(int newPosition){
        return newPosition>-1 && newPosition<BOARD_LENGHT;
    }

    //verifica si el movimiento a realizar es valido
    public boolean moveIsValid(int move, int position){

        if(!moveLeftIsValid(move,position)){ //si esta del lado izq, no puede moverse a la izq
            return false;
        }
        if(!moveRightIsValid(move,position)){ //si esta del lado derecho, no puede moverse a la derecha
            return false;
        }
        if(!moveUpIsValid(move,position)){
            return false; //si esta en la fila de arriba, no puede moverse mas arriba
        }
        if(!moveDownIsValid(move,position)){
            return false; //si esta en la ultima fila, no puede ir mas abajo
        }
        if(move==INVALID_OPTION){
            return false;
        }
        return true;
    }

    private boolean moveLeftIsValid(int move, int position){
        if(isOnTheLeftSide(position) && move==LEFT){ //si esta del lado izq, no puede moverse a la izq
            return false;
        }
        return true;
    }

    private boolean moveRightIsValid(int move, int position){
        if(isOnTheRightSide(position) && move == RIGHT){ //si esta del lado derecho, no puede moverse a la derecha
            return false;
        }
        return true;
    }

    private boolean moveUpIsValid(int move, int position){
        if(isUpOnTheBoard(position) && move == UP){
            return false; //si esta en la fila de arriba, no puede moverse mas arriba
        }
        return true;
    }

    private boolean moveDownIsValid(int move, int position){
        if(isDownOnTheBoard(position) && move == DOWN){
            return false; //si esta en la ultima fila, no puede ir mas abajo
        }
        return true;
    }

    public boolean soldierCanMove(Player me, int id){
        return me.getBoard().getSoldier(id).canMove();
    }

    private boolean isUpOnTheBoard(int position){
        return (position+1 == 1 || position+1 == 2 || position+1 == 3
                || position+1 == 4 || position+1 == 5);
    }

    private boolean isDownOnTheBoard(int position){
        return (position+1 == BOARD_LENGHT || position+1 == BOARD_LENGHT-1 || position+1 == BOARD_LENGHT-2
                || position+1 == BOARD_LENGHT-3 || position+1 == BOARD_LENGHT-4);
    }

    private boolean isOnTheRightSide(int position){
        return (position+1)%5==0;
    }

    private boolean isOnTheLeftSide(int position){
        return (position)%5==0;
    }
}
