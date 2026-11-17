# Task03 – A3: Data-Access Design Pattern
Common JDBC steps: obtain connection, prepare SQL, bind parameters, execute, map ResultSet, handle SQLExceptions and close resources. Differences: table/column names, key type, SQL and row-to-object mapping.
Generic contract: Dao<T,K> with insert(T), findById(K), findAll(), update(T), delete(K). StudentDao implements Dao<Student,Integer>; map Student.id -> student.id (primary key), registerNumber -> register_no, name -> name, email -> email.
A generic DAO centralizes lifecycle and CRUD conventions and improves testability. SQL inside service/GUI classes couples persistence to business/UI code and encourages duplication. Avoid over-generalizing complex queries; use focused repository methods when needed.
