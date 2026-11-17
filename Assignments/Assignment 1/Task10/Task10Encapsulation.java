/** Task 10: private state, validating methods, wrappers and autoboxing. */
class EncapsulatedSlot {
    private Integer slotNumber;
    private String vehicleNo;
    EncapsulatedSlot(Integer slotNumber) { setSlotNumber(slotNumber); }
    public Integer getSlotNumber() { return slotNumber; }
    public void setSlotNumber(Integer value) {
        if (value == null || value < 0) throw new IllegalArgumentException("Slot must be non-negative");
        slotNumber = value;
    }
    public void park(String registration) {
        if (registration == null || registration.isBlank()) throw new IllegalArgumentException("Registration required");
        if (vehicleNo != null) throw new IllegalStateException("Slot occupied");
        vehicleNo = registration;
    }
    public String getVehicleNo() { return vehicleNo; }
}
public class Task10Encapsulation {
    public static void main(String[] args) {
        EncapsulatedSlot slot = new EncapsulatedSlot(3); // int autoboxes to Integer
        slot.park("TN10XY1234");
        Integer requested = 3;
        System.out.println(slot.getSlotNumber().equals(requested));
        System.out.println(slot.getVehicleNo());
    }
}