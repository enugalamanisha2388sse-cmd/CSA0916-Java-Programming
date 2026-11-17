# Task 3 Report – Control Logic and Completed Code

## Aim
Design the allocation and fee logic and complete the supplied `ParkingFacility` class.

## Allocation pseudocode
1. Validate the vehicle number.
2. Visit each floor in order.
3. Visit each slot in that floor.
4. If a slot is null, store the registration and return its floor and slot indices.
5. If no free slot exists, return null.

## Release pseudocode
1. Check that floor and slot indices are within bounds.
2. If the selected slot is already null, return false.
3. Otherwise set it to null and return true.

## Fee pseudocode
1. Reject a negative duration.
2. Apply the valid student/staff monthly-pass rule.
3. Convert duration into billable units using the tariff's rounding rule.
4. Apply the applicable vehicle rate and any daily maximum.

## Completed code
The source file implements both TODO methods and includes the original-style demonstration main method.

## Alternative: free-slot stack
A stack of available slot coordinates can make allocation and release O(1) per operation, with O(S) extra coordinate storage. The simple scan uses O(S) worst-case allocation time and O(1) release when coordinates are known. Per-floor free counts can help identify full floors.

## Conclusion
The supplied test sequence demonstrates first-free-slot allocation, full capacity, valid release, duplicate release and invalid indices.

## Source file
`ParkingFacility.java`
