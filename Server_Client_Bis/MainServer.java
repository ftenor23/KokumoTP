package TP_Bis.Server_Client_Bis;

import TP_Bis.Manager.PlayerManager;
import TP_Bis.entity.Board;
import TP_Bis.entity.Game;
import com.google.gson.Gson;

public class MainServer {
    public static void main(String[] args) {
        /*Server server = new Server(true);
        Client client = new Client("127.0.0.1",true);
        server.setMessage("hola mundo");
        Board board = new Board();
        Gson gson=new Gson();
        String message= gson.toJson(board);
        server.setMessage(message);
        try{
            Thread.sleep(5000);
        }catch(Exception e){
            e.getLocalizedMessage();
        }
        message = client.receiveMessage();
        board=gson.fromJson(message, Board.class);
        PlayerManager p= new PlayerManager();
        p.printMyBoard(board);*/
        Game game = new Game();
        game.play(true);
        return;

    }
}
