package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.AttackGraphics;
import TP_Bis.entity.Player;
import TP_Bis.validator.AttackValidator;

public class AttackManager{
    private BoardManager boardManager = new BoardManager();

    //se deja registro del ataque en el tablero enemigo
    public void attackEnemy(Player me, Player enemy, int id){

        AttackGraphics.printSelectPositionToAttackMessage();
        int position = enterPosition();

        if(attackIsValid(me, enemy,id, position)) {
            attackReceived(enemy, position);
        }else{
            AttackGraphics.printNotValidAttack();
        }
    }

    private int enterPosition(){
        //restamos uno a la posicion para ubicarlo en el tablero (las posiciones
        //en el tablero van de 0 a 24, y no de 1 a 25 como se le indica al usuario
        int position=EnterData.nextInt()-1;
        return position;
    }
    //validamos datos del ataque con attackValidator
    //y nos devuelve un booleano indicando si el ataque es o no valido
    private boolean attackIsValid(Player me, Player enemy, int id, int position) {
        return AttackValidator.isValid(me, enemy, id, position);
    }

    //seteamos que el soldado puede moverse en el turno despues de realizar el ataque
    public static void setCanMove(Player me, int id){
        SoldierManager.setCanMove(me,id);
    }

    public void attackReceived(Player enemy, int position){
        boardManager.attackReceived(enemy.getBoard(), position);
    }

}
