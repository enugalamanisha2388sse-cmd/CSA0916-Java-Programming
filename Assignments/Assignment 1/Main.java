import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** Entry point and integrated demonstration for the Campus Smart-Parking System. */
public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("fee-demo")) {
            FeeDemo.run();
            return;
        }

        CampusParkingSystem system = new CampusParkingSystem(new int[] { 3, 2, 4 });
        system.registerUser(new ParkingUser("STU001", UserCategory.STUDENT, true));
        system.registerUser(new ParkingUser("VIS001", UserCategory.VISITOR, false));

        ParkingTicket studentTicket = system.park(
                new Car("TN01AA1111"), "STU001", LocalDateTime.now().minusHours(2));
        ParkingTicket visitorTicket = system.park(
                new ElectricCar("TN02BB2222"), "VIS001", LocalDateTime.now().minusHours(3));

        System.out.println("Student ticket: " + studentTicket);
        System.out.println("Visitor ticket: " + visitorTicket);
        System.out.println("Student exit fee: Rs " +
                system.exit("TN01AA1111", LocalDateTime.now()));
        System.out.println("Visitor exit fee: Rs " +
                system.exit("TN02BB2222", LocalDateTime.now()));
        System.out.println("Occupied slots: " + system.getOccupiedSlots());
    }
}

enum UserCategory { STUDENT, STAFF, VISITOR }

final class ParkingUser {
    private final String userId;
    private final UserCategory category;
    private final boolean hasMonthlyPass;
    private LocalDate oneDayPassDate;

    ParkingUser(String userId, UserCategory category, boolean hasMonthlyPass) {
        if (userId == null || userId.isBlank()) throw new IllegalArgumentException("User ID required.");
        this.userId = userId;
        this.category = Objects.requireNonNull(category);
        this.hasMonthlyPass = hasMonthlyPass;
    }

    String getUserId() { return userId; }
    UserCategory getCategory() { return category; }
    boolean hasValidPass(LocalDateTime at) {
        boolean monthlyEligible = category == UserCategory.STUDENT || category == UserCategory.STAFF;
        return (monthlyEligible && hasMonthlyPass)
                || (category == UserCategory.VISITOR && oneDayPassDate != null
                    && oneDayPassDate.equals(at.toLocalDate()));
    }
    void buyOneDayPass(LocalDate date) {
        if (category != UserCategory.VISITOR)
            throw new IllegalStateException("One-day pass is reserved for visitors.");
        oneDayPassDate = Objects.requireNonNull(date);
    }
}

interface Chargeable {
    double chargingFee(int kWh);
}

abstract class Vehicle {
    private final String regNo;

    protected Vehicle(String regNo) {
        if (regNo == null || regNo.isBlank()) throw new IllegalArgumentException("Registration number required.");
        this.regNo = regNo.trim().toUpperCase();
    }

    public String getRegNo() { return regNo; }
    protected abstract double ratePerHour();

    /** Every started hour is charged in full. */
    public double calculateFee(int minutes) {
        if (minutes < 0) throw new IllegalArgumentException("Duration cannot be negative.");
        int billableHours = (minutes + 59) / 60;
        return billableHours * ratePerHour();
    }

    @Override public boolean equals(Object other) {
        return other instanceof Vehicle vehicle && regNo.equals(vehicle.regNo);
    }
    @Override public int hashCode() { return regNo.hashCode(); }
    @Override public String toString() { return getClass().getSimpleName() + "[" + regNo + "]"; }
}

final class TwoWheeler extends Vehicle {
    TwoWheeler(String regNo) { super(regNo); }
    @Override protected double ratePerHour() { return 10.0; }
}

class Car extends Vehicle {
    Car(String regNo) { super(regNo); }
    @Override protected double ratePerHour() { return 30.0; }
}

class ElectricCar extends Car implements Chargeable {
    ElectricCar(String regNo) { super(regNo); }
    @Override protected double ratePerHour() { return 60.0; }
    @Override public double chargingFee(int kWh) {
        if (kWh < 0) throw new IllegalArgumentException("Energy cannot be negative.");
        return kWh * 3.0;
    }
}

final class EBike extends Vehicle implements Chargeable {
    EBike(String regNo) { super(regNo); }
    @Override protected double ratePerHour() { return 25.0; }
    /** E-bike uses a flat parking rate per visit; charging is billed separately. */
    @Override public double calculateFee(int minutes) {
        if (minutes < 0) throw new IllegalArgumentException("Duration cannot be negative.");
        return 40.0;
    }
    @Override public double chargingFee(int kWh) {
        if (kWh < 0) throw new IllegalArgumentException("Energy cannot be negative.");
        return kWh * 2.0;
    }
}

final class DeliveryVan extends Vehicle {
    DeliveryVan(String regNo) { super(regNo); }
    @Override protected double ratePerHour() { return 30.0; }

