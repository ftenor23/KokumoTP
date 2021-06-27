package TP_Bis.Server_Client;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ContextHandler implements HttpHandler {
    private String response;


    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream is = null;
        OutputStream os = null;

        //leer request
        is = exchange.getRequestBody();

        exchange.sendResponseHeaders(200, response.getBytes().length);

        os = exchange.getResponseBody();// captura la respuesta
        os.flush();
        os.write(response.getBytes()); //escribe la respuesta
        os.close();

        exchange.close();
    }
}
