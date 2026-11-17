/** Task 4: overloaded constructors and constructor chaining. */
import java.time.LocalDateTime;
import java.util.Objects;
class TicketVehicle {
    private final String registration;
    TicketVehicle(String registration) {
        if (registration == null || registration.isBlank()) throw new IllegalArgumentException("Registration required");
        this.registration = registration;
    }
    String getRegistration() { return registration; }
}
class TaskTicket {
    private static long nextNumber = 1000;
    private final long number;
    private final TicketVehicle vehicle;
    private final int floor, slot;
    private final LocalDateTime entry;
    TaskTicket(TicketVehicle vehicle, int floor, int slot) {
        this(vehicle, floor, slot, LocalDateTime.now());
    }
    TaskTicket(TicketVehicle vehicle, int floor, int slot, LocalDateTime entry) {
        if (floor < 0 || slot < 0) throw new IllegalArgumentException("Invalid slot");
        this.number = ++nextNumber;
        this.vehicle = Objects.requireNonNull(vehicle);
        this.floor = floor; this.slot = slot; this.entry = Objects.requireNonNull(entry);
    }
    public String toString() {
        return "Ticket#" + number + " " + vehicle.getRegistration() + " floor=" + floor + " slot=" + slot + " entry=" + entry;
    }
}
public class Task04TicketConstructors {
    public static void main(String[] args) {
        System.out.println(new TaskTicket(new TicketVehicle("TN01AA1111"), 0, 2));
        System.out.println(new TaskTicket(new TicketVehicle("TN02BB2222"), 1, 0, LocalDateTime.of(2026, 11, 17, 9, 0)));
    }
}