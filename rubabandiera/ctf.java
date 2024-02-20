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

    boolean bandiere[];
    public int solveX(int N) {
        bandiere = new boolean[N];
        for(int i=0 ; i<bandiere.length ; i++){
            bandiere[i] = true;
        }
        return altroGiroK(0, 2);
    }

    int altroGiro(int partenza){
        int ladro=partenza;
        //dump(bandiere);
        while(bandiere[ladro]==false){
            ladro = (ladro+1)%bandiere.length;
        }
        int bersaglio=(ladro+1)%bandiere.length;
        while(bandiere[bersaglio]==false){
            bersaglio = (bersaglio+1)%bandiere.length;
        }
        if(ladro==bersaglio){
            return ladro;
        }
        // pulisci
        int step = bersaglio>ladro ? bersaglio-ladro : (bersaglio+bandiere.length)-ladro;
        // System.out.println("primo ladro: "+ladro+"  bersaglio:"+bersaglio+"  step:"+step);
        int ultimoUtile=-1;
        for(int i=0; i<bandiere.length && bandiere[bersaglio]; i+=step*2){
            ultimoUtile=bersaglio;
            bandiere[bersaglio]=false;
            bersaglio = (bersaglio+step*2)%bandiere.length;
        };
        // ^^^
        // dump(bandiere);
        return altroGiro(ultimoUtile);
    } 

    int altroGiroK(int partenza, int step){
        int ladro=partenza;
        //dump(bandiere);
        while(bandiere[ladro]==false){
            ladro = (ladro+1)%bandiere.length;
        }
        if(step>=bandiere.length){
            return ladro;
        }
        // pulisci
        int ultimoUtile = -1;
        int bersaglio = (ladro+step)%bandiere.length;
        for(int i=0; i<bandiere.length && bandiere[bersaglio]; i+=step*2){
            ultimoUtile=bersaglio;
            bandiere[bersaglio]=false;
            bersaglio = (bersaglio+step*2)%bandiere.length;
        };
        // ^^^
        // dump(bandiere);
        return altroGiroK(ultimoUtile, step*2);
    } 

    public int solve(int N) {
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
            int risposta = solver.solveX(N);

            prnt.format("%d\n", risposta+1);
            fout.flush();
        }
    }
}