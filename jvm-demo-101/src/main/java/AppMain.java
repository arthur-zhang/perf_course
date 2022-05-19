import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created By Arthur Zhang at 2022/3/17
 */
public class AppMain {
    public static void main(String[] args) throws IOException {
        long sum = 0;
        for (int i = 0; i < 20000; i++) {
            Stream<Path> list = Files.list(Paths.get("/tmp/test"));
            list.forEach(path -> {
                if (path.endsWith(".conf")) {
                }
            });
            list.close();
        }
        System.out.println(sum);
        System.in.read();
    }
}
