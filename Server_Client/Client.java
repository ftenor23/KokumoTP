package TP_Bis.Server_Client;
import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.ClientGraphics;
import TP_Bis.Graphic.Graphics;
import TP_Bis.Manager.ClientManager;
import TP_Bis.Manager.GameManager;
import TP_Bis.Manager.PlayerManager;
import TP_Bis.entity.Data;
import TP_Bis.entity.Player;
import com.google.gson.Gson;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    //public static void main(String[] args)  throws IOException {
      public void run() throws IOException{
        Socket socketClient = null;
        BufferedReader input = null;
        PrintWriter output = null;
        Scanner in = new Scanner(System.in);

        // Creamos un socket en el lado cliente, enlazado con un
        // servidor al que accederemos ingresando su ip
        // y que escucha en el puerto 4444
        try {
            ClientGraphics.enterHostIP();
            String ip = EnterData.nextLine();
            socketClient = new Socket(ip, 4444);
            // Obtenemos el canal de entrada
            input = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            // Obtenemos el canal de salida
            output = new PrintWriter(new BufferedWriter(new
                    OutputStreamWriter(socketClient.getOutputStream())),true);
        } catch (IOException e) {
            ClientGraphics.IOException(e);
            System.exit(-1);
        }
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));


        Player host=new Player("host");
        ClientGraphics.connectingToHost();
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        ClientGraphics.enterName();
        String clientName=EnterData.nextLine();
        Player clientPlayer = new Player(clientName);
        Data data =new Data();
        try {
            execute(clientPlayer,host,data,output,input);
        } catch (IOException | InterruptedException e) {
            Graphics.printException(e);
        } catch (NullPointerException e){
            Graphics.printException(e);
        }

        // Libera recursos
        closeEverything(output,input,stdIn,socketClient);
    }
    private static boolean hostWon(Player host, Player client){
        return ClientManager.hostWon(host, client);
    }

    private static void closeEverything(PrintWriter output, BufferedReader input, BufferedReader stdIn,
                                 Socket socketClient) throws IOException {
        ClientManager.closeEverything(output,input,stdIn,socketClient);
    }

    private static void gameOver(Player host, Player clientPlayer, PrintWriter output,
                                BufferedReader input, String message, Data data, Gson gson) throws InterruptedException, IOException {
        ClientManager.gameOver(host,clientPlayer,output,input,message,data,gson);
    }

    private static void setDataToSend(Data data, Player clientPlayer, Player host, boolean gameOver){
        ClientManager.setDataToSend(data,clientPlayer,host,gameOver);
    }

    private static void execute(Player clientPlayer, Player host, Data data,
                                PrintWriter output, BufferedReader input) throws IOException, InterruptedException {
        ClientManager.execute(clientPlayer,host,data,output,input);
    }

    private static void setNewValues(Player clientPlayer, Player host, Data data,
                                     boolean gameOver){
        ClientManager.setNewValues(clientPlayer,host,data,gameOver);
    }
}

