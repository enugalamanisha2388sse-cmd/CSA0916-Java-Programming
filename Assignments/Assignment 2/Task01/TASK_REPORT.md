# Q1 – Building Text Under Load

## Prediction and design
A short ticket has little measurable difference. As fields and ticket count increase, repeated immutable `+` concatenation repeatedly copies the accumulated prefix. StringBuilder and StringBuffer append to a mutable buffer and should scale better. The program compares three workloads (5 fields × 1 ticket, 50 × 1,000, and 100 × 3,000), warms up the JVM, and reports the average of five measurements using `System.nanoTime()`.

## Interpretation
- Repeated `+`: potentially O(n²) character copying for n similarly sized fields.
- StringBuilder: amortized O(L) for L output characters; not synchronised.
- StringBuffer: similar append complexity with synchronised methods and extra contention overhead.
- Estimated object counts are qualitative: exact allocation counts require a profiler/JFR; they are not guessed as exact counts here.

## Design decision
Use StringBuilder for each ticket because each builder is local to one request/thread. For a shared booking log, prefer a thread-safe logging abstraction or explicit synchronisation around log writes; StringBuffer is an option for a genuinely shared mutable buffer but does not replace a sound logging design.

## Alternatives
StringJoiner/String.join can be clean when fields are already available as strings; String.format is expressive but formatting overhead can be higher. Both still need to produce the final output string. The benchmark output is machine/JVM-dependent and is printed at execution time.
