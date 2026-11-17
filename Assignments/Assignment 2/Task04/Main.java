import java.util.*;

public class Main {
    private static void test(int n) {
        Map<String,Integer> hash = new HashMap<>();
        Map<String,Integer> tree = new TreeMap<>();
        Map<String,Integer> linked = new LinkedHashMap<>();
        for(int i=0;i<n;i++){String k=String.format(Locale.ROOT,"MED%06d",n-i);hash.put(k,i);tree.put(k,i);linked.put(k,i);}
        Random random=new Random(42); int lookups=100000; long sum=0;
        long start=System.nanoTime();
        for(int i=0;i<lookups;i++){int k=random.nextInt(n);sum+=hash.getOrDefault(String.format(Locale.ROOT,"MED%06d",n-k),-1);}
        long hashTime=System.nanoTime()-start;
        random=new Random(42); start=System.nanoTime();
        for(int i=0;i<lookups;i++){int k=random.nextInt(n);sum+=tree.getOrDefault(String.format(Locale.ROOT,"MED%06d",n-k),-1);}
        long treeTime=System.nanoTime()-start;
        start=System.nanoTime(); String firstTree=tree.keySet().iterator().next(); long treeReport=System.nanoTime()-start;
        start=System.nanoTime(); String firstLinked=linked.keySet().iterator().next(); long linkedReport=System.nanoTime()-start;
        if(sum==Long.MIN_VALUE)System.out.println(sum);
        System.out.printf(Locale.US,"n=%d | 100k lookups HashMap %.3f ms, TreeMap %.3f ms | first sorted=%s, insertion-order=%s | first-key iterator setup TreeMap %.3f ms, LinkedHashMap %.3f ms%n",
            n,hashTime/1e6,treeTime/1e6,firstTree,firstLinked,treeReport/1e6,linkedReport/1e6);
    }
    public static void main(String[] args) {
        System.out.println("Q4 - Pharmacy inventory map comparison");
        for(int n:new int[]{1000,10000,50000})test(n);
        Map<String,Integer> inventory=new LinkedHashMap<>(); inventory.put("MED003",12);inventory.put("MED001",8);inventory.put("MED002",20);
        System.out.println("Insertion order: "+inventory.keySet());
        System.out.println("Code order: "+new TreeMap<>(inventory).keySet());
        System.out.println("Recommendation: HashMap for fast lookup plus TreeMap for sorted reporting, or TreeMap alone when sorted traversal is a primary requirement.");
        System.out.println("Hashtable is legacy-synchronised; modern code normally uses HashMap and explicit concurrency choices when needed.");
    }
}