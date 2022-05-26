import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.CheckedOutputStream;

public class CloseWaitClient02 {
    public static void main(String[] args) throws IOException, InterruptedException {

        List<HttpURLConnection> root = new ArrayList<>();
        for (int i = 0; ; i++) {
            try {
                System.out.println("i: " + i);
                HttpURLConnection con = (HttpURLConnection)

                                                new URL("http://192.168.31.197:8080/").openConnection();
                con.setReadTimeout(2000);
                con.connect();
                root.add(con);
            } catch (Exception e) {
                e.printStackTrace();
                TimeUnit.MILLISECONDS.sleep(200);
            }
        }


//        System.out.println(con.getResponseCode());
    }
}
