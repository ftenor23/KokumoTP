package TP_Bis.Graphic;


public abstract class Graphics {
    public static void invalidOption(){
        System.out.println("OPCION INVALIDA");
    }
    public static void printException(Exception e){
        System.out.println("Exception: " + e.getLocalizedMessage());
    }

    public static void invalidSelection(){
        System.out.println("Seleccion invalida.");
    }

    public static void printLine(){
        System.out.println(" ");
    }

    public static void enterAValidNumber(){
        System.out.println("Caracter no valido. Ingrese solo una variable numerica.");
    }
}

