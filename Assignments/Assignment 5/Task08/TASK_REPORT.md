# Task08 – B3: JDBC Performance Comparison
Compare Statement, PreparedStatement and PreparedStatement batch insertion for 10-row correctness tests and 1k/10k/100k performance runs. Verify rows with SELECT and inspect types using ResultSetMetaData.
Compare auto-commit with a single transaction. Inject an invalid amount halfway through; with one transaction, rollback should leave zero inserted rows from that attempted load. Batch size 1,000 is the specified PassLoader chunk size.
Capture database version, JDK, connector version, table indexes, warm-up and repeated median timings. Recommend batch + one transaction for nightly 100k import; for a single counter issue use PreparedStatement and commit per pass.
Discuss multi-row INSERT and MySQL LOAD DATA as alternatives; actual performance depends on schema, network and server settings.
