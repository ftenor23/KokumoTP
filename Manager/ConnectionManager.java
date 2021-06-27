package TP_Bis.Manager;

import TP_Bis.Graphic.Graphics;
import TP_Bis.Server_Client.Client;
import TP_Bis.Server_Client.Server;
import TP_Bis.Exchange.DataExchange;
import TP_Bis.entity.Player;
import com.google.gson.Gson;

public abstract class ConnectionManager {
    private final static String WAITING=GameManager.getWAITING();

    public static void sendData(DataExchange dataExchange, Player hostPlayer, Player clientPlayer, boolean gameOver, Server server) {

        Gson gson=new Gson();
        String exchangeMessage;
        dataExchange.setHostBoard(hostPlayer.getBoard());
        dataExchange.setClientBoard(clientPlayer.getBoard());
        dataExchange.setGameOver(gameOver);
        dataExchange.setConnectionLost(false);

        exchangeMessage = gson.toJson(dataExchange);

        server.setMessage(exchangeMessage);
    }

    public static String waitingOponent(Client client){
        String response="null";
        int counter=0;
        try{
            // response = client.receiveMessage();
            while(response.equals(WAITING) || response.equals("null")){//espero a que cambie el estado en server cliente
                //esperamos 0.5 segundos para ver si cambio la respuesta en el server
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