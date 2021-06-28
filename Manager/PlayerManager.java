package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.BoardGraphics;
import TP_Bis.Graphic.GameGraphics;
import TP_Bis.Graphic.PlayerGraphics;
import TP_Bis.entity.Board;
import TP_Bis.entity.Player;

import TP_Bis.validator.ActionValidator;



public class PlayerManager {
    private boolean commanderIsDead;
    private final BoardManager boardManager;
    private final AttackManager attackManager;
    private final SoldierManager soldierManager;
    private final MoveManager moveManager;
    private final ActionValidator actionValidator;
    private final static int ATTACK = 1;
    private final static int MOVE_SOLDIER = 2;
    private final static int INVALID_OPTION = -1;
    //si el comandante muere, los demas no pueden moverse


    public PlayerManager() {
        commanderIsDead = false;
        boardManager = new BoardManager();
        attackManager = new AttackManager();
        soldierManager = new SoldierManager();
        moveManager = new MoveManager();
        actionValidator = new ActionValidator();
    }

    //en este metodo se ejecuta toda la logica del juego. Devuelve un booleano
    //indicando si el jugador actual gano o no la partida
    public boolean turn(Player me, Player enemy) {
        boolean playerWon = false;
        if(!me.isFirstTurn()){
            GameGraphics.cleanConsole();
        }
        PlayerGraphics.playerTurn(me.getPlayerName());
        printMyBoard(me.getBoard());
        printEnemyBoard(enemy.getBoard());

        if (me.isFirstTurn()) {
            setSoldiers(me);
            printMyBoard(me.getBoard());
            printEnemyBoard(enemy.getBoard());
        } else {
            this.commanderIsDead = commanderIsDead(me);

            informSoldiersAttacked(me);

            for (int i = 0; i < soldiersAlive(me); i++) {
                int action = INVALID_OPTION; //inicializamos con un valor invalido para que se cumpla el ciclo
                int id = i + 1; //el id del soldado es i+1
                while (action != ATTACK && action != MOVE_SOLDIER && !soldierIsDead(me, id)) {

                    PlayerGraphics.selectMoveMenu(me, id);

                    PlayerGraphics.attack();

                    //si solo puede atacar o el comandante esta muerto,
                    //no se le imprime el menu de mover al soldado
                    if (!onlyAttack(me, id) && !commanderIsDead) {
                        PlayerGraphics.moveSoldier();
                    }

                    action = EnterData.nextInt();
                    if (action != ATTACK && action != MOVE_SOLDIER) {
                        action = INVALID_OPTION;
                        PlayerGraphics.invalidOption();
                    }
                    //si a pesar de los avisos, se ingresa la opcion de moverse cuando
                    //no es posible, le damos un aviso al jugador y vuelve a ejecutarse el ciclo
                    if ((commanderIsDead || !soldierCanMove(me, id)) && action == MOVE_SOLDIER) {
                        action = INVALID_OPTION;
                        PlayerGraphics.invalidOption();
                    }

                }
                //ejecutamos la jugada y nos devuelve un booleano indicando si el
                //jugador actual mato a todos los enemigos
                playerWon = executeAction(me, enemy, id, action);

                if(playerWon){
                    return playerWon;
                }
            }

        }

        return playerWon;//mientras devuelva false, siguen jugando
    }

    public void printMyBoard(Board board) {
        BoardGraphics.printOwnBoard(board);
    }

    private void printEnemyBoard(Board enemyBoard) {
        BoardGraphics.showEnemyBoard(enemyBoard);
    }

    private void setSoldiers(Player player) {
        SoldierManager soldierManager = new SoldierManager();
        //acomodamos los soldados en el tablero
        soldierManager.setSoldiers(player);
    }


    private void attackEnemy(Player me, Player enemy, int id) {
        //dirigimos el ataque
        attackManager.attackEnemy(me, enemy, id);
    }

    private void moveSoldier(Player player, int id) {
        MoveManager moveManager = new MoveManager();
        moveManager.moveSoldier(player, id);
    }

    public boolean enemiesAreDead(Player enemy) {
        return enemy.getBoard().allSoldiersAreDead();
    }

    public boolean mySoldiersAreDead(Player player) {
        return boardManager.allSoldiersAreDead(player.getBoard());
    }

    private boolean soldierIsDead(Player me, int id) {
        return me.getBoard().getSoldier(id).isDead();
    }

    private int soldiersAlive(Player me) {
        return me.getBoard().getNumberOfSoldiers();
    }


    private boolean commanderIsDead(Player me) {
        return me.getBoard().commanderIsDead();
    }

    private boolean soldierCanMove(Player me, int id) {
        return moveManager.getMoveValidator().soldierCanMove(me, id);
    }


    private boolean onlyAttack(Player me, int id) {
        return actionValidator.onlyAttack(me, id);
    }


    //ejecuta lo seleccionado por el jugador en turn()
    private boolean executeAction(Player me, Player enemy, int id, int action) {
        boolean playerWon = true;
        switch (action) {
            case 1:
                attackEnemy(me, enemy, id);
                if (enemiesAreDead(enemy)) {
                    return playerWon; //devolvemos true para indicar que gano la partida
                }
                playerWon = false;
                break;
            case 2:
                moveSoldier(me, id);
                playerWon = false;
                break;
            default:
                playerWon = false;
                break;
        }
        if(action!=INVALID_OPTION) {
            printMyBoard(me.getBoard());
            printEnemyBoard(enemy.getBoard());
        }
        return playerWon;

    }


    private void informSoldiersAttacked(Player player) {
        //al inicio de la partida, indicamos si alguno/s soldados
        //recibieron algun ataque
        if (commanderWasAttacked(player) && player.informCommanderWasAttacked()) {
            PlayerGraphics.soldierWasAttacked(1);
            player.setInformCommanderWasAttacked(false);
        }
        if (soldierIsDead(player, 2) && player.informSoldierOneIsDead()) {
            PlayerGraphics.soldierWasAttacked(2);
            player.setInformSoldierOneIsDead(false);
        }
        if(soldierIsDead(player,3) && player.informSoldierTwoIsDead()){
            PlayerGraphics.soldierWasAttacked(3);
            player.setInformSoldierTwoIsDead(false);
        }
        if (commanderIsDead(player) && player.informCommanderIsDead()) {
            PlayerGraphics.informCommanderIsDead();
            player.setInformCommanderIsDead(false);
        }
    }

    private boolean commanderWasAttacked(Player player) {
        return player.getBoard().getSoldier(1).getRemainingLives() == 1;
    }


}
