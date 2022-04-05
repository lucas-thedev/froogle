import java.util.ArrayList;

public class Quicksort {

    public static void quickSort(ArrayList<Termo> termos, int inicio, int fim) {
        if (inicio < fim) {
               int posicaoPivo = separar(termos, inicio, fim);
               quickSort(termos, inicio, posicaoPivo - 1);
               quickSort(termos, posicaoPivo + 1, fim);
        }
    }

    private static int separar(ArrayList<Termo> termos, int inicio, int fim) {
        int pivo = termos.get(inicio).counter;
        int i = inicio + 1, f = fim;
        while (i <= f) {
               if (termos.get(i).counter <= pivo)
                      i++;
               else if (pivo < termos.get(f).counter)
                      f--;
               else {
                      int troca = termos.get(i).counter;
                      termos.get(i).counter = termos.get(f).counter;
                      termos.get(f).counter = troca;
                      i++;
                      f--;
               }
        }
        termos.get(inicio).counter = termos.get(f).counter;
        termos.get(f).counter = pivo;
        return f;
    }
    
}
