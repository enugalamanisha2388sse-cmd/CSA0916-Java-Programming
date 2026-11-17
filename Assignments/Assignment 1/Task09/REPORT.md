# Task 9 Report – Abstract Classes and Interfaces

## Aim
Choose an abstract class for shared state and an interface for a capability.

## Design
An abstract user class stores common user identity. The `Payable` interface describes the payment capability and includes a default method and a static validation method.

## Why an interface?
A class can extend only one class but can implement multiple interfaces. Capabilities such as Chargeable or Payable can therefore be shared by otherwise unrelated classes.

## Alternative
Changing Vehicle to an interface means common instance state and constructor initialization must be implemented separately by each class. Changing Chargeable to an abstract class prevents a class that already extends Car from also extending that abstract class. The interface is the better fit for an optional capability.

## Complexity
Capability dispatch is effectively O(1). Object storage is O(1) per user/vehicle object; interface implementation adds no separate mandatory object.

## Conclusion
Use abstract classes for shared state and interfaces for independent behaviours.

## Source file
`Task09AbstractInterface.java`
