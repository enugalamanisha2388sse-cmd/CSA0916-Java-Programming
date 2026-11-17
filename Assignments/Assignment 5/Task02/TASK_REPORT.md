# Task02 – A2: Buffered I/O
For a 50 MiB (52,428,800-byte) log, reading one byte at a time requests roughly 52.4 million Java read operations. A BufferedInputStream fetches blocks and serves many byte reads from memory, greatly reducing underlying stream calls.
Buffered line counter: wrap FileInputStream in BufferedInputStream and InputStreamReader/BufferedReader; increment count for each readLine until EOF.
Buffering helps when many small reads are made from a slower source. It may add little benefit when data is already in memory, the source itself is highly buffered, or the program performs one large sequential read.
Files.readAllLines loads all lines and strings into memory: O(n) time and O(file size + object overhead) space. BufferedReader streaming is O(n) time and O(buffer + current line) extra space.
