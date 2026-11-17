import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Generic CRUD contract shared by StudentDB, CourseDB and MarksDB.
interface Dao<T, K> {
    void insert(T item) throws SQLException;
    Optional<T> findById(K id) throws SQLException;
    List<T> findAll() throws SQLException;
    void update(T item) throws SQLException;
    void delete(K id) throws SQLException;
}

// Object-to-table mapping: id -> student.id (primary key).
record Student(int id, String registerNo, String name, String email) {
}

class StudentDao implements Dao<Student, Integer> {
    private final Connection connection;

    StudentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Student student) throws SQLException {
        String sql = "INSERT INTO student(id, register_no, name, email) VALUES(?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            bindStudent(statement, student);
            statement.executeUpdate();
        }
    }

    @Override
    public Optional<Student> findById(Integer id) throws SQLException {
        String sql = "SELECT id, register_no, name, email FROM student WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Optional.of(mapStudent(result));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, register_no, name, email FROM student ORDER BY id";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                students.add(mapStudent(result));
            }
        }
        return students;
    }

    @Override
    public void update(Student student) throws SQLException {
        String sql = "UPDATE student SET register_no=?, name=?, email=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.registerNo());
            statement.setString(2, student.name());
            statement.setString(3, student.email());
            statement.setInt(4, student.id());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("Student not found: " + student.id());
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement =
                 connection.prepareStatement("DELETE FROM student WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private static void bindStudent(PreparedStatement statement, Student student)
            throws SQLException {
        statement.setInt(1, student.id());
        statement.setString(2, student.registerNo());
        statement.setString(3, student.name());
        statement.setString(4, student.email());
    }

    private static Student mapStudent(ResultSet result) throws SQLException {
        return new Student(
            result.getInt("id"),
            result.getString("register_no"),
            result.getString("name"),
            result.getString("email")
        );
    }
}

public class Main {
    private static Connection openConnection() throws SQLException {
        String url = System.getenv().getOrDefault(
            "DB_URL", "jdbc:mysql://localhost:3306/buspass_test"
        );
        String user = System.getenv().getOrDefault("DB_USER", "root");
        String password = System.getenv().getOrDefault("DB_PASSWORD", "");
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        try (Connection connection = openConnection()) {
            StudentDao dao = new StudentDao(connection);
            // Remove an earlier demo row so the program can be executed repeatedly.
            dao.delete(1001);
            Student sample = new Student(1001, "REG1001", "Asha", "asha@example.com");

            dao.insert(sample);
            System.out.println("INSERT: " + dao.findById(1001).orElse(null));

            Student changed = new Student(1001, "REG1001", "Asha Kumar",
                "asha.kumar@example.com");
            dao.update(changed);
            System.out.println("UPDATE: " + dao.findById(1001).orElse(null));

            System.out.println("FIND ALL:");
            for (Student student : dao.findAll()) {
                System.out.println(student);
            }

            dao.delete(1001);
            System.out.println("DELETE: " + dao.findById(1001).orElse(null));
        } catch (SQLException exception) {
            System.err.println("Database operation failed: " + exception.getMessage());
            System.err.println("Create the schema and set DB_URL, DB_USER and DB_PASSWORD.");
            System.exit(1);
        }
    }
}
