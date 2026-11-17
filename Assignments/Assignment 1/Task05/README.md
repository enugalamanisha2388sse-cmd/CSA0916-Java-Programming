# Task 5 – Apply Static Members and the Object Life Cycle

ParkingTicket.nextTicketNumber is static because the sequence belongs to the class, not an individual ticket. An instance counter could restart for each ticket and repeat numbers. Concurrent issuance should use synchronization or AtomicLong.

Static initialization is appropriate for immutable class-wide configuration. Static utility methods are suitable when no instance state is needed.

After exit, the active-ticket map removes its reference. If no other references remain, the ParkingTicket becomes eligible for garbage collection; Java does not guarantee the collection time. A retained log/caller reference keeps it reachable.

Java passes object references by value. park receives Vehicle and ParkingUser references, creates a ParkingTicket and returns its reference. Object state can be observed through other references, but reassigning a local parameter does not reassign the caller variable.

A static counter uses O(1) shared space and O(1) increment time. n active tickets require O(n) object space. Removed, unreachable tickets become eligible for collection.