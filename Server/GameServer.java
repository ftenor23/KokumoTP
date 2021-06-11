package TP_Bis.Server;

import TP_Bis.entity.Board;
import TP_Bis.entity.Player;
import TP_Bis.entity.Players;
import TP_Bis.entity.Soldier;
import com.google.gson.Gson;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private ServerSocket server = null;
    private Socket socketClient = null;
    private final int PORT = 5000;
    private InetAddress adress;

    public GameServer() throws IOException {
        server = new ServerSocket(PORT);// con scket no, usar httprequest
        System.out.println("Servidor iniciado");
        String localIpAddress = InetAddress.getLocalHost().getHostAddress();
        System.out.println(localIpAddress);
    }
}

   /* public void send(Player host, Player client) {
        //Players players = new Players();
        //players.setHostPlayer(host);
        players.setClientPlayer(client);
        try {
            while ((socketClient = server.accept()) != null) {
                System.out.println("Conexion desde: " + socketClient.getInetAddress());
                ObjectOutputStream os = new ObjectOutputStream(socketClient.getOutputStream());
                Gson gson = new Gson();
                //System.out.println(gson.toJson(soldier));
                os.writeObject(gson.toJson(players));

                //os.close();

            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        } catch (Exception e){
            System.out.println("Error "+e.getLocalizedMessage());
        }
    }

    public Players receiveData(){
        //Players players= new Players();
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
    }
}
*/