import java.util.*;
import java.io.*;
import java.lang.*;

public class main {
    public int solve(int N, int rm[]) {

        int ac = 0;
        int lastValue = -1;
        boolean x = false;
        boolean ready = false;

        while(ac != lastValue){
            lastValue = ac;
            x = false;
            ready = false;
            for (int i = 0; i < N; i++) {
                if(rm[i]!=0){
                    rm[i]--;
                    x = true;
                    if(ready){
                        ac++;
                        ready = false;
                    }
                } else if(x){
                    ready = true;
                }
            }
            if(x){ac++;}
        }
        return ac;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if(input_from_file) {
            fin = new FileInputStream("input.txt");
            fout = new FileOutputStream("output.txt");
        } else {
            fin = System.in;
            fout = System.out;
        }

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int f[] = new int[N];
            for (int i = 0; i < N; i++) {
                f[i] = scn.nextInt();
            }

            main solver = new main();
            int risposta = solver.solve(N, f);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}