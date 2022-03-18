import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class App {

    static String path = "AED_20_MiniGoogle_Etapa1_2022.txt";

    static void loadFile() throws IOException {
        Scanner file = new Scanner(new File(path));
        while (file.hasNextLine()) {
            System.out.println(file.nextLine());
        }
        file.close();     
    } 

    public static void main(String[] args) throws Exception {
        loadFile();
    }
}
