# Assignment 2 – CO2: Strings and Collections

This report follows Q1–Q7 in the supplied assignment brief. Each task folder contains its Java implementation and task-level discussion. Benchmark timings are intentionally generated at execution time because they depend on CPU, JVM version, warm-up, background load and heap configuration. Do not present sample timings as universal results.

## Q1 – String, StringBuilder or StringBuffer
The benchmark uses three workloads and five measured repetitions after warm-up. Repeated `+` can copy an increasingly large immutable prefix, giving O(n²) character copying for n similar fields. StringBuilder and StringBuffer provide amortized linear append in total output size. Choose StringBuilder for a thread-confined ticket; use a thread-safe logging design for shared logs, with StringBuffer only where a shared mutable buffer is actually required. Exact object allocations require a profiler/JFR rather than an estimate masquerading as a count.

## Q2 – List and Deque
The program tests ArrayList and LinkedList at 1k, 10k and 50k sizes. ArrayList offers O(1) indexed access and cache-friendly iteration; front/middle insertion/removal shifts O(n) elements. LinkedList has O(1) link changes once positioned, but finding a position is O(n) and indexed access is O(n). ArrayDeque is appropriate for frequent additions at both ends and removals from the front.

## Q3 – hashCode and equals
The broken class defines equals by registration number but inherits identity hashCode. Equal objects can be placed in different hash buckets. The corrected immutable Student uses the same regNo in equals and hashCode. HashSet add/contains is O(1) average and O(n) space. Mutating a hash-relevant field after insertion can make lookup fail. TreeSet provides O(log n) operations; HashMap<String,Student> makes the identity key explicit.

## Q4 – Map choice
HashMap gives O(1) average lookup and unspecified iteration order; TreeMap gives O(log n) operations and sorted key order; LinkedHashMap preserves insertion order with O(1) average lookup. For a high-lookup pharmacy with code-ordered reports, use HashMap for lookup plus a TreeMap reporting index/snapshot, or TreeMap alone if its measured lookup latency meets the defined service target. Hashtable is legacy and should not be the default for new code. Sorted arrays give O(log n) lookup, O(n) insertion and O(n) report traversal.

## Q5 – Generic Leaderboard
Leaderboard<T> accepts Comparator<? super T>, and ranking() sorts a copy so internal insertion order is not changed. addAll(List<? extends T>) accepts a subtype list. Comparator chain: points descending, goal difference descending, name ascending. This is reusable for teams, players and colleges without forcing a single natural order. One sort is O(n log n) with O(n) copy space.

## Q6 – PriorityQueue
The comparator uses severity ascending and arrival sequence ascending. Offer/poll are O(log n), peek O(1), storage O(n). Draining the eight-patient sample confirms the expected treatment order. Printing PriorityQueue itself does not guarantee sorted iteration. Strict priority can starve minor patients; an aging or maximum-wait policy is needed, with clinical constraints considered.

## Q7 – Diagnose and redesign
The selected bottleneck is repeated ArrayList.remove(0) in a FIFO Up Next workload. Repeated shifts make a full drain O(n²). ArrayDeque addLast/removeFirst gives amortized O(1) operations and O(n) full drain. The program compares identical fill-and-drain workloads at three sizes. ArrayList remains preferable for indexed browsing.

## Execution and evidence
Compile/run each task with Java 17 or later. The CI workflow compiles and runs all seven task programs and uploads the resulting console logs. For formal submission, retain raw run output and repeat benchmark runs on the same machine; do not claim timing values before collecting them.
