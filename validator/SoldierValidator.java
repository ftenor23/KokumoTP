package TP_Bis.validator;

import TP_Bis.entity.Soldier;

public class SoldierValidator {
    PositionValidator positionValidator = new PositionValidator();
    public boolean isValid(int positionOne, int positionTwo, int positionThree){
        return !positionValidator.arrayOutOfBounds(positionOne,positionTwo,positionThree);
    }

    public boolean isValid(int position){
        return !positionValidator.arrayOutOfBounds(position);
    }
}
