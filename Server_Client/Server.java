package TP_Bis.Server_Client;

import TP_Bis.Graphic.ConnectionGraphics;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;

public class Server {
    private static HttpServer server;
    private final static int HOST_PORT =8008;
    private final static int CLIENT_PORT=9009;
    private final int port;
    private final ContextHandler contextHandler;

    //en base a si corre como host, seleccionamos distintos puertos
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

    //envio mensaje al servidor
    public void setMessage(String message){
        contextHandler.setResponse(message);
    }

    //cierra el servidor al instante que es llamado
    public static void stop(){
        server.stop(0);
    }



}
