package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.Graphics;
import TP_Bis.Graphic.ConnectionGraphics;
import TP_Bis.Server_Client.Client;
import TP_Bis.Server_Client.Server;
import TP_Bis.Exchange.DataExchange;
import TP_Bis.entity.Player;
import java.net.BindException;

public class GameManager {

    private final static String WAITING= "waiting";
    private Server server;
    private Client client;

    public static String getWAITING() {
        return WAITING;
    }



    public void startGame(boolean runAsHost, String oponentIp) throws BindException{
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
        server.stop();
    }

    //esta funcion ejecuta un ciclo hasta que alguno de los dos jugadores pierda o
    //se pierda la conexion de alguno de ellos
    private void execute(Player hostPlayer, Player clientPlayer, Server server, Client client, boolean runAsHost){
        //ejecutamos un ciclo hasta que alguno de los dos jugadores pierda


        boolean gameOver = false;
        DataExchange dataExchange = new DataExchange();
        PlayerManager playerManager = new PlayerManager();
        String exchangeMessage="";
        while (true) {
            //Definimos que el primer turno es siempre del host, por lo tanto
            //consultamos si el jugador actual es host o no para saber
            //que ejecutar primero. Si no es el primer turno de ninguno de los dos
            //tambien se ejecuta el siguiente codigo que es para recibir los datos
            //del oponente
            if(!runAsHost || !hostPlayer.isFirstTurn()){
                printWaitingTurn(runAsHost, dataExchange,clientPlayer);
                exchangeMessage = waitingOponent(client);
                dataExchange = processData(exchangeMessage,hostPlayer,clientPlayer);
                if(dataExchange.connectionLost()){
                    ConnectionGraphics.closeGameConectionLost();
                    return;
                }
            }
            //enviamos al server el msj "waiting" para indicar que el jugador actual
            //esta realizando sus movimientos y el otro este a la a espera
            server.setMessage(WAITING);
            gameOver = dataExchange.isGameOver();
            //consultamos si el enemigo nos gano en su jugada
            if (gameOver){
                gameOver(hostPlayer,clientPlayer, dataExchange,server, runAsHost);
                return;
            }

            //consultamos si el turno actual es del host para saber en que orden enviar los
            //datos a playerManager
            //playerManager nos devuelve un booleano indicando si el juego termino
            //consultando si los jugadores enemigos estan muertos
            if(runAsHost) {
                if(hostPlayer.isFirstTurn()){
                    dataExchange.setHostName(hostPlayer.getPlayerName());
                    dataExchange.setClientName("enemigo");
                }
                gameOver = playerManager.turn(hostPlayer, clientPlayer);
            } else {
                if(clientPlayer.isFirstTurn()){
                    dataExchange.setClientName(clientPlayer.getPlayerName());
                }
                gameOver = playerManager.turn(clientPlayer, hostPlayer);
            }

            //consultamos si el juego termino por eliminar a los jugadores enemigos
            if(gameOver){
                gameOver(hostPlayer,clientPlayer, dataExchange,server, runAsHost);
                return;
            }

            //enviamos los datos al servidor para que el otro jugador los capture
            sendData(dataExchange, hostPlayer,clientPlayer,gameOver,server);

        }

    }

    public static boolean hostWon(Player client){
        PlayerManager playerManager = new PlayerManager();
        return playerManager.enemiesAreDead(client);
    }

    private static void gameOver(Player host, Player enemy, DataExchange dataExchange,Server server, boolean runAsHost){
        //imprimimos mensaje y enviamos datos a cliente para finalizar la partida
        ConnectionGraphics.gameOver();

        if(runAsHost){
            if(hostWon(enemy)){
                ConnectionGraphics.victory();
            }else{
                ConnectionGraphics.lose(dataExchange.getClientName());
            }
        }else{
            if(!hostWon(enemy)){
                ConnectionGraphics.victory();
            }else{
                ConnectionGraphics.lose(dataExchange.getHostName());
            }
        }

        sendData(dataExchange, host, enemy, true, server);
        ConnectionGraphics.serverClosing();
        ConnectionGraphics.backToMainPage();
        try{
            Thread.sleep(10000);
        }catch(Exception e){
            e.getLocalizedMessage();
        }
    }


    public String waitingOponent(Client client){

        return ConnectionManager.waitingOponent(client);

    }

    private void printWaitingTurn(boolean runAsHost, DataExchange dataExchange, Player clientPlayer){
        String clientName= dataExchange.getClientName();
        String hostName=" ";


        if(runAsHost) {
            ConnectionGraphics.waitingEnemyTurn(clientName);
        } else {
            if(!clientPlayer.isFirstTurn()){
                hostName= dataExchange.getHostName();
            }else {
                hostName="enemigo";
            }
            ConnectionGraphics.waitingEnemyTurn(hostName);
        }
    }

    //procesa los datos recibidos en json y nos devuelve un objeto DataExchange
    private DataExchange processData(String exchangeMessage, Player hostPlayer, Player clientPlayer) {
        return DataManager.processData(exchangeMessage,hostPlayer,clientPlayer);
    }

    //envia los datos al servidor
    private static void sendData(DataExchange dataExchange, Player hostPlayer, Player clientPlayer, boolean gameOver, Server server) {
        ConnectionManager.sendData(dataExchange,hostPlayer,clientPlayer,gameOver,server);
    }

}
