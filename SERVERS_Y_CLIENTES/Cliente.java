package TP_Bis.SERVERS_Y_CLIENTES;
import TP_Bis.Manager.GameManager;
import TP_Bis.Manager.PlayerManager;
import TP_Bis.entity.Board;
import TP_Bis.entity.Boards;
import TP_Bis.entity.Player;
import TP_Bis.entity.Players;
import com.google.gson.Gson;

import java.net.*;
import java.io.*;
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

        String linea;

        // El programa cliente no analiza los mensajes enviados por el
        // usario, simplemente los reenvía al servidor hasta que este
        // se despide con "Adios"
        PlayerManager playerManager = new PlayerManager();
        Player host=new Player("host");
        Player clientPlayer = new Player("jose");
        Boards boards=new Boards();
        boolean gameOver;
        try {
            while (true) {
                // Leo la entrada del usuario

                gameOver=playerManager.turn(clientPlayer, host);
                boards.setClientBoard(clientPlayer.getBoard());
                boards.setHostBoard(host.getBoard());
                Gson gson = new Gson();
                Board board = new Board();

                linea = gson.toJson(boards);//si envio board no hay problem
                System.out.println("Esperando el turno del host...");
                //linea = stdIn.readLine();
                // La envia al servidor
                salida.println(linea);
                // Envía a la salida estándar la respuesta del servidor
                linea = entrada.readLine();
                boards = gson.fromJson(linea, Boards.class);
                clientPlayer.setMyBoard(boards.getClientBoard());
                host.setMyBoard(boards.getHostBoard());


                if (gameOver){
                    if(hostWon(host,clientPlayer)){
                        System.out.println("Perdiste!");
                    }else{
                        System.out.println("Ganaste!");
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
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

