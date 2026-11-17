# Task06 – Assignment 3 (CO3)

## Objective
Prevent wallet-transfer deadlock and coordinate producers/consumers with a bounded buffer.

## Implementation
See `Main.java`. Compile with `javac Main.java` and run with `java Main` using Java 17.

## Analysis and complexity
Priority lock ordering removes circular wait; each transfer uses two locks and constant auxiliary space. Producer/consumer put and take are O(1) per operation with a fixed-size ring buffer of capacity 5.

## Verification notes
Check `actual-output.txt` after GitHub Actions runs. Thread completion order and elapsed-time measurements can vary by runtime; use local repeated measurements for the report. The assignment requires individual execution by both students.
