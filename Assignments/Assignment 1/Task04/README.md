# Task 4 – Create Classes and Objects

Constructors establish required state immediately. Vehicle(regNo) validates and normalizes registration; subclasses call super(regNo). ParkingTicket receives vehicle, user, floor, slot and entry time in one constructor and stores required data in final fields.

Constructor chaining: a subclass invokes its parent with super(regNo). this(...) can delegate one overloaded constructor to another to avoid duplicated validation. The this keyword distinguishes instance fields from parameters.

Alternative creation:
- Default constructor plus setters permits incomplete intermediate objects and requires validation on each setter.
- Static factory can validate and return a fully initialized ticket, and can later choose a specialized implementation.
- Constructor gives the simplest guarantee that required state exists at creation.

For n tickets, all approaches allocate n objects: O(n) total time and O(n) object storage. O(1) validation per ticket gives O(n) total validation. Factories add O(1) call overhead; setters add calls and expose intermediate states.