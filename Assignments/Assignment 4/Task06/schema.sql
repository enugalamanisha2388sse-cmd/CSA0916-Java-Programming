CREATE DATABASE IF NOT EXISTS campus_events;
USE campus_events;

CREATE TABLE IF NOT EXISTS registrations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(120) NOT NULL,
    email VARCHAR(190) NOT NULL,
    event_name VARCHAR(120) NOT NULL,
    registered_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_student_event UNIQUE (email, event_name)
);

-- Pagination example (page size 100; page 1 has offset 0)
SELECT id, student_name, email, event_name
FROM registrations
WHERE event_name = 'Tech Fest'
ORDER BY id DESC
LIMIT 100 OFFSET 0;
