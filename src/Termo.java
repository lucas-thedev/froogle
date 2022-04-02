import java.util.concurrent.atomic.AtomicInteger;

public class Termo {
    private static final AtomicInteger count = new AtomicInteger(0); 
    int id;
    String termo;
    int counter;

    Termo (String t) {
        this.termo = t;
        this.counter = 0; 
        this.id = count.incrementAndGet();
    }
}
