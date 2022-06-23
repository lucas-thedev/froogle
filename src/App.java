import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.print.Doc;

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

    public static void pausa(Scanner teclado) {
        System.out.println("\nEnter para continuar.");
        teclado.nextLine();
    }

    public static void menuExibir() {
        System.out.print("|------------------------Froogle------------------------------------------------|\n");
        System.out.print("|                                                                               |\n");
        System.out.print("|-------------------------------------------------------------------------------|\n");
        System.out.print("| Opção 1 - Consultar novo termo                                                |\n");
        System.out.print("| Opção 2 - Consultar múltiplos termos                                          |\n");
        System.out.print("| Opção 3 - Inserir novo termo                                                  |\n");
        System.out.print("| Opção 4 - Exibir termos (decrescente por frequência)                          |\n");
        System.out.print("| Opção 5 - A partir de dois termos calcular o peso refenrete a suas ocorrências|\n");
        System.out.print("| Opção 6 - Sair                                                                |\n");
        System.out.print("|-------------------------------------------------------------------------------|\n\n");
        System.out.print("Digite uma opção: ");
    }

    public static void PesoTermo(Termo[] termos) {
        System.out.print("\nDigite o Termo que deseja pesquisar: ");
        Scanner termoProcurado = new Scanner(System.in);
        String termoProcuradoS = termoProcurado.nextLine();
        int termo1Id = LoadData.searchTermo(termos, termoProcuradoS);

        System.out.print("\nDigite o PESO do termo anteriormente escolhido: ");
        int peso1Digitado = Integer.parseInt(termoProcurado.nextLine());
        int peso1 = peso1Digitado * termos[termo1Id].counter;

        System.out.print("\nDigite o segundo Termo que deseja pesquisar: ");
        termoProcuradoS = termoProcurado.nextLine();
        int termo2Id = LoadData.searchTermo(termos, termoProcuradoS);

        System.out.print("\nDigite o PESO do termo anteriormente escolhido: ");
        int peso2Digitado = Integer.parseInt(termoProcurado.nextLine());
        int peso2 = peso2Digitado * termos[termo2Id].counter;

        System.out.println("O peso da palavra " + termos[termo1Id].termo + " é: " + peso1);
        System.out.println("O peso da palavra " + termos[termo2Id].termo + " é: " + peso2);
        pausa(termoProcurado);
    }

    public static void main(String[] args) throws Exception {
        limparTela();

        
        Scanner menu = new Scanner(System.in);
        int opcao;
        
        do {
            Documento[] documentos = LoadData.ReadFiles(nomeArquivo);
            Termo[] termos = LoadData.LoadTermos(documentos);
            
            limparTela();
            menuExibir();

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
                        System.out.print("Aparece nos documentos: \n");
                        termos[termoID].printDocuments(); 
                    }

                    pausa(termoPorcurado);

                    break;
                case 2:
                    System.out.print("\nQuantos termos o documento deve ter:");
                    int qntTermosPresentes = Integer.parseInt(new Scanner(System.in).toString());

                    System.out.print("\nDigite os Termos que deseja pesquisar, separados por ';' sem espaço: ");
                    termoPorcurado = new Scanner(System.in);

                    String[] termosProcurados = termoPorcurado.nextLine().split(";");

                    System.out.print("Aparece nos documentos: \n");

                    Map<String, String> map = new HashMap<String, String>();

                    for (String termo : termosProcurados) {
                        termoID = LoadData.searchTermo(termos, termo);
                        if (termoID == -1) {
                            System.out.print("Termo " + termo + " não existe.");
                        } else {
                            for (Documento doc : termos[termoID].documentos) {
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
                    Quicksort.quickSort(termos, 0, Termo.count.get() - 1);
                    for (int i = Termo.count.get() - 1 ; i >= 0; i--) {
                        if (termos[i] != null) {
                            System.out.print(termos[i].termo + ' ');
                            System.out.println(termos[i].counter);
                        }
                    }
                    Scanner termoListar = new Scanner(System.in);
                    pausa(termoListar);
                    break;

                default:
                    System.out.print("\nOpção Inválida!");
                    break;

                case 5:
                PesoTermo(termos);
                break;

                case 6:
                    System.out.print("\nAté logo!");
                    menu.close();
            }
        } while (opcao != 6);
    }
}
