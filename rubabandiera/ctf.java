import java.util.*;
import java.io.*;

public class ctf {

    public static void dump(boolean v[]){
        String k = "";
        for(int i=0;i<v.length; i++){
            k += v[i] ? "B" : "_";
        }
        System.out.println(k);
    }

    // usa bandiere[x]=true per segnare i morti!
    public int solveN(int N) {
        bandiere = new boolean[N];
        return altroGiroN(0, 1);
    }

    int altroGiroN(int ladroPossibile, int passo){
        int ladro=ladroPossibile;
        int prossimo;
        
        if(passo>bandiere.length/2){
            return ladro;
        }
        int i;
        for(i=ladro;i<bandiere.length-passo; i+=passo*2){
            bandiere[i+passo] = true;
        }
        if(i>=bandiere.length){
            // ho finito il giro
            prossimo=0;
            while(bandiere[prossimo]==true){
                prossimo++;
            }
        } else {
            // perché il ladro sta in fondo al vettore e tocca fare il giro
            int bersaglio = 0;
            while(bandiere[bersaglio]==true){
                bersaglio++;
            }
            bandiere[bersaglio]=true;
            // cerco prossimo ladro
            prossimo=bersaglio+1;
            while(bandiere[prossimo]==true){
                prossimo++;
            }
        }
        return altroGiroN(prossimo,passo*2);
    } 

    boolean bandiere[];
    public int solveX(int N) {
        bandiere = new boolean[N];
        for(int i=0 ; i<bandiere.length ; i++){
            bandiere[i] = true;
        }
        return altroGiro(0, 1);
    }

    int altroGiro(int ladroPossibile, int passo){
        int ladro=ladroPossibile;
        int prossimo;
        
        if(passo>bandiere.length/2){
            return ladro;
        }
        // non mangio l'ultimo
        int i;
        for(i=ladro;i<bandiere.length-passo; i+=passo*2){
            bandiere[i+passo] = false;
        }
        if(i>=bandiere.length){
            // ho finito il giro
            prossimo=0;
            while(bandiere[prossimo]==false){
                prossimo++;
            }
        } else {
            // perché il ladro sta in fondo al vettore e tocca fare il giro
            int bersaglio = 0;
            while(bandiere[bersaglio]==false){
                bersaglio++;
            }
            bandiere[bersaglio]=false;
            // cerco prossimo ladro
            prossimo=bersaglio+1;
            while(bandiere[prossimo]==false){
                prossimo++;
            }
        }
        return altroGiro(prossimo,passo*2);
    } 

    public int solvePariPari(int N) {
        boolean bandiere[] = new boolean[N];
        for(int i=0 ; i<bandiere.length-1 ; i+=2){
            bandiere[i] = true;
            bandiere[i+1] = false;
        }
        int turni,ladro,bersaglio;
        if(bandiere.length%2==0){
            ladro = 0;
            turni=bandiere.length/2-1;
        }else{
            bandiere[bandiere.length-1]=true;
            ladro = bandiere.length-1;
            turni=bandiere.length/2;
        }
        for(int turno=0; turno<turni; turno++){
            // dump(bandiere);
            while(bandiere[ladro]==false){
                ladro = (ladro+1)%bandiere.length;
            }
            bersaglio = (ladro+1)%bandiere.length;
            while(bandiere[bersaglio]==false){
                bersaglio = (bersaglio+1)%bandiere.length;
            }
            bandiere[bersaglio]=false;
            ladro = (bersaglio+1)%bandiere.length;
        }
        // dump(bandiere);
        while(bandiere[ladro]==false){
            ladro = (ladro+1)%bandiere.length;
        }
        return ladro;
    }

    public int solveScanu(int N) {
        ArrayList<Integer> bandiere = new ArrayList<>();
        for(int i=0 ; i<N ; i++){
            bandiere.add(i);
        }
        int ladro = 0, bersaglio;
        for(int i=0 ; i<N-1 ; i++){
            bersaglio = (ladro+1)%bandiere.size();
            // System.out.println(bandiere.get(ladro) +"->"+bandiere.get(bersaglio));
            bandiere.remove(bersaglio);
            if(bersaglio>ladro){
                ladro = (ladro+1)%bandiere.size();
            }else{
                ladro = (ladro)%bandiere.size();
            }
        }
        return bandiere.get(0);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if(input_from_file) {
            // fin = new FileInputStream("input.txt");
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

            ctf solver = new ctf();
            int risposta = solver.solveN(N);

            prnt.format("%d\n", risposta+1);
            fout.flush();
        }
    }
}