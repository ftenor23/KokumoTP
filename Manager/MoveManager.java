package TP_Bis.Manager;

import TP_Bis.Graphic.MoveGraphics;
import TP_Bis.entity.Board;
import TP_Bis.entity.Player;
import TP_Bis.entity.Soldier;
import TP_Bis.validator.MoveValidator;

import java.util.Scanner;

public class MoveManager {
    private MoveValidator moveValidator = new MoveValidator();

    public MoveValidator getMoveValidator() {
        return moveValidator;
    }

    public void moveSoldier(Player player, int id){
        MoveValidator moveValidator = new MoveValidator();
        Scanner in = new Scanner(System.in);
        final int MIN_MOVE = 1;
        final int MAX_MOVE = 4;
        int position = getSoldierPosition(player,id);

        if(!moveValidator.isValid(position)){
            MoveGraphics.invalidPosition();//cargar error
            return;
        }
        //creamos un ciclo hasta que se cumpla la condicion
        int move=-1; //iniciamos en -1
        while(!moveValidator.moveIsValid(move, position)){ //verificar funcion
            MoveGraphics.selectMove();
            move = in.nextInt();
            if(!moveValidator.moveIsValid(move, position)){
                MoveGraphics.invalidOption();
                move=-1; //REVISAR
            }
        }
        move(player,position, id,move);
        setSoldierCantMove(player,id); //modificar para que cuando pase un turno pueda moverse
    }

    private void move(Player player, int position, int soldierId, int move){
        Soldier soldier = player.getBoard().getSoldier(soldierId);
        //verificar funcion
        int newPosition=-1;
        final int MAX_MOVEMENT=5;
        final int MIN_MOVEMENT=1;
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
        player.getBoard().getMatrix()[position].emptyGrid();
        player.getBoard().setSoldierPosition(newPosition, soldier, player.isFirstTurn());
        return;
    }

    private int getSoldierPosition(Player player, int id){
        return player.getBoard().getSoldierPosition(id);
    }

    private void setSoldierCantMove(Player player, int id){
        player.getBoard().getSoldier(id).setCanMove(false);
    }
}
