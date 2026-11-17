# Assignment 1 – Consolidated Submission Report

**Course:** Programming in Java (CO1)  
**Assignment:** Campus Smart-Parking System  
**Student:** Enugala Manisha  
**Register number:** Add your register number before final submission  
**Java version for automated checks:** JDK 17  
**Repository:** https://github.com/enugalamanisha2388sse-cmd/CSA0916-Java-Programming

## Objective
Analyse, design, implement and validate an object-oriented campus smart-parking model using Java fundamentals and OOP concepts.

## Task-wise submission
Each Task folder contains:
- Java source code for the task.
- `REPORT.md` with task explanation, design choices and alternative/complexity discussion where applicable.
- `OUTPUT.txt` with the expected/reference console output.
- `actual-output.txt` with console output captured from the GitHub Actions JDK 17 run.

| Task | Topic | Main source |
|---|---|---|
| 01 | Problem analysis and candidate objects | Task01ProblemModel.java |
| 02 | Data representation and arrays | Task02DataRepresentation.java |
| 03 | Control logic and completed ParkingFacility | ParkingFacility.java |
| 04 | Classes, objects and constructors | Task04TicketConstructors.java |
| 05 | Static members and object lifecycle | Task05StaticLifecycle.java |
| 06 | Inheritance hierarchy | Task06Inheritance.java |
| 07 | Method overriding | Task07Overriding.java |
| 08 | Polymorphism and completed FeeDemo | FeeDemo.java |
| 09 | Abstract classes and interfaces | Task09AbstractInterface.java |
| 10 | Encapsulation and access modifiers | Task10Encapsulation.java |
| 11 | Implementation and validation | ParkingFacility.java |
| 12 | Design challenge and adaptation | Task12DesignChallenge.java |

## Execution
Open a terminal in the required Task folder, then run:
```bash
javac *.java
java MainClassName
```
Use the main class name shown in the task table / task README. For Task 3 and Task 11, run `java ParkingFacility`. For Task 8, run `java FeeDemo`.

## Automated validation
The repository includes `.github/workflows/assignment1-task-checks.yml`. All 12 task jobs completed successfully on JDK 17 (12/12 passed) in the run linked below. Each task's captured console output is also committed as `actual-output.txt`, and a downloadable artifact is available from the Actions run.

Successful run: https://github.com/enugalamanisha2388sse-cmd/CSA0916-Java-Programming/actions/runs/44128551529

## Evidence and academic integrity note
The committed `OUTPUT.txt` files are reference/expected output records, not screenshots and not a claim that the student personally executed the programs. After running each program on your own computer:
1. Capture the terminal showing the compile/run command and complete output.
2. Save the screenshot in the matching Task folder (for example, `Task01/output-screenshot.png`).
3. Replace runtime placeholders such as timestamps with your actual output.
4. Confirm the final tariff values with faculty. The supplied brief does not specify numeric standard parking rates, fee slabs or exact EV charging rates; illustrative values in examples must not be presented as faculty-provided rates.

## Final checklist
- [ ] Add register number and submission date.
- [ ] Personally execute every task.
- [ ] Add one genuine output screenshot to every Task folder.
- [ ] Confirm tariff assumptions with faculty.
- [ ] Verify the GitHub repository access and submission link.
