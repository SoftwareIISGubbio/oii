import java.util.*;
import java.io.*;

public class OstacoliLungo {
    int[] posizione;
    int[] punti;
    int[] tempo;
    int tempoTotale;

    int memoria[][];

    public int solve(int N, int L, int D, int[] X, int[] P, int[] S) {
        this.posizione = X;
        this.punti = P;
        this.tempo = S;
        this.tempoTotale = D;

        memoria = new int[N+1][N+1];
        for(int i=0;i<N+1;i++){
            for(int j=0;j<N+1;j++){
                memoria[i][j]=-1;
            }   
        }
        System.out.println(N+" "+L+" "+D+"=========");
        int risposta = calcola(0, 0, 0, 0, 0);

        return risposta;
    }

    private int calcola(
        int attualePunteggio,
        int attualePosizione,
        int attualeTempo,
        int indiceProssimo,
        int attualeIndice
    ){
        int max;
        int puntiSalto, puntiIgnoro, puntiI;

        int risposta;

        if(attualeTempo>=tempoTotale){
            risposta = attualePunteggio;
        } else {

            if(memoria[attualeIndice][indiceProssimo] > -1)
                return memoria[attualeIndice][indiceProssimo];

            // scandaglio tutti i prossimi ostacoli
            max = 0;
            for(int i=indiceProssimo; i<posizione.length; i++){
                int deltaT = tempo[i] - attualeTempo;
                int deltaP = Math.abs( posizione[i] - attualePosizione );
                if(deltaP<=deltaT){
                    // lo salto
                    puntiSalto = punti[i] + calcola(0, posizione[i], tempo[i], i+1, i);
                    // lo ignoro
                    puntiIgnoro = calcola(0, attualePosizione, attualeTempo, i+1, attualeIndice);
                    puntiI = Math.max(puntiSalto, puntiIgnoro);
                } else {
                    // non ci arrivo, non lo salto
                    puntiI = calcola(0, attualePosizione, attualeTempo, i+1, attualeIndice);
                }
                if(puntiI>max){
                    max = puntiI;
                }
            }
            risposta = attualePunteggio + max;
            memoria[attualeIndice][indiceProssimo] = risposta;
        }
        return risposta;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;

        InputStream fin;
        OutputStream fout;
        if(input_from_file) {
            // fin = new FileInputStream("input.txt");
            fin = new FileInputStream("ostacoli_input_2.txt");
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
            int L = scn.nextInt();
            int D = scn.nextInt();

            int[] X = new int[N];
            int[] P = new int[N];
            int[] S = new int[N];
            for (int i = 0; i < N; i++) {
                X[i] = scn.nextInt();
                P[i] = scn.nextInt();
                S[i] = scn.nextInt();
            }

            OstacoliLungo solver = new OstacoliLungo();
            int risposta = solver.solve(N, L, D, X, P, S);

            prnt.format("Case #%d: %d\n", t, risposta);
            fout.flush();
        }
    }
}
