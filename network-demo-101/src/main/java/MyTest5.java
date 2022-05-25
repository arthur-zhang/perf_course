import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

public class MyTest5 {
    private static final int PORT = 8000;
    private static final String HOST = "192.168.31.197";

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();

        SocketAddress address = new InetSocketAddress(HOST, PORT);
        socket.connect(address);

        OutputStream output = socket.getOutputStream();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("hel");
        }
//        String req = "POST /delay HTTP/1.1\r\n" + "Content-Type: text/plain; charset=utf-8\r\n" + "Host: 192.168.31.197:8000\r\n" + "Connection: close\r\n" + "User-Agent: Paw/3.3.6 (Macintosh; OS X/12.3.0) GCDHTTPRequest\r\n" + "Content-Length: 9\r\n" + "\r\n" + sb + "\r\n\n";
//        sb.append("hello");
        System.out.println(sb.length());

        String req = "POST /route0 HTTP/1.1\r\n" + "Content-Type: text/plain; charset=utf-8\r\n" + "Content-Length: " + 10 * 1000 + "\r\n" + "Host: 192.168.31.197:8000\r\n" + "Connection: close\r\n" + "User-Agent: Paw/3.3.6\r\n" + "\r\n" + sb;

        System.out.println(req);
        byte[] request = req.getBytes(StandardCharsets.UTF_8);
        output.write(request);
//        TimeUnit.SECONDS.sleep(1);

        long start = System.currentTimeMillis();
        BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("resp: \n");
        String line;
        while ((line = r.readLine()) != null) {
            System.out.println(line);
        }

        socket.close();
        long end = System.currentTimeMillis();
        System.out.println("close time cost: " + (end - start));
//        System.in.read();
    }
}
