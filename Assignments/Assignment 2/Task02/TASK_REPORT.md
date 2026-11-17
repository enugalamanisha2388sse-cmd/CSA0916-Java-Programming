# Q2 – Playlist Editor: ArrayList, LinkedList and ArrayDeque

The program tests three sizes (1,000; 10,000; 50,000) and times batches of front insertions, indexed reads and full iteration for ArrayList and LinkedList. The timings are exploratory single-run measurements; repeat on the target machine for formal evidence and do not interpret one run as a universal result.

| Operation | ArrayList | LinkedList |
|---|---:|---:|
| Add at end | Amortized O(1) | O(1) with tail pointer |
| Insert/remove at front | O(n) due to shifting | O(1) |
| Insert/remove in middle after locating position | O(n) | O(n) traversal; O(1) link change |
| Get k-th element | O(1) | O(n) |
| Iterate all | O(n), cache-friendly | O(n), pointer traversal |

ArrayList is reasonable when append, indexed access and iteration dominate. Stop recommending it when front/middle modifications are frequent and shift costs dominate. LinkedList is not automatically faster: locating a middle position is linear and node allocation has overhead.

For the changed Up Next requirement, ArrayDeque supports addFirst/addLast/removeFirst/removeLast in amortized O(1), so it is a better queue/deque choice. It has no indexed get operation.
