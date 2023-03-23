import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sort {
    static class Rettangolo{
        public int base;
        public int altezza;
        public Rettangolo(int a, int b){
            altezza = a;
            base = b;
        }
        public String toString(){
            return "altezza="+altezza+"  base="+base;
        }
    }

    static class Quadrato implements Comparable<Quadrato>{
        public int lato;
        public Quadrato(int l){
            lato=l;
        }
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Comparable.html
        public int compareTo(Quadrato altro){
            if(this.lato==altro.lato){
                return 0;
            }
            return this.lato<altro.lato ? -1 : 1;
        }
        public String toString(){
            return "lato="+lato;
        }
    }

    private static void dump(ArrayList<Rettangolo> al){
        al.forEach( e -> {
            System.out.println(e);
        });
    }

    private static void dump(Quadrato[] v){
        for( Quadrato e : v){
            System.out.println(e);
        };
    }

    public static void main(String[] args) {
        System.out.println("\n===== rettangolo =====");
        ArrayList<Rettangolo> lista = new ArrayList<>();
        lista.add(new Rettangolo(1,2));
        lista.add(new Rettangolo(5,7));
        lista.add(new Rettangolo(3,9));
        dump( lista );

        System.out.println("ordino altezza");
        Collections.sort(lista, 
            (Rettangolo a, Rettangolo b)-> a.altezza-b.altezza );
        dump( lista );

        System.out.println("ordino base");
        // Arrays.sort
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Comparator.html
        Collections.sort(lista, 
            (Rettangolo a, Rettangolo b)-> a.base-b.base );
        dump( lista );

        System.out.println("\n===== quadrato =====");
        Quadrato q[] = {
            new Quadrato(4), new Quadrato(9), new Quadrato(2)
        };
        dump(q);
        System.out.println("ordino");
        Arrays.sort(q);
        dump(q);
    }
}
