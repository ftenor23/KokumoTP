package TP_Bis.SERVERS_Y_CLIENTES;
import TP_Bis.Manager.PlayerManager;
import TP_Bis.entity.Boards;
import TP_Bis.entity.Player;
import TP_Bis.entity.Players;
import com.google.gson.Gson;

import java.io.*;
import java.net.*;

public class Server {
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

        System.out.println("Escuchando: " + socketServidor);
        try {
            // Se bloquea hasta que recibe alguna petición de un cliente
            // abriendo un socket para el cliente
            socketCliente = socketServidor.accept();
            System.out.println("Connexión acceptada: "+ socketCliente);
            // Establece canal de entrada
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            // Establece canal de salida
            salida = new PrintWriter(new BufferedWriter(new
                    OutputStreamWriter(socketCliente.getOutputStream())),true);

            // Hace eco de lo que le proporciona el cliente, hasta que recibe "Adios"
            Player host= new Player("chinwenwencha");
            Player client = new Player("client");
            Players players = null;
            PlayerManager playerManager=new PlayerManager();
            Boards boards=new Boards();
            while (true) {
                //Scanner in = new Scanner(System.in);
                String str = entrada.readLine();
                System.out.println(str);
                //System.out.println("Cliente: " + str);
                Gson gson = new Gson();
                /* = gson.fromJson(str,Player.class);
                host.getMyBoard().printOwnBoard();*/
                boards = gson.fromJson(str,Boards.class);
                client.setMyBoard(boards.getClientBoard());
                client.getBoard().printOwnBoard();
                playerManager.turn(host,client);
                boards.setHostBoard(host.getBoard());
                boards.setClientBoard(client.getBoard());

                str = gson.toJson(boards);
                //str = in.nextLine();
                salida.println(str);
                System.out.println("Esperando turno del cliente...");
                if (str.equals("Adios")) break;
            }

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        salida.close();
        entrada.close();
        socketCliente.close();
        socketServidor.close();
    }
}


