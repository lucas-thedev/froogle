import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class Termo {
    static final AtomicInteger count = new AtomicInteger(-1);
    int id;
    String termo;
    int counter;
    int docCounter;
    Documento[] documentos;

    Termo (String t, Documento doc) {
        this.termo = t; 
        this.counter = 0; 
        this.docCounter = 0; 
        this.id = count.incrementAndGet();
        this.documentos = new Documento[100];
        setDocument(doc);
    }

    public static ArrayList<Termo> ordernarDecrescente(ArrayList<Termo> termos) {
        Quicksort.quickSort(termos, 0, termos.size() - 1);
        Collections.reverse(termos);
        return termos;
    }

    private void setDocument(Documento doc) {
        for (int i = 0; i < Documento.count.get(); i++) {
            if (documentos[i] == null) {
                documentos[docCounter] = doc; 
            }
            if (documentos[i].idDoc == doc.idDoc) {
                break;
            }

        }
    }
}
