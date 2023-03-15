import java.util.*;
import java.io.*;

public class Mostra {
   
    int mem[][];
    int V[];
    int G[];

    /** @return massimo guadagno possibile */
    public int solve(int V[], int G[]) {
        this.V = V;
        this.G = G;
        mem = new int[V.length][G.length];
        return rec(0,0);
    }

    private static int max3(int i1, int i2, int i3){
        if(i1>i2){
            return i1>i3 ? i1 : i3;
        } else {
            return i2>i3 ? i2 : i3;
        }
    }

    /** provo tutte le soluzioni, solo che mi segno 
     man mano i rtisultqati che trovo "per usi futuri" */
    public int rec(int posV, int posG){
        if(posV>=V.length){ // finiti i visitatori
            return 0;
        }        
        if(posG>=G.length){ // finite le guide
            return V.length - posV;
        }
        int costoPrendo;
        int costoNo;
        int costoSalto;
        // prova a togliere le due righe sotto poi vedi i tempi!
        if(mem[posV][posG]!=0)
            return mem[posV][posG];
        
        // prendo la guida
        int x = V[posV]<G[posG] ? 2 : 1;
        int resto = rec(posV+1,posG+1);
        costoPrendo = x + resto;
        
        // NON prendo la guida
        costoNo = 1 + rec(posV+1, posG);

        // salto questa guida
        costoSalto = rec(posV, posG+1);

        mem[posV][posG] = max3(costoPrendo, costoNo, costoSalto);
        return mem[posV][posG];
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // InputStream fin = new FileInputStream("ix.txt");
        // InputStream fin = new FileInputStream("input.txt");
        InputStream fin = new FileInputStream("mostra_input_1.txt");
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