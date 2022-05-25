import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.fluent.Async;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * Created By Arthur Zhang at 2022/4/1
 */
public class MyTest13 {

    public static void main(String[] args) throws IOException {
        test();
    }

    public static void test() throws IOException {
        SocketConfig socketConfig =
                SocketConfig.custom().setTcpNoDelay(true).setSoReuseAddress(true).setSoTimeout(1000).setSoLinger(0).setSoKeepAlive(false).build();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000).build();
        CloseableHttpClient httpclient =
                HttpClientBuilder.create().setDefaultSocketConfig(socketConfig).setDefaultRequestConfig(requestConfig).build();
        String url = "http://192.168.31.197:8000/delay";


        HttpPost httpPost = new HttpPost(url);
        String body = "{\"id\":1234, \"name\":\"ya\"}";
        httpPost.setEntity(EntityBuilder.create().setText(body).build());


        Request req = Request.Post("http://192.168.31.197:8000/delay")

                // Add headers
                .addHeader("Content-Type", "application/json; charset=utf-8")

                // Add body
                .bodyString("{\"id\": \"1234\",\"name\": \"ya\"}", ContentType.APPLICATION_JSON);

        Async.newInstance().execute(req, new FutureCallback<Content>() {
            @Override
            public void completed(Content result) {

                System.out.println(result);
            }

            @Override
            public void failed(Exception ex) {
                ex.printStackTrace();
            }

            @Override
            public void cancelled() {
            }
        });

        System.in.read();
    }
}

