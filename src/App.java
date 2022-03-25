import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App {
    static final String nomeArquivo = "ARQUIVOS.TXT";
    static String path = "AED_20_MiniGoogle_Etapa1_2022.txt";

    static void loadFile() throws IOException {
        Scanner file = new Scanner(new File(path));
        while (file.hasNextLine()) {
            System.out.println(file.nextLine());
        }
        file.close();
    }

    // Metodo responsavel por entrar vários arquivos e armazenar seus temos
    public static String[] carregarDadosMultiplos() throws FileNotFoundException {
        File arqQuantidade = new File(nomeArquivo);
        Scanner leitor = new Scanner(arqQuantidade);
        int qntArqvs = Integer.parseInt(leitor.nextLine());
        String[] arquivos = new String[qntArqvs];
        String[] dados = new String[1000];
        int contador = 0;

        // for entra no arquivo que armazena o nome de todos outros arquivos então cria
        // um vetor de string com o nome de todos arquivos usados
        for (int i = 0; i < qntArqvs; i++) {
            arquivos[i] = leitor.nextLine();
        }

        // foreach que passar por todos arquivos então adiciona na classe *Termo* as
        // entradas
        for (String leitorArquivo : arquivos) {
            File arquivo = new File(leitorArquivo);
            Scanner leitorTermo = new Scanner(arquivo);

            dados[contador] = leitorTermo.nextLine();
            leitorTermo.close();
            contador++;
        }

        leitor.close();
        return dados;
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {
        String[] dados = new String[1000];
        limparTela();
        dados = carregarDadosMultiplos();
        for (String dado : dados) {
            if (dado != null) {
                System.out.println(dado);
            }
        }
        loadFile();
    }
}
