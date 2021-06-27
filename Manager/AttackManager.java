package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.AttackGraphics;
import TP_Bis.entity.Player;
import TP_Bis.validator.AttackValidator;

public class AttackManager {
    private AttackValidator attackValidator = new AttackValidator();
    private BoardManager boardManager = new BoardManager();

    //se deja registro del ataque en el tablero enemigo
    public void attackEnemy(Player me, Player enemy, int id){

        AttackGraphics.printSelectPositionToAttackMessage();
        int position = EnterData.nextInt()-1;

        if(attack(me, enemy,id, position)) {
            attackReceived(enemy, position);
        }else{
            AttackGraphics.printNotValidAttack();
        }
    }

    //validamos datos del ataque con attackValidator
    //y nos devuelve un booleano indicando si el ataque es o no valido
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

    //seteamos que el soldado puede moverse en el turno despues de realizar el ataque
    public void setCanMove(Player me, int id){
        me.getBoard().getSoldier(id).setCanMove(true);
    }

    public void attackReceived(Player enemy, int position){
        boardManager.attackReceived(enemy.getBoard(), position);
    }

}
