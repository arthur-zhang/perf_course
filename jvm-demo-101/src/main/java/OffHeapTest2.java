import sun.nio.ch.DirectBuffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created By Arthur Zhang at 2022/3/25
 */
public class OffHeapTest2 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile f = null;
        FileChannel fc = null;
        RandomAccessFile world = null;
        FileChannel worldChannel = null;
        MappedByteBuffer buf = null;
        try {
            f = new RandomAccessFile("/data/dev/mem_test/src/main/java/test.bin", "rw");
            world = new RandomAccessFile("/data/dev/mem_test/src/main/java/test_copy.bin", "rw");
            fc = f.getChannel();
            buf = fc.map(FileChannel.MapMode.READ_WRITE, 0, f.length());

            int n = buf.remaining();

            for (int i = 0; i < n; i++) {
                buf.put((byte) 'a');
            }
//            worldChannel = world.getChannel();
//            MappedByteBuffer worldBuf = worldChannel.map(FileChannel.MapMode.READ_WRITE, 0, 20);
//            worldBuf.put(buf);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.in.read();
            System.out.println("closing...");

            ((DirectBuffer) buf).cleaner().clean();
            fc.close();
            f.close();
//            world.close();
//            worldChannel.close();
        }
        System.in.read();
    }
}
