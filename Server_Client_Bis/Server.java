package TP_Bis.Server_Client_Bis;

import TP_Bis.Graphic.ConnectionGraphics;
//import TP_Bis.Manager.ConnectionManager;
import TP_Bis.entity.Data;
import TP_Bis.entity.Player;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;

public class Server {
    private static HttpServer server;
    private static int HOST_PORT =8008;
    private static int CLIENT_PORT=9009;
    private int port;
    private ContextHandler contextHandler;
    InetSocketAddress address; //direccion (mi ip)

    public Server(boolean runAsHost){
        if(runAsHost){
            this.port= HOST_PORT;
        } else {
            this.port=CLIENT_PORT;
        }

        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e){
            e.printStackTrace();
        }

        contextHandler=new ContextHandler();

        server.createContext("/", contextHandler);
        server.start();
        //setMessage("waiting");
        ConnectionGraphics.serverStartedOnPort(port);
    }


    private static void gameOver(Player host, Player client, Data data, Gson gson, String str, PrintWriter output){
        //imprimimos mensaje y enviamos datos a cliente para finalizar la partida
        ConnectionGraphics.gameOver();
        //ConnectionManager.gameOver(host,client,data,gson,str,output);

    }


    //envio mensaje al servidor
    public void setMessage(String message){
        contextHandler.setResponse(message);
    }



}
