import java.util.*;

public class Main {
    static class Team {
        final String name; final int points, goalDiff;
        Team(String n,int p,int g){name=n;points=p;goalDiff=g;}
        public String toString(){return name+"("+points+","+goalDiff+")";}
    }
    static class Leaderboard<T> {
        private final List<T> items=new ArrayList<>();
        private final Comparator<? super T> order;
        Leaderboard(Comparator<? super T> c){order=c;}
        void add(T item){items.add(item);}
        void addAll(List<? extends T> values){items.addAll(values);}
        List<T> ranking(){List<T> copy=new ArrayList<>(items);copy.sort(order);return copy;}
    }
    public static void main(String[] args) {
        Comparator<Team> byRank=Comparator.comparingInt((Team t)->t.points).reversed()
            .thenComparing(Comparator.comparingInt((Team t)->t.goalDiff).reversed())
            .thenComparing(t->t.name);
        Leaderboard<Team> board=new Leaderboard<>(byRank);
        board.add(new Team("Panthers",9,4));board.add(new Team("Eagles",9,6));
        board.add(new Team("Cobras",6,1));board.add(new Team("Bisons",9,4));
        System.out.println(board.ranking());
        System.out.println("Expected: [Eagles(9,6), Bisons(9,4), Panthers(9,4), Cobras(6,1)]");
        System.out.println("Bisons precedes Panthers by alphabetical name after points and goal difference tie.");
        List<Team> before=board.ranking();
        board.addAll(Arrays.asList(new Team("Falcons",9,4)));
        System.out.println("Original ranking snapshot remains independent: "+before);
        Comparator<Integer> safe=Integer::compare;
        System.out.println("Overflow-safe integer comparator: "+safe.compare(Integer.MAX_VALUE,Integer.MIN_VALUE));
    }
}