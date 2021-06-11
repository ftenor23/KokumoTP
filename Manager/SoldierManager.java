package TP_Bis.Manager;

import TP_Bis.Graphic.SoldierGraphics;
import TP_Bis.entity.Board;
import TP_Bis.entity.Player;
import TP_Bis.entity.Soldier;
import TP_Bis.validator.SoldierValidator;

import java.util.Scanner;

public class SoldierManager {
    private BoardManager boardManager = new BoardManager();
    SoldierValidator soldierValidator = new SoldierValidator();

    public void setSoldiers(Player player){

        SoldierGraphics.positionSoldiers();
        int positionOne=-1;
        int positionTwo=-1;
        int positionThree=-1;
        Scanner in = new Scanner(System.in);
        boolean arrayOutOfBounds = true;
        while(arrayOutOfBounds) {
            System.out.println("Ingrese la posicion del comandante: ");
            positionOne = in.nextInt();
            arrayOutOfBounds = !soldierValidator.isValid(positionOne); //positionValidator
            if(arrayOutOfBounds) {
                System.out.println("Posicion fuera de los limites. Ingrese un numero entre 1 y " + player.getBoard().getMatrix().length);
            }
        }
        arrayOutOfBounds = true;
        while (arrayOutOfBounds) {
            System.out.println("Ingrese la posicion del soldado numero 1: ");
            positionTwo = in.nextInt();
            arrayOutOfBounds = !soldierValidator.isValid(positionTwo);
            if(arrayOutOfBounds) {
                System.out.println("Posicion fuera de los limites. Ingrese un numero entre 1 y " + player.getBoard().getMatrix().length);
            }
        }
        arrayOutOfBounds=true;
        while(arrayOutOfBounds) {
            System.out.println("Ingrese la posicion del soldado numero 2: ");
            positionThree = in.nextInt();
            arrayOutOfBounds=!soldierValidator.isValid(positionThree);
            if(arrayOutOfBounds){
                System.out.println("Posicion fuera de los limites. Ingrese un numero entre 1 y " + player.getBoard().getMatrix().length);
            }
        }
        boardManager.setSoldiers(player.getBoard(), positionOne, positionTwo, positionThree, player.isFirstTurn());
        player.setFirstTurn(false);
    }

    public SoldierValidator getSoldierValidator() {
        return soldierValidator;
    }
}
