/** Task 7: overriding, final fields, equals, hashCode and toString. */
abstract class OverrideVehicle {
    private final String regNo;
    OverrideVehicle(String regNo) { this.regNo = regNo; }
    final String getRegNo() { return regNo; }
    abstract double ratePerHour();
    @Override public final boolean equals(Object other) {
        return other instanceof OverrideVehicle v && regNo.equals(v.regNo);
    }
    @Override public final int hashCode() { return regNo.hashCode(); }
    @Override public String toString() { return getClass().getSimpleName() + "[" + regNo + "]"; }
}
class OverrideCar extends OverrideVehicle {
    OverrideCar(String regNo) { super(regNo); }
    @Override double ratePerHour() { return 30.0; }
}
class OverrideBike extends OverrideVehicle {
    OverrideBike(String regNo) { super(regNo); }
    @Override double ratePerHour() { return 10.0; }
}
public class Task07Overriding {
    public static void main(String[] args) {
        OverrideVehicle car = new OverrideCar("TN02B2");
        System.out.println(car + " rate=" + car.ratePerHour());
        System.out.println(car.equals(new OverrideCar("TN02B2")));
    }
}