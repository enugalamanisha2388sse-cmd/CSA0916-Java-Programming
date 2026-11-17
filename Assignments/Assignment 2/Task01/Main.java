import java.util.Locale;

public class Main {
    private static String buildPlus(int fields) {
        String text = "";
        for (int i = 0; i < fields; i++) text += "F" + i + "=" + i + ";";
        return text;
    }
    private static String buildBuilder(int fields) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < fields; i++) text.append("F").append(i).append("=").append(i).append(';');
        return text.toString();
    }
    private static String buildBuffer(int fields) {
        StringBuffer text = new StringBuffer();
        for (int i = 0; i < fields; i++) text.append("F").append(i).append("=").append(i).append(';');
        return text.toString();
    }
    private static long time(int fields, int tickets, int method) {
        long start = System.nanoTime();
        long checksum = 0;
        for (int t = 0; t < tickets; t++) {
            String s = method == 0 ? buildPlus(fields) : method == 1 ? buildBuilder(fields) : buildBuffer(fields);
            checksum += s.length();
        }
        if (checksum == -1) System.out.println(checksum);
        return System.nanoTime() - start;
    }
    private static double average(int fields, int tickets, int method) {
        for (int i = 0; i < 2; i++) time(fields, tickets, method);
        long total = 0;
        for (int i = 0; i < 5; i++) total += time(fields, tickets, method);
        return total / 5.0 / 1_000_000.0;
    }
    public static void main(String[] args) {
        int[][] workloads = {{5, 1}, {50, 1000}, {100, 3000}};
        String[] names = {"short ticket", "medium load", "large load"};
        System.out.println("Q1 - Ticket text construction (average of 5 measured runs; ms)");
        System.out.println("Workload, String +, StringBuilder, StringBuffer");
        for (int w = 0; w < workloads.length; w++) {
            int f = workloads[w][0], n = workloads[w][1];
            System.out.printf(Locale.US, "%s (%d fields x %d tickets), %.3f, %.3f, %.3f%n",
                names[w], f, n, average(f,n,0), average(f,n,1), average(f,n,2));
        }
        System.out.println("Estimated intermediate construction: repeated + creates successive immutable strings; builder/buffer append into a mutable buffer.");
        System.out.println("Recommendation: StringBuilder for a thread-confined ticket; StringBuffer only when synchronised access to one shared mutable buffer is genuinely required.");
        System.out.println("Complexity: repeated concatenation can copy O(n^2) total characters for n similarly sized fields; builder/buffer are amortized O(total output characters), often simplified to O(n) fields.");
    }
}