import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LoadData {

   static HashMap<String, Termo> termos = new HashMap<>();

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
        String[] termosToExclude = new String[]{"eu", "tu", "ele", "ela", "nós", "vós", "eles", "que", "de", "para", "até", "antes", "após", "depois", "com", "-", ","};

        boolean toExclude = false;

       for(String exclude : termosToExclude) {
            if (termo.equals(exclude)) {
                toExclude = true;
                break;
            }
       }

       return toExclude;
    }

    static public HashMap<String, Termo> LoadTermos(Documento[] documentos) throws FileNotFoundException {
        for (int i = 0; i < documentos.length; i++) {
            File arquivo = new File(documentos[i].titulo);
            Scanner leitorTermo = new Scanner(arquivo);

            while (leitorTermo.hasNextLine()) {
                
                String[] separatedWords = leitorTermo.nextLine().split(" ");

                for (String word : separatedWords) {
                    Termo aux = termos.get(word);              
                    handleTermo(aux, word, documentos[i]);
                }
            }
            
            leitorTermo.close(); 
        }
        return termos;
    }

    public static void handleTermo(Termo termo, String word, Documento doc) {
        if (termo != null) {
            termo.counter++;
            handleDocumento(termo.documentos, doc.titulo);
            termos.put(word, termo);
        } else {
            if (!checkTermosToExclude(word)) {
                termos.put(word, new Termo(word, new Documento(doc.titulo)));
            }
        }
    }

    public static void handleDocumento(ArrayList<Documento> documentos, String docTitle) {
        int docId = findDocumento(documentos, docTitle);
        if (docId > -1) {
            documentos.get(docId).frequency++;
        } else {
            documentos.add(new Documento(docTitle));
        }
    }

    public static int findDocumento(ArrayList<Documento> documentos, String docTitle) {
        int earlyReturn = -1;
        for (int i = 0; i < documentos.size(); i++){
            if (documentos.get(i).titulo.equals(docTitle)) {
                earlyReturn = i;
                return earlyReturn;
            }
        }
        return earlyReturn;

    }


    /**
     * Caso a palavra já exista, deve ser adicionado uma unidade nas repetições.
     * IF O TXT deve ser limpo, o valor das unidades de repetição atualizado, em sequencia escrever todos os elementos novamente.
     * Else O TXT deve ser limpo pois o primeiro elemento é o ultimo, deve ser escrito na primeira linha o novo elemento
     * (não sei se deve ordenar primeiro) e em sequencia escrito o restante.
     * PASSOS:
     * Criar um leitor de TXT e chama-lo toda vez que for selecionado a opção 2 ou no inicio da função insertTermo
     * Passar para um array temporario tudo o que está no TXT
     * Inserir o novo termo formatado
     * Reescrever todos os outros termos que está no array temporario
     * 
     * @param termo
     * @param termos
     * @param path
     */
    public static void insertTermo(String termo) throws IOException  {
        int cont = 0;
        String auxGravador = "";
        int auxNum = 0;
        String passaTXT ="", formatador = "";
        
        Map<Integer, String> dictionary = new HashMap<Integer, String>();

        Scanner scanner = new Scanner(new FileReader("./DADOS.TXT")).useDelimiter("\\n");
        int exists = -1;

        while (scanner.hasNext()) {
            String aux = scanner.next();
            aux = aux.toLowerCase(); 

            if(aux != null || aux !="")
            dictionary.put(cont, aux);
            
            if (aux.contains(termo))
             exists = cont;

             cont++;
        }

        if(exists == -1) { //<- TERMO NÃO EXISTE
 
            cont++;
            formatador = cont + ";" + termo + ";1";
            dictionary.put(cont, formatador);     
        } else {

            StringTokenizer t = new StringTokenizer(dictionary.get(exists), ";");
            
            int i = 0;
            
            while (t.hasMoreTokens()){
		        String ret = t.nextToken();
                
		        if (i!=2){
                    auxGravador+= ret + ";";
                   
                } else {
                    auxNum = Integer.parseInt(ret);
                    auxNum++;
                    auxGravador += auxNum;
                }
                i++;
	        }

            System.out.println(auxGravador);
                dictionary.remove(exists);
                dictionary.put(cont, auxGravador);

           
        }
            PrintWriter gravarArq = new PrintWriter("DADOS.TXT");
            for(int k=0; k<=cont; k++){
                if(dictionary.get(k)!= null && dictionary.get(k)!= ""){
                    if(k==0)
                        passaTXT+=  dictionary.get(k);
                    else
                        passaTXT+= "\n" + dictionary.get(k);      
                    }

            }
                    gravarArq.print(passaTXT);
                    gravarArq.close();

    }
}