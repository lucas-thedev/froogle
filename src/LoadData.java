import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LoadData {
    static public Documento[] ReadFiles(String path) throws FileNotFoundException {
        File arqQuantidade = new File(path);
        Scanner leitor = new Scanner(arqQuantidade);
        int qntArqvs = Integer.parseInt(leitor.nextLine());
        Documento[] documentos = new Documento[qntArqvs];

        for (int i = 0; i < qntArqvs; i++) {
            documentos[i] = new Documento(leitor.nextLine());
        }
        leitor.close();

        return documentos;
    }

    public static boolean checkTermosToExclude (String termo) {
        String[] termosToExclude = new String[]{"eu", "tu", "ele", "ela", "nós", "vós", "eles", "que", "de", "para", "até", "antes", "após", "depois", "com"};

        boolean toExclude = false;

       for(String exclude : termosToExclude) {
            if (termo.equals(exclude)) {
                toExclude = true;
                break;
            }
       }

       return toExclude;
    }

    static public Termo[] LoadTermos(Documento[] documentos) throws FileNotFoundException {
        Termo[] termos = new Termo[99999];
        int termoCounter = 0;

        for (int i = 0; i < documentos.length; i++) {
            File arquivo = new File(documentos[i].titulo);
            Scanner leitorTermo = new Scanner(arquivo);

            while (leitorTermo.hasNextLine()) {
                String[] separatedWords = leitorTermo.nextLine().split(" ");

                for (String word : separatedWords) {
                    int index = searchTermo(termos, word);
                    if (index > -1) {
                        termos[index].counter++;
                    } else {
                        if (!checkTermosToExclude(word)) {
                            termos[termoCounter] = new Termo(word, documentos[i]);
                            termoCounter++;
                        }
                    }
                }
            }
            leitorTermo.close();
        }

        return termos;
    }

    public static int searchTermo(Termo[] termos, String termo) {
        int earlyReturn = -1;
        for (int i = 0; i < Termo.count.get(); i++) {
            if (termos[i] == null) break;
            if (termos[i].termo.equals(termo)) {
                earlyReturn = i;
                break;
            }
        }
        return earlyReturn;
    }


}
