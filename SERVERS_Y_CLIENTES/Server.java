package TP_Bis.SERVERS_Y_CLIENTES;
import TP_Bis.Manager.GameManager;
import TP_Bis.Manager.PlayerManager;
import TP_Bis.entity.Data;
import TP_Bis.entity.Player;
import TP_Bis.entity.Players;
import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server{
    public static final int PORT = 4444;

    public static void main(String[] args) throws IOException {
        // Establece el puerto en el que escucha peticiones
        ServerSocket socketServidor = null;
        try {
            socketServidor = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("No puede escuchar en el puerto: " + PORT);
            System.exit(-1);
        }

        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;

        //System.out.println("Escuchando: " + socketServidor);
        System.out.println("Esperando al cliente...");
        try {
            // Se bloquea hasta que recibe alguna petición de un cliente
            // abriendo un socket para el cliente
            socketCliente = socketServidor.accept();
            System.out.println("Conexión aceptada: "/*+ socketCliente*/);
            // Establece canal de entrada
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            // Establece canal de salida
            salida = new PrintWriter(new BufferedWriter(new
                    OutputStreamWriter(socketCliente.getOutputStream())),true);

            // Hace eco de lo que le proporciona el cliente, hasta que recibe "Adios"
            Scanner in = new Scanner(System.in);
            System.out.println("Ingrese su nombre:");
            String hostName= in.nextLine();
            Player host= new Player(hostName);
            Player client = new Player(" ");
            PlayerManager playerManager=new PlayerManager();
            Data data =new Data();
            boolean gameOver=false;
            boolean firstTurn=true;
            while (true) {
                //Scanner in = new Scanner(System.in);
                Gson gson = new Gson();
                String str = entrada.readLine();
                //System.out.println(str);
                //System.out.println("Cliente: " + str);
                data = gson.fromJson(str, Data.class);
                host.setMyBoard(data.getHostBoard());
                client.setMyBoard(data.getClientBoard());
                gameOver = data.isGameOver();

                if (gameOver){
                    System.out.println("Juego terminado");
                    if(hostWon(host,client)){
                        System.out.println("Ganaste!");
                    }
                    else{
                        System.out.println("Perdiste, gano " + client.getPlayerName() + "!!");
                    }
                    //gameOver = playerManager.turn(host,client);
                    data.setHostBoard(host.getBoard());
                    data.setClientBoard(client.getBoard());
                    data.setGameOver(gameOver);

                    str = gson.toJson(data);
                    //str = in.nextLine();
                    salida.println(str);
                    System.out.println("El servidor se cerrara en 10 segundos...");
                    Thread.sleep(10000);
                    break;
                }

                if(firstTurn) {
                    data.setHostName(hostName);
                    client.setPlayerName(data.getClientName());
                    firstTurn = false;
                }

                gameOver = playerManager.turn(host,client);
                if(gameOver){
                    System.out.println("Juego terminado");
                    if(hostWon(host,client)){
                        System.out.println("Ganaste!");
                    }
                    else{
                        System.out.println("Perdiste, gano " + client.getPlayerName() + "!!");
                    }
                    //gameOver = playerManager.turn(host,client);
                    data.setHostBoard(host.getBoard());
                    data.setClientBoard(client.getBoard());
                    data.setGameOver(gameOver);

                    str = gson.toJson(data);
                    //str = in.nextLine();
                    salida.println(str);
                    System.out.println("El servidor se cerrara en 10 segundos...");
                    Thread.sleep(10000);
                    break;
                }
                data.setHostBoard(host.getBoard());
                data.setClientBoard(client.getBoard());
                data.setGameOver(gameOver);

                str = gson.toJson(data);
                //str = in.nextLine();
                salida.println(str);
                System.out.println("Esperando turno "+client.getPlayerName() + "...");

            }

        } catch (IOException | InterruptedException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        salida.close();
        entrada.close();
        socketCliente.close();
        socketServidor.close();
    }

    private static boolean hostWon(Player host, Player client){
        GameManager gameManager=new GameManager(host, client);
        return gameManager.hostWon();
    }



}


