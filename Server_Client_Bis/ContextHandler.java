package TP_Bis.Server_Client_Bis;

import TP_Bis.entity.Board;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ContextHandler implements HttpHandler {
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream is = null;
        OutputStream os = null;

        //leer request
        is = exchange.getRequestBody();


        /*Board board = new Board();

        Gson json = new Gson();
        response = json.toJson(board); *///subo el objeto en json a la pagina


        exchange.sendResponseHeaders(200, response.getBytes().length);

        os = exchange.getResponseBody();// captura la respuesta
        os.flush();
        os.write(response.getBytes()); //escribe la respuesta
        os.close();

        exchange.close();
    }
}
