# Task07 – Assignment 3 (CO3)

## Objective
Process score files concurrently while isolating corrupt and missing-file failures.

## Implementation
See `Main.java`. Compile with `javac Main.java` and run with `java Main` using Java 17.

## Analysis and complexity
For N files and P workers, ideal elapsed work approaches O(N/P) for equal-cost jobs; results storage is O(N), worker resources O(P), and per-file failures are captured independently.

## Verification notes
Check `actual-output.txt` after GitHub Actions runs. Thread completion order and elapsed-time measurements can vary by runtime; use local repeated measurements for the report. The assignment requires individual execution by both students.
