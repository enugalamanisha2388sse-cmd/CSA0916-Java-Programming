import java.util.*;

public class Main {
    private static long measure(Runnable action) {
        long start = System.nanoTime(); action.run(); return System.nanoTime() - start;
    }
    private static void runSize(int n) {
        List<Integer> a = new ArrayList<>(), l = new LinkedList<>();
        for (int i=0;i<n;i++) { a.add(i); l.add(i); }
        long arrayFront = measure(() -> { for(int i=0;i<Math.min(1000,n);i++) a.add(0,-i); });
        long linkFront = measure(() -> { for(int i=0;i<Math.min(1000,n);i++) l.add(0,-i); });
        int reps = Math.min(2000,n);
        long arrayGet = measure(() -> { long x=0; for(int i=0;i<reps;i++) x += a.get((i*31)%a.size()); if(x==Long.MIN_VALUE)System.out.println(x); });
        long linkGet = measure(() -> { long x=0; for(int i=0;i<reps;i++) x += l.get((i*31)%l.size()); if(x==Long.MIN_VALUE)System.out.println(x); });
        long arrayIter = measure(() -> { long x=0; for(int v:a)x+=v; if(x==Long.MIN_VALUE)System.out.println(x); });
        long linkIter = measure(() -> { long x=0; for(int v:l)x+=v; if(x==Long.MIN_VALUE)System.out.println(x); });
        System.out.printf(Locale.US,"n=%d | front insert x%d: ArrayList %.3f ms, LinkedList %.3f ms | random get x%d: %.3f ms, %.3f ms | iterate: %.3f ms, %.3f ms%n",
            n,Math.min(1000,n),arrayFront/1e6,linkFront/1e6,reps,arrayGet/1e6,linkGet/1e6,arrayIter/1e6,linkIter/1e6);
    }
    public static void main(String[] args) {
        System.out.println("Q2 - Playlist collection experiments (single illustrative run; timings vary)");
        for(int n:new int[]{1000,10000,50000}) runSize(n);
        ArrayDeque<String> upNext = new ArrayDeque<>();
        upNext.addLast("Track B"); upNext.addFirst("Track A"); upNext.addLast("Track C");
        System.out.println("ArrayDeque Up Next: " + upNext + "; play next = " + upNext.removeFirst());
        System.out.println("Guidance: ArrayList suits append + frequent indexed reads/iteration; reconsider it for frequent front/middle insertions or removals.");
        System.out.println("ArrayDeque is a strong fit for a double-ended Up Next queue; it does not provide indexed access.");
    }
}