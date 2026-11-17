import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    // Splits and validates one bus-pass record.
    static String[] parse(String line) {
        String[] fields = line.split("\\|", -1);

        if (fields.length != 7) {
            throw new IllegalArgumentException("wrong field count");
        }

        Integer.parseInt(fields[3]);
        Double.parseDouble(fields[6]);

        return fields;
    }

    // Searches for a pass ID and skips malformed records.
    static String find(Path file, String passId) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] fields = parse(line);

                    if (fields[0].equals(passId)) {
                        return String.join("|", fields);
                    }
                } catch (RuntimeException exception) {
                    System.err.println("Damaged line skipped: " + line);
                }
            }
        }

        return null;
    }

    public static void main(String[] args) throws Exception {
        Path file = Path.of("passes.txt");

        if (args.length > 1 && args[0].equals("find")) {
            System.out.println(find(file, args[1]));
        } else {
            System.out.println("Usage: java Main find <passId>; add/list operations are described in TASK_REPORT.md");
        }
    }
}
