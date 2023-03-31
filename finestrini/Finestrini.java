import java.util.*;
import java.io.*;

public class finestraMem {

    int L[], R[];
    int mem[][][];

    public int solve(int N, int[] L, int[] R) {
        this.L = L;
        this.R = R;
        mem= new int[N][2][3];
        for(int i=0;i<N;i++){
            mem[i][0][0]=-1;
            mem[i][1][0]=-1;
            mem[i][0][1]=-1;
            mem[i][1][1]=-1;
            mem[i][0][2]=-1;
            mem[i][1][2]=-1;
        }
        int costoL = piazza(0, false, 0);
        int costoR = piazza(0, true, 0);
        return Math.min(costoL, costoR);
    }

    int piazza(int posto, boolean destra, int quantiGiaMessi) {
        int costo = destra ? R[posto] : L[posto];
        quantiGiaMessi++;

        int x = destra ? 1 : 0;

        if(mem[posto][x][quantiGiaMessi]!=-1)
            return mem[posto][x][quantiGiaMessi];

        if (posto != R.length - 1) {
            if (quantiGiaMessi == 2) {
                costo += piazza(posto + 1, !destra, 0);
            } else {
                int costoAltro = piazza(posto + 1, !destra, 0);
                int costoStesso = piazza(posto + 1, destra, quantiGiaMessi);
                costo += Math.min(costoAltro, costoStesso);
            }
        }
        mem[posto][x][quantiGiaMessi]=costo;
        return costo;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if (input_from_file) {
            //fin = new FileInputStream("input.txt");
            fin = new FileInputStream("finestrini_input_2.txt");
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

            int[] L = new int[N];
            int[] R = new int[N];
            for (int i = 0; i < N; i++) {
                L[i] = scn.nextInt();
                R[i] = scn.nextInt();
            }
            finestraMem solver = new finestraMem();
            int risposta = solver.solve(N, L, R);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}

	
