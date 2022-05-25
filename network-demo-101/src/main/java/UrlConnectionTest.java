import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created By Arthur Zhang at 2022/4/7
 */
public class UrlConnectionTest {
    public static void main(String[] args) throws IOException {
        String _url = "http://school-performance-http.easicare-test-2:8080/school-performance/student-archive/schools/30375dee54dc47ef8410b6508cd7aa6a/archive-bags/0054616e455f4ccc91f64e9cf11e5571/students/335cb51e8c0343918969e939b1461e8f";
        URL url = new URL(_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        System.out.println("status: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("resp..........");
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } else {
        }
    }
}
