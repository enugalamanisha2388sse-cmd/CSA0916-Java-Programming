# Task 9 – Use Abstract Classes and Interfaces

Vehicle is abstract because vehicles share stored registration state, constructor validation and common fee logic. Chargeable is an interface because charging is a capability shared by otherwise unrelated classes such as ElectricCar and EBike.

A class may extend one class and implement multiple interfaces. An interface default method can supply shared optional behaviour; a static interface method can provide related utilities.

Alternative: make Vehicle an interface and Chargeable an abstract class. Vehicle interface cannot provide ordinary instance constructors or inherited instance state in the same way; implementations must store registration themselves. Making Chargeable an abstract class prevents ElectricCar from extending both Car and Chargeable because Java has single class inheritance. A capability is therefore better represented by an interface.

Method dispatch remains effectively O(1). Object storage is similar, but removing the abstract base may duplicate fields in each concrete vehicle.