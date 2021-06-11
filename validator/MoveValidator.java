package TP_Bis.validator;

import TP_Bis.entity.Board;
import TP_Bis.entity.Player;

public class MoveValidator {
    private final static int MIN_MOVE = 1;
    private final static int MAX_MOVE =4;
    private Board board=new Board();
    public boolean isValid(int position){
        return !(position<0);
    }

    public boolean moveIsValid(int move){
        return !(move<MIN_MOVE || move>MAX_MOVE);
    }

    public boolean validPosition(int newPosition){
        return newPosition>0 && newPosition<board.getMatrix().length;
    }

    public boolean moveIsValid(int move, int position){
        final int LEFT=3;
        final int RIGHT=4;
        final int UP=1;
        final int DOWN=2;

        if(position%5==0&&move==LEFT){
            return false;
        }
        if((position-1)%5==0 && move == RIGHT){
            return false;
        }
        if((position == 1 || position == 2 || position == 3
        || position == 4 || position == 5) && move == UP){
            return false;
        }
        if((position == 25 || position == 24 || position == 23
                || position == 22 || position == 21) && move == DOWN){
            return false;
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