    /** Bills the car rate normally and double the car rate only for minutes overlapping 09:00–17:00. */
    double calculateFee(LocalDateTime entry, LocalDateTime exit) {
        if (entry == null || exit == null || exit.isBefore(entry))
            throw new IllegalArgumentException("Invalid parking interval.");
        long totalMinutes = Duration.between(entry, exit).toMinutes();
        long daytimeMinutes = 0;
        LocalDate date = entry.toLocalDate();
        while (!date.isAfter(exit.toLocalDate())) {
            LocalDateTime windowStart = date.atTime(LocalTime.of(9, 0));
            LocalDateTime windowEnd = date.atTime(LocalTime.of(17, 0));
            LocalDateTime overlapStart = entry.isAfter(windowStart) ? entry : windowStart;
            LocalDateTime overlapEnd = exit.isBefore(windowEnd) ? exit : windowEnd;
            if (overlapEnd.isAfter(overlapStart))
                daytimeMinutes += Duration.between(overlapStart, overlapEnd).toMinutes();
            date = date.plusDays(1);
        }
        long nightMinutes = totalMinutes - daytimeMinutes;
        return (daytimeMinutes * 60.0 + nightMinutes * 30.0) / 60.0;
    }
}

final class ParkingTicket {
    private static long nextTicketNumber = 1000;
    private final long ticketNumber;
    private final Vehicle vehicle;
    private final ParkingUser user;
    private final int floor;
    private final int slot;
    private final LocalDateTime entryTime;

    ParkingTicket(Vehicle vehicle, ParkingUser user, int floor, int slot, LocalDateTime entryTime) {
        this.ticketNumber = ++nextTicketNumber;
        this.vehicle = Objects.requireNonNull(vehicle);
        this.user = Objects.requireNonNull(user);
        this.floor = floor;
        this.slot = slot;
        this.entryTime = Objects.requireNonNull(entryTime);
    }

    Vehicle getVehicle() { return vehicle; }
    ParkingUser getUser() { return user; }
    int getFloor() { return floor; }
    int getSlot() { return slot; }
    LocalDateTime getEntryTime() { return entryTime; }

    @Override public String toString() {
        return "Ticket#" + ticketNumber + " " + vehicle + " floor=" + floor + " slot=" + slot;
    }
}

final class CampusParkingSystem {
    private final ParkingFacility facility;
    private final Map<String, ParkingTicket> activeTickets = new HashMap<>();
    private final Map<String, ParkingUser> users = new HashMap<>();

    CampusParkingSystem(int[] slotsPerFloor) {
        facility = new ParkingFacility(slotsPerFloor);
    }

    void registerUser(ParkingUser user) {
        users.put(user.getUserId(), user);
    }

    ParkingTicket park(Vehicle vehicle, String userId, LocalDateTime entryTime) {
        Objects.requireNonNull(vehicle);
        ParkingUser user = users.get(userId);
        if (user == null) throw new IllegalArgumentException("Unknown user: " + userId);
        String regNo = vehicle.getRegNo();
        if (activeTickets.containsKey(regNo)) throw new IllegalArgumentException("Vehicle is already parked.");
        int[] position = facility.allocate(regNo);
        if (position == null) throw new IllegalStateException("Parking facility is full.");
        ParkingTicket ticket = new ParkingTicket(vehicle, user, position[0], position[1], entryTime);
        activeTickets.put(regNo, ticket);
        return ticket;
    }

    double exit(String regNo, LocalDateTime exitTime) {
        return exit(regNo, exitTime, 0);
    }

    /** Exit overload accepts the actual energy supplied to an EV charger. */
    double exit(String regNo, LocalDateTime exitTime, int chargingKWh) {
        if (chargingKWh < 0) throw new IllegalArgumentException("Charging energy cannot be negative.");
        ParkingTicket ticket = activeTickets.get(regNo.toUpperCase());
        if (ticket == null) throw new IllegalArgumentException("No active ticket for vehicle.");
        if (exitTime.isBefore(ticket.getEntryTime())) throw new IllegalArgumentException("Exit precedes entry.");
        long minutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
        double fee;
        if (ticket.getUser().hasValidPass(exitTime)) {
            fee = 0.0;
        } else if (ticket.getVehicle() instanceof DeliveryVan van) {
            fee = van.calculateFee(ticket.getEntryTime(), exitTime);
        } else {
            fee = ticket.getVehicle().calculateFee(Math.toIntExact(minutes));
        }
        if (ticket.getVehicle() instanceof Chargeable chargeable) {
            fee += chargeable.chargingFee(chargingKWh);
        }
        if (!facility.release(ticket.getFloor(), ticket.getSlot()))
            throw new IllegalStateException("Could not release the assigned slot.");
        activeTickets.remove(regNo.toUpperCase());
        return fee;
    }

    int getOccupiedSlots() { return facility.occupiedSlots(); }
}

/** The assignment's polymorphism/interface demonstration and expected sample output. */
final class FeeDemo {
    private FeeDemo() { }

    static void run() {
        Vehicle[] vehicles = {
            new TwoWheeler("TN01A1"),
            new Car("TN02B2"),
            new ElectricCar("TN03C3")
        };
        for (Vehicle vehicle : vehicles) {
            double fee = vehicle.calculateFee(130);
            if (vehicle instanceof Chargeable chargeable) fee += chargeable.chargingFee(10);
            System.out.println(vehicle.getClass().getSimpleName() + " " + vehicle.getRegNo()
                    + " -> Rs " + fee);
        }
        System.out.println(new Car("TN02B2").equals(new ElectricCar("TN02B2")));
    }
}
