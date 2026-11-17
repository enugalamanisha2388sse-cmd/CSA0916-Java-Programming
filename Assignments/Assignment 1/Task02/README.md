# Task 2 – Choose the Data Representation

| Field | Java representation | Reason |
|---|---|---|
| Floor and slot | int | Discrete zero-based indices |
| Registration | String | Alphanumeric identifier |
| Entry/exit | LocalDateTime | Date/time arithmetic |
| Duration | long minutes | Safer elapsed-time range |
| Fee/rate | double in sample; BigDecimal for production | Decimal currency |
| Ticket number | long | Large monotonic identifier |
| Active tickets | HashMap<String, ParkingTicket> | Average constant-time lookup |

Input can be read using Scanner for interactive use or command-line arguments for repeatable runs. Validate before constructing domain objects.

Integer division risk: 130 / 60 using integers is 2. For started-hour billing use (minutes + 59) / 60, giving 3 for 130 minutes. Do not truncate a decimal duration before rounding.

## Alternative representations
A jagged String[][] directly models unequal floor capacities and uses O(total slots) space; scanning for a free slot is O(total slots). A flat array with floor * slotsPerFloor + slot assumes equal floor sizes; unequal sizes require prefix offsets. ArrayList<String[]> also supports unequal rows and has similar scan time, with small list overhead. Jagged arrays are clearest for this brief.