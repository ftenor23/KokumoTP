package TP_Bis.SERVERS_Y_CLIENTES;
import TP_Bis.Manager.GameManager;
import TP_Bis.Manager.PlayerManager;
import TP_Bis.entity.Board;
import TP_Bis.entity.Data;
import TP_Bis.entity.Player;
import com.google.gson.Gson;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args)  throws IOException {
        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;

        // Creamos un socket en el lado cliente, enlazado con un
        // servidor que está en la misma máquina que el cliente
        // y que escucha en el puerto 4444
        try {
            socketCliente = new Socket("localhost", 4444);
            // Obtenemos el canal de entrada
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            // Obtenemos el canal de salida
            salida = new PrintWriter(new BufferedWriter(new
                    OutputStreamWriter(socketCliente.getOutputStream())),true);
        } catch (IOException e) {
            System.err.println("No puede establer canales de E/S para la conexión");
            System.exit(-1);
        }
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));

        String linea="";

        Scanner in = new Scanner(System.in);
        PlayerManager playerManager = new PlayerManager();
        Player host=new Player("host");
        System.out.println("Estableciendo conexion con el host...");
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        System.out.println("Ingrese su nombre:");
        String clientName=in.nextLine();
        Player clientPlayer = new Player(clientName);
        Data data =new Data();
        boolean gameOver=false;
        boolean knowHostName = false;
        Gson gson = new Gson();
        try {
            while (true) {
                // Leo la entrada del usuario
                if(clientPlayer.isFirstTurn()) {
                    data.setClientName(clientPlayer.getPlayerName());
                }

                if (gameOver){
                    if(hostWon(host,clientPlayer)){
                        System.out.println("Perdiste, gano " + host.getPlayerName() + "!!");
                    }else{
                        System.out.println("Ganaste!");
                    }
                    System.out.println("El servidor se cerrara en 10 segundos...");
                    salida.println(linea);
                    // Envía a la salida estándar la respuesta del servidor
                    linea = entrada.readLine();
                    data = gson.fromJson(linea, Data.class);
                    clientPlayer.setMyBoard(data.getClientBoard());
                    host.setMyBoard(data.getHostBoard());
                    data.setGameOver(gameOver);
                    Thread.sleep(10000);
                    break;
                }else{
                    System.out.println("Esperando el turno de " + host.getPlayerName() + "...");
                }

                gameOver=playerManager.turn(clientPlayer, host);

                data.setClientBoard(clientPlayer.getBoard());
                data.setHostBoard(host.getBoard());
                data.setGameOver(gameOver);

                //Board board = new Board();

                linea = gson.toJson(data);//si envio board no hay problem

                if (gameOver){
                    if(hostWon(host,clientPlayer)){
                        System.out.println("Perdiste, gano " + host.getPlayerName() + "!!");
                    }else{
                        System.out.println("Ganaste!");
                    }
                    System.out.println("El servidor se cerrara en 10 segundos...");
                    salida.println(linea);
                    // Envía a la salida estándar la respuesta del servidor
                    linea = entrada.readLine();
                    data = gson.fromJson(linea, Data.class);
                    clientPlayer.setMyBoard(data.getClientBoard());
                    host.setMyBoard(data.getHostBoard());
                    data.setGameOver(gameOver);
                    Thread.sleep(10000);
                    break;
                }else{
                    System.out.println("Esperando el turno de " + host.getPlayerName() + "...");
                }
                //linea = stdIn.readLine();
                // La envia al servidor
                salida.println(linea);
                // Envía a la salida estándar la respuesta del servidor
                linea = entrada.readLine();
                data = gson.fromJson(linea, Data.class);
                clientPlayer.setMyBoard(data.getClientBoard());
                host.setMyBoard(data.getHostBoard());
                gameOver= data.isGameOver();

                if(!knowHostName){
                    host.setPlayerName(data.getHostName());
                    knowHostName=true;
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (NullPointerException e){

        }

        // Libera recursos
        salida.close();
        entrada.close();
        stdIn.close();
        socketCliente.close();
    }
    private static boolean hostWon(Player host, Player client){
        GameManager gameManager=new GameManager(host, client);
        return gameManager.hostWon();
    }
}

