package TP_Bis.Server;

import TP_Bis.Manager.PlayerManager;
import TP_Bis.entity.Board;
import TP_Bis.entity.Player;
import TP_Bis.entity.Soldier;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable{

    private int port;
    private String json;
    private Player clientPlayer;

    public Client(int port, String json) {
        this.port=port;
        this.json=json;
    }

        /*try {
            Socket sc = new Socket(HOST, PORT);
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            out.writeUTF("HOLA DESDE CLIENTE");
            String message=in.readUTF();

            System.out.println(message);

            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
*/



    @Override
    public void run() {
        final String HOST = "127.0.0.1";
        Scanner entry = new Scanner(System.in);
        ObjectInputStream in;
        ObjectOutputStream out;

        System.out.println("Ingrese nombre del jugador: ");
        String name = entry.nextLine();
        String message = new String();
        clientPlayer = new Player(name);
        PlayerManager playerManager = new PlayerManager();
        try {
            Socket sc = new Socket(HOST, port);
            while (true) {
                in = new ObjectInputStream(sc.getInputStream()); //recibimos objeto de servidor
                String json = (String) in.readObject();
                Gson gson = new Gson();
                Player host = gson.fromJson(json, Player.class);


                if(!playerManager.turn(clientPlayer, host)) {
                    System.out.println("Perdiste!");
                    sc.close();
                    return;
                }
                message = gson.toJson(host);
                out = new ObjectOutputStream(sc.getOutputStream());
                out.writeObject(message);
                sc.close();

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("error " + e.getLocalizedMessage());

        }

    }

    private String enterHost(){
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese la ip del host: ");
        return in.nextLine();
    }


}
