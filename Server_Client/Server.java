package TP_Bis.Server_Client;
import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.ServerGraphics;
import TP_Bis.Manager.GameManager;
import TP_Bis.Manager.PlayerManager;
import TP_Bis.Manager.ServerManager;
import TP_Bis.entity.Data;
import TP_Bis.entity.Player;
import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server{
    public static final int PORT = 4444;

    public static void main(String[] args) throws IOException {
      //public void run()throws IOException{
        // Establece el puerto en el que escucha peticiones
        String ip = InetAddress.getLocalHost().getHostAddress();
        ServerGraphics.showIp(ip);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
           ServerGraphics.printException(e);
            System.exit(-1);
        }

        Socket socketClient = null;
        BufferedReader input = null;
        PrintWriter output = null;

        ServerGraphics.waitingForClient();
        try {
            // Se bloquea hasta que recibe alguna petición de un cliente
            // abriendo un socket para el cliente
            socketClient = serverSocket.accept();
            ServerGraphics.connectionAccepted(socketClient); // Establece canal de entrada
            input = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            // Establece canal de salida
            output = new PrintWriter(new BufferedWriter(new
                    OutputStreamWriter(socketClient.getOutputStream())),true);

            ServerGraphics.enterName();
            String hostName= EnterData.nextLine();
            Player host= new Player(hostName);
            Player client = new Player(" ");

            execute(input,host,client,output);
        } catch (IOException e) {
            ServerGraphics.printException(e);
        }
        output.close();
        input.close();
        socketClient.close();
        serverSocket.close();
    }

    private static boolean hostWon(Player host, Player client){
        return ServerManager.hostWon(host,client);
    }

    private static void gameOver(Player host, Player client, Data data, Gson gson, String str, PrintWriter output){
        //imprimimos mensaje y enviamos datos a cliente para finalizar la partida
        ServerGraphics.gameOver();
            ServerManager.gameOver(host,client,data,gson,str,output);
    }

    private static void execute(BufferedReader input,Player host, Player client,
                                PrintWriter output) throws IOException {
        //ejecutamos un ciclo hasta que alguno de los dos jugadores pierda
        ServerManager.execute(input,host,client,output);
    }

}


