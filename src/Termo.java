import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Termo {
    static final AtomicInteger count = new AtomicInteger(0);
    int id;
    String termo;
    Integer counter;
    ArrayList<Documento> documentos;

    Termo (String t, Documento doc) {
        this.termo = t; 
        this.counter = 1; 
        this.id = count.incrementAndGet();
        this.documentos = new ArrayList<Documento>();
        documentos.add(doc);
    }

    public void printDocuments () {
        for (Documento doc : documentos) {
            System.out.println(doc.titulo);
        }
    }

}
