import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created By Arthur Zhang at 2022/4/1
 */
public class MyTest10 {

    public static void main(String[] args) throws IOException {
        test();
    }

    public static void test() throws IOException {
        SocketConfig socketConfig =
                SocketConfig.custom().setTcpNoDelay(true).setSoReuseAddress(true)
                        .setSoTimeout(1000)
                        .setSoLinger(0).setSoKeepAlive(false).build();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).build();
        CloseableHttpClient httpclient =
                HttpClientBuilder.create().setDefaultSocketConfig(socketConfig).setDefaultRequestConfig(requestConfig).build();
        String url = "http://192.168.31.197:8000/delay";

        HttpPost httpPost = new HttpPost(url);
        String body = "{\"id\":1234, \"name\":\"ya\"}";
        httpPost.setEntity(EntityBuilder.create().setText(body).build());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    httpclient.close();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            System.out.println(response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
            if (response != null) response.close();
        }
    }
}

