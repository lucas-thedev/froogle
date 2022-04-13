import java.util.concurrent.atomic.AtomicInteger;

public class Documento {
    private static final AtomicInteger count = new AtomicInteger(0);
    int idDoc;
    String titulo;
    String url;

    Documento(String titulo, String url) {
        this.idDoc = count.incrementAndGet();
        this.titulo = titulo;
        this.url = url;
    }
}
