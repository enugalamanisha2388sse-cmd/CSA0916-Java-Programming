/** Task 5: class-level ticket counter and object lifecycle illustration. */
class StaticTicket {
    private static long nextTicket = 0;
    private final long ticketNumber;
    StaticTicket() { ticketNumber = ++nextTicket; }
    long getTicketNumber() { return ticketNumber; }
    static long issuedCount() { return nextTicket; }
}
public class Task05StaticLifecycle {
    public static void main(String[] args) {
        StaticTicket first = new StaticTicket();
        StaticTicket second = new StaticTicket();
        System.out.println(first.getTicketNumber());
        System.out.println(second.getTicketNumber());
        System.out.println("Issued=" + StaticTicket.issuedCount());
        first = null; // if no other reference exists, this ticket is eligible for GC
        System.out.println("First ticket reference released");
    }
}