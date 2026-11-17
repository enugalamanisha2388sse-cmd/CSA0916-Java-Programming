# Task 7 Report – Method Overriding

## Aim
Demonstrate subclass-specific fee rates and override Object methods.

## Implementation
The abstract vehicle class declares `ratePerHour()`. Car and Bike override it with their own rate. The `@Override` annotation lets the compiler verify the method signature.

## Overriding rules
The method name and parameter list must match. The return type must be identical or covariant. Visibility cannot be reduced, and checked exceptions cannot be broadened.

## final and Object methods
The registration getter is final to protect the identity contract. `equals()` and `hashCode()` use the same registration-number identity. `toString()` provides a readable representation.

## Alternative: switch
A central switch over k vehicle categories can take O(k) checks in the worst case and must be edited when a category is added. Overriding uses dynamic dispatch in effectively O(1) time and keeps the behaviour with each class.

## Conclusion
Overriding keeps vehicle-specific behaviour localized and maintainable.

## Source file
`Task07Overriding.java`
