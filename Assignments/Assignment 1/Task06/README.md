# Task 6 – Design the Inheritance Hierarchy

Vehicle is the abstract superclass. TwoWheeler, Car and DeliveryVan extend it; ElectricCar extends Car. This combines hierarchical inheritance from Vehicle with multilevel inheritance Vehicle -> Car -> ElectricCar. ElectricCar also implements Chargeable.

Parking users can use an enum (STUDENT, STAFF, VISITOR) when category differences are data/pass rules only. Separate subclasses are needed only if they gain distinct behaviour.

Constructor trace for new ElectricCar(regNo): ElectricCar constructor starts -> Car constructor -> Vehicle constructor validates/stores registration -> Vehicle completes -> Car completes -> ElectricCar completes. super(regNo) forwards registration. Private fields are accessed through getters.

UML (text):
Vehicle (abstract)
  |-- TwoWheeler
  |-- Car
  |     |-- ElectricCar implements Chargeable
  |-- DeliveryVan
EBike extends Vehicle and implements Chargeable.

Alternative composition: one Vehicle containing a VehicleType pricing strategy avoids subclass proliferation. Adding a type means adding a strategy rather than changing Vehicle. Inheritance suits stable is-a categories; composition suits independently changing pricing. Both have O(1) dispatch; composition adds a strategy reference/object.