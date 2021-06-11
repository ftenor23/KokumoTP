package TP_Bis.Manager;

import TP_Bis.Server.Client;
import TP_Bis.Server.ClientBis;
import TP_Bis.Server.Server;
import TP_Bis.Server.ServerBis;
import TP_Bis.entity.Board;
import TP_Bis.entity.Game;
import TP_Bis.entity.Player;
import com.google.gson.Gson;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ServerManager implements Observer {
    ServerBis server;
    public ServerManager(){
        server = new ServerBis(5000);
        server.addObserver(this);
        Thread t=new Thread(server);
        t.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        server.getPlayer().setPlayerName((String) arg);
    }
    public void sendData(){
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese mensaje a enviar");
        String mensaje = in.nextLine();
        ClientBis c = new ClientBis(7000,mensaje);
        Thread t = new Thread(c);
        t.start();
    }
    public void printName(){
        System.out.println("NOMBRE ACTUALIZADO");
        System.out.println(server.getPlayer().getPlayerName());
    }
    public static void main(String args[]) throws InterruptedException {
        ServerManager s = new ServerManager();
        s.sendData();
        s.wait(5000);
        s.printName();
    }
}
