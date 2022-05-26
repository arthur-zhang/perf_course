import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FdTest {
    public static void main(String[] args) throws IOException {
        List<FileInputStream> root = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            File f = new File("/tmp/test" + i + ".txt");
            if (!f.exists()) f.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(f);
            root.add(fileInputStream);
        }
        System.out.println("done");
        System.in.read();
    }
}
