package TP_Bis.Server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Server {

    public Server () throws IOException{
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
}
