import java.util.*;
import java.io.*;

public class ctf {

    // quello sotto è solo un gioco, non è necessatrio funzionan anche con Math.log
    public int log2(long n){
        int log=0;
        while(n>1){
            n>>=1;
            log++;
        }
        return log;
    }

    static double l2 = Math.log(2);
    public long solveLog(long N) {
        // long pot = (long)(Math.log(N)/l2);
        long pot = log2(N);
        // long num = N-(long)Math.pow(2,pot);
        long num = N-(1L<<pot);
        return 2*num+1;
    }

    public long solveLogF(long N) {
        long pot=1;
        while(pot<=N){
            pot*=2;
        };
        pot/=2;
        long num = N-pot;
        return 2*num+1;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if(input_from_file) {
            // fin = new FileInputStream("input.txt");
            fin = new FileInputStream("input.txt");
            fout = new FileOutputStream("output.txt");
        } else {
            fin = System.in;
            fout = System.out;
        }

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for(int t = 1; t <= T; t++) {
            long N = scn.nextLong();

            ctf solver = new ctf();
            long risposta = solver.solveLog(N);

            // prnt.format("%d\n", risposta);
            prnt.println(risposta);
            fout.flush();
        }
    }
}
