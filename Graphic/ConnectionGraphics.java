package TP_Bis.Graphic;


public abstract class ConnectionGraphics extends Graphics{


    public static void gameOver(){
        System.out.println("Juego terminado:");
    }

    public static void victory(){
        System.out.println("Ganaste!");
    }

    public static void lose(String enemyName){
        System.out.println("Perdiste! Gano " + enemyName + "!!");
    }

    public static void serverClosing(){
        System.out.println("El servidor se cerrara en 10 segundos...");
    }

    public static void backToMainPage(){
        System.out.println("Volviendo a la pantalla inicial...");
    }
    public static void enterName(){
        System.out.println("Ingrese su nombre: ");
    }

    public static void waitingEnemyTurn(String enemyName){
        System.out.println("Esperando turno de "+enemyName + "...");
    }

    public static void enterEnemyIp(boolean runAsHost){
        if(runAsHost){
            inviteOponent();
        }else{
            joinGame();
        }
    }

    private static void inviteOponent(){
        System.out.println("Ingrese la ip de su invitado:");
    }

    private static void joinGame(){
        System.out.println("Ingrese la ip del host: ");
    }

    public static void closeGameConectionLost(){
        System.out.println("Conexion perdida, el juego se cerrara en breve...");
    }

    public static void serverStartedOnPort(int port){
        System.out.println("Servidor iniciado en el puerto " + port);
    }

    public static void conectionAccepted(){
        System.out.println("Conexion aceptada");
    }

    public static void conectionLost(){
        System.out.println("No se puede establecer conexion con el oponente. Aguarde un momento...");
    }

    public static void cantConnectWithServer(){
        System.out.println("No se pudo conectar con el oponente. Consulte si ya inicio la partida. Volviendo a intentar...");
    }

    public static void connectedAgain(){
        System.out.println("Se volvio a establecer la conexion con el oponente");
    }

    public static void ipNotValid(String ip){
        System.out.println("La ip: " + ip + " no es valida, vuelva a ingresarla correctamente.");
    }
}

