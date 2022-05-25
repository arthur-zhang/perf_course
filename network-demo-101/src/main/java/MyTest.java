import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class MyTest {
    private static int PORT = 8000;
    private static String HOST = "192.168.31.197";

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.setSoLinger(true, 0);
        SocketAddress address = new InetSocketAddress(HOST, PORT);
        socket.connect(address);
        OutputStream output = socket.getOutputStream();

        String body = "{\"id\":1234, \"name\":\"ya\"}";
        String req =
                "POST /delay HTTP/1.1\r\n" + "Content-Type: text/plain; charset=utf-8\r\n" +
                        "Content-Length: " + body.length() + "\r\n"
                        + "Host: 192.168.31.197:8000\r\n" + "Connection: close\r\n" + "User-Agent" +
                        ": Paw/3.3.6\r\n" + "\r" + "\n" + body;

        System.out.println(req);
        byte[] request = req.getBytes(StandardCharsets.UTF_8);
        output.write(request);
        TimeUnit.SECONDS.sleep(1);
        long start = System.currentTimeMillis();
        socket.close();
        long end = System.currentTimeMillis();
        System.out.println("close time cost: " + (end - start));
    }
}
