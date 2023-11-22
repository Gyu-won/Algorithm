import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st  = new StringTokenizer(bf.readLine());

        Integer A = Integer.parseInt(st.nextToken());
        Integer B = Integer.parseInt(st.nextToken());

        bw.write(Integer.toString(A-B));
        bw.flush();
        bf.close();
        bw.close();
    }
}