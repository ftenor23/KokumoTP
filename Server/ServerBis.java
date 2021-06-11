package TP_Bis.Server;

import TP_Bis.entity.Player;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Scanner;

public class ServerBis extends Observable implements Runnable {

    private int puerto;
    Player player = new Player("Facu");

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ServerBis(int puerto){
        this.puerto=puerto;
    }
    @Override
    public void run() {
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        Gson gson= new Gson();

        try {
            servidor=new ServerSocket(puerto);
            System.out.println("Servidor iniciado");

            while(true){
                sc=servidor.accept();
                System.out.println("Cliente conectado");

                in = new DataInputStream(sc.getInputStream());
                String mensaje = in.readUTF();
                System.out.println(mensaje);

                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();

                sc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
