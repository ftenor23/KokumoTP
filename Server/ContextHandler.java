package TP_Bis.Server;

import TP_Bis.entity.Board;
import TP_Bis.entity.Player;



import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ContextHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException { //el exchange seria la request. es la variable de intercambio
        InputStream is = null;
        OutputStream os = null;

        //leer request
        is = exchange.getRequestBody(); //supongo que segun elrequest que haga enviara una u otra cosa del contexto


        Board board = new Board();//quiero que lo que esta en la pagina sea la clase person
        //convierto la clase person en json
        Gson json = new Gson();
        String response = json.toJson(board);

        //String response = "This will be the response from the Server"; //esto es lo que tengo en esa direccion con ese puerto en ese contexto        //enviar esa respuesta
        exchange.sendResponseHeaders(200, response.getBytes().length); //200 codigo de que esta todo ok

        os = exchange.getResponseBody();// captura la respuesta
        os.flush();
        os.write(response.getBytes()); //escribe la respuesta
        os.close();

        exchange.close();
    }
}