package TP_Bis.Graphic;

import java.net.Socket;

public abstract class ServerGraphics extends Graphics{
    public static void showIp(String ip){
        System.out.println("La ip de este equipo es: " + ip);
    }
    public static void waitingForClient(){
        System.out.println("Esperando al cliente...");
    }

    public static void connectionAccepted(Socket client){
        System.out.println("Conexión aceptada: " + client.getInetAddress().getHostAddress());
    }
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

    public static void enterName(){
        System.out.println("Ingrese su nombre: ");
    }

    public static void waitingEnemyTurn(String enemyName){
        System.out.println("Esperando turno "+enemyName + "...");
    }

    public static void enterOpponentIp(){
        System.out.println("Ingrese la ip de su oponente:");
    }
}

