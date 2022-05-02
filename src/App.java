import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    static final String nomeArquivo = "ARQUIVOS.TXT";
    static final String nomeArquivoGravar = "DADOS.TXT";
    static String path = "AED_20_MiniGoogle_Etapa1_2022.txt";

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

    public static void main(String[] args) throws Exception {
        limparTela();
        Documento[] documentos = LoadData.ReadFiles(nomeArquivo);
        Termo[] termos = LoadData.LoadTermos(documentos);

        Scanner menu = new Scanner(System.in);
        int opcao;
        do {
            limparTela();
            System.out.print("----------Froogle----------|\n\n");
            System.out.print("|--------------------------|\n");
            System.out.print("| Opção 1 - Consultar novo termo\n");
            System.out.print("| Opção 2 - Inserir novo termo\n");
            System.out.print("| Opção 3 - Exibir termos (decrescente por frequência)\n");
            System.out.print("| Opção 4 - Sair\n");
            System.out.print("|---------------------------|\n");
            System.out.print("Digite uma opção: ");

            opcao = menu.nextInt();
            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o Termo que deseja pesquisar: ");
                    Scanner termoPorcurado = new Scanner(System.in);
                    String termoPorcurado_ = termoPorcurado.nextLine();
                    int termoID = LoadData.searchTermo(termos, termoPorcurado_);
                    if (termoID == -1) {
                        System.out.print("Termo não existe.");
                    } else {
                        System.out.print("\nO termo: " + termos[termoID].termo);
                        if (termos[termoID].docCounter == 0) {
                            System.out.print(", não aparece em algum documento.");
                        } else {
                            System.out.print(", aparece nos docuemntos: \n");
                            for (int i = 0; i < termos[termoID].docCounter; i++) {
                                System.out.print(termos[termoID].documentos[i] + ", ");
                            }
                        }
                    }
                    pausa(termoPorcurado);
                    break;

                case 2:
                    System.out.print("\nDigite o Termo que deseja inserir: (FALTANDO*********)");
                    Scanner termoInserir = new Scanner(System.in);
                    String termoInserir_ = termoInserir.nextLine();
                    pausa(termoInserir);
                    break;

                case 3:
                    System.out.print("\nExibir todos Termos (POR FREQUENCIA. FALTANDO********)\n");
                    for (Termo termo : termos) {
                        if (termos != null) {
                            System.out.println(termo.termo);
                        }
                    }
                    break;

                default:
                    System.out.print("\nOpção Inválida!");
                    break;

                case 4:
                    System.out.print("\nAté logo!");
                    menu.close();
            }
        } while (opcao != 4);
    }
}
