import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CloseWaitClient01 {
    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 1000; i++) {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("192.168.31.197", 8080));
            socket.close();
        }
        System.out.println("done");
        System.in.read();

    }
}
