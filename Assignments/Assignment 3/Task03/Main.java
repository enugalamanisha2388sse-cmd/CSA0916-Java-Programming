import java.io.*;
import java.nio.file.*;
import java.util.*;

class TrackedInput implements AutoCloseable {
    static int openCount = 0;
    private final String name;
    TrackedInput(String name) { this.name = name; openCount++; }
    void read(boolean fail) throws IOException {
        if (fail) throw new IOException("Corrupt store file: " + name);
    }
    public void close() { openCount--; }
}
public class Main {
    static void manual(boolean fail) {
        TrackedInput in = null;
        try {
            in = new TrackedInput("store-B.csv");
            in.read(fail);
        } catch (IOException e) {
            System.out.println("Manual version logged: " + e.getMessage());
        } finally {
            if (in != null) in.close();
        }
    }
    static void automatic(boolean fail) {
        try (TrackedInput in = new TrackedInput("store-B.csv")) {
            in.read(fail);
        } catch (IOException e) {
            System.out.println("Try-with-resources logged: " + e.getMessage());
        }
    }
    static String generate(Path input, Path output) throws IOException {
        int total = 0;
        try (BufferedReader r = Files.newBufferedReader(input)) {
            String line;
            while ((line = r.readLine()) != null) total += Integer.parseInt(line.trim());
        }
        try (BufferedWriter w = Files.newBufferedWriter(output)) {
            w.write("Daily sales total: " + total);
        }
        return Files.readString(output);
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Manual failing run:");
        manual(true);
        System.out.println("Tracked resources still open: " + TrackedInput.openCount);
        System.out.println("Try-with-resources failing run:");
        automatic(true);
        System.out.println("Tracked resources still open: " + TrackedInput.openCount);
        Path dir = Files.createTempDirectory("sales-report");
        Path input = dir.resolve("sales.txt"), output = dir.resolve("summary.txt");
        Files.write(input, Arrays.asList("100", "250", "75"));
        System.out.println(generate(input, output));
        Files.deleteIfExists(input); Files.deleteIfExists(output); Files.deleteIfExists(dir);
    }
}