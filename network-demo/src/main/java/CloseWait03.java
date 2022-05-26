import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class CloseWait03 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8080);
        int count = 0;
        while (true) {
            try {

                Socket socket = serverSocket.accept();
                System.out.println("accept done: " + (++count));

//                TimeUnit.SECONDS.sleep(3);
                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                byte[] buf = new byte[4 * 1024];


                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
                String respBody =
                        "HTTP/1.1 200 OK\r\n" +
                                "Date: Mon, 23 May 2022 15:00:58 GMT\r\n" +
                                "Server: Apache\r\n" +
                                "Last-Modified: Tue, 12 Jan 2010 13:48:00 GMT\r\n" +
                                "ETag: \"51-47cf7e6ee8400\"\r\n" +
                                "Accept-Ranges: bytes\r\n" +
                                "Content-Length: 81\r\n" +
                                "Cache-Control: max-age=86400\r\n" +
                                "Expires: Tue, 24 May 2022 15:00:58 GMT\r\n" +
                                "Connection: Close\r\n" +
                                "Content-Type: text/html\r\n" +
                                "\r\n" +
                                "<html>\r\n" +
                                "<meta http-equiv=\"refresh\" content=\"0;url=http://www.baidu.com/\">\r\n" +
                                "</html>\r\n";

//                TimeUnit.SECONDS.sleep(2);

                bos.write(respBody.getBytes(StandardCharsets.UTF_8));
                bos.flush();
//                socket.close();
            } catch (Exception e) {
                System.out.println("count: " + count);
                e.printStackTrace();
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }
    }
}
