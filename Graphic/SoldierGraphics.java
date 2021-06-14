package TP_Bis.Graphic;

import TP_Bis.Manager.BoardManager;

public abstract class SoldierGraphics extends Graphics{
    public static void positionSoldiers(){
        System.out.println("Posicionar soldados:\n");
    }

    public static void enterCommanderPosition(){
        System.out.println("Ingrese la posicion del comandante: ");
    }

    public static void positionOutOfBounds(){
        System.out.println("Posicion fuera de los limites. Ingrese un numero entre 1 y " + BoardManager.boardLenght());
    }

    public static void enterSoldierPosition(int id){
        System.out.println("Ingrese la posicion del soldado numero " + id + ": ");
    }
}
