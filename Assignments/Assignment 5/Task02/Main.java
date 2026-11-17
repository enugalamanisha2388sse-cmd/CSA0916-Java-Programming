import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    // Counts the number of lines in a file using buffered input.
    static long count(File file) throws IOException {
        long lineCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        }

        return lineCount;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("50 MiB byte-at-a-time: about 52,428,800 read calls.");
        System.out.println("BufferedReader streams blocks; O(file) time, O(buffer + line) extra space.");

        if (args.length > 0) {
            System.out.println("Lines=" + count(new File(args[0])));
        }
    }
}
