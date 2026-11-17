import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class SeatBooking {
    private final boolean[] booked;
    private int bookingsMade = 0;
    SeatBooking(int seats) { booked = new boolean[seats]; }
    // Atomic check-then-book operation: the intrinsic monitor protects both statements.
    public synchronized boolean book(int seat) {
        if (booked[seat]) return false;
        booked[seat] = true;
        bookingsMade++;
        return true;
    }
    public int getBookingsMade() { return bookingsMade; }
}
public class Main {
    static int runCompetition() throws InterruptedException {
        SeatBooking screen = new SeatBooking(10);
        Thread[] users = new Thread[100];
        for (int i = 0; i < users.length; i++) {
            final int seat = i % 10;
            users[i] = new Thread(() -> screen.book(seat));
            users[i].start();
        }
        for (Thread t : users) t.join();
        return screen.getBookingsMade();
    }
    public static void main(String[] args) throws Exception {
        for (int run = 1; run <= 10; run++)
            System.out.println("Run " + run + " - Seats sold: " + runCompetition());
        System.out.println("Expected seats sold per run: 10");
        System.out.println("All runs preserve the one-booking-per-seat invariant.");
    }
}