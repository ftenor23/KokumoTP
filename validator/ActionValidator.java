package TP_Bis.validator;

import TP_Bis.entity.Player;

public class ActionValidator {
    //si el soldado puede moverse, significa que puede realizar las dos acciones en
    //el siguiente turno
    public boolean onlyAttack(Player me, int id){

        return !soldierCanMove(me,id);
    }

    private boolean soldierCanMove(Player me, int id){
        return me.getBoard().getSoldier(id).canMove();
    }
}
