package TP_Bis.Manager;

import TP_Bis.Graphic.Graphics;
import TP_Bis.Server_Client_Bis.Client;
import TP_Bis.Server_Client_Bis.Server;
import TP_Bis.entity.Data;
import TP_Bis.entity.Player;
import com.google.gson.Gson;

public abstract class ConnectionManager {
    private final static String WAITING="waiting";

    public static Data processData(String exchangeMessage, Player hostPlayer, Player clientPlayer,
                             boolean gameOver) {
        Data data = new Data();
        Gson gson = new Gson();
        if(exchangeMessage.equals("null") || exchangeMessage.equals(WAITING)){
            data.setConnectionLost(true);
            return data;
        }
        data = gson.fromJson(exchangeMessage, Data.class);
        hostPlayer.setMyBoard(data.getHostBoard());
        clientPlayer.setMyBoard(data.getClientBoard());
        gameOver = data.isGameOver();
        return data;
    }

    public static void sendData(Data data, Player hostPlayer, Player clientPlayer, boolean gameOver, Server server) {


        Gson gson=new Gson();
        String exchangeMessage;
        data.setHostBoard(hostPlayer.getBoard());
        data.setClientBoard(clientPlayer.getBoard());
        data.setGameOver(gameOver);
        data.setConnectionLost(false);

        exchangeMessage = gson.toJson(data);

        server.setMessage(exchangeMessage);
    }

    public static String waitingOponent(Client client){
        String response="null";
        int counter=0;
        try{
            // response = client.receiveMessage();
            while(response.equals(WAITING) || response.equals("null")){//espero a que cambie el estado en server cliente
                //esperamos 3 segundos para ver si cambio la respuesta en el server
                Thread.sleep(500);
                response = client.receiveMessage();
                if(response.equals("null")){
                    counter++;
                }
                //si preguntamos 50 veces por la respuesta y no podemos conectarnos
                //al server, asumimos que se perdio la conexion y cerramos el juego
                if(counter==50){
                    return response;
                }
            }
        }catch (Exception e){
            Graphics.printException(e);
        }
        return response;
    }
}
