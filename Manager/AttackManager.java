package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.AttackGraphics;
import TP_Bis.Graphic.BoardGraphics;
import TP_Bis.entity.Player;
import TP_Bis.validator.AttackValidator;

import java.util.Scanner;

public class AttackManager {
    private AttackValidator attackValidator = new AttackValidator();
    private BoardManager boardManager = new BoardManager();


    public void attackEnemy(Player me, Player enemy, int id){
        Scanner in = new Scanner(System.in);
        AttackGraphics.printSelectPositionToAttackMessage();
        int position = EnterData.nextInt()-1;
        if(attack(me, enemy,id, position)) {
            attackReceived(enemy, position);
        }else{
            AttackGraphics.printNotValidAttack();
        }
    }

    private boolean attack(Player me, Player enemy, int id, int position) {
        if(attackValidator.positionOutOfBounds(position)){
            return false;
        }
        if (attackValidator.zoneIsImpassable(enemy, position)) {
            AttackGraphics.attackToImpassableZone();
            return false;
        }

        if (!attackValidator.soldierCanMove(me, id)) {
            setCanMove(me,id);
        }
        if (attackValidator.positionIsOccupied(enemy, position)) {
            AttackGraphics.enemyAttacked();
        }
    return true;
    }

    public void setCanMove(Player me, int id){
        me.getBoard().getSoldier(id).setCanMove(true);
    }

    public void attackReceived(Player enemy, int position){
        boardManager.attackReceived(enemy.getBoard(), position);
    }

}
