# Assignment 1 – Campus Smart-Parking System

**Course outcome:** CO1 – Java fundamentals and object-oriented programming.

## Complete task-wise material
This assignment is organized into all twelve required task folders:
- [Task 01](Task01/README.md)
- [Task 02](Task02/README.md)
- [Task 03](Task03/README.md)
- [Task 04](Task04/README.md)
- [Task 05](Task05/README.md)
- [Task 06](Task06/README.md)
- [Task 07](Task07/README.md)
- [Task 08](Task08/README.md)
- [Task 09](Task09/README.md)
- [Task 10](Task10/README.md)
- [Task 11](Task11/README.md)
- [Task 12](Task12/README.md)

Each task folder contains its analysis, design reasoning, alternative concept and complexity discussion where requested. Task 3 includes allocation/fee pseudocode and the supplied code-completion exercise. Task 8 documents polymorphism and the supplied FeeDemo expected output. Task 11 provides the testing plan and data-structure comparison. Task 12 covers the Day-4 changed requirements.

## Source files
- `ParkingFacility.java` – jagged-array allocation/release implementation from Task 3.
- `Main.java` – integrated object-oriented system: users, passes, vehicle hierarchy, ticket lifecycle, active-ticket map, fee calculation and Task 12 categories.
- `VehicleFactory.java` – validates supported vehicle categories and rejects unknown types.
- `ParkingSystemTests.java` – dependency-free regression tests for allocation, rates, passes, duplicate entry and exit.
- `README.md` – this guide.

## Requirements and commands
JDK 17 or later. From this directory:
```bash
javac Main.java ParkingFacility.java VehicleFactory.java ParkingSystemTests.java
java ParkingFacility
java Main
java Main fee-demo
java ParkingSystemTests
```

## FeeDemo expected output
```text
TwoWheeler TN01A1 -> Rs 30.0
Car TN02B2 -> Rs 90.0
ElectricCar TN03C3 -> Rs 210.0
true
```

## Important policy assumptions
The PDF does not provide numeric standard vehicle rates or exact first-hour/additional-hour/daily-maximum slab values. The sample program therefore uses clearly labelled illustrative rates and started-hour billing; confirm the final tariff with faculty. EV charging energy is passed explicitly to the exit overload (`exit(regNo, exitTime, chargingKWh)`), rather than assuming a fixed amount. E-bike parking uses a flat illustrative Rs 40 per visit. Delivery vans are billed at double the car rate only for the actual minutes overlapping 09:00–17:00. Visitor one-day passes are valid only on their purchased date. Student/staff monthly passes are free; visitor monthly-pass flags do not waive fees.

## Validation status
A GitHub Actions workflow was added to compile and run the regression suite on pushes. Check the Actions tab for its run result. The latest GitHub Actions run has successfully completed compilation, the regression-test step, and the FeeDemo step. The workflow is available in the repository Actions tab. Add screenshots of your own execution before final academic submission. Add screenshots of your own executed output before final academic submission.

## Complexity summary
- Jagged-array allocation: O(total slots) worst-case; release by known coordinates: O(1); slot storage O(total slots).
- HashMap active-ticket lookup/insert/remove: average O(1), worst-case O(n); storage O(n).
- Vehicle fee calculation: O(1), except delivery-van date-window overlap calculation which is O(number of calendar days in the stay).
