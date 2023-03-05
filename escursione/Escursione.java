import java.util.*;
import java.io.*;

public class Escursione {
    static Nodo grafo[];

    public int solve(int X, int Y, int[][] V) {

        grafo = new Nodo[ X*Y ];

        // creo i nodi
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                grafo[ (i*Y)+j ] = new Nodo(i+"-"+j);
            }
        }

        // archi verso avanti ( relativi indietro)
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y-1; j++) {
                int da = (i*Y)+j;  // c'era una X
                int a = (i*Y)+j+1;  // c'era un X

                int peso = Math.abs(V[i][j] - V[i][j+1]);
                new Arco(da, a, peso);
                new Arco(a, da, peso);
              
            }
        }

        // archi verso il basso e relativi indietro
        for (int i = 0; i < X-1; i++) {
            for (int j = 0; j < Y; j++) {
                int da = (i*Y)+j;
                int a = (i*Y)+j+Y;
                int peso = Math.abs(V[i][j] - V[i+1][j]);
                new Arco(da, a, peso);
                new Arco(a, da, peso);
            }
        }
        
        camminiMinimi(grafo[0]);
        return grafo[grafo.length-1].costoPercorso;
    }

    // ------------------ INIZIO implementazione dell'oggetto Grafo ---------
    static class Nodo implements Comparable<Nodo>{
        String id;
        int costoPercorso;
        Nodo padre;
        ArrayList<Arco> archi;
        public Nodo( String id ){
            this.id = id;
            archi = new ArrayList<>();
        }
        public String toString(){
            String vicini = "";
            for(Arco a: archi){
                vicini+=a;
            }
            return "{"+id+" 💰:"+costoPercorso+" ↑"+(padre==null?"-":padre.id)+"} "+vicini;
        }
        @Override
        public int compareTo(Nodo o) {
            return this.costoPercorso - o.costoPercorso;
        }
    }
    static class Arco{
        Nodo a;
        int peso;
        // il costruttore di un arco inserisce anche l'arco stesso
        // nel nodo relativo, il parametro "da" viene ignorato
        // in questa implementazione
        public Arco(int da, int a, int peso){
            this.a = grafo[a];
            this.peso = peso;
            grafo[da].archi.add(this);
        }
        public String toString(){
            return "[→"+a.id+" 💰"+peso+"]";
        }
    }
    
    // ------------------ FINE implementazione dell'oggetto Grafo -----------

    static void camminiMinimi(Nodo x){
        // pulizia
        for(Nodo n: grafo){
            n.costoPercorso = Integer.MAX_VALUE;
            n.padre = null;
        }
        // costruzione partenza
        x.costoPercorso = 0;
        PriorityQueue<Nodo> s = new PriorityQueue<>();
        s.add(x);
        
        // costruzione della copertura
        while( !s.isEmpty() ){
            Nodo attuale = s.poll();
            for(Arco a: attuale.archi){
                Nodo vicino = a.a;
                // In pratica il gioco sta qui: il costro del cammino non è la somma
                // ma il massimo dei costi
                int nDist = attuale.costoPercorso > a.peso ? attuale.costoPercorso : a.peso;

                if( nDist < vicino.costoPercorso ){
                    vicino.costoPercorso = nDist;
                    vicino.padre = attuale;
                    s.add(vicino);
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // fin = new FileInputStream("input.txt");
        InputStream fin = new FileInputStream("escursione_input_1.txt");
        OutputStream fout = new FileOutputStream("output.txt");
        
        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        // T = numero di casi
        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            // N = numero di dati in una riga
            int H = scn.nextInt();
            int W = scn.nextInt();

            // inserisce tutti i dati di una riga in un array
            int[][] V = new int[H][W];

            for (int i = 0; i < H ; i++) {
                for (int j = 0; j < W ; j++) {
                    V[i][j] = scn.nextInt();
                }
            }

            Escursione solver = new Escursione();
            int risposta = solver.solve(H, W, V);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}