import java.util.*;
import java.io.*;

public class CestiniStack {

    public String solve(int N, int M, int Q, String S, char[] type, int[] A, int[] B) {
        ArrayList<Stack<Character>> cestini = new ArrayList<>();
        Stack<Character> stack = new Stack<>();

        for (char s : S.toCharArray()) {
            stack.push( s );
        }
        cestini.add(stack);

        for (int i = 1; i < M; i++) {
            cestini.add(new Stack<Character>());
        }

        String risposta = "";

        for (int i = 0; i < Q; i++) {
            if (type[i] == 's') {
                cestini.get(B[i]).push(cestini.get(A[i]).pop());
            } else if (type[i] == 'c') {
                risposta += cestini.get(A[i]).get(B[i]);
            }
        }

        return risposta;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fin = System.in;
        OutputStream fout = System.out;

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

            CestiniStack solver = new CestiniStack();
            String risposta = solver.solve(N, M, Q, S, type, A, B);

            prnt.format("Case #%d: %s", t, risposta);
            prnt.println();
            fout.flush();
        }
    }
}