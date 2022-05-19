import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created By Arthur Zhang at 2022/3/25
 */
public class OffHeapTest {
    public static void main(String[] args) throws IOException {
        System.out.println("in main");
        System.in.read();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024);
        System.in.read();
        byteBuffer.clear();
        System.in.read();
    }
}
