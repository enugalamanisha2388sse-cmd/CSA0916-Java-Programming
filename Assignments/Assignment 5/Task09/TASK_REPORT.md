# Task09 – B4: Final Engineering Decision
Migration reads the legacy pass file, validates each record, inserts valid rows through PreparedStatement batches of 1,000 inside a transaction, and records rejected rows with source line and reason. On a database failure, rollback the transaction.
Use DatabaseMetaData to report database product name/version, driver name/version and table names.
For 12 campuses and 100× current volume, a shared relational database is generally the strongest candidate for concurrent counter access, indexed search, constraints, transactions, backup and central reporting. Text files remain useful for import/export; Java serialization is unsuitable as the primary shared operational store.
Compare shared DB with separate per-campus serialized files: local files reduce connectivity needs but complicate cross-campus search, concurrent updates, consistency, backup and version migration. Support final choice with your measured data.
