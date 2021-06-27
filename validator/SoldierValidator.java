package TP_Bis.validator;



public class SoldierValidator {

    public boolean isValid(int positionOne, int positionTwo, int positionThree){
        return !PositionValidator.arrayOutOfBounds(positionOne,positionTwo,positionThree);
    }

    public boolean isValid(int position){
        return !PositionValidator.arrayOutOfBounds(position);
    }
}
