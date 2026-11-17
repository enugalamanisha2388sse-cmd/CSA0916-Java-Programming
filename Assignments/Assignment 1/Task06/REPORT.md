# Task 6 Report – Inheritance Hierarchy

## Aim
Model vehicle categories using Java inheritance and trace constructor execution.

## Hierarchy
`BaseVehicle` is the abstract superclass. `BaseCar` extends it, and `TaskElectricCar` extends `BaseCar`. This demonstrates multilevel inheritance. A full design can also use hierarchical inheritance for two-wheelers and delivery vehicles.

## Inherited and added behaviour
The base class stores the registration and declares the rate contract. Car supplies a car rate. ElectricCar inherits the car implementation and can add charging capability through an interface.

## Constructor trace
Creating an electric car calls its constructor; `super(regNo)` invokes the car constructor; the car constructor invokes the vehicle constructor. The base constructor completes first, followed by Car and then ElectricCar.

## Alternative: composition
A Vehicle containing a VehicleType/pricing strategy avoids creating a subclass for every pricing variation. Inheritance expresses a stable is-a relationship; composition is more flexible when pricing rules change independently. Both dispatch in effectively O(1) time; composition adds a strategy reference.

## Conclusion
Inheritance is appropriate for stable vehicle categories; interfaces model cross-cutting capabilities such as charging.

## Source file
`Task06Inheritance.java`
