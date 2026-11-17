# Q7 – Diagnose, Redesign and Validate

## A. Diagnosis
The selected limitation is the playlist Up Next queue when it is represented by an ArrayList and repeatedly removes index 0. Each removal shifts the remaining elements, so draining n items performs O(n²) total element movement.

## B. Objective
Preserve FIFO order while reducing repeated front-removal work to amortized O(1) per removal and O(n) total work for n items.

## C. Revised design
Use ArrayDeque: append with addLast and serve with removeFirst. This is an intentional workload redesign around deque operations, not a claim that one collection is universally faster.

## D/E. Comparison
The program fills and drains the same number of integer items for n = 2,000, 10,000 and 30,000, reporting elapsed time for both implementations. The timings are produced at runtime; compare on the same JVM and machine and repeat runs before drawing conclusions.

| Measure | Original ArrayList front removal | Revised ArrayDeque |
|---|---:|---:|
| One front removal | O(n) | Amortized O(1) |
| Drain n items | O(n²) | O(n) |
| Extra storage | O(n) | O(n) |

## F. Defence and trade-offs
The redesign should substantially reduce work as n grows because it avoids shifting. ArrayList may still be preferable for a playlist view dominated by indexed access. ArrayDeque does not provide constant-time indexed reads. The final choice should match the operation mix, not only the benchmark.
