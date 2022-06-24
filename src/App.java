import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    static final String nomeArquivo = "ARQUIVOS.TXT";
    static final String nomeArquivoGravar = "DADOS.TXT";

    static void salvarDados(ArrayList<Termo> dados) throws IOException {
        File arquivo = new File(nomeArquivoGravar);
        FileWriter escritor = new FileWriter(arquivo, false);

        for (int i = 0; i < dados.size(); i++) {
            String paraGravar = formatTermo(dados.get(i));
            escritor.append(paraGravar + "\n");
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

    public  static void pausa(Scanner teclado){
        System.out.println("\nEnter para continuar.");
        teclado.nextLine();
    }

    public static void menuExibir(){
        System.out.print("|------------------------Froogle-----------------------|\n");
        System.out.print("|                                                      |\n");
        System.out.print("|------------------------------------------------------|\n");
        System.out.print("| Opção 1 - Consultar novo termo                       |\n");
        System.out.print("| Opção 2 - Consultar entre dois termos                |\n");
        System.out.print("| Opção 3 - Inserir novo termo                         |\n");
        System.out.print("| Opção 4 - Exibir termos (decrescente por frequência) |\n");
        System.out.print("| Opção 5 - Sair                                       |\n");
        System.out.print("|------------------------------------------------------|\n\n");
        System.out.print("Digite uma opção: ");
    }

    public static void main(String[] args) throws Exception {
        limparTela();

        
        Scanner menu = new Scanner(System.in);
        int opcao;
        
        do {
            Documento[] documentos = LoadData.ReadFiles(nomeArquivo);
            HashMap<String, Termo> termos = LoadData.LoadTermos(documentos);
            
            limparTela();
            menuExibir();

            opcao = menu.nextInt();
            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o Termo que deseja pesquisar: ");

                    Scanner termoPorcurado = new Scanner(System.in);
                    String termoPorcurado_ = termoPorcurado.nextLine();

                    Termo aux = termos.get(termoPorcurado_);

                    if (aux == null) {
                        System.out.print("Termo não existe.");
                    } else {
                        System.out.print("Aparece nos documentos: \n");
                        aux.printDocuments(); 
                    }

                    pausa(termoPorcurado);

                    break;
                case 2:
                    System.out.print("\nDigite os Termos que deseja pesquisar, separados por ';' sem espaço: ");
                    termoPorcurado = new Scanner(System.in);

                    String[] termosProcurados = termoPorcurado.nextLine().split(";");

                    System.out.print("Aparece nos documentos: \n");

                    for (String word : termosProcurados) {
                        Termo auxCase2 = termos.get(word);
                        if (auxCase2 == null) {
                            System.out.print("Termo " + auxCase2 + " não existe.");
                        } else {
                            for (Documento doc : auxCase2.documentos) {
                                System.out.println(doc.titulo);
                            }
                        }
                    }
  
                    pausa(termoPorcurado);   
                    break;

                case 3:
                    System.out.print("\nDigite o Termo que deseja inserir: ");
                    Scanner termoInserir = new Scanner(System.in);
                    String termoInserir_ = termoInserir.nextLine(); 
                    termoInserir_ = termoInserir_.toLowerCase();          
                    LoadData.insertTermo(termoInserir_);
                    System.out.println("Termo inserido com sucesso!");
                    break;

                case 4:
                    System.out.print("\nTodos os termos: \n");
                    //Quicksort.quickSort(termos, 0, Termo.count.get() - 1);
                    /*for (int i = Termo.count.get() - 1 ; i >= 0; i--) {
                        if (termos[i] != null) {
                            System.out.print(termos[i].termo + ' ');
                            System.out.println(termos[i].counter);
                        }
                    }
                    Scanner termoListar = new Scanner(System.in);
                    pausa(termoListar);*/
                    break;

                default:
                    System.out.print("\nOpção Inválida!");
                    break;

                case 5:
                    System.out.print("\nAté logo!");
                    menu.close();
            }
        } while (opcao != 4);
    }
}
