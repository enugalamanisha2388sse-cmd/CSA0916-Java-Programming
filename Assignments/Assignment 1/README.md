# Assignment 1 – Campus Smart-Parking System

**Course outcome:** CO1 – Java fundamentals and object-oriented programming.

## Files
- `ParkingFacility.java` – jagged-array slot allocation and release; includes the completed allocation exercise.
- `Main.java` – integrated object-oriented model and demonstration. It includes vehicles, inheritance, overriding, polymorphism, the `Chargeable` interface, encapsulated ticket/user data, a static ticket counter, and an active-ticket `HashMap`.
- `FeeDemo` is included in `Main.java` to demonstrate the assignment's polymorphism/interface example.

## Requirements
- JDK 17 or later (the source uses modern `instanceof` pattern matching).
- Run commands from this directory.

## Compile and run
```bash
javac ParkingFacility.java Main.java
java ParkingFacility
java Main
java Main fee-demo
```

## FeeDemo expected output
```text
TwoWheeler TN01A1 -> Rs 30.0
Car TN02B2 -> Rs 90.0
ElectricCar TN03C3 -> Rs 210.0
true
```

## Design notes
- A jagged `String[][]` represents floors with different slot counts.
- Slot allocation scans floor-by-floor and slot-by-slot: worst-case **O(N)** time for N total slots; release by known coordinates is **O(1)**.
- The integrated system uses a `HashMap<String, ParkingTicket>` for average **O(1)** ticket lookup by registration number, with **O(n)** storage for n active tickets.
- Vehicle subclasses override fee behaviour; `Chargeable` models the optional charging capability.
- Registration numbers are normalized to uppercase and used for equality and hash codes.

## Task coverage
This code provides runnable implementations for the programming portions of the brief. The written analysis, pseudocode, UML diagram, detailed test evidence/screenshots, and complexity comparisons requested in Tasks 1–12 should be completed in the assignment report and verified by the student. Do not submit unexecuted code: compile, run, test boundary cases, and capture your own output screenshots before submission.

## Important assumptions in this implementation
- Parking fees are rounded up to started hours.
- The sample system charges a fixed 10 kWh charging amount when an ElectricCar exits; replace this demonstration assumption with the actual charging input/rule if your faculty specifies one.
- E-bike parking uses a flat Rs 40 per visit.
- Delivery-van pricing is represented as an hourly calculation with double rate for hours beginning between 09:00 and 16:59. Refine this calculation if the required policy prorates partial hours or handles boundary overlap differently.
- Monthly and one-day passes are represented as user flags for demonstration; validity dates and pass payment processing are outside this sample.

Review these assumptions with the assignment faculty and be prepared to explain or adjust them.
