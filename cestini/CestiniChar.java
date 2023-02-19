import java.util.*;
import java.io.*;

public class CestiniChar {

    /**
     * 
     * @param N numero degli oggetti, lunghezza della stringa
     * @param M numero dei cestini
     * @param Q spostamenti
     * @param S stringa che rappresenta gli spostamenti
     * @param type s(postamento) o c(ontrollo)
     * @param A
     * @param B
     * @return
     */
    public String solve(int N, int M, int Q, String S, char[] type, int[] A, int[] B) {
        char cesto[][] = new char[M][S.length()];
        cesto[0] = S.toCharArray();
        int testa[] = new int[M];
        testa[0] = S.length();
        for(int i=1; i<M; i++){
            testa[i]=0;
        }

        String risposta = "";

        for (int i = 0; i < Q; i++) {
            if (type[i] == 's') {
                // System.out.println(i+" "+A[i]+" "+B[i]+"   "+testa[A[i]]);
                char ultimo = cesto[A[i]][ testa[A[i]]-1 ];
                testa[ A[i] ]--;
                cesto[ B[i] ][ testa[B[i]] ] = ultimo;
                testa[B[i]]++;
            } else if (type[i] == 'c') {
                risposta += cesto[ A[i] ][ B[i] ];
            }
        }

        return risposta;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fin = System.in;
        OutputStream fout = System.out;

        System.out.println("scrivi qualcosa");
        System.in.read();
        System.out.println("vado");

        // se preferisci leggere e scrivere da file
        // ti basta decommentare le seguenti righe
        fin = new FileInputStream("cestini_input_1.txt");
        fout = new FileOutputStream("output.txt");

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int M = scn.nextInt();
            int Q = scn.nextInt();

            String S = scn.next();

            char[] type = new char[Q];
            int[] A = new int[Q];
            int[] B = new int[Q];

            for (int i = 0; i < Q; i++) {
                type[i] = scn.next().charAt(0);
                A[i] = scn.nextInt();
                B[i] = scn.nextInt();
                // System.out.println(type[i] + " " + A[i] + " " + B[i]);
            }

            CestiniChar solver = new CestiniChar();
            System.out.println(N + " " + M + " " + Q);
            String risposta = solver.solve(N, M, Q, S, type, A, B);

            prnt.format("Case #%d: %s", t, risposta);
            prnt.println();
            fout.flush();
        }
    }
}