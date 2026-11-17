# Task03 – Assignment 3 (CO3)

## Objective
Compare manual resource cleanup with try-with-resources under failure.

## Implementation
See `Main.java`. Compile with `javac Main.java` and run with `java Main` using Java 17.

## Analysis and complexity
Reading f files with total B bytes is O(B). With one input and one output open at a time, open-handle usage is O(1). Try-with-resources closes resources even when the body throws; it does not guarantee atomic publication or valid input.

## Verification notes
Check `actual-output.txt` after GitHub Actions runs. Thread completion order and elapsed-time measurements can vary by runtime; use local repeated measurements for the report. The assignment requires individual execution by both students.
