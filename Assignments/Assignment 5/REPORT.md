# Assignment 5 – CO5 Report
## Part A – Design
A1: delimited text and DataOutputStream layouts; add/find/list pseudocode; damaged records, missing file and incomplete-write recovery; compare RandomAccessFile.
A2: 50 MB byte-at-a-time read makes approximately 52,428,800 read operations; buffering amortizes I/O calls. Compare memory/time with Files.readAllLines.
A3: generic Dao<T,K> contract and StudentDao mapping.
A4: mark credentials and live Connection transient; explain object graph and serialVersionUID evolution.
A5: transfer ₹500 using one JDBC transaction with rollback; use PreparedStatement for login.
## Part B – Implementation
B1: pass file add/find/list, damaged-line tolerance and measured scan-vs-map search.
B2: test malformed pass data and contrast unsafe SQL with parameterized login.
B3: compare Statement, PreparedStatement and JDBC batch; test transaction rollback and metadata.
B4: migrate validated records in batches, report rejects and inspect DatabaseMetaData.
## Evidence to complete
Insert measured results for 1/10/50 MB line counts and 1k/10k/100k inserts; include actual console logs, SQL query results and screenshots. Do not substitute estimated values for measurements.
