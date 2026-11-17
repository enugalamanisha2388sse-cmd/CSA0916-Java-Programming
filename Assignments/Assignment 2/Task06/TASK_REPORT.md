# Q6 – Priority, Order and Fairness in a Queue

The PriorityQueue comparator orders lower severity numbers first, then the monotonically increasing arrival counter. Thus severity 1 is critical and equal-severity patients retain first-arrived-first-served order. The supplied TODO is completed in the Triage class.

The eight-patient order is checked by draining the queue: Meena, Kumar, Lata, Arun, Ravi, John, Nila, Asha. Printing PriorityQueue directly is not a sorted traversal; only its head is guaranteed to be the minimum. A sorted copy is used for the waiting display.

Complexities: offer O(log n), poll O(log n), peek O(1), storage O(n). The experiment measures add and drain operations at 1,000, 10,000 and 50,000 elements. 5,000 arrivals/day averages about 0.058 arrivals/second across 24 hours; peak arrival rate, treatment times and service capacity matter more than daily total alone.

Starvation: a continuous stream of severe patients can indefinitely postpone severity-5 patients. A fair policy can increase priority with waiting time or impose a maximum wait, while preserving clinical safety. Recalculate effective priority periodically or use time buckets; a fixed comparator over immutable severity alone does not implement aging.
