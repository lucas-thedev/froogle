import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    static final String nomeArquivo = "ARQUIVOS.TXT";
    static final String nomeArquivoGravar = "DADOS.TXT";
    static String path = "AED_20_MiniGoogle_Etapa1_2022.txt";

    static void salvarDados(ArrayList<Termo> dados) throws IOException{
        File arquivo = new File(nomeArquivoGravar);
        FileWriter escritor = new FileWriter(arquivo, false);

        for(int i=0; i<dados.size(); i++){
            String paraGravar = formatTermo(dados.get(i));
            escritor.append(paraGravar+"\n");
        }
        
        escritor.close();
    }

    public static String formatTermo(Termo termo) {
        return String.valueOf(termo.id) + ";" + termo.termo + ";" + String.valueOf(termo.counter);
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {
        limparTela();
        Documento[] documentos = LoadData.ReadFiles(nomeArquivo);
        Termo[] termos = LoadData.LoadTermos(documentos);


        for (Termo termo : termos) {
            if (termo != null) {
                System.out.println(termo.termo);
            }
            
        }


    }
}
