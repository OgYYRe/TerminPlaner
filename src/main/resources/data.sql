-- Räume einfügen (IDs 101 bis 105)
-- Beachten Sie: Wenn 'id' INT PRIMARY KEY ist,
-- müssen wir möglicherweise die Werte explizit setzen, oder die Tabelle hat AUTO_INCREMENT.
-- Ich gehe davon aus, dass wir die ID selbst setzen.

INSERT INTO rooms (id) VALUES
(101),
(102),
(103),
(104),
(105);

-- Beispieldatensätze für Reservations einfügen
INSERT INTO reservations (room_id, start_at, end_at, remark, public_code, private_code) VALUES
(
    101,
    '2025-12-01 10:00:00',
    '2025-12-01 11:00:00',
    'Wöchentliches Team-Meeting',
    'PUB_TEAM1',
    'PRIV_XYZ1'
),
(
    103,
    '2025-12-05 14:30:00',
    '2025-12-05 16:00:00',
    'Kundenpräsentation',
    'PUB_KUNDE',
    'PRIV_XYZ2'
),
(
    105,
    '2025-12-10 09:00:00',
    '2025-12-10 17:00:00',
    'Ganztages-Workshop',
    'PUB_WS_ALL',
    'PRIV_XYZ3'
);