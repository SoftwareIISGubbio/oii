import java.util.*;
import java.io.*;

public class Cestini {

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
        String cesto[] = new String[M];
        cesto[0] = S;
        for(int i=1; i<M; i++){
            cesto[i]="";
        }

        String risposta = "";

        for (int i = 0; i < Q; i++) {
            if (type[i] == 's') {
                char ultimo = cesto[A[i]].charAt(cesto[A[i]].length()-1);
                cesto[A[i]] = cesto[A[i]].substring(0, cesto[A[i]].length() - 1);
                cesto[B[i]] += ultimo;
            } else if (type[i] == 'c') {
                risposta += cesto[A[i]].charAt(B[i]);
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

            Cestini solver = new Cestini();
            String risposta = solver.solve(N, M, Q, S, type, A, B);

            prnt.format("Case #%d: %s", t, risposta);
            prnt.println();
            fout.flush();

        }
    }
}