package TP_Bis.Graphic;

public abstract class PositionGraphics {
    public static void positionOcuppied(int ninjaId) {
        System.out.println("La posicion en la que quiere ubicar al ninja " + ninjaId + " esta fuera de los limites. Ingrese otra posicion:");
    }
}
