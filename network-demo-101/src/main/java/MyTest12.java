import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created By Arthur Zhang at 2022/4/1
 */
public class MyTest12 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "http://192.168.31.197:8000/route2";

        OkHttpClient client =
                new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS)
                        .build();
        Request request = new Request.Builder().url(url).build();

        long start = System.currentTimeMillis();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("code: " + response.code());
            TimeUnit.MILLISECONDS.sleep(300);
            response.close();
        }

        long end = System.currentTimeMillis();
    }
}
