# Task 5 Report – Static Members and Object Life Cycle

## Aim
Demonstrate class-level ticket numbering and explain object reachability.

## Static counter
The ticket sequence belongs to the class, so a static counter is shared by all ticket objects. An instance counter would restart for each new object and would not provide a class-wide sequence.

## Object life cycle
When a vehicle exits, the active-ticket collection should remove its reference. If no other reachable reference points to the ticket, it becomes eligible for garbage collection. Java does not guarantee exactly when collection occurs.

## Object passing
Java passes arguments by value. For object arguments, the copied value is a reference to the same object. A method can mutate that object's state, but reassigning its local parameter does not reassign the caller's variable.

## Complexity
Incrementing the counter is O(1). n live ticket objects require O(n) storage.

## Conclusion
Static state is appropriate for data shared by all ticket instances, such as the ticket sequence.

## Source file
`Task05StaticLifecycle.java`
