# Task04 – Assignment 3 (CO3)

## Objective
Create scanner workers with Thread and Runnable and observe their lifecycle.

## Implementation
See `Main.java`. Compile with `javac Main.java` and run with `java Main` using Java 17.

## Analysis and complexity
With n scripts and p workers, ideal processing time is approximately O(n/p) units of equal work; thread creation and scheduling add overhead. Each platform thread consumes stack memory, so a fixed pool bounds thread count.

## Verification notes
Check `actual-output.txt` after GitHub Actions runs. Thread completion order and elapsed-time measurements can vary by runtime; use local repeated measurements for the report. The assignment requires individual execution by both students.
