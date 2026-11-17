import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

class ResultRecord {
    final String file; final Integer score; final String error;
    ResultRecord(String file, Integer score, String error) { this.file=file; this.score=score; this.error=error; }
    boolean success() { return error == null; }
}
public class Main {
    static Callable<ResultRecord> job(Path file) {
        return () -> {
            try {
                if (!Files.exists(file)) throw new IOException("Missing file");
                String raw = Files.readString(file).trim();
                int score = Integer.parseInt(raw);
                if (score < 0 || score > 100) throw new IllegalArgumentException("Score out of range");
                return new ResultRecord(file.getFileName().toString(), score, null);
            } catch (Exception e) {
                return new ResultRecord(file.getFileName().toString(), null, e.getClass().getSimpleName()+": "+e.getMessage());
            }
        };
    }
    static void run(Path dir, int poolSize) throws Exception {
        List<Path> files = new ArrayList<>();
        for (int i=1;i<=10;i++) files.add(dir.resolve("score"+i+".txt"));
        files.add(dir.resolve("missing.txt"));
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);
        ConcurrentLinkedQueue<ResultRecord> results = new ConcurrentLinkedQueue<>();
        long start = System.nanoTime();
        try {
            List<Future<ResultRecord>> futures = new ArrayList<>();
            for (Path f:files) futures.add(pool.submit(job(f)));
            for (Future<ResultRecord> future:futures) results.add(future.get());
        } finally { pool.shutdown(); pool.awaitTermination(10, TimeUnit.SECONDS); }
        long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-start);
        int ok=0, failed=0, total=0;
        for (ResultRecord r:results) {
            if (r.success()) { ok++; total += r.score; }
            else { failed++; System.out.println("Failed "+r.file+": "+r.error); }
        }
        System.out.println("Pool="+poolSize+" success="+ok+" failed="+failed+" scoreTotal="+total+" elapsedMs="+elapsed);
    }
    public static void main(String[] args) throws Exception {
        Path dir=Files.createTempDirectory("result-processing");
        for (int i=1;i<=10;i++) Files.writeString(dir.resolve("score"+i+".txt"), String.valueOf(i*10));
        Files.writeString(dir.resolve("score10.txt"), "corrupt");
        for (int p:new int[]{1,2,4,8}) run(dir,p);
        try (DirectoryStream<Path> stream=Files.newDirectoryStream(dir)) {
            for (Path p:stream) Files.deleteIfExists(p);
        }
        Files.deleteIfExists(dir);
    }
}