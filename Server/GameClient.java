package TP_Bis.Server;

import TP_Bis.entity.Player;
import TP_Bis.entity.Players;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    private String HOST;
    private final static int PORT = 5000;
    private Socket socketClient;

    public GameClient(){
        this.HOST = enterHost();
        try {
            socketClient = new Socket(HOST, PORT);
        }catch (Exception e){
            System.out.println("error " + e.getLocalizedMessage());
        }
    }

    private String enterHost(){
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese la ip del host: ");
        return in.nextLine();
    }

    public void send(Player clientPlayer){
        try {
            ObjectOutputStream os = new ObjectOutputStream(socketClient.getOutputStream());
            Gson gson = new Gson();
            os.writeObject(gson.toJson(clientPlayer));
        }catch(IOException e){
            System.out.println("error: "+ e.getLocalizedMessage());
        }
    }

    /*public Players receiveData(){
        Players players= new Players();
        try{
            ObjectInputStream ois = new ObjectInputStream(socketClient.getInputStream()); //recibimos objeto de servidor
            String json = (String) ois.readObject();
            Gson gson = new Gson();
            players = (Players) gson.fromJson(json, Players.class);
        }catch(IOException e){
            System.out.println("error: " + e.getLocalizedMessage());
            return null;
        }catch(java.lang.ClassNotFoundException e){
            System.out.println("error: " + e.getLocalizedMessage());
            return null;
        }catch (Exception e){
            System.out.println("Error: " + e.getLocalizedMessage());
            return null;
        }
        return players;

    }*/
}
