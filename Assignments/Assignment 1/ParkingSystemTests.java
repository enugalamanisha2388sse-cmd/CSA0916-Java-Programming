import java.time.LocalDate;
import java.time.LocalDateTime;

/** Dependency-free regression tests for the Campus Smart-Parking System. */
public class ParkingSystemTests {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        test("allocation and release", () -> {
            ParkingFacility p = new ParkingFacility(new int[] {2, 1});
            check("[0, 0]".equals(java.util.Arrays.toString(p.allocate("A1"))), "first slot");
            check("[0, 1]".equals(java.util.Arrays.toString(p.allocate("A2"))), "second slot");
            check("[1, 0]".equals(java.util.Arrays.toString(p.allocate("A3"))), "next floor");
            check(p.allocate("A4") == null, "full facility");
            check(p.release(0, 1), "release occupied");
            check(!p.release(0, 1), "cannot release twice");
            check(!p.release(9, 0), "invalid floor");
        });
        test("started-hour billing", () -> {
            check(new Car("TN01").calculateFee(0) == 0.0, "zero minutes");
            check(new Car("TN01").calculateFee(60) == 30.0, "exact hour");
            check(new Car("TN01").calculateFee(61) == 60.0, "partial next hour rounds up");
            check(new Car("TN01").calculateFee(130) == 90.0, "130 minutes");
        });
        test("electric charging is explicit", () -> {
            ElectricCar ev = new ElectricCar("EV01");
            check(ev.calculateFee(60) == 60.0, "EV parking rate");
            check(ev.chargingFee(10) == 30.0, "charging fee");
        });
        test("e-bike flat parking rate", () -> {
            EBike bike = new EBike("EB01");
            check(bike.calculateFee(1) == 40.0, "flat fee");
            check(bike.calculateFee(900) == 40.0, "flat fee independent of duration");
        });
        test("delivery van daytime overlap pricing", () -> {
            DeliveryVan van = new DeliveryVan("DV01");
            LocalDate d = LocalDate.of(2026, 1, 1);
            check(van.calculateFee(d.atTime(9, 0), d.atTime(10, 0)) == 60.0, "daytime double rate");
            check(van.calculateFee(d.atTime(8, 0), d.atTime(9, 0)) == 30.0, "night rate");
            check(van.calculateFee(d.atTime(8, 30), d.atTime(9, 30)) == 45.0, "partial overlap");
        });
        test("visitor one-day pass", () -> {
            ParkingUser visitor = new ParkingUser("V1", UserCategory.VISITOR, false);
            LocalDate d = LocalDate.of(2026, 1, 1);
            visitor.buyOneDayPass(d);
            check(visitor.hasValidPass(d.atStartOfDay()), "valid on purchased date");
            check(!visitor.hasValidPass(d.plusDays(1).atStartOfDay()), "expires next day");
        });
        test("monthly pass eligibility", () -> {
            LocalDateTime now = LocalDateTime.of(2026, 1, 1, 12, 0);
            ParkingUser student = new ParkingUser("S1", UserCategory.STUDENT, true);
            ParkingUser visitor = new ParkingUser("V2", UserCategory.VISITOR, true);
            check(student.hasValidPass(now), "student monthly pass");
            check(!visitor.hasValidPass(now), "visitor monthly pass not eligible");
        });
        test("integrated entry and exit", () -> {
            CampusParkingSystem system = new CampusParkingSystem(new int[] {1, 1});
            system.registerUser(new ParkingUser("VIS", UserCategory.VISITOR, false));
            system.park(new Car("TN10"), "VIS", LocalDateTime.of(2026, 1, 1, 10, 0));
            boolean duplicateRejected = false;
            try {
                system.park(new Car("TN10"), "VIS", LocalDateTime.of(2026, 1, 1, 10, 1));
            } catch (IllegalArgumentException expected) { duplicateRejected = true; }
            check(duplicateRejected, "duplicate registration rejected");
            double fee = system.exit("TN10", LocalDateTime.of(2026, 1, 1, 11, 0));
            check(fee == 30.0, "one-hour car fee");
            check(system.getOccupiedSlots() == 0, "slot released after exit");
        });
        System.out.println("RESULT: " + passed + " passed, " + failed + " failed");
        if (failed > 0) throw new AssertionError("Some tests failed.");
    }

    private static void test(String name, Runnable body) {
        try {
            body.run();
            passed++;
            System.out.println("PASS: " + name);
        } catch (Throwable error) {
            failed++;
            System.out.println("FAIL: " + name + " - " + error.getMessage());
        }
    }

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
