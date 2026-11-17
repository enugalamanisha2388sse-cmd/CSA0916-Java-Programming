# Task01 – A1: Design a File Format
## Delimited record
`passId|registerNo|name|routeNo|boardingStop|validUntil|amountPaid`
Escape delimiter/backslash in text fields or use a documented quoting scheme. Validate exactly seven fields, date format and numeric amount.
## Binary DataOutputStream layout
Write fields in fixed order: UTF passId, UTF registerNo, UTF name, int routeNo, UTF boardingStop, UTF validityDate (ISO-8601), long amountPaise. Prefix each record with a length if variable-sized records must be skipped safely; version the file header.
## Pseudocode
ADD: validate record; append one complete encoded record; flush; report success only after write completes.
FIND: scan records; parse/validate each; skip malformed records with line number; return matching ID or null.
LIST_ROUTE: scan valid records; collect those whose routeNo matches; report malformed records separately.
## Failure handling
Missing file: create only for add; find/list return empty with a clear status. Damaged line: quarantine/log line number and continue. Incomplete write: write to temporary file and atomically replace for rewrites; append format should include checksum/length and recover by ignoring incomplete trailing record.
## RandomAccessFile
Fixed-width records permit direct seek/update in O(1) record positioning but waste padding and complicate variable-length names. Delimited text is inspectable and flexible, but lookup is O(n) and parsing can fail on malformed delimiters.
