package TP_Bis.Server_Client_Bis;

import TP_Bis.Manager.PlayerManager;
import TP_Bis.entity.Board;
import TP_Bis.entity.Game;
import com.google.gson.Gson;

public class MainClient {
    public static void main(String[] args) {
       /*Client client = new Client("127.0.0.1", false);
        Server server = new Server(false);
        String message = client.receiveMessage();
        Gson gson=new Gson();
        Board board = gson.fromJson(message,Board.class);
        PlayerManager p= new PlayerManager();
        p.printMyBoard(board); //funciona
        server.setMessage(message);*/
        Game game = new Game();
        game.play(false);
    }

}
