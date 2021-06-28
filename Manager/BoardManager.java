package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.BoardGraphics;
import TP_Bis.entity.Board;
import TP_Bis.entity.Player;
import TP_Bis.entity.Soldier;
import TP_Bis.validator.PositionValidator;


public class BoardManager {

    //La funcion acomoda a los soldados en el tablero en el primer turno
    public void setSoldiers(Board board, int positionOne, int positionTwo, int positionThree, boolean firstTurn){
        //ingresar las posiciones de los soldados
        int id;
        //Restamos uno a cada posicion para poder acomodarlos correctamente. La posicion uno en realidad es= 0
        positionOne--;
        positionTwo--;
        positionThree--;
        for(int i=0; i<board.getNumberOfSoldiers();i++){
            id=i+1;
            if(i==0) {
                while(board.getMatrix()[positionOne].isOccupied() || arrayOutOfBounds(positionOne)){
                    if(board.getMatrix()[positionOne].isOccupied()) {
                        BoardGraphics.positionOccupied(id);
                        positionOne = EnterData.nextInt() - 1;
                    }
                    if(arrayOutOfBounds(positionOne)){
                        BoardGraphics.positionOutOfBounds(id);
                        positionOne= EnterData.nextInt() - 1;
                    }
                }
                board.getSoldiers()[i]=new Soldier(true, id, positionOne);
            }
            if(i==1){
                while(board.getMatrix()[positionTwo].isOccupied() || arrayOutOfBounds(positionTwo)){
                    if(board.getMatrix()[positionTwo].isOccupied()) {
                        BoardGraphics.positionOccupied(id);
                        positionTwo = EnterData.nextInt() - 1;
                    }
                    if(arrayOutOfBounds(positionTwo)){
                        BoardGraphics.positionOutOfBounds(id);
                        positionTwo = EnterData.nextInt() - 1;
                    }
                }
                board.getSoldiers()[i]=new Soldier(false, id, positionTwo);
            }
            if(i==2){
                while(board.getMatrix()[positionThree].isOccupied() || arrayOutOfBounds(positionThree)){
                    if(board.getMatrix()[positionThree].isOccupied()) {
                        BoardGraphics.positionOccupied(id);
                        positionThree = EnterData.nextInt() - 1;
                    }
                    if(arrayOutOfBounds(positionThree)){
                        BoardGraphics.positionOutOfBounds(id);
                        positionThree = EnterData.nextInt() - 1;
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

    private boolean arrayOutOfBounds(int position){

        return PositionValidator.arrayOutOfBounds(position);
    }

    //seteamos la posicion del soldado
    public static void setSoldierPosition(Board board, int position, Soldier soldier, boolean firstTurn) {

        if(board.getMatrix()[position].isOccupied()){
            if(firstTurn) {
                BoardGraphics.occupiedPosition();
            } else {
                BoardGraphics.lostTurnPositionOccupied();
            }
        } else if(board.getMatrix()[position].isImpassable()){
            BoardGraphics.lostTurnImpassableZone();
        } else {
            board.getSoldiers()[soldier.getId()-1].setPosition(position);
            board.getMatrix()[position].occupyGrid(board.getSoldiers()[soldier.getId()-1]);
        }
    }

    //recibimos un ataque y realiza las modificaciones correspondientes
    //en mi tablero
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

    public static int boardLenght(){
        Board board = new Board();
        return board.getMatrix().length;
    }

    //booleano que informa si todos los soldados enemigos estan muertos para
    //dar por terminado el juego
    protected boolean allSoldiersAreDead(Board board){
        for(int i=0; i<board.getSoldiers().length; i++){
            if(!board.getSoldiers()[i].isDead()){
                return false;
            }
        }
        return true;
    }

    public void setBoard(Player player, Board board){
        player.setMyBoard(board);
    }
}

