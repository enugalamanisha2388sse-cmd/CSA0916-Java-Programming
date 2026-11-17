CREATE DATABASE IF NOT EXISTS buspass_test;
USE buspass_test;
CREATE TABLE IF NOT EXISTS bus_pass(pass_id VARCHAR(24) PRIMARY KEY,register_no VARCHAR(32) NOT NULL,student_name VARCHAR(120) NOT NULL,route_no INT NOT NULL,boarding_stop VARCHAR(120) NOT NULL,valid_until DATE NOT NULL,amount_paid DECIMAL(10,2) NOT NULL CHECK(amount_paid>=0));
