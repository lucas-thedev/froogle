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
            System.out.println("1- Consultar termo \n2- Inserir novo termo");

            vmenu = ler.nextInt();

            if (vmenu < 1 || vmenu > 2) {
                limparTela();

                System.out.println(
                        "Não exite esa opção!\n Digite um número correspondente a sua operação...");

                ler.nextLine();
            }

        }while(vmenu < 1 || vmenu > 2);
    }
}
