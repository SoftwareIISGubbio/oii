import java.util.*;
import java.io.*;

public class Lampadine {

    class Nodo{
        boolean visitato = false;
        boolean interruttore = false;
        ArrayList<Integer> vicini = new ArrayList<>();
        public String toString(){
            StringBuilder s = new StringBuilder("["+interruttore+"]");
            for(int i=0; i<vicini.size(); i++){
                s.append(" "+vicini.get(i));
            }
            return s.toString();
        }
    }

    Nodo grafo[];
        /* 
    int DFS(int partenza){
    label v as discovered
    for all directed edges from v to w that are in G.adjacentEdges(v) do
        if vertex w is not labeled as discovered then
            recursively call DFS(G, w)
    } */

    int DFS(int partenza){
        grafo[partenza].visitato=true;   
        for(int iVicino: grafo[partenza].vicini){
            if( !grafo[iVicino].visitato ){
                
            }
        }
        return 0;
    }
    
    public String solve(int N, int A, int B, int[] Z, int[] X, int[] Y) {
        System.out.println(N+" "+A+" "+B);
        // creo i nodi che mi servono
        grafo = new Nodo[N];
        for(int i=0; i<N ; i++){
            grafo[i] = new Nodo();
        }
        // imposto tutti gli interruttori singoli
        for(int i=0; i<A ; i++){
            grafo[ Z[i] ].interruttore = true;
        }
        for(int i=0; i<B ; i++){
            grafo[ X[i] ].vicini.add( Y[i] );
            // and reverse! (se gli archi non sono orientati)
            grafo[ Y[i] ].vicini.add( X[i] );
        }
        for(Nodo x: grafo){
            System.out.println( "   "+ x );
        }
        return "42";
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream fin = System.in;
        OutputStream fout = System.out;

        // se preferisci leggere e scrivere da file
        // ti basta decommentare le seguenti righe
        fin = new FileInputStream("input.txt");
        fout = new FileOutputStream("output.txt");

        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            // prima riga del blocco
            int N = scn.nextInt();
            int A = scn.nextInt();
            int B = scn.nextInt();

            int[] Z = new int[A];
            int[] X = new int[B];
            int[] Y = new int[B];

            for (int i = 0; i < A; i++) {
                Z[i] = scn.nextInt();
            }

            for (int i = 0; i < B; i++) {
                X[i] = scn.nextInt();
                Y[i] = scn.nextInt();
            }

            Lampadine solver = new Lampadine();
            String risposta = solver.solve(N, A, B, Z, X, Y);

            prnt.format("Case #%d: %s", t, risposta);
            prnt.println();
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}