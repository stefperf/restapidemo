CREATE TABLE app_user (
    user_number BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Auto-increment userNumber, serves as primary key
    userid VARCHAR(255) UNIQUE NOT NULL,           -- Unique userid
    name VARCHAR(255) NOT NULL,                    -- Name
    surname VARCHAR(255) NOT NULL                  -- Surname
);
