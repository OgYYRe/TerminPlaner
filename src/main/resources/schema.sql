SET NAMES utf8mb4;
SET time_zone = '+00:00';

CREATE TABLE IF NOT EXISTS rooms (
                                     id   INT PRIMARY KEY
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS reservations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_id INT NOT NULL,
    start_at DATETIME NOT NULL,
    end_at   DATETIME NOT NULL,
    remark   VARCHAR(200) NOT NULL,
    public_code  VARCHAR(32) NOT NULL UNIQUE,
    private_code VARCHAR(32) NOT NULL UNIQUE,
    participants VARCHAR(200) NOT NULL,
    CONSTRAINT chk_time CHECK (end_at > start_at),
    CONSTRAINT fk_room FOREIGN KEY (room_id) REFERENCES rooms(id)
    ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_res_room_time ON reservations (room_id, start_at, end_at);