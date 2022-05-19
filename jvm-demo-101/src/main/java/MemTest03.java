import java.io.IOException;

/**
 * Created By Arthur Zhang at 2022/4/1
 */
class ObjA {
}

class ObjB {

    private int i;

    private double d;

    private Integer io;

}

class ObjC {
    private byte[] bytes = new byte[10 * 1024 * 1024];
    C2 obj2;
    C5 obj5;
}

public class MemTest03 {
    public static void main(String[] args) throws IOException {
        ObjA a = new ObjA();
        ObjB b = new ObjB();
        ObjC c = new ObjC();
        System.in.read();
        System.out.println("" + a + b + c);
    }
}
