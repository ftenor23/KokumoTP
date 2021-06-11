package TP_Bis.validator;

import TP_Bis.entity.Player;

public class AttackValidator {

    public boolean soldierCanMove(Player me, int id) {
        return me.getBoard().getSoldier(id).canMove();
    }


    public boolean zoneIsImpassable(Player enemy, int position){
        return enemy.getBoard().getMatrix()[position].isImpassable();
    }

    public boolean positionIsOccupied(Player enemy, int position){
        return enemy.getBoard().getMatrix()[position].isOccupied();
    }


}