import java.util.List;
import java.util.Optional;

// Generic Data Access Object contract for CRUD operations.
interface Dao<T, K> {
    void insert(T item) throws Exception;

    Optional<T> findById(K id) throws Exception;

    List<T> findAll() throws Exception;

    void update(T item) throws Exception;

    void delete(K id) throws Exception;
}

// Student entity represented as a record.
record Student(int id, String registerNo, String name, String email) {
}

// DAO implementation skeleton for Student records.
class StudentDao implements Dao<Student, Integer> {

    @Override
    public void insert(Student student) {
        // Add JDBC INSERT implementation here.
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public void update(Student student) {
        // Add JDBC UPDATE implementation here.
    }

    @Override
    public void delete(Integer id) {
        // Add JDBC DELETE implementation here.
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("Generic DAO CRUD contract; Student.id maps to primary key student.id.");
    }
}
