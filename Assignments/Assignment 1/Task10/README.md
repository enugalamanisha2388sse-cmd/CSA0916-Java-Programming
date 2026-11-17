# Task 10 – Enforce Encapsulation through Packages and Access Modifiers

Recommended package structure:
src/parking/model: Vehicle, Car, ElectricCar, ParkingUser, ParkingTicket
src/parking/facility: ParkingFacility
src/parking/service: CampusParkingSystem
src/parking/app: Main

Use private fields, public constructors/business API, protected only for intended subclass hooks, and package-private implementation details. Getters expose read-only state. Setters validate before changing fields. Immutable identifiers are final and have no setter.

Wrapper classes appear in generic collections (Integer in ArrayList<Integer>). Autoboxing converts int and Integer; a null Integer can throw NullPointerException during unboxing. Do not compare wrappers using ==; use primitive values or equals.

Alternative public fields: callers could assign negative slots, blank registrations or invalid fees. Every caller must repeat validation, creating inconsistent rules. Validation is O(1) per update either way; encapsulation centralizes it once and reduces maintenance.