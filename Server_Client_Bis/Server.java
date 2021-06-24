package TP_Bis.Server_Client_Bis;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.Graphics;
import TP_Bis.Graphic.ServerGraphics;
import TP_Bis.Manager.PlayerManager;
//import TP_Bis.Manager.ConnectionManager;
import TP_Bis.entity.Data;
import TP_Bis.entity.Player;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetAddress;
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

        System.out.println("Server started on port " + port);
    }

    /*public void run()throws IOException{
        // Establece el puerto en el que escucha peticiones
        String ip = InetAddress.getLocalHost().getHostAddress();
        ServerGraphics.showIp(ip);

        ServerGraphics.enterOpponentIp();
        String opponentIp=EnterData.nextLine();
        Client client = new Client(opponentIp, false);

        ServerGraphics.waitingForClient();
        try {
            // Se bloquea hasta que recibe alguna petición de un cliente

            ServerGraphics.enterName();
            String hostName= EnterData.nextLine();
            Player host= new Player(hostName);
            Player enemy = new Player(" ");

            execute(host,enemy,client); //verificar como enviar server
        } catch (IOException e) {
            ServerGraphics.printException(e);
        }

    }

    //private static boolean hostWon(Player host, Player client){
       /* return ConnectionManager.hostWon(host,client);
    }*/

    private static void gameOver(Player host, Player client, Data data, Gson gson, String str, PrintWriter output){
        //imprimimos mensaje y enviamos datos a cliente para finalizar la partida
        ServerGraphics.gameOver();
        //ConnectionManager.gameOver(host,client,data,gson,str,output);

    }

    /*private void execute(Player host, Player enemy,Client client) throws IOException {
        //ejecutamos un ciclo hasta que alguno de los dos jugadores pierda
        /*ServerManager serverManager = new ServerManager();
        serverManager.execute(host, enemy,client);
        boolean firstTurn=true;
        boolean gameOver = false;
        Data data = new Data();
        PlayerManager playerManager = new PlayerManager();
        Gson gson = new Gson();
        while (true) {

            String str = waitingOponent(client);

            data = gson.fromJson(str, Data.class);
            host.setMyBoard(data.getHostBoard());
            enemy.setMyBoard(data.getClientBoard());

            gameOver = data.isGameOver();

            if (gameOver){
                gameOver(host,enemy,data,gson,str,output);
                return;
            }


            setMessage("waiting");
            gameOver = playerManager.turn(host,enemy);
            if(gameOver){
                gameOver(host,enemy,data,gson,str,output);
                return;
            }
            data.setHostBoard(host.getBoard());
            data.setClientBoard(enemy.getBoard());
            data.setGameOver(gameOver);

            str = gson.toJson(data);

            setMessage(str);
            ServerGraphics.waitingEnemyTurn(enemy.getPlayerName());

        }
    }*/


    //envio mensaje al servidor
    public void setMessage(String message){
        contextHandler.setResponse(message);
    }

    //se pasa a game manager
    public String waitingOponent(Client client){
        String response=null;
        try{
            response = client.receiveMessage();
            while(response.equals("waiting")){//espero a que cambie el estado en server cliente
                try{
                    Thread.sleep(500);
                    response = client.receiveMessage();
                }catch(Exception e) {
                    Graphics.printException(e);
                }
            }
        }catch (Exception e){
            Graphics.printException(e);
        }
        return response;
    }

    /*Turno jugador
            posicionar soldaos=>envio a server para validar
      */



}
