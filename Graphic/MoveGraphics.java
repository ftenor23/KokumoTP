package TP_Bis.Graphic;

public class MoveGraphics extends Graphics{
    public static void invalidPosition() {
        System.out.println("Posicion invalida");
    }

    public static void selectMove() {
        System.out.println("Ingrese el tipo de movimiento a realizar: ");
        System.out.println("1)Mover arriba");
        System.out.println("2)Mover abajo");
        System.out.println("3)Mover a la izquierda");
        System.out.println("4)Mover a la derecha");
    }

    public static void invalidOption() {
        System.out.println("OPCION INVALIDA");
    }

    public static void invalidMovement() {
        System.out.println("Movimiento invalido");
    }

    public static void positionOccupied() {
        System.out.println("Posicion ocupada.");
    }

    public static void impassablePosition() {
        System.out.println("Posicion intransitable.");
    }

    public static void error(){
        System.out.println("Error");
    }
}
