# Task05 – Assignment 3 (CO3)

## Objective
Prevent a cinema seat from being booked more than once under concurrent requests.

## Implementation
See `Main.java`. Compile with `javac Main.java` and run with `java Main` using Java 17.

## Analysis and complexity
Each synchronized booking is O(1) expected work; the boolean seat array uses O(s) space for s seats. Contention may serialize competing callers. The monitor protects the check and update as one critical section.

## Verification notes
Check `actual-output.txt` after GitHub Actions runs. Thread completion order and elapsed-time measurements can vary by runtime; use local repeated measurements for the report. The assignment requires individual execution by both students.
