import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;



public class LoadData {

   static Termo[] termos = new Termo[99999];
   static int cont = 0;

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
         
        int termoCounter = 0;

        for (int i = 0; i < documentos.length; i++) {
            File arquivo = new File(documentos[i].titulo);
            Scanner leitorTermo = new Scanner(arquivo);

            while (leitorTermo.hasNextLine()) {
                
                String[] separatedWords = leitorTermo.nextLine().split(" ");

                for (String word : separatedWords) {
                    cont++;
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
    public static String formatTermo(Termo termo) {
        return String.valueOf(termo.id) + ";" + termo.termo + ";" + String.valueOf(termo.counter);
    }

    /**
     * Caso a palavra já exista, deve ser adicionado uma unidade nas repetições.
     * IF O TXT deve ser limpo, o valor das unidades de repetição atualizado, em sequencia escrever todos os elementos novamente.
     * Else O TXT deve ser limpo pois o primeiro elemento é o ultimo, deve ser escrito na primeira linha o novo elemento
     * (não sei se deve ordenar primeiro) e em sequencia escrito o restante.
     * @param termo
     * @param termos
     * @param path
     */
    public static void insertTermo(String termo, Termo[] termos1,String path)   {
        
        int exists = searchTermo(termos, termo);
        
        if(exists == -1) {
            try{            
                PrintWriter ps = new PrintWriter("DADOS.TXT");
                
                String formataTermo =  termos1.length + ";" + termo + ";1";
                ps.print(formataTermo);
                for (int i = Termo.count.get() - 1 ; i >= 0; i--) {
                    if (termos[i] != null) {
                        System.out.print(termos[i].termo + ' ');
                        System.out.println(termos[i].counter);
                    }
                }
                //  for (int i=0; i<termos.length; i++) {
                //      ps.println(i+";" + termos[i].termo +";"+ termos[i].counter);
                //  }
                ps.close();

            }catch(Exception e){
                System.out.println(e);
            }
            
        } else {
            int aux = termos[exists].counter++;
            System.out.println("ali");
            // cria um novo TXT, adiciona o termo a ser inserido e depois adiciona os termos que já existiam;
            
        }
    }

}
