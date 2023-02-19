import java.util.*;
import java.io.*;

public class Interruttori {
    class Lampadina{
        int id;
        boolean singolo=false;
        boolean visited=false;
        int click = 0;
        ArrayList<Lampadina> adiacenti = new ArrayList<>();
        public Lampadina(int i){
            id = i;
        }
    }

    void dump( String desc, Lampadina grafo[] ){
        System.out.println("====="+desc+"===============");
        for(int i=0; i<grafo.length; i++){
            System.out.print( " %2d %s  ".formatted(i, grafo[i].singolo ? "s" : " ") );
            for(Lampadina l: grafo[i].adiacenti){
                System.out.print(" "+l.id);
            }
            System.out.println();
        }
    }

    public String solve(int N, int A, int B, int[] S, int[] X, int[] Y) {

        Lampadina grafo[] = new Lampadina[N];
        for(int i=0; i<N ; i++){
            grafo[i] = new Lampadina(i);
        }
        for(int i=0; i<A ; i++){
            grafo[ S[i] ].singolo = true;
        }
        for(int i=0; i<B ; i++){
            grafo[ X[i] ].adiacenti.add( grafo[ Y[i] ] );
            grafo[ Y[i] ].adiacenti.add( grafo[ X[i] ] );
        }

        // dump("grafo",grafo);

        // inserisco i nodi "singolo" in coda
        LinkedList<Lampadina> coda = new LinkedList<>();
        for(Lampadina l: grafo){
            if(l.singolo){
                l.click = 1;
                l.visited = true;
                coda.add( l );
            }
        }
        // System.out.println("  si parte da num nodi "+coda.size());
        // finché la coda non è vuota
        while(!coda.isEmpty()){
            Lampadina inEsame = coda.remove();
            for(Lampadina prossima : inEsame.adiacenti){
                if( !prossima.visited ){
                    prossima.visited = true;
                    prossima.click = inEsame.click+1;
                    coda.add(prossima);
                }
            }
        }
        int maxClick = 0;
        int maxIndex = -1;
        for(Lampadina l: grafo){
            if(l.click>maxClick){
                maxClick = l.click;
                maxIndex = l.id;
            }
        }

        return maxIndex+" "+maxClick;
    }
   

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if(input_from_file) {
            // fin = new FileInputStream("input.txt");
            fin = new FileInputStream("interruttori_input_2.txt");
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
            int A = scn.nextInt();
            int B = scn.nextInt();

            int[] S = new int[A];
            int[] X = new int[B];
            int[] Y = new int[B];
            for (int i = 0; i < A; i++) {
                S[i] = scn.nextInt();
            }
            for (int i = 0; i < B; i++) {
                X[i] = scn.nextInt();
                Y[i] = scn.nextInt();
            }

            Interruttori solver = new Interruttori();
            String risposta = solver.solve(N, A, B, S, X, Y);


            prnt.format("Case #%d: %s\n", t, risposta);
            fout.flush();
        }

        prnt.close();
        scn.close();
    }
}
