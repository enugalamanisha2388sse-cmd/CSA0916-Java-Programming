# Task 11 – Implement, Test and Defend the Design

Integrated source is in Main.java and ParkingFacility.java. The service coordinates vehicle/user registration, ticket creation, fee calculation and slot release. Active tickets are indexed by registration in a HashMap.

## Test plan
| Case | Expected result |
|---|---|
| Car enters/exits | Ticket issued, fee returned, slot released |
| Electric car | Vehicle fee plus charging fee |
| Full facility | Allocation reports full |
| Last free slot | Last slot is allocated |
| Zero duration | Zero billable hours |
| Exactly 60 minutes | One started hour |
| 61 minutes | Two started hours |
| Duplicate registration | Second entry rejected |
| Unknown exit registration | Rejected |
| Invalid floor/slot | release returns false |
| Exit before entry | Rejected |

## Active ticket structures
Array: O(n) exit lookup, O(n) space.
ArrayList: O(n) lookup, amortized O(1) append, O(n) space.
HashMap: average O(1) lookup/insert/remove, worst O(n), O(n) space.

Evidence note: the table specifies expected behaviour, not measured actual output. Compile and execute with the chosen JDK, then add actual console output/screenshots to this folder. Do not represent expected results as measured results.