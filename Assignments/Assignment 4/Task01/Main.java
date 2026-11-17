public class Main {
    public static void main(String[] args) {
        System.out.println("TASK 1 - GUI DESIGN REVIEW");
        System.out.println("Problems: (1) UI and payment logic are coupled; (2) String values may be compared with ==;");
        System.out.println("(3) input is not validated; (4) database access runs on the event thread;");
        System.out.println("(5) Thread.sleep freezes the UI; (6) SQL concatenation risks injection;");
        System.out.println("(7) resources may not close; (8) exceptions and receipt feedback are poorly handled.");
        System.out.println("Redesign: FeeWindow -> FeeController -> FeeService -> FeeRepository");
        System.out.println("FeeWindow collects input and displays results; FeeService validates and processes payment.");
        System.out.println("Use equals() for strings, PreparedStatement, try-with-resources, and SwingWorker for I/O.");
        System.out.println("Shared listener: less duplication, but branches grow. Separate actions: clearer responsibility and reuse.");
    }
}
