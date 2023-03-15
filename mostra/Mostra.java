import java.util.*;
import java.io.*;

public class Mostra {
   
    int mem[][];
    int V[];
    int G[];

    /** @return massimo guadagno possibile */
    public int solve(int V[], int G[]) {
        this.V = new int[V.length+G.length];
        for(int i=0; i<G.length; i++){
            this.V[i] = 50000000;
        }
        for(int i=0; i<V.length; i++){
            this.V[i+G.length] = V[i];
        }
        this.G = G;
        mem = new int[this.V.length][G.length];
        return rec(0,0)-G.length;
    }

    public int rec(int posV, int posG){
        // System.out.println(posV+" "+posG);
        if(posV>=V.length){ // finiti i visitatori
            return 0;
        }        
        if(posG>=G.length){ // finite le guide
            return V.length - posV;
        }
        int costoPrendo;
        int costoNo;
        /*if(mem[posV][posG]!=0)
            return mem[posV][posG]; */
        // prendo
        if(posG<G.length){
            int x = V[posV]<G[posG] ? 2 : 1;
            int resto = rec(posV+1,posG+1);
            // System.out.println("    "+x );
            costoPrendo = x + resto;
        } else {
            // non la prendo che l'ho finite
            costoPrendo = V.length - posV;
        }
        // oppure no
        costoNo = 1 + rec(posV+1, posG);
        mem[posV][posG] = costoPrendo>costoNo ? costoPrendo : costoNo;
        return mem[posV][posG];
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // InputStream fin = new FileInputStream("ix.txt");
        InputStream fin = new FileInputStream("input.txt");
        // InputStream fin = new FileInputStream("mostra_input_1.txt");
        OutputStream fout = new FileOutputStream("output.txt");
        
        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scn.nextInt();
            int M = scn.nextInt();

            int[] V = new int[N];
            for (int i = 0; i < N ; i++) {
                V[i] = scn.nextInt();
            }

            int[] G = new int[M];
            for (int i = 0; i < M ; i++) {
                G[i] = scn.nextInt();
            }

            Mostra solver = new Mostra();
            int risposta = solver.solve(V, G);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}