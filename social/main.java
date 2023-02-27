import java.util.*;
import java.io.*;
import java.lang.*;

public class main {
    public String solve(int N, int[] C) {
        ArrayList<Follower> x = new ArrayList<Follower>();
        for (int i = 0; i < C.length; i++) {
            x.add(new Follower(i, C[i]));
        }
        Collections.sort(x, (a, b) -> b.getValue()-a.getValue());
        if(x.get(0).getValue()==0){ return ""+0; }
        
        String res = "";
        int oob = 0;

        for (int index = 0; index < N; index++) {
            if(x.get(index).getValue()==0){
                for (int i = index; i < N; i++) {
                    x.remove(index);
                }
                break;
            }
        }

        for (int i = 0; i < N; i++) {
            oob = x.get(0).getPos()!=i ? 0 : (i==x.size()-1 ? 0 : x.size()-1);
            res += x.get(oob).getPos()+" ";
            x.get(oob).setValue(x.get(oob).getValue()-1);
            if(x.get(oob).getValue()==0){x.remove(oob);}
        }

        return res;
    }
   
    public class Follower{
        int pos = 0;
        int value = 0;
        public Follower(int pos, int value){
            this.pos = pos;
            this.value = value;
        }
        // getter and setter
        public int getValue() {
            return value;
        }
        public int getPos() {
            return pos;
        }
        public void setValue(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) throws FileNotFoundException, IOException {
        // se preferisci leggere e scrivere da file
        // ti basta modificare la seguente variabile
        boolean input_from_file = true;


        InputStream fin;
        OutputStream fout;
        if(input_from_file) {
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
            int[] C = new int[N];
            for (int i = 0; i < N; i++) {
                C[i] = scn.nextInt();
            }

            main solver = new main();
            String risposta = solver.solve(N, C);

            prnt.format("Case #%d: %s\n", t, risposta);
            fout.flush();
        }
    }
}