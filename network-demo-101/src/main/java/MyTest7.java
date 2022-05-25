import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * Created By Arthur Zhang at 2022/4/1
 */
public class MyTest7 {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(1234), 50);

        server.createContext("/", httpExchange -> {

            byte[] respContents = "Hello World".getBytes(StandardCharsets.UTF_8);

            // 设置响应头
            httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            // 设置响应code和内容长度
            httpExchange.sendResponseHeaders(200, respContents.length);

            // 设置响应内容
            httpExchange.getResponseBody().write(respContents);

            // 关闭处理器, 同时将关闭请求和响应的输入输出流（如果还没关闭）
            httpExchange.close();
        });
        server.start();
    }
}
