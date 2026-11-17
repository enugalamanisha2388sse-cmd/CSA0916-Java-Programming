# Task 1 – Understand the Problem and Identify the Objects

## Problem analysis
On entry, validate the user and vehicle, find a suitable free slot, issue a unique ticket and record entry time. On exit, find the ticket, calculate the fee, release the slot and close the ticket.

## Candidate classes
- ParkingFacility: owns floor/slot layout and prevents double allocation.
- Vehicle (abstract): common registration number and fee contract.
- TwoWheeler, Car, ElectricCar: vehicle-specific pricing.
- ParkingUser: identity, category and pass entitlement.
- ParkingTicket: ticket number, vehicle, user, location and entry time.
- CampusParkingSystem: coordinates registration, entry, exit and active tickets.
- Chargeable: optional charging capability.

## Business rules
1. A slot is free or occupied by one registration number.
2. A registration number cannot have two active tickets.
3. Ticket numbers are unique during an application run.
4. Exit cannot precede entry; unknown tickets cannot be exited.
5. Eligible students/staff with a valid monthly pass park free; visitors pay unless they obtain the Task 12 day pass.
6. A slot is released only after valid exit.
7. Invalid indices and negative durations are rejected.

## Assumptions
The PDF does not specify exact rates or slab values. Rates and started-hour billing in the sample are demonstration assumptions to confirm with faculty. Floor capacities may differ.

## Alternative: objects vs parallel arrays
An object groups registration, type and behaviour. Unsorted search is O(n); adding an attribute means changing the class/subclass. Parallel arrays also need O(n) search, but insertion/deletion must keep indices synchronized. A new attribute requires another array and coordinated updates. Both use O(n) storage; objects have per-object overhead, while arrays are compact but error-prone. A HashMap keyed by registration improves average lookup to O(1).