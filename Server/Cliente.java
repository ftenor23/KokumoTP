package TP_Bis.Server;

import TP_Bis.entity.Board;
import TP_Bis.entity.Player;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Cliente {
    public void ble(){
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
            /*connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
            dos.writeChars("param1=value1&param2=value2");
            dos.flush();
            dos.close();*/
            Server server = new Server(9000);
           // System.out.println(result);

        }catch (Exception ex){
            ex.getMessage();
        }

    }
}




