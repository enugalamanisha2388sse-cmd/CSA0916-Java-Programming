# Task 12 – Re-evaluate and Adapt the Design

## A. Impact
Five floors with unequal capacities cannot be accurately represented by a rectangular array. A jagged String[][] with one row per floor handles this. ParkingFacility already accepts a per-floor slot-count array. New categories require EBike and DeliveryVan behaviour. Visitor day-pass validity requires a pass state or separate pass object. User/pass and pricing logic need modification; slot allocation can remain unchanged.

## B. Evaluation
The abstract Vehicle base and Chargeable interface support extension. New categories can be subclasses/implementations rather than a central type switch. Pricing should be close to vehicle policy or isolated in a PricingPolicy strategy. The jagged facility and ticket model remain appropriate if their contracts are preserved.

## C. Decision: MODIFY
Retain the vehicle hierarchy, jagged slot array, ticket model and map-based active-ticket index. Add EBike and DeliveryVan implementations and model one-day pass validity. A future improvement is a PricingPolicy strategy to keep time-window rules configurable.

## Changed-rule tests to execute
| Scenario | Expected behaviour |
|---|---|
| E-bike | Flat parking fee per visit |
| Delivery van 09:00–17:00 | Double car rate for overlapping time |
| Visitor with valid one-day pass | Parking fee waived |
| Visitor without pass | Normal visitor fee |
| Floor counts {2,3,1,4,2} | 12 slots total, unequal row sizes retained |
| Full facility | Next allocation reports full |

Trade-off: inheritance is easy to understand but pricing changes can cause subclass growth. A pricing strategy adds indirection and an object but isolates changing rules. MODIFY is appropriate because allocation/ticket fundamentals remain valid while new policies need targeted extensions.