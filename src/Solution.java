import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution extends FormClass{
 public static String token = "1e3e8da220875a7ed56df7aadd9acb285bc2250aca6893a1b37e47accf97e45d04afabd2dd58320e05715";
 public static String STnumberSexHistory = "";
final public static String owneredIDv1 = "155689035";
    final static public String Version = "5.73";

    public static void main(String[] args) throws IOException {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("C:\\Users\\Vlad\\Desktop\\token.txt"), "UTF-8"));
            String strLine;
            int i = 0;
            while ((strLine = br.readLine()) != null){
                if (i == 2)
                    token = strLine.split("- ")[1];
                i++;
            }
        }catch (IOException e){
            System.out.println("Ошибка, файл не найден");
        }
        CteateForm();

    }

}
