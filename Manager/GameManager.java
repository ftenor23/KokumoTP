package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.Graphics;
import TP_Bis.Graphic.ConnectionGraphics;
import TP_Bis.Server_Client_Bis.Client;
import TP_Bis.Server_Client_Bis.Server;
import TP_Bis.entity.Data;
import TP_Bis.entity.Player;
import com.google.gson.Gson;

import java.io.IOException;

public class GameManager {

    private static String WAITING= "waiting";
    private PlayerManager playerManager;
    private Server server;
    private Client client;

    public GameManager(){
        this.playerManager=new PlayerManager();
    }

    public void startGame(boolean runAsHost, String oponentIp){
        server=new Server(runAsHost);
        client = new Client(oponentIp, runAsHost);
        Player hostPlayer;
        Player clientPlayer;
       ConnectionGraphics.enterName();
       String name= EnterData.nextLine();
       if(runAsHost){
          hostPlayer = new Player(name);
          clientPlayer = new Player("enemigo");
       }else {
           hostPlayer = new Player("enemigo");
           clientPlayer = new Player(name);
       }

       try{
           execute(hostPlayer,clientPlayer,server,client,runAsHost);
       }catch(Exception e){
           Graphics.printException(e);
       }

    }

    public static boolean hostWon(Player host, Player client){
        PlayerManager playerManager = new PlayerManager();
        if(playerManager.enemiesAreDead(client)){
            return true;
        }else{
            return false;
        }
    }

    private static void gameOver(Player host, Player enemy, Data data, Gson gson, String str, Server server, boolean runAsHost){
        //imprimimos mensaje y enviamos datos a cliente para finalizar la partida
        ConnectionGraphics.gameOver();

        if(runAsHost){
            if(hostWon(host,enemy)){
                ConnectionGraphics.victory();
            }else{
                ConnectionGraphics.lose(data.getClientName());
            }
        }else{
            if(!hostWon(host,enemy)){
                ConnectionGraphics.victory();
            }else{
                ConnectionGraphics.lose(data.getHostName());
            }
        }

        data.setHostBoard(host.getBoard());
        data.setClientBoard(enemy.getBoard());
        data.setGameOver(true);

        str = gson.toJson(data);
//ENVIAR MENSAJE AL SERVER
        server.setMessage(str);
        ConnectionGraphics.serverClosing();
        try{
            Thread.sleep(10000);
        }catch(Exception e){
            e.getLocalizedMessage();
        }
    }

    private void execute(Player hostPlayer, Player clientPlayer, Server server, Client client, boolean runAsHost) throws IOException {
        //ejecutamos un ciclo hasta que alguno de los dos jugadores pierda
        /*ServerManager serverManager = new ServerManager();
        serverManager.execute(host, enemy,client);*/
        boolean firstTurn=true;
        boolean gameOver = false;
        boolean knowOponentName = false;
        Data data = new Data();
        PlayerManager playerManager = new PlayerManager();
        Gson gson = new Gson();
        String exchangeMessage="";
        while (!gameOver) {

            //Definimos que el primer turno es siempre del host, por lo tanto
            //consultamos si el jugador actual es host o no para saber
            //que ejecutar primero. Si no es el primer turno de ninguno de los dos
            //tambien se ejecuta el siguiente codigo
            if(!runAsHost || !hostPlayer.isFirstTurn()){
                printWaitingTurn(runAsHost,data,clientPlayer);
                exchangeMessage = waitingOponent(client);
                data = processData(exchangeMessage,hostPlayer,clientPlayer,gameOver);
            }
            server.setMessage(WAITING);
            gameOver = data.isGameOver();
            //conultamos si el enemigo perdio
            if (gameOver){
                gameOver(hostPlayer,clientPlayer,data,gson,exchangeMessage,server, runAsHost);
                //deberia cerrar  el server
                return;
            }

            //consultamos si el turno actual es del host para saber en que orden enviar los
            //datos a playerManager
            //playerManager nos devuelve un booleano indicando si el juego termino
            //consultando si los jugadores enemigos estan muertos
            if(runAsHost) {
                if(hostPlayer.isFirstTurn()){
                    data.setHostName(hostPlayer.getPlayerName());
                    data.setClientName("enemigo");
                }
                gameOver = playerManager.turn(hostPlayer, clientPlayer);
            } else {
                if(clientPlayer.isFirstTurn()){
                    data.setClientName(clientPlayer.getPlayerName());
                }
                gameOver = playerManager.turn(clientPlayer, hostPlayer);
            }

            //consultamos si el juego termino
            if(gameOver){
                gameOver(hostPlayer,clientPlayer,data,gson,exchangeMessage,server, runAsHost);
                return;
            }

            data.setHostBoard(hostPlayer.getBoard());
            data.setClientBoard(clientPlayer.getBoard());
            data.setGameOver(gameOver);

            exchangeMessage = gson.toJson(data);
//crear clase exchangeManager
            server.setMessage(exchangeMessage);
            //sendData(data, hostPlayer,clientPlayer,gameOver,server);

        }

    }

    public String waitingOponent(Client client){
        String response="null";
        try{
           // response = client.receiveMessage();
            while(response.equals(WAITING) || response.equals("null")){//espero a que cambie el estado en server cliente
                //esperamos 3 segundos para ver si cambio la respuesta en el server
                Thread.sleep(3000);
                response = client.receiveMessage();
            }
        }catch (Exception e){
            Graphics.printException(e);
        }
        return response;
    }

    private void printWaitingTurn(boolean runAsHost, Data data, Player clientPlayer){
        String clientName= data.getClientName();
        String hostName=new String();


        if(runAsHost) {
            ConnectionGraphics.waitingEnemyTurn(clientName);
        } else {
            if(!clientPlayer.isFirstTurn()){
                hostName=data.getHostName();
            }else {
                hostName="enemigo";
            }
            ConnectionGraphics.waitingEnemyTurn(hostName);
        }
    }


    private Data processData(String str, Player hostPlayer, Player clientPlayer,
                             boolean gameOver) {
        Data data = new Data();
        Gson gson = new Gson();
        data = gson.fromJson(str, Data.class);
        hostPlayer.setMyBoard(data.getHostBoard());
        clientPlayer.setMyBoard(data.getClientBoard());
        gameOver = data.isGameOver();
        return data;
    }

    private void sendData(Data data, Player hostPlayer, Player clientPlayer, boolean gameOver, Server server) {
        Gson gson=new Gson();
        String str;
        data.setHostBoard(hostPlayer.getBoard());
        data.setClientBoard(clientPlayer.getBoard());
        data.setGameOver(gameOver);

        str = gson.toJson(data);

        server.setMessage(str);
    }

}
