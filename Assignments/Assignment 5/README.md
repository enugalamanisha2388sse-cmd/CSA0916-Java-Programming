# Assignment 5 – CO5: Data Persistence Engineering

**Student:** Enugala Manisha  
**Register number:** Add your register number before submission  
**Course:** Programming in Java (CSA09)  
**Java version:** Java 17 or later

## Task folders

| Folder | Assignment area | Main implementation |
|---|---|---|
| Task01 | A1 – File format design | Delimited and binary format design |
| Task02 | A2 – Buffered I/O | Buffered line counter |
| Task03 | A3 – DAO pattern | Generic DAO and JDBC Student CRUD |
| Task04 | A4 – Serialization | Serializable Hostel object graph |
| Task05 | A5 – Correctness and safety | Atomic wallet transfer and safe/unsafe login comparison |
| Task06 | B1 – File store | Bus-pass find operation and damaged-line handling |
| Task07 | B2 – Adversarial input | Invalid file-record test cases and SQL safety notes |
| Task08 | B3 – JDBC performance | Statement, PreparedStatement, auto-commit and batch comparison |
| Task09 | B4 – Engineering decision | Validated file-to-database migration and JDBC metadata |

## Requirements

- JDK 17+
- MySQL Server 8.0+
- MySQL Connector/J JDBC driver
- A local assignment-only database; do not use real student or payment data.

MySQL Connector/J is the official JDBC driver for connecting Java applications to MySQL. See the [MySQL Connector/J Developer Guide](https://dev.mysql.com/doc/connector-j/en/).

## Configure the JDBC driver

Download the MySQL Connector/J ZIP/JAR from the official MySQL site. Add the JAR to the classpath when compiling and running JDBC tasks.

**Windows Command Prompt example** (replace the JAR path with the location on your laptop):

```bat
set CLASSPATH=.;C:\path\to\mysql-connector-j.jar
```

Configure the database connection using environment variables. Example:

```bat
set DB_URL=jdbc:mysql://localhost:3306/buspass_test
set DB_USER=root
set DB_PASSWORD=your_local_mysql_password
```

Do not commit your real database password to GitHub.

## Database setup and run commands

Run each SQL file in MySQL Workbench or the MySQL command-line client before running its Java task.

### Task03 – Generic Student DAO

1. Run `Task03/schema.sql`.
2. Set the JDBC environment variables above.
3. Open Command Prompt in `Task03`:

```bat
javac Main.java
java Main
```

The program inserts a sample Student, reads it, updates it, lists records, deletes it and checks that it no longer exists.

### Task05 – Transactions and SQL injection

1. Run `Task05/schema.sql`.
2. Open Command Prompt in `Task05`:

```bat
javac Main.java
java Main
```

The default run attempts a ₹500 transfer from wallet 1 to wallet 2. To compare the unsafe and parameterized login examples in the disposable demo database:

```bat
java Main login-demo
```

The unsafe query is intentionally vulnerable and must only be used against this local dummy database. The PreparedStatement example treats the supplied values as data. A real application must store and verify strong password hashes, not plaintext passwords.

### Task08 – JDBC performance comparison

1. Run `Task08/schema.sql`.
2. Open Command Prompt in `Task08`:

```bat
javac Main.java
java Main
```

The program compares Statement, PreparedStatement in one transaction, PreparedStatement with auto-commit, and PreparedStatement batch insertion for 1,000, 10,000 and 100,000 rows. It also checks column metadata and demonstrates rollback with an invalid amount. Timings depend on your computer, database, network and connector; record your own repeated measurements and report the median.

### Task09 – File migration

1. Run `Task08/schema.sql` to create the `bus_pass` table.
2. Open Command Prompt in `Task09`:

```bat
javac Main.java
java Main ..\Task06\passes.txt
```

The program validates records, batches valid rows in groups of 1,000 within a transaction, writes rejected rows and reasons to `rejected-records.txt`, and reports database/driver/table metadata.

## General compilation

Non-JDBC tasks can be compiled from their own folders:

```bat
javac Main.java
java Main
```

Each Task folder is independent and should be compiled from inside that folder. Do not compile every `Main.java` in one shared output directory because the tasks intentionally reuse the class name `Main`.

## Evidence and submission checklist

- [ ] Add your correct register number.
- [ ] Run every task on your own machine.
- [ ] Save screenshots of actual execution and database table contents.
- [ ] Record actual timing measurements; do not use invented or illustrative values.
- [ ] Include the actual outputs generated on your system.
- [ ] Add the repository URL to the assignment report.
- [ ] Confirm the faculty's repository naming and sharing requirements.

**Important:** A successful Java compilation check does not prove that MySQL operations ran. JDBC tasks require a configured local MySQL server and Connector/J. The source code and SQL setup scripts are provided here; capture your own actual database execution evidence before submission.
