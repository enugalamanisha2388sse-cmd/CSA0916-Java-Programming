import java.util.Arrays;

/**
 * Jagged-array parking layout: each floor may have a different number of slots.
 * A null slot is free; a non-null slot stores the vehicle registration number.
 */
public class ParkingFacility {
    private final String[][] slots;

    public ParkingFacility(int[] slotsPerFloor) {
        if (slotsPerFloor == null || slotsPerFloor.length == 0) {
            throw new IllegalArgumentException("At least one floor is required.");
        }
        slots = new String[slotsPerFloor.length][];
        for (int floor = 0; floor < slotsPerFloor.length; floor++) {
            if (slotsPerFloor[floor] < 0) {
                throw new IllegalArgumentException("Slot count cannot be negative.");
            }
            slots[floor] = new String[slotsPerFloor[floor]];
        }
    }

    /** Returns {floor, slot} for the first free slot, or null when full. */
    public int[] allocate(String vehicleNo) {
        if (vehicleNo == null || vehicleNo.isBlank()) {
            throw new IllegalArgumentException("Vehicle number is required.");
        }
        for (int floor = 0; floor < slots.length; floor++) {
            for (int slot = 0; slot < slots[floor].length; slot++) {
                if (slots[floor][slot] == null) {
                    slots[floor][slot] = vehicleNo;
                    return new int[] { floor, slot };
                }
            }
        }
        return null;
    }

    /** Releases a valid occupied slot. */
    public boolean release(int floor, int slot) {
        if (floor < 0 || floor >= slots.length
                || slot < 0 || slot >= slots[floor].length
                || slots[floor][slot] == null) {
            return false;
        }
        slots[floor][slot] = null;
        return true;
    }

    public String vehicleAt(int floor, int slot) {
        if (floor < 0 || floor >= slots.length
                || slot < 0 || slot >= slots[floor].length) {
            throw new IndexOutOfBoundsException("Invalid floor or slot.");
        }
        return slots[floor][slot];
    }

    public int totalSlots() {
        int total = 0;
        for (String[] floor : slots) total += floor.length;
        return total;
    }

    public int occupiedSlots() {
        int occupied = 0;
        for (String[] floor : slots) {
            for (String vehicle : floor) if (vehicle != null) occupied++;
        }
        return occupied;
    }

    public static void main(String[] args) {
        ParkingFacility p = new ParkingFacility(new int[] { 2, 1 });
        System.out.println(Arrays.toString(p.allocate("TN01AB1234")));
        System.out.println(Arrays.toString(p.allocate("TN02CD5678")));
        System.out.println(Arrays.toString(p.allocate("TN03EF9012")));
        System.out.println(Arrays.toString(p.allocate("TN04GH4444")));
        System.out.println(p.release(0, 1) + " " + p.release(0, 1) + " "
                + p.release(5, 0));
        System.out.println(Arrays.toString(p.allocate("TN04GH4444")));
    }
}
