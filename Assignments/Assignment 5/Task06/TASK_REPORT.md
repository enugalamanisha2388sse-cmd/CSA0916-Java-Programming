# Task06 – B1: Bus-Pass File Store
Implement append/add, find-by-ID and list-by-route against the seven-field delimited format from A1. Parsing must validate field count, route number, ISO date and amount; malformed records are skipped and reported rather than aborting the scan.
Test: normal P1003 match; absent ID returns null; malformed P1002 line is skipped. Search cost for q queries over n records is O(qn); loading once into HashMap costs O(n) expected build time plus O(q) expected lookup, with O(n) extra memory.
Measure 1/10/50 MB buffered and unbuffered line counting and 1k–100k record search. Record hardware, JDK, warm-up, repetitions and median; do not invent timing values.
