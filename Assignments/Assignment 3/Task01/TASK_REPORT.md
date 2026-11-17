# Task01 – Assignment 3 (CO3)

## Objective
Analyse ATM failure paths and demonstrate checked/unchecked exception handling.

## Implementation
See `Main.java`. Compile with `javac Main.java` and run with `java Main` using Java 17.

## Analysis and complexity
Input parsing and validation are O(1) per withdrawal. Exception construction captures diagnostic stack information and allocates objects; normal validation with Scanner checks/return codes avoids throwing for expected invalid input. finally runs on both success and handled failure.

## Verification notes
Check `actual-output.txt` after GitHub Actions runs. Thread completion order and elapsed-time measurements can vary by runtime; use local repeated measurements for the report. The assignment requires individual execution by both students.
