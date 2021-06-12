package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.SoldierGraphics;
import TP_Bis.entity.Board;
import TP_Bis.entity.Player;
import TP_Bis.entity.Soldier;
import TP_Bis.validator.SoldierValidator;

import java.util.Scanner;

public class SoldierManager {
    private static int INITIAL_POSITION = -1;
    private BoardManager boardManager = new BoardManager();
    private SoldierValidator soldierValidator = new SoldierValidator();

    public void setSoldiers(Player player){

        SoldierGraphics.positionSoldiers();
        int positionOne=INITIAL_POSITION;
        int positionTwo=INITIAL_POSITION;
        int positionThree=INITIAL_POSITION;
        Scanner in = new Scanner(System.in);
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

    public SoldierValidator getSoldierValidator() {
        return soldierValidator;
    }
}
