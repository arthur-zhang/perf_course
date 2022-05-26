import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CloseWaitClient04 {
    public static void main(String[] args) throws IOException, InterruptedException {
        OkHttpClient client = new OkHttpClient().newBuilder()
//                                      .readTimeout(100, TimeUnit.MILLISECONDS)
                                      .build();
        for (int i = 0; ; i++) {
            System.out.println("processing: " + i);
            try {
                Request request = new Request.Builder()
                                          .url("http://192.168.31.197:8080/")
                                          .method("GET", null)
                                          .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println("onFailure");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        response.body().string();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                TimeUnit.MILLISECONDS.sleep(500);
            }
        }
    }
}
