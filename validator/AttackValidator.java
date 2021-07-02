package TP_Bis.validator;

import TP_Bis.Graphic.AttackGraphics;
import TP_Bis.Manager.AttackManager;
import TP_Bis.entity.Board;
import TP_Bis.entity.Player;

public class AttackValidator {

    private static boolean soldierCanMove(Player me, int id) {
        return me.getBoard().getSoldier(id).canMove();
    }


    private static boolean zoneIsImpassable(Player enemy, int position){
        return enemy.getBoard().getMatrix()[position].isImpassable();
    }

    private static boolean positionIsOccupied(Player enemy, int position){
        return enemy.getBoard().getMatrix()[position].isOccupied();
    }

    private static boolean positionOutOfBounds(int position){
        Board board = new Board();
        return (position<0 || position>board.getMatrix().length);
    }

    public static boolean isValid(Player me, Player enemy, int id, int position) {
        if(positionOutOfBounds(position)){
            return false;
        }
        if (zoneIsImpassable(enemy, position)) {
            AttackGraphics.attackToImpassableZone();
            return false;
        }

        if (!soldierCanMove(me, id)) {
            AttackManager.setCanMove(me,id);
        }
        if (positionIsOccupied(enemy, position)) {
            AttackGraphics.enemyAttacked();
        }
        return true;
    }

}