package Lista;
import java.util.concurrent.atomic.AtomicInteger;

public class List {
    private static final AtomicInteger count = new AtomicInteger(0);
    int idDoc;
    String titulo;
    String url;

    List(String titulo, String url) {
        this.idDoc = count.incrementAndGet();
        this.titulo = titulo;
        this.url = url;
    }
}
