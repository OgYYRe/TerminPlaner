INSERT INTO rooms (id, name) VALUES
     (101),
     (102),
     (103),
     (104),
     (105)
ON DUPLICATE KEY UPDATE name = VALUES(name);