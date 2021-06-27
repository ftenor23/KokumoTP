package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.MoveGraphics;
import TP_Bis.entity.Player;
import TP_Bis.entity.Soldier;
import TP_Bis.validator.MoveValidator;

public class MoveManager {
    private final MoveValidator moveValidator = new MoveValidator();

    public MoveValidator getMoveValidator() {
        return moveValidator;
    }

    public void moveSoldier(Player player, int id){
        MoveValidator moveValidator = new MoveValidator();
        int position = getSoldierPosition(player,id);

       /* if(!moveValidator.isValid(position)){
            MoveGraphics.invalidPosition();//cargar error
            return;
        }*/
        //creamos un ciclo hasta que se cumpla la condicion
        int move=-1; //iniciamos en -1
        while(!moveValidator.moveIsValid(move, position)){ //verificar funcion
            MoveGraphics.selectMove();
            move = EnterData.nextInt();
            if(!moveValidator.moveIsValid(move, position)){
                MoveGraphics.invalidOption();
                move=-1;
            }
        }
        move(player,position, id,move);
        setSoldierCantMove(player,id); //modificar para que cuando pase un turno pueda moverse
    }

    private void move(Player player, int position, int soldierId, int move){
        Soldier soldier = getSoldier(player,soldierId);
        int newPosition=-1;
        final int MAX_MOVEMENT=5;//si nos movemos hacia arriba o abajo sumamos 5 grillas
        final int MIN_MOVEMENT=1;//si nos movemos a izq o derecha, 1 sola
        switch (move){
            case 1:
                newPosition=position-MAX_MOVEMENT; //se mueve hacia arriba
                break;
            case 2:
                newPosition=position+MAX_MOVEMENT; //se mueve hacia abajo
                break;
            case 3:
                newPosition=position-MIN_MOVEMENT;
                break;
            case 4:
                newPosition=position+MIN_MOVEMENT;
                break;
            default:
                MoveGraphics.error();
                break;
        }
        if(!moveValidator.validPosition(newPosition)){
            MoveGraphics.invalidMovement();
            return;
        }
        if(player.getBoard().getMatrix()[newPosition].isOccupied()) {
            MoveGraphics.positionOccupied();
            return;
        }
        if(player.getBoard().getMatrix()[newPosition].isImpassable()){
            MoveGraphics.impassablePosition();
            return;
        }

        emptyGrid(player,position);
        setSoldierPosition(player,newPosition,soldier);

    }

    private Soldier getSoldier(Player player, int soldierId){
        return player.getBoard().getSoldier(soldierId);
    }

    private int getSoldierPosition(Player player, int id){
        return player.getBoard().getSoldierPosition(id);
    }

    private void setSoldierCantMove(Player player, int id){
        player.getBoard().getSoldier(id).setCanMove(false);
    }

    private void emptyGrid(Player player, int position){
        player.getBoard().getMatrix()[position].emptyGrid();
    }

    private void setSoldierPosition(Player player, int newPosition, Soldier soldier){
        BoardManager boardManager = new BoardManager();
        boardManager.setSoldierPosition(player.getBoard(), newPosition, soldier, player.isFirstTurn());
    }
}
