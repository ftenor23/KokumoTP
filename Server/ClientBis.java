package TP_Bis.Server;

import TP_Bis.entity.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientBis implements Runnable{
    private int puerto;
    private String mensaje;
    Player player = new Player("SOFI");

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ClientBis(int puerto, String mensaje){
        this.mensaje=mensaje;
        this.puerto=puerto;
    }
    @Override
    public void run() {
        final String HOST = "127.0.0.1";

        DataInputStream in;
        DataOutputStream out;

        try {
            Socket sc = new Socket(HOST, puerto);
            System.out.println("Ingrese mensaje para servidor");
            Scanner entry = new Scanner(System.in);
            mensaje=entry.nextLine();
            //in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            out.writeUTF(mensaje);
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
