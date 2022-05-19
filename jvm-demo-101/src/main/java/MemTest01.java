import java.io.IOException;

/**
 * Created By Arthur Zhang at 2022/3/21
 */

class C3 {
    private byte[] bytes = new byte[30 * 1024 * 1024];
}

class C4 {
    private byte[] bytes = new byte[40 * 1024 * 1024];
}

class C2 {
    private byte[] bytes = new byte[20 * 1024 * 1024];
    C3 obj3;
    C4 obj4;

    public C2(C3 obj3, C4 obj4) {
        this.obj3 = obj3;
        this.obj4 = obj4;
    }
}

class C5 {
    private byte[] bytes = new byte[50 * 1024 * 1024];
}


class C1 {
    private byte[] bytes = new byte[10 * 1024 * 1024];
    C2 obj2;
    C5 obj5;

    public C1(C3 obj3, C5 obj5) {
        this.obj2 = new C2(obj3, new C4());
        this.obj5 = obj5;
    }
}

public class MemTest01 {
    public static void main(String[] args) throws IOException {
        C3 obj3 = new C3();
        C5 obj5 = new C5();
        C1 obj1 = new C1(obj3, obj5);
        System.in.read();
        System.out.println(obj1 + "\t" + obj3 + "\t" + obj5);
    }

//    public static void main2(String[] args) throws IOException {
//        int size = 500;
//        List<byte[]> list = new ArrayList<>(size);
//        for (int i = 0; i < size; i++) {
//            list.add(new byte[_1_m]);
//        }
//        System.in.read();
//    }
}
