/** Task 2: Java data types and a jagged array for unequal floor capacities. */
import java.time.LocalDateTime;
import java.util.Arrays;
public class Task02DataRepresentation {
    public static void main(String[] args) {
        int floorNumber = 1, slotNumber = 3;
        String registration = "TN01AB1234";
        LocalDateTime entryTime = LocalDateTime.now();
        long durationMinutes = 130L;
        double fee = 90.0;
        String[][] slots = new String[3][];
        int[] capacity = {2, 3, 1};
        for (int f = 0; f < capacity.length; f++) slots[f] = new String[capacity[f]];
        slots[floorNumber][slotNumber - 1] = registration;
        long startedHours = (durationMinutes + 59) / 60;
        System.out.println("floor=" + floorNumber + ", slot=" + slotNumber);
        System.out.println(registration + " " + entryTime + " fee=" + fee);
        System.out.println("startedHours=" + startedHours);
        System.out.println(Arrays.deepToString(slots));
    }
}