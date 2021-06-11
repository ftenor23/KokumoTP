package TP_Bis.Server;

import TP_Bis.Manager.PlayerManager;
import TP_Bis.entity.Board;
import TP_Bis.entity.Game;
import TP_Bis.entity.Player;
import TP_Bis.entity.Soldier;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import jdk.nashorn.internal.runtime.JSONFunctions;



import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server {

        private static HttpServer server;
        int port;
        InetSocketAddress address; //direccion (mi ip)

        public Server(int prt){
            port = prt;

            try {
                server = HttpServer.create(new InetSocketAddress(port), 0);
            } catch (IOException e){
                e.printStackTrace();
            }
            //crear contexto del servidor con la direccion hasta "/" (a donde se va a ir a buscar el servicio)
            //en contextHandler se implementa  el contexto
            server.createContext("/", new ContextHandler());

            server.start();

            System.out.println("Server started on port " + port);
        }

        public void receiveData(){
            try {
                URL url = new URL("http://127.0.0.1:8000/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("accept", "application/json");
                InputStream responseStream = connection.getInputStream();

                Scanner s = new Scanner(responseStream).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";
                Gson gson = new Gson();

                Board board = gson.fromJson(result, Board.class);

                board.printOwnBoard();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                dos.writeChars("param1=value1&param2=value2");
                dos.flush();
                dos.close();
                // System.out.println(result);

            }catch (Exception ex){
                ex.getMessage();
            }

        }
}



    /*public Server () throws IOException{
        final int PORT = 9009;
        HttpServer httpd = HttpServer.create(new InetSocketAddress(PORT), 0);
        HttpContext context = httpd.createContext("/");
        context.setHandler(Server::manageRequest);

        httpd.start();
    }

    private static void manageRequest(HttpExchange exchange) throws IOException{
        final int answerCode = 200;
        String content = "Respuesta desde el servidor HTTP";

        exchange.sendResponseHeaders(answerCode,content.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(content.getBytes());
        os.close();
    }
}*/



    /*private int port;

    ServerSocket server = null;
    Socket socketClient = null;

    public Server(int port){
        this.port=port;
    }


    @Override
    public void run() {

        ObjectInputStream in;
        ObjectOutputStream out;
        InetAddress adress;
        Player clientPlayer = null;
        Scanner entry = new Scanner(System.in);
        System.out.println("Ingrese el nombre del jugador: ");
        String name = entry.nextLine();
        Player host = new Player(name);
        PlayerManager playerManager = new PlayerManager();
        boolean hostLost=false;
        String json = new String();
        String message=new String();
        Gson gson = new Gson();
        try {
            server = new ServerSocket(port);
            System.out.println("Servidor iniciado");
            String localIpAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println(localIpAddress);

            //siempre va a estar escuchando peticiones
            while(true){

                socketClient= server.accept();
                if(!host.isFirstTurn()){
                    in = new ObjectInputStream(socketClient.getInputStream()); //recibimos objeto de servidor
                    json = (String) in.readObject();

                    clientPlayer = (Player) gson.fromJson(json, Player.class);
                } else {
                    System.out.println("Conexion desde: " + socketClient.getInetAddress());

                }
                //ObjectOutputStream os = new ObjectOutputStream(socketClient.getOutputStream());
                hostLost = playerManager.turn(host,clientPlayer);
                message = gson.toJson(clientPlayer);
                out = new ObjectOutputStream(socketClient.getOutputStream());
                out.writeObject(message);


                this.setChanged();
                this.notifyObservers(json);
                this.clearChanged();
                if(hostLost){
                    socketClient.close();
                    System.out.println("Perdiste");
                    return;
                }


                socketClient.close();
            }


        }catch(IOException | ClassNotFoundException e){
            System.out.println("Error: " + e.getLocalizedMessage());
        }

    }

    public void sendData(Board myBoard) {
        Gson gson = new Gson();
        String message = gson.toJson(myBoard);

        /*Client client = new Client(9009, message);
        Thread thread=new Thread(client);
        thread.start();
        try{
            ObjectOutputStream oos=new ObjectOutputStream(socketClient.getOutputStream());
            oos.writeObject(message);
            oos.close();
        }catch(IOException e){
            System.out.println(e.getLocalizedMessage());
        }

    }*/






/*String message;

    public Server() throws IOException{
        final int PORT = 9009;
        HttpServer httpd = HttpServer.create(new InetSocketAddress(PORT), 0);
        HttpContext context = httpd.createContext("/");
        message = new String();
        context.setHandler(Server::manageRequest);
        httpd.start();

    }

    private static void manageRequest(HttpExchange exchange) throws IOException{
        final int answerCode = 200;
        String content = "Servidor activo";


        exchange.sendResponseHeaders(answerCode, content.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(content.getBytes());
        os.close();
    }

*/

       /*public static void main(String args[]){
// buscar
        ServerSocket server = null;
        Socket socketClient = null;
        final int PORT= 5000;
        DataInputStream in;
        DataOutputStream out;
        InetAddress adress;

        try {
            server = new ServerSocket(PORT);// con scket no, usar httprequest
            System.out.println("Servidor iniciado");
            String localIpAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println(localIpAddress);

            while((socketClient= server.accept()) != null){
                System.out.println("Conexion desde: " + socketClient.getInetAddress());
                ObjectOutputStream os = new ObjectOutputStream(socketClient.getOutputStream());
                Soldier soldier = new Soldier(false, 1, 18);
                Gson gson = new Gson();
                //System.out.println(gson.toJson(soldier));
                os.writeObject(gson.toJson(soldier));

                os.close();


            }


        }catch(IOException e){
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        /*try{
            server=new ServerSocket(PORT);// con scket no, usar httprequest
            System.out.println("Servidor iniciado");
            String localIpAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println(localIpAddress);
            while(true) {
                sc = server.accept(); //el codigo se frena esperando una respuesta. Devuelve socket del cliente
                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                String message = in.readUTF();

                System.out.println(message);
                out.writeUTF("Hola mundo desde server");//deberia recibir un json?
                sc.close();
                System.out.println("Cliente desconectado");
            }

        }catch (Exception e){

        }
    }

}*/
