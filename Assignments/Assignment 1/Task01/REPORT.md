# Task 1 Report – Problem Analysis and Object Identification

## Aim
Analyse the Campus Smart-Parking System and identify the objects required to model its operations.

## Candidate objects and responsibilities
- **ParkingVehicle:** stores registration and vehicle category.
- **ParkingVisit / ParkingTicket:** associates a vehicle and user with its entry time and parking visit.
- **ParkingUser:** identifies a student, staff member or visitor and their pass eligibility.
- **ParkingFacility:** represents floors and slots and controls allocation and release.
- **ParkingSystem:** coordinates entry, ticket issue, fee calculation and exit.
- **Vehicle subclasses / pricing policy:** encapsulate vehicle-specific fee behaviour.

## Business rules
1. A slot may contain at most one vehicle.
2. A ticket identifier must be unique and must not be reused.
3. A vehicle cannot have two active parking visits.
4. Exit must refer to an active ticket and cannot precede entry.
5. A successful exit releases the occupied slot.
6. Students and staff with valid monthly passes park free; visitors pay the applicable fee.
7. EV charging is charged separately.

## Assumptions
The facility has a known capacity per floor. Registration numbers are non-empty strings. Fee rates and any slab limits must be taken from the faculty's tariff; the brief does not specify numeric rates.

## Alternative: objects vs parallel arrays
Both require O(n) search if scanned linearly. Objects group registration, type and time in one record; adding an attribute means adding a field. Parallel arrays require coordinated index updates and another array for every new attribute, risking mismatched records. Both use O(n) storage; arrays may have lower per-record overhead, while objects provide clearer encapsulation.

## Program
`Task01ProblemModel.java` models a vehicle and a parking visit and prints their association.

## Conclusion
A domain-object design is easier to validate and extend than parallel arrays.

## Source file
`Task01ProblemModel.java`
