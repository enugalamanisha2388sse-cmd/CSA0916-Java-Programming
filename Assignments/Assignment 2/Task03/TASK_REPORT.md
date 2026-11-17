# Q3 – Engineer the hashCode–equals Contract

The intentionally broken class defines equality by registration number but inherits identity-based Object.hashCode(). Equal objects can therefore land in different hash buckets; HashSet generally checks equals only among candidates in the matching bucket. The broken-set size can vary in the rare event of hash collisions, so the program labels the outcome rather than assuming an exact result.

The corrected Student class uses the same immutable registration number in both equals and hashCode. Expected corrected output includes `Unique students: 2`. The hash contract requires: equal objects must have equal hash codes; unequal objects may share a hash code.

Changing a field used by hashCode after insertion can make the object unreachable in its old bucket, even though the same reference remains physically stored. Prefer immutable identity fields.

Alternatives:
- HashSet: O(1) average add/contains, O(n) space.
- TreeSet with Comparator on regNo: O(log n) add/contains, O(n) space; comparator must be consistent with intended uniqueness.
- HashMap<String,Student>: O(1) average lookup/put, O(n) space, explicit key is the registration number.

Three checks: duplicate registration numbers, distinct registration numbers, and mutation of an identity field.
