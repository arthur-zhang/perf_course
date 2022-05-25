import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created By Arthur Zhang at 2022/4/1
 */
public class MyTest7_client {
    public static void main(String[] args) throws IOException {
        String url = "http://192.168.31.197:1234/hello";


        for (int i = 0; i < 100; i++) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url + "?_=" + i).build();
            long start = System.currentTimeMillis();

            try (Response response = client.newCall(request).execute()) {
//                System.out.println("code: " + response.code());
                response.body().string();
            }

            long end = System.currentTimeMillis();
            System.out.println("loop:" + i + "\t" + (end - start));
        }
    }
}
