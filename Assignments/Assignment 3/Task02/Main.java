import java.io.IOException;

class ExamException extends Exception {
    ExamException(String message) { super(message); }
    ExamException(String message, Throwable cause) { super(message, cause); }
}
class SubmissionStorageException extends ExamException {
    SubmissionStorageException(String message, Throwable cause) { super(message, cause); }
}
class ExamWindowClosedException extends ExamException {
    ExamWindowClosedException(String m) { super(m); }
}
class CourseRegistrationException extends ExamException {
    CourseRegistrationException(String m) { super(m); }
}
class Storage {
    void save(String fileName, long sizeKb) throws SubmissionStorageException {
        try {
            if (sizeKb > 500) throw new IOException("Disk quota exceeded");
            System.out.println("Saved " + fileName);
        } catch (IOException e) {
            throw new SubmissionStorageException("Could not store " + fileName, e);
        }
    }
}
class ExamService {
    private final Storage storage = new Storage();
    void submit(String file, long size, boolean windowOpen, boolean registered) throws ExamException {
        if (!windowOpen) throw new ExamWindowClosedException("Exam window has closed");
        if (!registered) throw new CourseRegistrationException("Student is not registered");
        storage.save(file, size);
    }
}
public class Main {
    public static void main(String[] args) {
        ExamService service = new ExamService();
        Object[][] tests = {
            {"answer01.pdf", 120L, true, true},
            {"answer02.pdf", 800L, true, true},
            {"late.pdf", 100L, false, true},
            {"unregistered.pdf", 100L, true, false}
        };
        for (Object[] t : tests) {
            try {
                service.submit((String)t[0], (Long)t[1], (Boolean)t[2], (Boolean)t[3]);
            } catch (ExamException e) {
                System.out.println("Error: " + e.getMessage());
                if (e.getCause() != null) System.out.println("Cause: " + e.getCause().getMessage());
                System.out.println("Exception type: " + e.getClass().getSimpleName());
            }
        }
    }
}