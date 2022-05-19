import java.io.IOException;
import java.util.UUID;

/**
 * Created By Arthur Zhang at 2022/3/25
 */
public class MemTest02 {
    public static void main(String args[]) throws IOException {
        System.out.println("in main.......");
        for (int i = 0; i < 10000 * 10000; i++) {
            uuid();
        }
        System.out.println("done");
        System.in.read();
    }

    public static void uuid() {
        UUID.randomUUID().toString().intern();
    }
}

