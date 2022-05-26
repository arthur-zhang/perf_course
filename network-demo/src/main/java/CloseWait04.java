import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class CloseWait04 {
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);

        httpServer.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                byte[] respContents = "Hello World".getBytes("UTF-8");
                httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");

                httpExchange.sendResponseHeaders(200, respContents.length);
                httpExchange.getResponseBody().write(respContents);
//                httpExchange.close();
            }
        });
        httpServer.setExecutor(Executors.newFixedThreadPool(100));

        httpServer.start();
    }
}
