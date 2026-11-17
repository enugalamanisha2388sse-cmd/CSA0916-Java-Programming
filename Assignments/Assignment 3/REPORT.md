# Assignment 3 – CO3 Joint Report
## Exception Handling & Multithreading

**Student A:** Add name and register number  
**Student B:** Add name and register number  
**Repository:** https://github.com/enugalamanisha2388sse-cmd/CSA0916-Java-Programming

## Q1 – ATM failure handling
The program validates numeric input, positive account/withdrawal values, available balance, and the card-reader file. NumberFormatException is unchecked; IOException is checked; IllegalArgumentException and the custom InsufficientFundsException are unchecked. The finally block prints cleanup evidence for every handled path. Specific catches precede broader catches; catching Exception alone hides useful distinctions. Input validation with hasNextInt/return codes avoids exception-driven ordinary control flow, while exception construction/throwing adds stack-trace work and allocation.

## Q2 – User-defined exceptions and propagation
ExamException is a checked base type. ExamWindowClosedException, CourseRegistrationException and SubmissionStorageException express domain failures. Storage wraps IOException while retaining it as the cause; service propagates the domain exception to the controller. Checked exceptions force callers to catch or declare them. An unchecked design removes that compile-time obligation. Stack traces and chained causes retain diagnostic context at additional memory cost.

## Q3 – Resource safety
The manual version closes its resource in finally; the try-with-resources version closes automatically, including when read throws. Resources close in reverse declaration order. A close failure during an existing failure is recorded as a suppressed exception. Processing f input files is O(total input bytes); the program should hold only the current input plus output open, rather than accumulating handles. TWR does not solve invalid data, partial output/atomic publication, insufficient permissions, or disk exhaustion.

## Q4 – Thread creation and lifecycle
Both extending Thread and implementing Runnable are demonstrated. start() schedules a new thread; run() called directly is an ordinary method call. join() waits for completion; isAlive() checks whether a thread remains active. Scheduling makes completion order nondeterministic, so correctness must not depend on print order. A fixed ExecutorService pool bounds worker threads and reuses them.

## Q5 – Seat booking race
The check-then-book operation is synchronized on the SeatBooking instance, making the state check and update atomic relative to other calls on that instance. The expected invariant is at most one sale per seat and exactly 10 successful bookings for 100 attempts over 10 seats. A synchronized method locks the whole method; a synchronized block can narrow the critical section. Static synchronization is needed only for class-wide shared state across instances. AtomicBoolean per seat or a lock can also enforce one-time booking.

## Q6 – Deadlock and communication
Wallet transfers acquire locks in ascending account ID order, breaking circular wait and preventing the demonstrated lock-order deadlock. The bounded token counter uses while loops around wait(), updates the circular buffer under its intrinsic monitor, and calls notifyAll(). The expected token sum is 210 and buffer occupancy remains between 0 and 5. ArrayBlockingQueue is a less error-prone alternative for producer/consumer coordination; timed tryLock can support bounded waiting and failure handling.

## Q7 – Concurrent fault-tolerant processing
Each score file is an independent Callable. Futures collect each result; per-file exceptions become failure records rather than stopping the whole batch. ConcurrentLinkedQueue stores shared results safely. The sample includes valid, corrupt and missing-file cases and measures pools of 1, 2, 4 and 8. For N files and P workers, ideal work time approaches total work/P, subject to I/O, scheduling and contention. One thread per file risks excessive stack/memory; parallel streams provide less explicit pool control.

## Individual contribution record
Complete this table with the actual pair's work before submission.

| Question | Student A primary responsibility | Student B primary responsibility | Joint |
|---|---|---|---|
| Q1 | Exception hierarchy, failure classification | Test cases, catch tracing | ATM implementation and conclusion |
| Q2 | Exception design and propagation | Failure scenarios and chaining evidence | Portal design |
| Q3 | Debugging and measurements | Resource design and correctness | Compare implementations |
| Q4 | Thread design and lifecycle | Ten-run behaviour experiments | Explain scheduling |
| Q5 | Reproduce and explain race | Compare synchronization strategies | Choose deployment strategy |
| Q6 | Producer-consumer design | Deadlock analysis and prevention | Test both parts |
| Q7 | Executor design and diagram | Implementation and measurement | Defend design |

## Evidence still to add
The assignment brief requires both students to run all tasks in their own repositories, screenshots, raw measurement data, stack traces/test tables, and progressive commits. Replace sample timing observations with measurements from each student's own machine and add the actual Student A/B identities. Do not submit this template without completing those items.
