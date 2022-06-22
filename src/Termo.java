import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Termo {
    static final AtomicInteger count = new AtomicInteger(0);
    int id;
    String termo;
    int counter;
    int docCounter;
    ArrayList<Documento> documentos;

    Termo (String t, Documento doc) {
        this.termo = t; 
        this.counter = 0; 
        this.docCounter = 0; 
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
