-- users 테이블
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100),
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- schedules 테이블
CREATE TABLE schedules (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           title VARCHAR(200) NOT NULL,
                           contents TEXT,
                           start_time DATETIME NOT NULL,
                           end_time DATETIME NOT NULL,
                           password VARCHAR(100) NOT NULL,
                           name VARCHAR(50) NOT NULL,
                           created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                           user_id BIGINT,
                           CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
