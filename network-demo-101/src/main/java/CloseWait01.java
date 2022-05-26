import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CloseWait01 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        List<Socket> socketList = new ArrayList<>();
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                socketList.add(socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
