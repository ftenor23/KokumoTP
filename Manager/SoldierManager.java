package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.SoldierGraphics;

import TP_Bis.entity.Board;
import TP_Bis.entity.Player;

import TP_Bis.entity.Soldier;
import TP_Bis.validator.SoldierValidator;



public class SoldierManager{
    private final static int CANT_OF_SOLDIERS = Board.getNumberOfSoldiers();
    private final static int INITIAL_POSITION = -1;
    private final BoardManager boardManager = new BoardManager();
    private final SoldierValidator soldierValidator = new SoldierValidator();

    //acomoda a los soldados. (lo ideal seria tener un vector de posiciones
    //para poder hacer al juego mas modificable y no repetir tanto codigo)
    public void setSoldiers(Player player){

        SoldierGraphics.positionSoldiers();
        int[] positions = generateArray();

        validatePositions(positions);
        boardManager.setSoldiers(player.getBoard(), positions, player.isFirstTurn());
        player.setFirstTurn(false);
    }

    public static void setSoldierCantMove(Player player, int id){
        player.getBoard().getSoldier(id).setCanMove(false);
    }

   public static Soldier getSoldier(Player player, int soldierId){
        return player.getBoard().getSoldier(soldierId);
    }

    public static int getSoldierPosition(Player player, int id){
        return player.getBoard().getSoldierPosition(id);
    }

    public static void emptyGrid(Player player, int position){
        player.getBoard().getMatrix()[position].emptyGrid();
    }

    private int[] generateArray(){

        int[] positions = new int[CANT_OF_SOLDIERS];
        for(int i=0; i<CANT_OF_SOLDIERS; i++){
            positions[i] = -1;
        }

        return positions;
    }

    private void validatePositions(int [] positions){
        boolean arrayOutOfBounds;
        for(int i=0; i<positions.length;i++){
            arrayOutOfBounds=true;
            int id=i+1;//id del soldado
            while(arrayOutOfBounds) {
                if(i==0){
                    SoldierGraphics.enterCommanderPosition();
                }else {
                    SoldierGraphics.enterSoldierPosition(id);
                }
                positions[i] = EnterData.nextInt();
                arrayOutOfBounds=!soldierValidator.isValid(positions[i]);
                if(arrayOutOfBounds){
                    SoldierGraphics.positionOutOfBounds();
                }
            }
        }
    }

    public static void substractALife(Soldier soldier){
        int remainingLives=soldier.getRemainingLives();
        soldier.setRemainingLives(remainingLives-1);

        if(soldier.getRemainingLives()<1){
            soldier.setDead(true);
        }
    }

    public static void setCanMove(Player me, int id){
        me.getBoard().getSoldier(id).setCanMove(true);
    }
}
