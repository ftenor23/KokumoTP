package TP_Bis.Graphic;

public class Graphics {
    public static void invalidOption(){
        System.out.println("OPCION INVALIDA");
    }
    public static void printException(Exception e){
        System.out.println("Exception: " + e.getLocalizedMessage());
    }
}
