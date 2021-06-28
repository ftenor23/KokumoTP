package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.SoldierGraphics;

import TP_Bis.entity.Player;

import TP_Bis.entity.Soldier;
import TP_Bis.validator.SoldierValidator;



public class SoldierManager {
    private final static int INITIAL_POSITION = -1;
    private final BoardManager boardManager = new BoardManager();
    private final SoldierValidator soldierValidator = new SoldierValidator();

    //acomoda a los soldados. (lo ideal seria tener un vector de posiciones
    //para poder hacer al juego mas modificable y no repetir tanto codigo)
    public void setSoldiers(Player player){

        SoldierGraphics.positionSoldiers();
        int positionOne=INITIAL_POSITION;
        int positionTwo=INITIAL_POSITION;
        int positionThree=INITIAL_POSITION;
        //Scanner in = new Scanner(System.in);
        boolean arrayOutOfBounds = true;
        while(arrayOutOfBounds) {
            SoldierGraphics.enterCommanderPosition();
            positionOne = EnterData.nextInt();
            arrayOutOfBounds = !soldierValidator.isValid(positionOne); //verificamos si es una posicion valida
            if(arrayOutOfBounds) {
                SoldierGraphics.positionOutOfBounds();
            }
        }
        arrayOutOfBounds = true;
        while (arrayOutOfBounds) {
            SoldierGraphics.enterSoldierPosition(1);
            positionTwo = EnterData.nextInt();
            arrayOutOfBounds = !soldierValidator.isValid(positionTwo);
            if(arrayOutOfBounds) {
                SoldierGraphics.positionOutOfBounds();
            }
        }
        arrayOutOfBounds=true;
        while(arrayOutOfBounds) {
            SoldierGraphics.enterSoldierPosition(2);
            positionThree = EnterData.nextInt();
            arrayOutOfBounds=!soldierValidator.isValid(positionThree);
            if(arrayOutOfBounds){
                SoldierGraphics.positionOutOfBounds();
            }
        }
        boardManager.setSoldiers(player.getBoard(), positionOne, positionTwo, positionThree, player.isFirstTurn());
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
}
