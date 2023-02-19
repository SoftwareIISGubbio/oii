import java.util.*;
import java.io.*;
import java.lang.*;

public class Caramelle {

    public static long mcm(long a,long b){
        long p=a*b;
        if(b>a){
            long x = a;
            a=b;
            b=x;
        }
        long r;
        while(b!=0){
            r = a % b;
            a=b;
            b=r;
        }
        return p/a;
    }
    
    public int solve(int N, int[] V) {
        // aggiungi codice...
        long risposta = V[0];
        for(int i=1; i<N; i++){
            risposta = mcm(risposta,V[i]);
        }

        return (int) risposta;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fin = System.in;
        OutputStream fout = System.out;

        // se preferisci leggere e scrivere da file
        // ti basta decommentare le seguenti righe
        fin = new FileInputStream("caramelle_input_2.txt");
        fout = new FileOutputStream("output.txt");

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scn.nextInt();

            int[] V = new int[N];
            for (int i = 0; i < N; i++) {
                V[i] = scn.nextInt();
            }

            Caramelle solver = new Caramelle();
            int risposta = solver.solve(N, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}