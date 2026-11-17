# Task 7 – Apply Method Overriding

Subclasses override calculateFee when their billing differs. @Override asks the compiler to verify the override. Name and parameters must match; return type must be identical or covariant; visibility cannot be reduced; checked exceptions cannot be broadened.

final suits immutable registration/ticket identifiers and classes whose invariants must not be changed. Do not mark extension points final when new vehicle types must override them.

toString returns a useful representation. equals and hashCode must use the same identity rule. The supplied Vehicle compares registration only, so Car("TN02B2").equals(ElectricCar("TN02B2")) is true. This is appropriate only if registration globally identifies the vehicle independent of subtype. If type is part of identity, include concrete type in both methods.

Alternative switch: one calculateFee(type, minutes) uses a switch over k types. Straightforward worst-case O(k), while virtual dispatch is effectively O(1). Adding a type modifies the central switch and tests; subclass overriding generally adds a class without changing existing implementations.