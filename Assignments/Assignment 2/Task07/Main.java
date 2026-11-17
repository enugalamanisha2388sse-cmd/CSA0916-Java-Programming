import java.util.*;

public class Main {
    private static long arrayListFrontRemove(int n) {
        List<Integer> list=new ArrayList<>();for(int i=0;i<n;i++)list.add(i);
        long start=System.nanoTime();long sum=0;while(!list.isEmpty())sum+=list.remove(0);
        if(sum==Long.MIN_VALUE)System.out.println(sum);return System.nanoTime()-start;
    }
    private static long dequeFrontRemove(int n) {
        Deque<Integer> deque=new ArrayDeque<>();for(int i=0;i<n;i++)deque.addLast(i);
        long start=System.nanoTime();long sum=0;while(!deque.isEmpty())sum+=deque.removeFirst();
        if(sum==Long.MIN_VALUE)System.out.println(sum);return System.nanoTime()-start;
    }
    public static void main(String[] args) {
        System.out.println("Q7 - Diagnose and redesign: playlist Up Next front-removal bottleneck");
        System.out.println("Same workload: fill n items, then remove every item from the front.");
        for(int n:new int[]{2000,10000,30000}) {
            long a=arrayListFrontRemove(n),d=dequeFrontRemove(n);
            System.out.printf(Locale.US,"n=%d | ArrayList %.3f ms | ArrayDeque %.3f ms | ratio %.2fx%n",n,a/1e6,d/1e6,d==0?0:(double)a/d);
        }
        System.out.println("Diagnosis: ArrayList.remove(0) shifts the remaining elements on every removal.");
        System.out.println("Objective: preserve FIFO behaviour while avoiding repeated shifts; target amortized O(1) removal at the front.");
        System.out.println("Redesign: ArrayDeque.addLast/removeFirst. Original total work O(n^2); redesigned total work O(n).");
        System.out.println("Trade-off: ArrayDeque has no indexed access; retain ArrayList for random-access playlist browsing.");
    }
}