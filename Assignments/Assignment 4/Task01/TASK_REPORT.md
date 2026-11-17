# Task 1 – GUI Design Review

## Diagnosis
1. One listener handles unrelated actions, creating conditional complexity and making changes risky.
2. All business logic in the event handler violates separation of concerns and makes it hard to test.
3. Comparing strings with `==` compares references; use `equals` / `equalsIgnoreCase`.
4. Missing validation permits blank, malformed or negative payment values.
5. Database calls and `sleep()` on the Event Dispatch Thread freeze the window.
6. SQL string concatenation permits injection; use parameterized `PreparedStatement`.
7. Unclosed JDBC resources can leak connections, statements and result sets; use try-with-resources.
8. Weak exception handling hides failures; show useful feedback and log technical details.
9. Receipt action should be separate from payment processing and enabled only after success.

## Redesigned structure
```
FeeWindow (Swing components; input/output only)
       |
FeeController (event coordination)
       |
FeeService (validation + payment rules)
       |
FeeRepository (JDBC persistence)
```
The UI reads fields, calls the controller and renders success/error. The service validates amount and student details. The repository uses PreparedStatement and closes resources. Run long I/O in SwingWorker.

## Event handling
Use separate ActionListener objects or Swing Actions for Pay and Print Receipt. The shared listener has less setup but requires branching; separate actions improve cohesion, testability and reuse.

## Design defence (under 100 words)
The window should only collect input and display results. FeeService owns validation and payment rules, while FeeRepository owns database operations. This separation makes business rules testable without opening a window and prevents SQL code from leaking into UI handlers. Parameterized SQL and try-with-resources improve security and reliability. Background work keeps the Event Dispatch Thread responsive. Separate actions make each command easier to understand and extend.
