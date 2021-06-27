package TP_Bis.Server_Client;


import TP_Bis.Graphic.ConnectionGraphics;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Client {
    private String enemyIp;
    private final static int HOST_PORT =8008;
    private final static int CLIENT_PORT=9009;
    private int port;
    private String urlToGo;
    private boolean connected=false;
    private boolean connectionLost=false;
    private boolean showCantConectWithServer=true;
    private final static String WAITING="waiting";


    public Client(String enemyIp, boolean runAsHost) {
        if(runAsHost){
            this.port=CLIENT_PORT;
        }else{
            this.port=HOST_PORT;
        }
        this.enemyIp = enemyIp;
        this.urlToGo ="http://" + enemyIp + ":" + port;
    }

    //leo mensaje
    public String receiveMessage(){
        String result="null";

        try {
            URL url = new URL(urlToGo);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();

            Scanner s = new Scanner(responseStream).useDelimiter("\\A");
            //recibo JSON del oponente
            result = s.hasNext() ? s.next() : "";

            //si el resultado leido es "waiting", debo seguir esperando
            if(result.equals(WAITING)){
                if(!connected){
                    ConnectionGraphics.conectionAccepted();
                    connected=true;
                }else if(connectionLost){
                    ConnectionGraphics.connectedAgain();
                    connectionLost=false;
                }
            }

        }catch (Exception ex){
            if(connected) {
                if(!connectionLost) {
                    ConnectionGraphics.conectionLost();
                    connectionLost = true;
                }
            }else if(showCantConectWithServer){
                ConnectionGraphics.cantConnectWithServer();
                showCantConectWithServer=false;
            }

        }
        return result;
    }


}
