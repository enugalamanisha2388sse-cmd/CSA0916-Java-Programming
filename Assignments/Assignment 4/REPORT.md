# Assignment 4 – CO4: Event-Driven GUI Design

This report consolidates the six task reports. See each Task folder for its diagnosis, event table, layout structure, implementation and test instructions.

## Task summary
1. GUI review: separate window, controller, service and repository; validate inputs and use safe JDBC.
2. Appointment design: map sources to events/listeners/actions; refresh doctor choices on department changes.
3. Library UI: nested BorderLayout and FlowLayout; centre results area expands on resize.
4. Drawing pad: persist mouse points in model state, update count and repaint.
5. Currency converter: compare frame listener, named inner class, anonymous class and lambda; Enter triggers conversion.
6. Event registration: Swing/JDBC layered design, duplicate prevention, background work and pagination.

## Complexity highlights
- Department-to-doctor map lookup: average O(1) map access plus O(d) to populate d doctors.
- Drawing repaint with n stored points: O(n) rendering; point storage O(n).
- Registration insert: typically O(log n) index maintenance in a B-tree index (database-dependent).
- Pagination returns O(k) records for page size k rather than materializing all n=100,000 rows.

## Evidence checklist
- Task03: screenshots at 420×300, 700×480 and 1000×700.
- Task04: before and after minimising/restoring.
- Task05: valid and invalid conversion.
- Task06: normal, invalid and duplicate registrations, filter and export.
- Add your name, register number, Java version and local evidence before final submission.
