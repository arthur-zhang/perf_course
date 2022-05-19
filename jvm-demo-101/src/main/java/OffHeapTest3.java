import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Created By Arthur Zhang at 2022/3/25
 */
public class OffHeapTest3 {
    public static void main(String[] args) throws IOException {
        while (true) {
            int size = new Random().nextInt(1000);
            if (size == 0) continue;
            size = Math.abs(size);
            ByteBuffer.allocateDirect(size);
        }
    }
}
