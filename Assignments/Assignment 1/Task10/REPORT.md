# Task 10 Report – Encapsulation

## Aim
Protect object state using access modifiers and validating methods.

## Implementation
The slot number and vehicle registration are private. The setter rejects null and negative slot numbers. The park method rejects blank registrations and prevents a second vehicle from occupying an already occupied slot.

## Access modifiers
- `private`: internal state and helper methods.
- `public`: supported API.
- `protected`: intended subclass extension points.
- package-private: implementation details shared only inside a package.

## Wrappers and autoboxing
Collections of generic values use wrapper types such as Integer. Java autoboxes an int to Integer. Compare wrappers using `equals()` rather than relying on reference identity with `==`. Unboxing a null wrapper can throw NullPointerException.

## Alternative: public fields
Public mutable fields allow callers to bypass validation and create invalid state. Encapsulation centralizes checks. Validation remains O(1) per update, but maintenance is simpler because the rule is implemented once.

## Conclusion
Private state and validated methods preserve the slot's invariants.

## Source file
`Task10Encapsulation.java`
