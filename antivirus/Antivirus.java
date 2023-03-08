import java.util.*;
import java.io.*;

public class Cabala {

    public int[] solve(int M, String F1, String F2, String F3, String F4) {
        int p1,p2,p3,p4;
        for(p1=0; p1<= F1.length()-M; p1++){
            String pezzo = F1.substring(p1, p1+M);
            p2 = F2.indexOf(pezzo);
            if(p2>-1){
                p3 = F3.indexOf(pezzo);
                if(p3>-1){
                    p4 = F4.indexOf(pezzo);
                    if(p4>-1){
                        int soluzione[] = {p1,p2,p3,p4};
                        return soluzione;
                    }   
                }
            }
        }
        return null;
    }



    public static void main(String[] args) throws FileNotFoundException, IOException {
        // InputStream fin = new FileInputStream("input.txt");
        InputStream fin = new FileInputStream("antivirus_input_1.txt");
        OutputStream fout = new FileOutputStream("output.txt");
        
        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

        // T = numero di casi
        int T = scn.nextInt();
        for (int t = 1; t <= T; t++) {
            // N = numero di dati in una riga
            int N1 = scn.nextInt();
            int N2 = scn.nextInt();
            int N3 = scn.nextInt();
            int N4 = scn.nextInt();
            int M = scn.nextInt();

            String F1 = scn.next();
            String F2 = scn.next();
            String F3 = scn.next();
            String F4 = scn.next();

            Cabala solver = new Cabala();
            int risposta[] = solver.solve(M, F1, F2, F3, F4);

            prnt.format("Case #%d: %d %d %d %d\n", t,risposta[0],risposta[1],risposta[2],risposta[3]);
            fout.flush();
        }
        prnt.close();
        scn.close();
    }
}