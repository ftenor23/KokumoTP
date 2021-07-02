package TP_Bis.Manager;

import TP_Bis.DataIn.EnterData;

import TP_Bis.Graphic.GameGraphics;
import TP_Bis.Graphic.Graphics;
import TP_Bis.Graphic.ConnectionGraphics;
import TP_Bis.Server_Client.Client;
import TP_Bis.Server_Client.Server;
import TP_Bis.Exchange.DataExchange;
import TP_Bis.entity.Player;
import TP_Bis.validator.IPValidator;

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
        GameGraphics.enterName();
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
                receiveMessage(runAsHost,dataExchange,hostPlayer,clientPlayer,exchangeMessage,client);
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
            setNamesToSend(runAsHost,hostPlayer,clientPlayer,dataExchange);
            gameOver = playerManager.turn(clientPlayer, hostPlayer);


            //consultamos si el juego termino por eliminar a los jugadores enemigos
            //si el juego termina, entramos al metodo gameOver y desde ahi informamos
            //al servidor el estado del juego. Al terminar de ejecutar gameOver, salimos
            //del while
            if(gameOver){
                gameOver(hostPlayer,clientPlayer, dataExchange,server, runAsHost);
                return;
            }

            //enviamos los datos al servidor para que el otro jugador los capture
            sendData(dataExchange, hostPlayer,clientPlayer,gameOver,server);

        }

    }

    private static boolean hostWon(Player client){
        PlayerManager playerManager = new PlayerManager();
        return playerManager.enemiesAreDead(client);
    }

    private static void gameOver(Player host, Player enemy, DataExchange dataExchange,Server server, boolean runAsHost){
        //imprimimos mensaje y enviamos datos a cliente para finalizar la partida
        GameGraphics.gameOver();

        showWinner(runAsHost,enemy,dataExchange);

        sendData(dataExchange, host, enemy, true, server);
        ConnectionGraphics.serverClosing();
        GameGraphics.backToMainPage();
        try{
            Thread.sleep(10000);
        }catch(Exception e){
            e.getLocalizedMessage();
        }
    }

    private static void showWinner(boolean runAsHost, Player enemy, DataExchange dataExchange){
        if(runAsHost){
            if(hostWon(enemy)){
                GameGraphics.victory();
            }else{
                GameGraphics.lose(dataExchange.getClientName());
            }
        }else{
            if(!hostWon(enemy)){
                GameGraphics.victory();
            }else{
                GameGraphics.lose(dataExchange.getHostName());
            }
        }
    }

    public String waitingOponent(Client client){

        return ConnectionManager.waitingOponent(client);

    }

    private void printWaitingTurn(boolean runAsHost, DataExchange dataExchange, Player clientPlayer){
        String clientName = dataExchange.getClientName();
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

    public void singlePlay(){
        final int pOne=1;
        final int pTwo=2;
        GameGraphics.enterPlayerName(pOne);
        String namePlayerOne= EnterData.nextLine();
        GameGraphics.enterPlayerName(pTwo);
        String namePlayerTwo=EnterData.nextLine();
        Player playerOne=new Player(namePlayerOne);
        Player playerTwo=new Player(namePlayerTwo);
        PlayerManager playerManager = new PlayerManager();

        while(!gameOverSinglePlay(playerOne, playerTwo, playerManager));

        if(playerOneLost(playerOne,playerManager)){
            GameGraphics.playerWon(playerTwo.getPlayerName());

        }
        if(playerTwoLost(playerTwo,playerManager)){
            GameGraphics.playerWon(playerOne.getPlayerName());

        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return;
    }

    private static boolean gameOverSinglePlay(Player playerOne, Player playerTwo, PlayerManager playerManager) {
        while(!playerManager.turn(playerOne, playerTwo) && !playerManager.turn(playerTwo,playerOne));
        return true;
    }

    private static boolean playerOneLost(Player playerOne, PlayerManager playerManager){
        return playerManager.mySoldiersAreDead(playerOne);
    }

    private static boolean playerTwoLost(Player playerTwo, PlayerManager playerManager){
        return playerManager.mySoldiersAreDead(playerTwo);
    }

    private void receiveMessage(boolean runAsHost, DataExchange dataExchange,Player hostPlayer, Player clientPlayer,
                             String exchangeMessage, Client client){
        printWaitingTurn(runAsHost, dataExchange,clientPlayer);

        //recibe un json del oponente
        exchangeMessage = waitingOponent(client);

        dataExchange = processData(exchangeMessage,hostPlayer,clientPlayer);
        if(dataExchange.connectionLost()){
            ConnectionGraphics.closeGameConectionLost();
            GameGraphics.backToMainPage();
            try{
                Thread.sleep(5000);
            }catch(Exception e){

            }
            return;
        }
    }

    private void setNamesToSend(boolean runAsHost, Player hostPlayer, Player clientPlayer, DataExchange dataExchange){
        if(runAsHost) {
            if(hostPlayer.isFirstTurn()){
                dataExchange.setHostName(hostPlayer.getPlayerName());
                dataExchange.setClientName("enemigo");
            }

        } else {
            if(clientPlayer.isFirstTurn()){
                dataExchange.setClientName(clientPlayer.getPlayerName());
            }

        }
    }

    public String enterIp(boolean runAsHost){
        boolean ipIsValid=false;
        String oponentIp="";
        while(!ipIsValid) {
            //pedimos la ip del oponente
            ConnectionGraphics.enterEnemyIp(runAsHost);
            oponentIp = EnterData.nextLine();
            //verificamos si la ip es valida
            ipIsValid= IPValidator.ipIsValid(oponentIp);
            if(!ipIsValid){
                ConnectionGraphics.ipNotValid(oponentIp);
            }
        }
        return oponentIp;

    }

}
