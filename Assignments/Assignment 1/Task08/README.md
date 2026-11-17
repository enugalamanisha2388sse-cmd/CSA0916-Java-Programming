# Task 8 – Apply Polymorphism

Compile-time polymorphism: overload methods, for example calculateFee(int minutes) and calculateFee(int minutes, boolean includeCharging). The compiler selects based on declared argument types.

Runtime polymorphism: Vehicle v = new ElectricCar("TN03C3"); v.calculateFee(130). Declared reference is Vehicle; actual object is ElectricCar; runtime dispatch selects the most specific override (or inherited Car implementation).

This avoids a long chain of type checks in the caller.

FeeDemo expected output:
TwoWheeler TN01A1 -> Rs 30.0
Car TN02B2 -> Rs 90.0
ElectricCar TN03C3 -> Rs 210.0
true

The final true occurs because equals compares registration only. It is reasonable only if registration is the sole identity key.

Alternative instanceof chain checks up to k types and casts. Worst-case O(k); dynamic dispatch is effectively O(1) and keeps behaviour with the class.