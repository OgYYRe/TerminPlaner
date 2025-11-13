INSERT IGNORE INTO rooms (id) VALUES
    (101),
    (102),
    (103),
    (104),
    (105);

INSERT INTO reservations (room_id, start_at, end_at, remark, public_code, private_code, participants) VALUES
(
  101,
  '2025-12-01 10:00:00',
  '2025-12-01 11:00:00',
  'Wöchentliches Team-Meeting',
  'p6qMh4uT',
  '8R38dgdO',
  'Max Mustermann, Erika Mustermann'
);