import java.util.*;

class Prioricoda{
    static class Elemento implements Comparable<Elemento>{
        int id;
        int peso = 0;
        public Elemento(int id, int peso){
            this.id = id;
            this.peso = peso;
        }
        @Override
        public int compareTo(Elemento o) {
            return this.peso - o.peso;
        }
        @Override
        public String toString(){
            return "[id:"+id+"]";
        }
    }

    public static void main(String[] args) {
        System.out.println("hello");
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/PriorityQueue.html
        PriorityQueue<Elemento> pc = new PriorityQueue<>();
        Elemento e1 = new Elemento(1,10);
        Elemento e2 = new Elemento(2,20);
        Elemento e3 = new Elemento(3,5);
        pc.add(e1); 
        pc.add(e2); 
        pc.add(e3); 

        Elemento estratto;
        estratto = pc.poll();
        System.out.println( estratto );
        System.out.println( pc.poll() );
    }
}