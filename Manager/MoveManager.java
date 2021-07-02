package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;

import TP_Bis.Graphic.MoveGraphics;
import TP_Bis.entity.Player;
import TP_Bis.entity.Soldier;
import TP_Bis.validator.MoveValidator;

public class MoveManager {
    private final MoveValidator moveValidator = new MoveValidator();
    private static final int MAX_MOVEMENT=5;//si nos movemos hacia arriba o abajo sumamos 5 grillas
    private static final int MIN_MOVEMENT=1;//si nos movemos a izq o derecha, 1 sola
    public MoveValidator getMoveValidator() {
        return moveValidator;
    }

    //seleccionamos el movimiento a realizar y lo validamos. Si es valido, ejecuta move()
    public void moveSoldier(Player player, int id){
        MoveValidator moveValidator = new MoveValidator();
        int position = getSoldierPosition(player,id);

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
        //seteamos que el soldado no pueda moverse en la partida siguiente
        setSoldierCantMove(player,id);
    }

    //en base al movimiento seleccionado, verifica si es valido y acomoda al
    //soldado en la nueva posicion
    private void move(Player player, int position, int soldierId, int move){
        Soldier soldier = getSoldier(player,soldierId);
        int newPosition=returnNewPosition(move,position);

        if(!moveValidator.validPosition(newPosition)){
            MoveGraphics.invalidMovement();
            return;
        }
        if(occupiedPosition(player,newPosition)) {
            MoveGraphics.positionOccupied();
            return;
        }
        if(impassablePosition(player,newPosition)){
            MoveGraphics.impassablePosition();
            return;
        }

        emptyGrid(player,position);
        setSoldierPosition(player,newPosition,soldier);

    }

    private boolean occupiedPosition(Player player, int newPosition){
        return player.getBoard().getMatrix()[newPosition].isOccupied();
    }

    private boolean impassablePosition(Player player, int newPosition){
        return player.getBoard().getMatrix()[newPosition].isImpassable();
    }

    private int returnNewPosition(int move, int position){
        int newPosition=-1;
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
        return newPosition;
    }
    private Soldier getSoldier(Player player, int soldierId){
        return SoldierManager.getSoldier(player,soldierId);
    }

    private int getSoldierPosition(Player player, int id){
        return SoldierManager.getSoldierPosition(player, id);
    }

    private void setSoldierCantMove(Player player, int id){
        SoldierManager.setSoldierCantMove(player,id);
    }

    private void emptyGrid(Player player, int position){
        SoldierManager.emptyGrid(player,position);
    }

    private void setSoldierPosition(Player player, int newPosition, Soldier soldier){
        BoardManager.setSoldierPosition(player.getBoard(), newPosition, soldier, player.isFirstTurn());
    }
}
