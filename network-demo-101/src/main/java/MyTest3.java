import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created By Arthur Zhang at 2022/3/30
 */

public class MyTest3 {
    public static void main(String[] args) throws IOException {
        int port = 9999;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.err.println("Accepted connection from client");

            System.in.read();

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream out = clientSocket.getOutputStream();

            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }

            System.out.println("wait system.in");
            // close IO streams, then socket
            System.err.println("Closing connection with client");
            out.close();
            in.close();
            clientSocket.close();
        }
    }
}
