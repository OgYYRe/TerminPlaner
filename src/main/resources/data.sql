-- Räume einfügen (IDs 101 bis 105)
-- (Dieser Teil war wahrscheinlich in Ordnung, da der Fehler bei Statement #2 auftrat)
INSERT IGNORE INTO rooms (id) VALUES
    (101),
    (102),
    (103),
    (104),
    (105);

-- Beispieldatensätze für Reservations einfügen
-- JETZT MIT DER SPALTE 'participants'
INSERT INTO reservations (room_id, start_at, end_at, remark, public_code, private_code, participants) VALUES
(
  101,
  '2025-12-01 10:00:00',
  '2025-12-01 11:00:00',
  'Wöchentliches Team-Meeting',
  'p6qMh4uT',
  '8R38dgdO',
  'Max Mustermann, Erika Mustermann' -- Beispiel-Teilnehmer
),
(
  103,
  '2025-12-05 14:30:00',
  '2025-12-05 16:00:00',
  'Kundenpräsentation',
  'ia7Aie2mn',
  'pappP738j',
  'Peter Beispiel' -- Beispiel-Teilnehmer
),
(
  105,
  '2025-12-10 09:00:00',
  '2025-12-10 17:00:00',
  'Ganztages-Workshop',
  'sd97hdbsA',
  'w1onfd898',
  'Alle Workshop-Teilnehmer' -- Beispiel-Teilnehmer
),
(102,'2025-12-02 09:00:00','2025-12-02 10:00:00','Projektbesprechung','r9Hw82aD','qP71jL9z','Erika Muster'),
(103,'2025-12-03 13:00:00','2025-12-03 14:30:00','Kundentermin','t5Gd33nP','s9Lp72vF','Peter Beispiel'),
(104,'2025-12-04 15:00:00','2025-12-04 17:00:00','Jahresplanung','m1Na82bC','z3Kr90pD','Sabine Test'),
(105,'2025-12-05 08:00:00','2025-12-05 09:00:00','Morgenbriefing','x7Hb19qT','v6Ds73rW','Lukas Demo'),
(101,'2025-12-06 11:00:00','2025-12-06 12:00:00','Budgetbesprechung','k3Td58pL','n2Yw94mX','Julia Beispiel'),
(102,'2025-12-07 09:30:00','2025-12-07 10:30:00','Sprint Review','u9Cc54sZ','e8Gt65aP','Marco Muster'),
(103,'2025-12-08 14:00:00','2025-12-08 15:00:00','Workshop Planung','f2Br68lA','h4Wx87dE','Tina Beispiel'),

(101,'2025-12-11 08:30:00','2025-12-11 09:30:00','Tagesstart','b8Wc21qR','j2Ur53pL','Florian Test'),
(102,'2025-12-12 10:00:00','2025-12-12 12:00:00','Abteilungsmeeting','y5Qp74fC','t9Hb36eZ','Sarah Muster'),
(103,'2025-12-13 09:00:00','2025-12-13 10:00:00','Planung Quartal','l6De49aM','c7Gs22bV','David Beispiel'),
(104,'2025-12-14 14:30:00','2025-12-14 16:00:00','Kundenpräsentation','g3Ht77rN','o8Fw99kJ','Lisa Demo'),
(104,'2025-12-09 13:00:00','2025-12-09 15:30:00','Projekt Kickoff','a5Lp82bQ','d6Mn70kV','Anna Test');