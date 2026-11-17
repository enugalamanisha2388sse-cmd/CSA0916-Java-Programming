# Task 4 Report – Classes, Objects and Constructors

## Aim
Create a ticket object with valid state using constructors and constructor chaining.

## Design
The example defines a vehicle with a validated registration and a ticket containing a unique number, vehicle reference, floor, slot and entry timestamp.

## Constructor use
The shorter ticket constructor delegates to the full constructor using `this(...)`. The full constructor validates indices and non-null references. This avoids duplicated initialization logic. A subclass would call its parent constructor using `super(...)`.

## Alternative approaches
- Default constructor plus setters may expose an incomplete object between calls.
- A static factory can validate inputs and return a fully initialized instance.
- A constructor directly establishes required state at creation.

For n tickets, creation and constant-time validation take O(n) total time and O(n) storage in all approaches. Factories add a small call layer; setters require extra calls and permit intermediate invalid state unless carefully controlled.

## Conclusion
Constructor chaining provides a concise way to create valid ticket objects.

## Source file
`Task04TicketConstructors.java`
