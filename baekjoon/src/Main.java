import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputString = br.readLine();
        char[] charArr = inputString.toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == ' ') {
                i++;
                bw.write(charArr[i] - 32);
                continue;
            }
            bw.write(charArr[i]);
        }
        bw.flush();
    }
}