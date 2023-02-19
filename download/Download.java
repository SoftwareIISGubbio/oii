import java.util.*;
import java.io.*;

public class Download {

    static class Coppia{
        int a,b;
        Coppia(int a,int b){
            this.a=a;
            this.b=b;
        }
    }

    public Coppia solve(int N, int F, int C) {
        int nf = N / F;
        N = N - nf*F;
        int nc = N/C;
        return new Coppia(nf, nc);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if(input_from_file) {
            fin = new FileInputStream("download_input_1.txt");
            fout = new FileOutputStream("output.txt");
        } else {
            fin = System.in;
            fout = System.out;
        }

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int F = scn.nextInt();
            int C = scn.nextInt();

            System.out.println(N+" "+F+" "+C);

            Download solver = new Download();
            Coppia risposta = solver.solve(N, F, C);

            prnt.format("Case #%d: %d %d\n", t, risposta.a, risposta.b);
            fout.flush();
        }
    }
}
