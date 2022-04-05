import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    static final String nomeArquivo = "ARQUIVOS.TXT";
    static final String nomeArquivoGravar = "DADOS.TXT";
    static String path = "AED_20_MiniGoogle_Etapa1_2022.txt";

    public static String carregarDadosMultiplos() throws FileNotFoundException {
        File arqQuantidade = new File(nomeArquivo);
        Scanner leitor = new Scanner(arqQuantidade);
        int qntArqvs = Integer.parseInt(leitor.nextLine());
        String[] arquivos = new String[qntArqvs];
        String dados = "";

        for (int i = 0; i < qntArqvs; i++) {
            arquivos[i] = leitor.nextLine();
        }

        for (String leitorArquivo : arquivos) {
            File arquivo = new File(leitorArquivo);
            Scanner leitorTermo = new Scanner(arquivo);
            // contador = 0;
            while (leitorTermo.hasNextLine()) {
                dados += leitorTermo.nextLine();
            }
            leitorTermo.close();
        }

        leitor.close();
        return dados;
    }

    static void salvarDados(ArrayList<Termo> dados) throws IOException{
        File arquivo = new File(nomeArquivoGravar);
        FileWriter escritor = new FileWriter(arquivo, false);

        for(int i=0; i<dados.size(); i++){
            String paraGravar = formatTermo(dados.get(i));
            escritor.append(paraGravar+"\n");
        }
        
        escritor.close();
    }

    public static int searchTermo(ArrayList<Termo> termos, String termo) {
        int earlyReturn = -1;
        for (int i = 0; i < termos.size(); i++) {
            if (termos.get(i).termo.equals(termo)) {
                earlyReturn = i;
                break;
            }
        }
        return earlyReturn;
    }

    static public ArrayList<Termo> processData(String dados) {

        String[] separatedWords = dados.split(" ");
        ArrayList<Termo> termos = new ArrayList<Termo>();

        for (String word : separatedWords) {
            int index = searchTermo(termos, word);
            if (index > -1) {
                termos.get(index).counter++;
            } else {
                Termo newTermo = new Termo(word);
                newTermo.counter++;
                termos.add(newTermo);
            }
        }

        return termos;
    }

    public static String formatTermo(Termo termo) {
        return String.valueOf(termo.id) + ";" + termo.termo + ";" + String.valueOf(termo.counter);
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {

        Scanner ler = new Scanner(System.in);
        int vmenu;

        do {
            
            limparTela();
            System.out.println("Selecione a opção desejada");
            System.out.println("1- Consultar termo \n2- Ordenar termos\n3-Inserir novo termo");

            vmenu = ler.nextInt();

            if (vmenu < 1 || vmenu > 3) {
                limparTela();

                System.out.println("Não exite esa opção!\n Digite um número correspondente a sua operação...");

                ler.nextLine();
            }

            switch(vmenu){
                case 1: 

                    System.out.println(carregarDadosMultiplos()); 
                break;

                case 2:
                    //inserir novo dado();
                break;

                case 3:
                    //Ordenar decrescente por sua frquencia;
                break;

            }

        }while(vmenu < 1 || vmenu > 3);

        ler.close();

        

        String dados = carregarDadosMultiplos();
        limparTela();
        ArrayList<Termo> termos = processData(dados);
        salvarDados(termos);
    }
}
