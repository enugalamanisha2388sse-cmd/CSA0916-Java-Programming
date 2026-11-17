/** Task 1: candidate domain objects and their responsibilities. */
import java.time.LocalDateTime;
enum UserKind { STUDENT, STAFF, VISITOR }
final class ParkingVehicle {
    final String registration;
    final String type;
    ParkingVehicle(String registration, String type) {
        if (registration == null || registration.isBlank()) throw new IllegalArgumentException("Registration required");
        this.registration = registration;
        this.type = type;
    }
}
final class ParkingVisit {
    final ParkingVehicle vehicle;
    final String userId;
    final LocalDateTime entryTime;
    ParkingVisit(ParkingVehicle vehicle, String userId, LocalDateTime entryTime) {
        this.vehicle = vehicle; this.userId = userId; this.entryTime = entryTime;
    }
}
public class Task01ProblemModel {
    public static void main(String[] args) {
        ParkingVehicle v = new ParkingVehicle("TN01AB1234", "CAR");
        ParkingVisit visit = new ParkingVisit(v, "STU001", LocalDateTime.now());
        System.out.println(visit.vehicle.registration + " " + visit.vehicle.type + " " + visit.userId);
    }
}