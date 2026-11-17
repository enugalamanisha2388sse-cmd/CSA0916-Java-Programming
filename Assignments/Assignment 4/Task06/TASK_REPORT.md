# Task 6 – Campus Event Registration Design Challenge

## Users and screens
- Student: registration form with student name, email and event selection.
- Committee member: registration table, event filter and CSV export.
- Main window: File menu (Refresh, Export, Exit); validation/error/confirmation dialogs.

## Layers
```
Swing UI (JFrame, form, JTable, dialogs)
             |
RegistrationService (validation, duplicate policy)
             |
RegistrationRepository (JDBC, PreparedStatement)
             |
MySQL campus_events.registrations
```
The sample source demonstrates the UI and JDBC operations in SwingWorker. Production code should extract the shown JDBC operations into a repository class and validation into a service class rather than leave them in the frame.

## Rules and testable requirements
- Name is required; email must match a basic email pattern.
- A student cannot register twice for the same event: database UNIQUE(email,event_name) is the final concurrency-safe guard.
- SQL uses PreparedStatement; JDBC resources use try-with-resources.
- Database loading and registration run in SwingWorker, not on the Event Dispatch Thread.
- Committee users can filter by event and view results in a JTable.

## Pseudocode
Register: validate fields -> service checks business rules -> repository inserts parameterized SQL -> unique constraint rejects duplicate -> show success/error dialog.
Filter: read selected event -> background query with WHERE and LIMIT/OFFSET -> publish rows -> update table model on EDT.

## 100,000 rows: all-at-once vs pagination
Loading all 100,000 rows transfers and retains O(n) records and can increase startup time, memory pressure and EDT freeze risk if mishandled. Paginated loading transfers O(k) rows per page (k=page size, e.g. 100), reduces memory and improves perceived responsiveness, at the cost of extra queries and page navigation. Use indexes on event_name and stable ordering by id.

## Setup
1. Run `schema.sql` in MySQL.
2. Add MySQL Connector/J to the classpath.
3. Set JDBC URL, username and password in Main.java (do not commit real credentials).
4. Compile/run with the connector JAR on classpath.

## Evidence still required
Capture normal registration, invalid input, duplicate registration, event filtering and export behaviour. The supplied prototype has an export placeholder dialog; implement CSV writing before claiming export is complete.
