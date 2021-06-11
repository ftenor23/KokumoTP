package TP_Bis.validator;

import TP_Bis.entity.Player;

public class ActionValidator {
    public boolean onlyAttack(Player me, int id){
        if(soldierCanMove(me, id)){
            return false;
        }
        return true;
    }

    private boolean soldierCanMove(Player me, int id){
        return me.getBoard().getSoldier(id).canMove();
    }
}
