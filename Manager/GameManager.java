package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.Graphics;
import TP_Bis.Graphic.ServerGraphics;
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

       ServerGraphics.enterName();
       String name= EnterData.nextLine();
       Player player = new Player(name);
       Player enemy = new Player(" ");

       try{
           execute(player,enemy,server,client,runAsHost);
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

    private static void gameOver(Player host, Player enemy, Data data, Gson gson, String str, Server server){
        //imprimimos mensaje y enviamos datos a cliente para finalizar la partida
        ServerGraphics.gameOver();
        if(hostWon(host,enemy)){
            ServerGraphics.victory();
        }
        else{
            ServerGraphics.lose(enemy.getPlayerName());
        }

        data.setHostBoard(host.getBoard());
        data.setClientBoard(enemy.getBoard());
        data.setGameOver(true);

        str = gson.toJson(data);
//ENVIAR MENSAJE AL SERVER
        server.setMessage(str);
        ServerGraphics.serverClosing();
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
        Data data = new Data();
        PlayerManager playerManager = new PlayerManager();
        Gson gson = new Gson();
        String str="";
        while (!gameOver) {

            //iria un if preguntando si corre como host o no para determinar el turno
            if(!runAsHost || !hostPlayer.isFirstTurn()){
                ServerGraphics.waitingEnemyTurn(clientPlayer.getPlayerName());
                System.out.println(client.getPort());
                str = waitingOponent(client);
                data = gson.fromJson(str, Data.class);
                hostPlayer.setMyBoard(data.getHostBoard());
                clientPlayer.setMyBoard(data.getClientBoard());

                gameOver = data.isGameOver();
            }
            server.setMessage(WAITING);

            //si el enemigo perdio
            if (gameOver){
                gameOver(hostPlayer,clientPlayer,data,gson,str,server);
                return;
            }


            if(runAsHost) {
                gameOver = playerManager.turn(hostPlayer, clientPlayer);
            } else {
                gameOver = playerManager.turn(clientPlayer, hostPlayer);
            }


            if(gameOver){
                gameOver(hostPlayer,clientPlayer,data,gson,str,server);
                return;
            }
            data.setHostBoard(hostPlayer.getBoard());
            data.setClientBoard(clientPlayer.getBoard());
            data.setGameOver(gameOver);

            str = gson.toJson(data);

            server.setMessage(str);

            ServerGraphics.waitingEnemyTurn(clientPlayer.getPlayerName());

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

}
