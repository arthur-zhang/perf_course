import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created By Arthur Zhang at 2022/4/1
 */
public class MyTest11 {

    public static void main(String[] args) throws IOException {
        test();
    }

    public static void test() throws IOException {
        SocketConfig socketConfig =
                SocketConfig.custom().setTcpNoDelay(true).setSoReuseAddress(true).setSoLinger(0).setSoKeepAlive(false).build();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).build();
        CloseableHttpClient httpclient =
                HttpClientBuilder.create().setDefaultSocketConfig(socketConfig).setDefaultRequestConfig(requestConfig).build();
        String url = "http://192.168.31.197:8000/route2";
//        String url = "http://192.168.31.197:9000/route2";

        HttpPost httpPost = new HttpPost(url);
        String body = "{\"id\":1234, \"name\":\"ya\"}";
        httpPost.setEntity(EntityBuilder.create().setText(body).build());

        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);

//            System.out.println(response.getStatusLine().getStatusCode());
//            HttpEntity entity = response.getEntity();
//            EntityUtils.consume(entity);
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) response.close();
            httpclient.close();
        }
    }
}

