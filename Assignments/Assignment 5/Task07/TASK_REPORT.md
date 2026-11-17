# Task07 – B2: Adversarial Input
Create an assignment-only local database with dummy users. Compare the unsafe concatenated login query against a parameterized PreparedStatement using the same invalid test credentials; record acceptance/rejection and explain why parameter binding treats input as data.
Damaged pass fixtures: wrong field count, non-numeric amount and empty line. Expected robust behaviour: each is rejected/quarantined with line number while subsequent valid records remain readable.
Never test injection against a production or third-party system. Keep credentials local and do not commit them.
