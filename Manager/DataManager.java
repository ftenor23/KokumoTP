package TP_Bis.Manager;

import TP_Bis.Exchange.DataExchange;
import TP_Bis.entity.Player;
import com.google.gson.Gson;

public abstract class DataManager {
    private final static String WAITING=GameManager.getWAITING();

    public static DataExchange processData(String exchangeMessage, Player hostPlayer, Player clientPlayer) {
        DataExchange dataExchange = new DataExchange();
        Gson gson = new Gson();
        if(exchangeMessage.equals("null") || exchangeMessage.equals(WAITING)){
            //si el mensaje devuelto por el servidor ajeno es null o "waiting"
            //significa que se cayo la conexion del otro jugador
            //por lo tanto lo informamos a traves de DataExchange
            dataExchange.setConnectionLost(true);
            return dataExchange;
        }
        dataExchange = gson.fromJson(exchangeMessage, DataExchange.class);
        hostPlayer.setMyBoard(dataExchange.getHostBoard());
        clientPlayer.setMyBoard(dataExchange.getClientBoard());
        return dataExchange;
    }
}
