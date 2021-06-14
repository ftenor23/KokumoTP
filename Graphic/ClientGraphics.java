package TP_Bis.Graphic;

public abstract class ClientGraphics extends ServerGraphics{
    public static void enterHostIP(){
        System.out.println("Ingrese la IP del Host: ");
    }
    public static void IOException(Exception e){
        System.err.println("No puede establer canales de E/S para la conexión");
        System.out.println(e.getLocalizedMessage());
    }

    public static void connectingToHost(){
        System.out.println("Estableciendo conexion con el host...");
    }

    public static void ipNotValid(String ip){
        System.out.println("La ip: " + ip + " no es valida, vuelva a ingresarla correctamente.");
    }
}
