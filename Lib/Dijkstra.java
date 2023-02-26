import java.util.*;
import java.io.*;

/****************************************************************************
 * Qui è tutto implementato in un unico file perché era richiesto
 * da gioco. Non è certo un gran che bello.
 ***************************************************************************/
class Dijkstra {
    // ------------------ INIZIO implementazione dell'oggetto Grafo ---------
    static class Nodo implements Comparable<Nodo>{
        int id;
        int costoPercorso;
        Nodo padre;
        ArrayList<Arco> archi;
        public Nodo( int id ){
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
            // return o.costoPercorso - this.costoPercorso;
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
    static Nodo grafo[];
    // ------------------ FINE implementazione dell'oggetto Grafo -----------

    // ------ stampe di utilità (in pratica solo robe decorative) -----------
    static void dump(){
        for(Nodo n:grafo){
            System.out.println(n);
        }
    }
    static void coperturaDump(){
        System.out.print("🌳");
        Nodo n=null;
        for(int i=0; i<grafo.length; i++){
            if(grafo[i].padre==null){
                n = grafo[i];
                break;
            }
        }
        coperturaDumpRec(0, n);
    }
    private static void coperturaDumpRec(int nest, Nodo n){
        String indent = new String(new char[nest*2]).replace('\0', ' ');
        System.out.println(indent+n);
        for(int i=0; i<grafo.length; i++){
            if(grafo[i].padre == n){
                coperturaDumpRec(nest+1, grafo[i]);
            }
        }
    }

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
                int nDist = attuale.costoPercorso + a.peso;
                if( nDist < vicino.costoPercorso ){
                    vicino.costoPercorso = nDist;
                    vicino.padre = attuale;
                    s.add(vicino);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        InputStream fin = new FileInputStream("simmetrico.txt");

        Scanner scn = new Scanner(fin);

        int numeroNodi = scn.nextInt();
        grafo = new Nodo[numeroNodi];
        for(int i=0; i<numeroNodi; i++){
            grafo[i] = new Nodo(i);
        }

        int numeroArchi = scn.nextInt();
        for(int i = 0; i < numeroArchi; i++) {
            int da = scn.nextInt();
            int a = scn.nextInt();
            int peso = scn.nextInt();
            new Arco(da, a, peso);
            new Arco(a, da, peso);  // questo serve per archi simmetrici
        }
        scn.close();
        fin.close();
        
        dump();

        camminiMinimi(grafo[0]);

        coperturaDump();
    }
}