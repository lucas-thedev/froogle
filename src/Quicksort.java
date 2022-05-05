public class Quicksort {

    public static void quickSort(Termo[] termos, int inicio, int fim) {
        if (inicio < fim) {
               int posicaoPivo = separar(termos, inicio, fim);
               quickSort(termos, inicio, posicaoPivo - 1);
               quickSort(termos, posicaoPivo + 1, fim);
        }
    }

    private static int separar(Termo[] termos, int inicio, int fim) {
        int pivo = termos[inicio].counter;
        int i = inicio + 1, f = fim;
        while (i <= f) {
               if (termos[i].counter <= pivo)
                      i++;
               else if (pivo < termos[f].counter)
                      f--;
               else {
                      int troca = termos[i].counter;
                      termos[i].counter = termos[f].counter;
                      termos[f].counter = troca;
                      i++;
                      f--;
               }
        }
        termos[inicio].counter = termos[f].counter;
        termos[f].counter = pivo;
        return f;
    }
    
}
