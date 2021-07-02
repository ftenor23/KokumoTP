package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.BoardGraphics;
import TP_Bis.entity.Board;
import TP_Bis.entity.Player;
import TP_Bis.entity.Soldier;
import TP_Bis.validator.PositionValidator;


public class BoardManager{

    private final static int INVALID_ID=-1;
    //La funcion acomoda a los soldados en el tablero en el primer turno
    public void setSoldiers(Board board, int[] positions, boolean firstTurn) {
        //ingresar las posiciones de los soldados
        int id;
        //Restamos uno a cada posicion para poder acomodarlos correctamente. La posicion uno en realidad es= 0
        restOneToEveryPosition(positions);
        validatePositions(positions,board);
    }

    private boolean arrayOutOfBounds(int position) {
        //le sumamos uno a la posicion porque el validador
        //analiza posiciones de 1 a 25 y no de 0 a 24
        int positionToSend = position + 1;

        return PositionValidator.arrayOutOfBounds(positionToSend);
    }

    //seteamos la posicion del soldado
    public static void setSoldierPosition(Board board, int position, Soldier soldier, boolean firstTurn) {

        if (board.getMatrix()[position].isOccupied()) {
            if (firstTurn) {
                BoardGraphics.occupiedPosition();
            } else {
                BoardGraphics.lostTurnPositionOccupied();
            }
        } else if (board.getMatrix()[position].isImpassable()) {
            BoardGraphics.lostTurnImpassableZone();
        } else {
            board.getSoldiers()[soldier.getId() - 1].setPosition(position);
            board.getMatrix()[position].occupyGrid(board.getSoldiers()[soldier.getId() - 1]);
        }
    }

    //recibimos un ataque y realiza las modificaciones correspondientes
    //en mi tablero
    public void attackReceived(Board myBoard, int position) {
        int id = INVALID_ID;
        for (int i = 0; i < myBoard.getSoldiers().length; i++) {
            if (myBoard.getSoldiers()[i].getPosition() == position) {
                id = getId(myBoard,i);
            }
        }
        if (id > INVALID_ID) {
            SoldierManager.substractALife(myBoard.getSoldiers()[id]);
            myBoard.getMatrix()[position].attackReceived(myBoard.getSoldiers()[id]);
        } else {
            //marcamos la zona como impasable
            myBoard.getMatrix()[position].attackReceived();
        }
    }

    public static int boardLenght() {
        return Board.getBoardSize();
    }

    public int getId(Board myBoard, int i){
        return myBoard.getSoldiers()[i].getId() - 1;
    }

    //booleano que informa si todos los soldados enemigos estan muertos para
    //dar por terminado el juego
    protected boolean allSoldiersAreDead(Board board) {
        for (int i = 0; i < board.getSoldiers().length; i++) {
            if (!board.getSoldiers()[i].isDead()) {
                return false;
            }
        }
        return true;
    }

    public void setBoard(Player player, Board board) {
        player.setMyBoard(board);
    }

    private void restOneToEveryPosition(int[] positions) {
        for (int i = 0; i < positions.length; i++) {
            positions[i]--;
        }
    }

    private void validatePositions(int[] positions, Board board) {

        for (int i = 0; i < positions.length; i++) {
            while(!positionIsValid(board,positions[i],i));
            generateSoldierInBoard(board,i,positions);
            setSoldierPosition(board, positions[i], board.getSoldiers()[i], true);
        }
    }

    private boolean isCommander(int i){
        return i==0;
    }

    private static boolean positionIsValid(Board board, int position, int i){
        return PositionValidator.positionIsValid(board,position,i);
    }
    private void generateSoldierInBoard(Board board, int i,int[] positions){
        int id=i+1;//sumamos uno al id
        boolean commander=isCommander(i);
        board.getSoldiers()[i] = new Soldier(commander, id, positions[i]);
    }
}
