# Q5 – Generic Ranking Component

The supplied TODOs are completed in a reusable `Leaderboard<T>`. It accepts `Comparator<? super T>`, stores items, and returns a sorted copy without mutating the internal list. `addAll(List<? extends T>)` demonstrates a producer-side wildcard.

Team does not need to implement Comparable because ranking policies may differ between competitions. Comparator chaining expresses points descending, goal difference descending, and name ascending. The resulting output is `[Eagles(9,6), Bisons(9,4), Panthers(9,4), Cobras(6,1)]`.

Bisons is above Panthers because points and goal difference tie, so the final A–Z name comparator decides. Comparing integer fields by subtraction is unsafe because overflow can reverse the sign; use comparingInt/Integer.compare.

One sort costs O(n log n); copying the list costs O(n) additional space, and the returned sorted copy also uses O(n) space. Three stable sorts cost roughly 3 × O(n log n), with additional temporary space depending on implementation. Comparator is easier to reuse for different types and ranking rules than hard-coding Team's natural order.
