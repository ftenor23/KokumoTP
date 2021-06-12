package TP_Bis.Manager;

import TP_Bis.Graphic.ServerGraphics;
import TP_Bis.entity.Data;
import TP_Bis.entity.Player;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerManager {
    public static boolean hostWon(Player host, Player client){
        GameManager gameManager=new GameManager(host, client);
        return gameManager.hostWon();
    }

    public static void gameOver(Player host, Player client, Data data, Gson gson, String str, PrintWriter output){
        ServerGraphics.gameOver();
        if(hostWon(host,client)){
            ServerGraphics.victory();
        }
        else{
            ServerGraphics.lose(client.getPlayerName());
        }

        data.setHostBoard(host.getBoard());
        data.setClientBoard(client.getBoard());
        data.setGameOver(true);

        str = gson.toJson(data);

        output.println(str);
        ServerGraphics.serverClosing();
        try{
            Thread.sleep(10000);
        }catch(Exception e){
            e.getLocalizedMessage();
        }


    }

    public static void execute(BufferedReader input, Player host, Player client,
                                PrintWriter output) throws IOException {
        boolean firstTurn=true;
        boolean gameOver = false;
        Data data = new Data();
        PlayerManager playerManager = new PlayerManager();
        while (true) {

            Gson gson = new Gson();
            String str = input.readLine();

            data = gson.fromJson(str, Data.class);
            host.setMyBoard(data.getHostBoard());
            client.setMyBoard(data.getClientBoard());

            gameOver = data.isGameOver();

            if (gameOver){
                gameOver(host,client,data,gson,str,output);
                break;
            }

            if(firstTurn) {
                data.setHostName(host.getPlayerName());
                client.setPlayerName(data.getClientName());
                firstTurn = false;
            }

            gameOver = playerManager.turn(host,client);
            if(gameOver){
                gameOver(host,client,data,gson,str,output);
                break;
            }
            data.setHostBoard(host.getBoard());
            data.setClientBoard(client.getBoard());
            data.setGameOver(gameOver);

            str = gson.toJson(data);

            output.println(str);
            ServerGraphics.waitingEnemyTurn(client.getPlayerName());

        }
    }
}
