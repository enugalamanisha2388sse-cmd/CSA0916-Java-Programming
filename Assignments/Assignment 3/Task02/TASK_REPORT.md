# Task02 – Assignment 3 (CO3)

## Objective
Design domain exceptions and preserve the original storage failure through exception chaining.

## Implementation
See `Main.java`. Compile with `javac Main.java` and run with `java Main` using Java 17.

## Analysis and complexity
Exception propagation is O(depth) in the call chain. Capturing stack traces costs work proportional to stack depth and retains cause references. The checked hierarchy makes handling obligations visible in method signatures.

## Verification notes
Check `actual-output.txt` after GitHub Actions runs. Thread completion order and elapsed-time measurements can vary by runtime; use local repeated measurements for the report. The assignment requires individual execution by both students.
