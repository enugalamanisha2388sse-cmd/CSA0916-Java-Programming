# Q4 – Choosing a Map for Pharmacy Inventory

The experiment creates 1,000, 10,000 and 50,000 medicine records, performs 100,000 seeded lookups, and compares HashMap and TreeMap. It also demonstrates insertion order with LinkedHashMap and sorted code order with TreeMap. The printed values are runtime measurements, not fixed benchmark claims.

| Structure | Lookup | Insert | Iteration order |
|---|---:|---:|---|
| HashMap | O(1) average | O(1) average | Unspecified |
| TreeMap | O(log n) | O(log n) | Sorted key order |
| LinkedHashMap | O(1) average | O(1) average | Insertion order |

A sensible pharmacy design is HashMap for frequent code lookup plus a TreeMap snapshot/index for sorted reports, or TreeMap alone if sorted traversal is sufficiently frequent and lookup performance meets the defined service target. Define the target with the actual application SLA; the brief does not prescribe a numeric threshold. Hashtable is generally not preferred for new code because it is a legacy synchronised map; choose modern concurrency controls deliberately.

Alternative sorted array: binarySearch lookup O(log n), insertion O(n) due to shifting, sorted report O(n), storage O(n). Map implementations trade lookup/insertion costs for flexible indexing and ordering.
