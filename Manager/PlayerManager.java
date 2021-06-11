package TP_Bis.Manager;

import TP_Bis.Graphic.PlayerGraphics;
import TP_Bis.entity.Board;
import TP_Bis.entity.Player;
import TP_Bis.entity.Soldier;
import TP_Bis.validator.ActionValidator;

import java.util.Scanner;

public class PlayerManager {
    private boolean commanderIsDead;
    private BoardManager boardManager;
    private AttackManager attackManager;
    private SoldierManager soldierManager;
    private MoveManager moveManager;
    private ActionValidator actionValidator;
    private final static int ATTACK = 1;
    private final static int MOVE_SOLDIER=2;
    //si el comandante muere, los demas no pueden moverse


    public PlayerManager() {
        commanderIsDead=false;
        boardManager=new BoardManager();
        attackManager=new AttackManager();
        soldierManager=new SoldierManager();
        moveManager = new MoveManager();
        actionValidator=new ActionValidator();
    }

    public boolean turn(Player me, Player enemy) {
        Scanner in = new Scanner(System.in);
        boolean playerWon = false;
        PlayerGraphics.playerTurn(me);
        if (me.isFirstTurn()) {
            printMyBoard(me.getBoard());
            setSoldiers(me);
        } else {
            this.commanderIsDead = commanderIsDead(me);

            for (int i = 0; i < soldiersAlive(me); i++) {
                int action = -1;
                int id = i+1;
                while (action != ATTACK && action != MOVE_SOLDIER) {
                    if (!soldierIsDead(me,id)) {

                        PlayerGraphics.selectMoveMenu(id);
                        PlayerGraphics.attack();

                        if (!onlyAttack(me,id)) {
                            PlayerGraphics.moveSoldier();
                        }

                        action = in.nextInt();
                        if (action != ATTACK || action != MOVE_SOLDIER) {
                            if ((commanderIsDead||!soldierCanMove(me,id)) && action == MOVE_SOLDIER) {
                                action = -1;
                                PlayerGraphics.invalidOption();
                            }
                        }
                        playerWon = executeAction(me,enemy,id,action);
                        if(playerWon){
                            return true;
                        }
                    }
                    action=ATTACK; //cambiamos el valor a uno que nos haga salir del while

                }

            }
            printMyBoard(me.getBoard());
            printEnemyBoard(enemy.getBoard());

        }
        return playerWon; //mientras devuelva false, siguen jugando
    }


    private void printMyBoard(Board board) {
        System.out.println("Mi tablero: ");
        boardManager.printOwnBoard(board);
    }


    private void printEnemyBoard(Board enemyBoard){
        boardManager.showEnemyBoard(enemyBoard);
    }

    private void setSoldiers(Player player){
        SoldierManager soldierManager = new SoldierManager();
        soldierManager.setSoldiers(player);
    }


    private void attackEnemy(Player me, Player enemy, int id){
        attackManager.attackEnemy(me,enemy,id);
    }

    private void moveSoldier(Player player, int id){
        MoveManager moveManager = new MoveManager();
        moveManager.moveSoldier(player,id);
    }
    public boolean enemiesAreDead(Player enemy){
        return enemy.getBoard().allSoldiersAreDead();
    }

    public boolean mySoldiersAreDead(Player player){
        return boardManager.allSoldiersAreDead(player.getBoard());
    }
    private boolean soldierIsDead(Player me, int id){
        return me.getBoard().getSoldier(id).isDead();
    }

    private int soldiersAlive(Player me){
        return me.getBoard().getNumberOfSoldiers();
    }

    private boolean commanderIsDead(Player me){
        return me.getBoard().commanderIsDead();
    }

    private boolean soldierCanMove(Player me, int id){
       return moveManager.getMoveValidator().soldierCanMove(me,id);
    }

    private boolean canMoveSoldier(Player me, int id){
        return !commanderIsDead && moveManager.getMoveValidator().soldierCanMove(me, id);
    }

    private boolean onlyAttack(Player me, int id){
        return actionValidator.onlyAttack(me,id);
    }

    private boolean executeAction(Player me, Player enemy, int id,int action){
        boolean playerWon=true;
        switch (action) {
            case 1:
                attackEnemy(me, enemy, id);
                if(enemiesAreDead(enemy)){
                    return playerWon; //devolvemos true para indicar que gano la partida
                }
                playerWon=false;
                break;
            case 2:
                moveSoldier(me, id);
                playerWon=false;
                break;
            default:
                break;
        }
        return playerWon;

    }

    public void setBoard(Player player, Board changedBoard){
        boardManager.setBoard(player, changedBoard);
    }

}
