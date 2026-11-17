# Task 3 – Design the Control Logic

## Allocation pseudocode
ALLOCATE(vehicleNo)
1. Reject null or blank registration.
2. For each floor f from 0 to floor count - 1:
   For each slot s from 0 to slots on floor f - 1:
   If slots[f][s] is null, store vehicleNo and return (f,s).
3. Return FULL.

## Fee pseudocode (started-hour example)
CALCULATE_FEE(vehicle, elapsedMinutes, user)
1. Reject negative elapsedMinutes.
2. If user has eligible valid pass, return 0.
3. billableHours = (elapsedMinutes + 59) DIV 60.
4. Return billableHours * vehicle.ratePerHour.

The PDF mentions possible first-hour, additional-hour and daily-maximum slabs but supplies no numeric values. Confirm these with faculty rather than inventing them.

ParkingFacility.java completes the supplied TODO methods. Expected output: [0, 0], [0, 1], [1, 0], null, true false false, [0, 1].

## Alternative free-slot stack
Maintain a stack of free slot coordinates per floor. Pop/push is O(1); finding a non-empty floor by scanning counts is O(F), or O(1) with a separate stack of non-empty floors. Extra storage is O(F*S), same order as slot storage, plus metadata. The simple scan has O(F*S) worst-case allocation and O(1) release by known coordinates.