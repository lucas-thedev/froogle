import java.util.ArrayList;
import java.util.Collections;
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

    public static ArrayList<Termo> ordernarDecrescente(ArrayList<Termo> termos) {
        Quicksort.quickSort(termos, 0, termos.size() - 1);
        Collections.reverse(termos);
        return termos;
    }
}
