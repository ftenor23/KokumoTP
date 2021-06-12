package TP_Bis.validator;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.PositionGraphics;
import TP_Bis.entity.Board;

import java.util.Scanner;

public class PositionValidator {
    private Board board;

    public PositionValidator() {
        board=new Board();
    }

    public boolean isValid(int position, int ninjaId){

        while(arrayOutOfBounds(position)){
            PositionGraphics.positionOcuppied(ninjaId);
            position= EnterData.nextInt() - 1;
        }
        return true;
    }



    public boolean arrayOutOfBounds(int positionOne, int positionTwo, int positionThree){
        if(positionOne<1 || positionOne>25 || positionTwo<1 || positionTwo>25 || positionThree<1 || positionThree>25){
            return true;
        }
        return false;
    }

    public boolean arrayOutOfBounds(int position) {

        return position<0 || position>board.getMatrix().length;
    }

}
