/** Task 12: e-bike flat fee, delivery-van daytime double rate, visitor day pass and unequal floors. */
import java.time.*;
class NewVehicle {
    final String registration;
    NewVehicle(String registration) { this.registration = registration; }
}
class EBike extends NewVehicle {
    EBike(String registration) { super(registration); }
    double parkingFee() { return 40.0; } // illustrative flat fee; confirm tariff with faculty
}
class DeliveryVan extends NewVehicle {
    DeliveryVan(String registration) { super(registration); }
    double fee(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start)) throw new IllegalArgumentException("Exit before entry");
        long total = Duration.between(start, end).toMinutes(), day = 0;
        for (LocalDate d = start.toLocalDate(); !d.isAfter(end.toLocalDate()); d = d.plusDays(1)) {
            LocalDateTime a = d.atTime(9, 0), b = d.atTime(17, 0);
            LocalDateTime x = start.isAfter(a) ? start : a, y = end.isBefore(b) ? end : b;
            if (y.isAfter(x)) day += Duration.between(x, y).toMinutes();
        }
        long other = total - day;
        return (day * 60.0 + other * 30.0) / 60.0; // car rate Rs30/hour; double during 9–5
    }
}
class VisitorDayPass {
    private final LocalDate validDate;
    VisitorDayPass(LocalDate validDate) { this.validDate = validDate; }
    boolean isValidAt(LocalDateTime time) { return validDate.equals(time.toLocalDate()); }
}
public class Task12DesignChallenge {
    public static void main(String[] args) {
        int[] slotsPerFloor = {2, 3, 1, 4, 2};
        String[][] floors = new String[slotsPerFloor.length][];
        for (int i = 0; i < slotsPerFloor.length; i++) floors[i] = new String[slotsPerFloor[i]];
        LocalDate date = LocalDate.of(2026, 11, 17);
        System.out.println("Floors=" + floors.length + ", total slots=12");
        System.out.println("E-bike flat fee=Rs " + new EBike("EB01").parkingFee());
        System.out.println("Van 09:00-10:00 fee=Rs " + new DeliveryVan("DV01").fee(date.atTime(9,0), date.atTime(10,0)));
        System.out.println("Visitor day pass valid=" + new VisitorDayPass(date).isValidAt(date.atTime(12,0)));
    }
}