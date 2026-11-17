/** Task 9: abstract class for shared state; interface for a capability. */
interface Payable {
    double paymentAmount();
    default boolean paymentRequired() { return paymentAmount() > 0; }
    static boolean validAmount(double amount) { return amount >= 0; }
}
abstract class AbstractParkingUser {
    private final String id;
    AbstractParkingUser(String id) { this.id = id; }
    String getId() { return id; }
}
class VisitorUser extends AbstractParkingUser implements Payable {
    private final double fee;
    VisitorUser(String id, double fee) {
        super(id);
        if (!Payable.validAmount(fee)) throw new IllegalArgumentException("Invalid fee");
        this.fee = fee;
    }
    public double paymentAmount() { return fee; }
}
public class Task09AbstractInterface {
    public static void main(String[] args) {
        Payable visitor = new VisitorUser("VIS001", 120.0);
        System.out.println("Payment required=" + visitor.paymentRequired());
        System.out.println("Amount=" + visitor.paymentAmount());
    }
}