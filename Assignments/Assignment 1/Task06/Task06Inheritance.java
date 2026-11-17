/** Task 6: vehicle inheritance and constructor execution order. */
abstract class BaseVehicle {
    private final String regNo;
    BaseVehicle(String regNo) { this.regNo = regNo; System.out.println("Vehicle constructor"); }
    String getRegNo() { return regNo; }
    abstract double rate();
}
class BaseCar extends BaseVehicle {
    BaseCar(String regNo) { super(regNo); System.out.println("Car constructor"); }
    double rate() { return 90.0; }
}
class TaskElectricCar extends BaseCar {
    TaskElectricCar(String regNo) { super(regNo); System.out.println("ElectricCar constructor"); }
}
public class Task06Inheritance {
    public static void main(String[] args) {
        TaskElectricCar ev = new TaskElectricCar("TN03C3");
        System.out.println(ev.getRegNo() + " rate=" + ev.rate());
    }
}