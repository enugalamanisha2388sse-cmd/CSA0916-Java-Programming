CREATE DATABASE IF NOT EXISTS buspass_test;
USE buspass_test;

CREATE TABLE IF NOT EXISTS wallet (
    wallet_id BIGINT PRIMARY KEY,
    wallet_name VARCHAR(80) NOT NULL,
    balance DECIMAL(12,2) NOT NULL CHECK (balance >= 0)
) ENGINE=InnoDB;

INSERT INTO wallet(wallet_id, wallet_name, balance)
VALUES (1, 'Hostel Wallet', 5000.00), (2, 'Canteen Wallet', 1000.00)
ON DUPLICATE KEY UPDATE wallet_name=VALUES(wallet_name);

CREATE TABLE IF NOT EXISTS demo_user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(80) NOT NULL UNIQUE,
    password_text VARCHAR(120) NOT NULL
) ENGINE=InnoDB;

INSERT INTO demo_user(username, password_text)
VALUES ('testuser', 'correct-demo-password')
ON DUPLICATE KEY UPDATE username=VALUES(username);
