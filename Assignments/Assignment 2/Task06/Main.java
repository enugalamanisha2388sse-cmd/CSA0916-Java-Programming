import java.util.*;

public class Main {
    static class Patient {
        final String name; final int severity; final long arrival;
        Patient(String n,int s,long a){name=n;severity=s;arrival=a;}
        public String toString(){return name+"(S"+severity+")";}
    }
    static class Triage {
        private long counter=0;
        private final PriorityQueue<Patient> queue=new PriorityQueue<>(
            Comparator.comparingInt((Patient p)->p.severity).thenComparingLong(p->p.arrival));
        void admit(String name,int severity){if(severity<1||severity>5)throw new IllegalArgumentException("Severity must be 1..5");queue.add(new Patient(name,severity,counter++));}
        Patient next(){return queue.poll();}
        List<Patient> waitingInTreatmentOrder(){List<Patient> copy=new ArrayList<>(queue);copy.sort(queue.comparator());return copy;}
    }
    private static void growth(int n){
        PriorityQueue<Integer> q=new PriorityQueue<>();long start=System.nanoTime();
        for(int i=0;i<n;i++)q.add(n-i);
        long add=System.nanoTime()-start;start=System.nanoTime();
        while(!q.isEmpty())q.poll();long remove=System.nanoTime()-start;
        System.out.printf(Locale.US,"n=%d add %.3f ms, remove-all %.3f ms%n",n,add/1e6,remove/1e6);
    }
    public static void main(String[] args) {
        Triage er=new Triage();
        er.admit("Ravi",3);er.admit("Meena",1);er.admit("John",3);er.admit("Asha",5);
        er.admit("Kumar",1);er.admit("Lata",2);er.admit("Nila",4);er.admit("Arun",2);
        System.out.println("Q6 - Priority triage");
        System.out.println("Waiting in treatment order: "+er.waitingInTreatmentOrder());
        StringBuilder order=new StringBuilder();Patient p;
        while((p=er.next())!=null)order.append(p).append(' ');
        System.out.println("Treatment order: "+order.toString().trim());
        System.out.println("Required example: Meena(S1) Kumar(S1) Lata(S2) Arun(S2) Ravi(S3) John(S3) Nila(S4) Asha(S5)");
        System.out.println("Growth investigation (timings vary):");for(int n:new int[]{1000,10000,50000})growth(n);
        System.out.println("Complexity: offer O(log n), poll O(log n), peek O(1), storage O(n).");
        System.out.println("Fairness: strict severity priority can starve minor cases; use a maximum-wait policy/aging with periodic priority recalculation.");
    }
}