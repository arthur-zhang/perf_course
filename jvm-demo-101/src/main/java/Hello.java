import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created By Arthur Zhang at 2022/3/21
 */
public class Hello {
    public static void main(String[] args) throws IOException {
        System.out.println("hello, world");
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while(true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
        System.in.read();
    }
}
