# Task 2 Report – Data Representation

## Aim
Choose Java data types and array structures for the parking system.

## Data types
- Floor and slot index: `int`.
- Registration and vehicle category: `String`.
- Entry/exit time: `LocalDateTime`.
- Elapsed duration: `long` minutes.
- Monetary fee: `double` in this teaching example; `BigDecimal` is preferable for production currency calculations.

## Array structure
A jagged `String[][]` represents unequal floor capacities. Each row is allocated using that floor's actual capacity. A one-dimensional array is convenient only when floors have equal capacity or when prefix offsets are maintained. An `ArrayList<String[]>` can also represent unequal rows but adds collection overhead.

## Input
Use `Scanner` for interactive console input or command-line arguments for scripted execution.

## Integer division / casting risk
Integer division truncates: `130 / 60` gives 2, not 2.17. For started-hour billing, use `(minutes + 59L) / 60L`, which gives 3 for 130 minutes. Do not cast a fractional duration to an integer before applying the billing rule.

## Complexity
For F floors and S total slots, locating a free slot by scan is O(F×S) worst case. Slot storage is O(S). A flat array has O(1) index calculation but still needs a search to find a free slot.

## Conclusion
The jagged array is the clearest representation when floor capacities differ.

## Source file
`Task02DataRepresentation.java`
