# Task 8 Report – Polymorphism and Completed FeeDemo

## Aim
Demonstrate compile-time and runtime polymorphism and complete the supplied FeeDemo.

## Compile-time polymorphism
Method overloading provides methods with the same name but different parameter lists. The compiler selects the matching overload from the declared argument types.

## Runtime polymorphism
A `Vehicle` reference may refer to a `TwoWheeler`, `Car` or `ElectricCar`. Calling `calculateFee(130)` dispatches to the actual object's rate implementation. The interface check adds the charging fee for a chargeable vehicle.

## Integer-only started-hour calculation
The required ceiling division is `(minutes + 59) / 60` for non-negative minutes. Thus 130 minutes produces 3 billable hours.

## Expected output
The assignment brief specifies:
```text
TwoWheeler TN01A1 -> Rs 30.0
Car TN02B2 -> Rs 90.0
ElectricCar TN03C3 -> Rs 210.0
true
```
The last line is true because equality compares registration numbers only. This is suitable only if registration uniquely identifies a vehicle regardless of subtype.

## Conclusion
Runtime polymorphism avoids a growing chain of vehicle-type conditionals.

## Source file
`FeeDemo.java`
