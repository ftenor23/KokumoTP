package TP_Bis.Manager;

import TP_Bis.Graphic.ClientGraphics;
import TP_Bis.Graphic.Graphics;
import TP_Bis.Server_Client_Bis.Client;
import TP_Bis.Server_Client_Bis.Server;
import TP_Bis.entity.Data;
import TP_Bis.entity.Player;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/*public class ClientManager {

    public static boolean hostWon(Player host, Player client) {
        GameManager gameManager = new GameManager(host, client);
        return gameManager.hostWon();
    }

    public static void closeEverything(PrintWriter output, BufferedReader input, BufferedReader stdIn,
                                        Socket socketClient) throws IOException {
        output.close();
        input.close();
        stdIn.close();
        socketClient.close();
    }

    public void gameOver(Server server, Client client, Player host, Player clientPlayer, String message, Data data, Gson gson) throws InterruptedException, IOException {

        if (hostWon(host, clientPlayer)) {
            ClientGraphics.lose(host.getPlayerName());
        } else {
            ClientGraphics.victory();
        }
        ClientGraphics.serverClosing();

        server.setMessage(message);
        // Envía a la salida estándar la respuesta del servidor
        message = waitingOponent(client); //verificar, me parece que no es necesario
        data = gson.fromJson(message, Data.class);
        clientPlayer.setMyBoard(data.getClientBoard());
        host.setMyBoard(data.getHostBoard());
        data.setGameOver(true);
        Thread.sleep(10000);
    }

    public static void setDataToSend(Data data, Player clientPlayer, Player host, boolean gameOver) {
        data.setClientBoard(clientPlayer.getBoard());
        data.setHostBoard(host.getBoard());
        data.setGameOver(gameOver);
    }

    public void execute(Player clientPlayer, Player host, Data data,
                               Server server, Client client) throws IOException, InterruptedException {
        //Ejecuta juego como cliente hasta que uno de los dos jugadores pierde

        boolean gameOver = false;
        boolean knowHostName = false;
        String message = " ";
        Gson gson = new Gson();
        PlayerManager playerManager = new PlayerManager();

        while (true) {
            // Leo la entrada del usuario
            if (clientPlayer.isFirstTurn()) {
                data.setClientName(clientPlayer.getPlayerName());
            }

            if (gameOver) {

                gameOver(server, client,host, clientPlayer, message, data, gson);

                return;
            }
            server.setMessage("waiting");

            gameOver = playerManager.turn(clientPlayer, host);

            //guardamos los datos para enviar a host
            setDataToSend(data, clientPlayer, host, gameOver);

            message = gson.toJson(data);

            if (gameOver) {
                gameOver(server, client,host, clientPlayer, message, data, gson);
                return;
            } else {
                ClientGraphics.waitingEnemyTurn(host.getPlayerName());
            }

            // La envia al servidor
            server.setMessage(message);
            // Leemos el mensaje del server y lo convertimos a nuestra clase Data
            message = waitingOponent(client);
            data = gson.fromJson(message, Data.class);
            setNewValues(clientPlayer, host, data, gameOver);

            if (!knowHostName) {
                host.setPlayerName(data.getHostName());
                knowHostName = true;
            }
        }
    }

    public static void setNewValues(Player clientPlayer, Player host, Data data,
                                     boolean gameOver) {
        //seteamos los tableros con los valores originales

        clientPlayer.setMyBoard(data.getClientBoard());
        host.setMyBoard(data.getHostBoard());
        //si el juego se termino, lo guardamos en nuestra variable para poder ejecutar los ciclos
        gameOver = data.isGameOver();
    }

    public String waitingOponent(Client client){
        String response=null;
        try{
            response = client.receiveMessage();
            while(response.equals("waiting")){//espero a que cambie el estado en server cliente
                try{
                    Thread.sleep(500);
                }catch(Exception e) {
                    response = client.receiveMessage();
                }
            }
        }catch (Exception e){
            Graphics.printException(e);
        }
        return response;
    }


}*/