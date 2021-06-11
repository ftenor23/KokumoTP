package TP_Bis.Manager;

import TP_Bis.Graphic.AttackGraphics;
import TP_Bis.Graphic.BoardGraphics;
import TP_Bis.entity.Player;
import TP_Bis.validator.AttackValidator;

import java.util.Scanner;

public class AttackManager {
    private AttackValidator attackValidator = new AttackValidator();
    private BoardManager boardManager = new BoardManager();
    private AttackGraphics attackGraphics = new AttackGraphics();

    public void attackEnemy(Player me, Player enemy, int id){
        Scanner in = new Scanner(System.in);
        attackGraphics.printSelectPositionToAttackMessage();
        int position = in.nextInt()-1;
        if(attack(me, enemy,id, position)) {
            attackReceived(enemy, position);
        }else{
            attackGraphics.printNotValidAttack();
        }
    }

    private boolean attack(Player me, Player enemy, int id, int position) {
        if (!attackValidator.soldierCanMove(me, id)) {
            setCanMove(me,id);
        }
        if (attackValidator.zoneIsImpassable(enemy, position)) {
            attackGraphics.attackToImpassableZone();
            return false;
        }
        if (attackValidator.positionIsOccupied(enemy, position)) {
            attackGraphics.enemyAttacked();
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
