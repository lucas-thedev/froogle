import java.util.concurrent.atomic.AtomicInteger;

public class Documento {
    static final AtomicInteger count = new AtomicInteger(-1);
    int idDoc;
    String titulo;
    String url;
    int frequency;
    Integer peso;

    Documento(String titulo) {
        this.idDoc = count.incrementAndGet();
        this.titulo = titulo;
    }
}
