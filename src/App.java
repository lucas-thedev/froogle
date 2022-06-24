import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public static void pausa(Scanner teclado) {
        System.out.println("\nEnter para continuar.");
        teclado.nextLine();
    }

    public static void mapDocumentos(ArrayList<Documento> documentos, int peso){
        for (Documento doc : documentos) {
            doc.peso = peso * doc.frequency;
        }
    }

    public static void menuExibir(){
        System.out.print("|------------------------Froogle-----------------------|\n");
        System.out.print("|                                                      |\n");
        System.out.print("|------------------------------------------------------|\n");
        System.out.print("| Opção 1 - Consultar novo termo                       |\n");
        System.out.print("| Opção 2 - Consultar entre dois termos                |\n");
        System.out.print("| Opção 3 - Inserir novo termo                         |\n");
        System.out.print("| Opção 4 - Exibir termos (decrescente por frequência) |\n");
        System.out.print("| Opção 5 - Exibir lista de documentos por peso        |\n");
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
                    ArrayList<Termo> termosList = new ArrayList<Termo>(termos.values());
                    Collections.sort(termosList, new Comparator<Termo>() {
                        @Override
                        public int compare(Termo termo1, Termo termo2)
                        {
                
                            return  termo1.counter.compareTo(termo2.counter);
                        }
                    });
                    Collections.reverse(termosList);
                    salvarDados(termosList);
                    break;

                default:
                    System.out.print("\nOpção Inválida!");
                    break;

                case 5:
                    System.out.print("\nDigite o Termo que deseja pesquisar: ");
                    Scanner termoProcurado = new Scanner(System.in);
                    String termoProcuradoS = termoProcurado.nextLine();
                    Termo termo1 = termos.get(termoProcuradoS);

                    System.out.print("\nDigite o PESO do termo anteriormente escolhido: ");
                    int peso1Digitado = Integer.parseInt(termoProcurado.nextLine());
                    mapDocumentos(termo1.documentos, peso1Digitado);
                    

                    System.out.print("\nDigite o segundo Termo que deseja pesquisar: ");
                    termoProcuradoS = termoProcurado.nextLine();
                    Termo termo2 = termos.get(termoProcuradoS);

                    System.out.print("\nDigite o PESO do termo anteriormente escolhido: ");
                    int peso2Digitado = Integer.parseInt(termoProcurado.nextLine());
                    mapDocumentos(termo2.documentos, peso2Digitado);

                    Collections.sort(termo1.documentos, new Comparator<Documento>() {
                        @Override
                        public int compare(Documento doc1, Documento doc2)
                        {
                
                            return  doc1.peso.compareTo(doc2.peso);
                        }
                    });

                    Collections.sort(termo2.documentos, new Comparator<Documento>() {
                        @Override
                        public int compare(Documento doc1, Documento doc2)
                        {
                
                            return  doc1.peso.compareTo(doc2.peso);
                        }
                    });

                    termo1.printDocuments();
                    termo2.printDocuments();

                    pausa(termoProcurado);   

                    break;

                case 6:
                    System.out.print("\nAté logo!");
                    menu.close();
            }
        } while (opcao != 6);
    }
}
