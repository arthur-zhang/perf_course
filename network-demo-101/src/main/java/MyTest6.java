import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created By Arthur Zhang at 2022/3/30
 */
public class MyTest6 {

    public static void run(boolean direct) throws IOException {
        String url;
        if (direct) {
            url = "http://192.168.31.197:8080/hello";
        } else {
            url = "http://192.168.31.197:8001/hello";
        }
        OkHttpClient client = new OkHttpClient();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 3000; i++) {
            str.append('a');
        }
        Request request = new Request.Builder().url(url + "?_=" + str.toString()).build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("code: " + response.code());
            System.out.println(response.body().string());
        }
    }



    public static void runLongUrl() throws IOException {
        OkHttpClient client = new OkHttpClient();
        for (int j = 0; j < 8192; j++) {
            String url = "http://192.168.31.197:8001/hello";
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < 8192 - j; i++) {
                str.append('a');
            }
            Request request = new Request.Builder().url(url + "?=" + str.toString()).build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() != 414) {
                    System.out.println("j=" + j + "\t" + response.code());
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        run(false);
//        run(true);
//        runLongUrl();
    }
}
