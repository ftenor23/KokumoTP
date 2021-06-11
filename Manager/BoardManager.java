package TP_Bis.Manager;

import TP_Bis.Graphic.BoardGraphics;
import TP_Bis.entity.Board;
import TP_Bis.entity.Player;
import TP_Bis.entity.Soldier;

import java.util.Scanner;

public class BoardManager {
    BoardGraphics boardGraphics = new BoardGraphics();

    public void setSoldiers(Board board, int positionOne, int positionTwo, int positionThree, boolean firstTurn){
        //ingresar las posiciones de los soldados
        Scanner in = new Scanner(System.in);
        int id;
        positionOne--;
        positionTwo--;
        positionThree--;
        for(int i=0; i<board.getNumberOfSoldiers();i++){
            id=i+1;
            if(i==0) {
                while(board.getMatrix()[positionOne].isOccupied() || arrayOutOfBounds(board,positionOne)){
                    if(board.getMatrix()[positionOne].isOccupied()) {
                        boardGraphics.positionOccupied(id);
                        positionOne = in.nextInt() - 1;
                    }
                    if(arrayOutOfBounds(board,positionOne)){
                        boardGraphics.positionOutOfBounds(id);
                        positionOne= in.nextInt() - 1;
                    }
                }
                board.getSoldiers()[i]=new Soldier(true, id, positionOne);
            }
            if(i==1){
                while(board.getMatrix()[positionTwo].isOccupied() || arrayOutOfBounds(board,positionTwo)){
                    if(board.getMatrix()[positionTwo].isOccupied()) {
                        boardGraphics.positionOccupied(id);
                        positionTwo = in.nextInt() - 1;
                    }
                    if(arrayOutOfBounds(board,positionTwo)){
                        boardGraphics.positionOutOfBounds(id);
                        positionTwo = in.nextInt() - 1;
                    }
                }
                board.getSoldiers()[i]=new Soldier(false, id, positionTwo);
            }
            if(i==2){
                while(board.getMatrix()[positionThree].isOccupied() || arrayOutOfBounds(board,positionThree)){
                    if(board.getMatrix()[positionThree].isOccupied()) {
                        boardGraphics.positionOccupied(id);
                        positionThree = in.nextInt() - 1;
                    }
                    if(arrayOutOfBounds(board,positionThree)){
                        boardGraphics.positionOutOfBounds(id);
                        positionThree = in.nextInt() - 1;
                    }
                }
                board.getSoldiers()[i]=new Soldier(false, id, positionThree);
            }
            switch (i) {
                case 0:
                    setSoldierPosition(board,positionOne, board.getSoldiers()[i], firstTurn); //restamos uno porque el tablero arranca en cero
                    break;
                case 1:
                    setSoldierPosition(board,positionTwo, board.getSoldiers()[i], firstTurn);
                    break;
                case 2:
                    setSoldierPosition(board,positionThree, board.getSoldiers()[i], firstTurn);
                    break;
                default:
                    break;
            }


        }
    }

    private boolean arrayOutOfBounds(Board board,int position){
        return position<0 || position>= board.getMatrix().length;
    }
    public void setSoldierPosition(Board board, int position, Soldier soldier, boolean firstTurn) {

        if(board.getMatrix()[position].isOccupied()){
            if(firstTurn) {
                boardGraphics.occupiedPosition();
            } else {
                boardGraphics.lostTurnPositionOccupied();
            }
        } else if(board.getMatrix()[position].isImpassable()){
            boardGraphics.lostTurnImpassableZone();
        } else {
            board.getSoldiers()[soldier.getId()-1].setPosition(position);
            board.getMatrix()[position].occupyGrid(board.getSoldiers()[soldier.getId()-1]);
        }
    }

    public void printOwnBoard(Board board){
        boardGraphics.printOwnBoard(board);
    }

    public void showEnemyBoard(Board enemyBoard){
        boardGraphics.showEnemyBoard(enemyBoard);
    }

    public void attackReceived(Board myBoard,int position) {
        int id = -1;
        for (int i = 0; i < myBoard.getSoldiers().length; i++) {
            if (myBoard.getSoldiers()[i].getPosition() == position) {
                id = myBoard.getSoldiers()[i].getId() - 1;
            }
        }
        if (id > -1) {
            myBoard.getSoldiers()[id].substractALife();
            myBoard.getMatrix()[position].attackReceived(myBoard.getSoldiers()[id]);
        } else {
            myBoard.getMatrix()[position].attackReceived();
        }
    }



    public int getSoldierPosition(Board board, int id){

        for (int i = 0; i < board.getSoldiers().length; i++) {
            if(board.getSoldiers()[i].getId()==id){
                return board.getSoldiers()[i].getPosition();
            }
        }
        return -1;
    }


    protected boolean allSoldiersAreDead(Board board){
        for(int i=0; i<board.getSoldiers().length; i++){
            if(!board.getSoldiers()[i].isDead()){
                return false;
            }
        }
        return true;
    }

    protected boolean commanderIsDead(Board board){
        for(int i=0; i<board.getSoldiers().length;i++){
            if(board.getSoldiers()[i].isCommander() && board.getSoldiers()[i].isDead()){
                return true;
            }
        }
        return false;
    }

    protected int soldiersAlive(Board board){
        int soldiersAlive=0;
        for(int i=0;i<board.getSoldiers().length;i++){
            if(!board.getSoldiers()[i].isDead()){
                soldiersAlive++;
            }
        }
        return soldiersAlive;
    }

    public void setBoard(Player player, Board board){
        player.setMyBoard(board);
    }
}

