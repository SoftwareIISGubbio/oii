import java.util.*;
import java.io.*;

public class CabalaString {

    public long solve(int N, int M) {
        // N è il numero di cifre di C
        // M rappresenta il modulo
        // trovare un numero C che ha resto massimo mod M tale che
        // C composto solo dalle cifre 3,6,9
        // C non ha due cifre uguali in fila
        long n3 = combina(M,N, "3") % M; 
        long n6 = combina(M,N, "6") % M;
        long n9 = combina(M,N, "9") % M;
        if(n3 > n6){
            return n3 > n9 ? n3 :n9;
        }else{
            return n6 > n9 ? n6 :n9;
        }
    }

    private long combina(int M, int N, String n){
        if(n.length()==N){
            return Long.parseLong(n); 
        } else {
            char ultima = n.charAt(n.length()-1);
            long n1=0,n2=0;
            if(ultima=='3'){
                n1 = combina(M, N, n+6);
                n2 = combina(M, N, n+9);
            }
            if(ultima=='6'){
                n1 = combina(M, N, n+3);
                n2 = combina(M, N, n+9);
            }
            if(ultima=='9'){
                n1 = combina(M, N, n+3);
                n2 = combina(M, N, n+6);
            }
            return n1%M > n2%M ? n1 : n2;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // InputStream fin = new FileInputStream("input.txt");
        InputStream fin = new FileInputStream("cabala.input6.txt");
        OutputStream fout = new FileOutputStream("output.txt");
        
        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        // T = numero di casi
        int T = scn.nextInt();
        long start = System.currentTimeMillis();
        for (int t = 1; t <= T; t++) {
            // N = numero di dati in una riga
            int N = scn.nextInt();
            int M = scn.nextInt();


            CabalaString solver = new CabalaString();
            long risposta = solver.solve(N, M);

            prnt.format("Case #%d: %d\n", t,risposta);
            fout.flush();
        }
        System.err.println("t msec= "+(System.currentTimeMillis()-start));
        prnt.close();
        scn.close();
    }
}