import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created By Arthur Zhang at 2022/4/2
 */
class LongEvent {
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}

class LongEventFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}

class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) {
        System.out.println(longEvent.getValue());
    }
}

@SuppressWarnings("ALL")
public class DistruptorTest {
    public static void main(String[] args) throws IOException {

        int bufferSize = 1024;

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // init
                    Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(new LongEventFactory(), bufferSize,
                            Executors.newSingleThreadExecutor(), ProducerType.SINGLE, new YieldingWaitStrategy());
                    disruptor.handleEventsWith(new LongEventHandler());
                    disruptor.start();

                    // publish event
                    RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

                    ByteBuffer bb = ByteBuffer.allocate(8);
                    for (int j = 0; j < 100 * 10000; j++) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        bb.putLong(0, j);
                        long sequence = ringBuffer.next(); // get next event sequence
                        try {
                            LongEvent event = ringBuffer.get(sequence);// for the sequence
                            event.setValue(bb.getLong(0));
                        } finally {
                            ringBuffer.publish(sequence);
                        }
                    }

                    disruptor.shutdown();
                    System.out.println("done");
                }
            }).start();
        }
        System.in.read();
    }
}
