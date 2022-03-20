import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App {
    static final String nomeArquivo = "ARQUIVOS.TXT";
    static String path = "AED_20_MiniGoogle_Etapa1_2022.txt";
    static int contador;

    static void loadFile() throws IOException {
        Scanner file = new Scanner(new File(path));
        while (file.hasNextLine()) {
            System.out.println(file.nextLine());
        }
        file.close();
    }

    //Metodo responsavel por entrar vários arquivos e armazenar seus temos
    public static Termo[] carregarDadosMultiplos() throws FileNotFoundException {
        contador = 0;
        File arqQuantidade = new File(nomeArquivo);
        Scanner leitor = new Scanner(arqQuantidade);
        int qntArqvs = Integer.parseInt(leitor.nextLine());
        String[] arquivos = new String[qntArqvs];
        //for entra no arquivo que armazena o nome de todos outros arquivos então cria um vetor de string com o nome de todos arquivos usados
        for (int i = 0; i < qntArqvs; i++) {
            String dados = leitor.nextLine();
            arquivos[i] = dados;
        }
        
        ArrayList<Termo> termos = new ArrayList<Termo>();       //Metodo que achei que seria possivel criar um vetor de tamanho indefinido
        //foreach que passar por todos arquivos então adiciona na classe *Termo* as entradas
        for (String string : arquivos) {
            File arquivo = new File(arquivos[contador]);
            Scanner leitorTermo = new Scanner(arquivo);
            int qntTermos = Integer.parseInt(leitorTermo.nextLine());

            String[] dados = leitor.nextLine().split(";");

            contador++;
        }

        leitor.close();
        return termos;
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {
        loadFile();
    }
}
