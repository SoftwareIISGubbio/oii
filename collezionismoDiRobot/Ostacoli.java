import java.util.*;
import java.io.*;

public class Ostacoli{
    class Coppia { // implements Comparable<Coppia>{
        int posizione,valore;
        Coppia(int pos,int dis){
            this.posizione=pos;
            this.valore=dis;
        }
        @Override
        public String toString() {
            return "["+posizione+","+valore+"]";
        }
        /* 
        @Override
        public int compareTo(Coppia o) {
            return o.valore-this.valore;
        }*/
    }

    public void dumpV(Object x){
        if(x instanceof Object[] d){
            for(int i=0;i<d.length;i++)
                System.out.print(d[i]+" ");
        } else if(x instanceof int[] d){
            for(int i=0;i<d.length;i++)
                System.out.print(d[i]+" ");
        }
        System.out.println();
    }

    public int solve(int N, int K, int[] C) {
        // ordino C
        Arrays.sort( C );
        // dumpV(C);
        
        // creo un vettore con distanze e relative posizioni
        Coppia d[] = new Coppia[C.length-1];
        for(int i=0;i<C.length-1;i++){
            d[i] = new Coppia(i, C[i+1]-C[i]);
        }
        // dumpV(d);

        // ordino in modo da prendere i tagli più grandi
        Arrays.sort( d , (Coppia x, Coppia y) -> y.valore-x.valore );
        // dumpV(d);

        // prendo le K-1 posizioni dei tagli
        int[] tagli = new int[K-1];
        for(int i=0;i<tagli.length;i++){
            tagli[i] = d[i].posizione;
        }
        Arrays.sort(tagli);
        // dumpV(tagli);

        int prev=0;
        int totale = 0;
        for(int i=0;i<tagli.length;i++){
            int inizio = prev;
            int fine = tagli[i];
            int distanza = C[fine] - C[inizio];
            // System.out.println("   "+distanza+"   ");
            prev = fine+1;
            totale += distanza;
        }
        // aggiungo pezzo finale
        totale+=C[C.length-1]-C[prev];
        // System.out.println();

        return totale;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if(input_from_file) {
            // fin = new FileInputStream("collezionismo_input_1.txt");
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
            int N = scn.nextInt();
            int K = scn.nextInt();

            int[] C = new int[N];
            for (int i = 0; i < N; i++) {
                C[i] = scn.nextInt();
            }

            Ostacoli solver = new Ostacoli();
            int risposta = solver.solve(N, K, C);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}
