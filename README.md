# Projektarbeit M223
## Abschlussprojekt – Termin Planer
**Oguzhan Cetinkaya, Leon Reiter, Luca Waldvogel**

---

## Inhaltsverzeichnis
1. [Vorwort](#1-vorwort)
2. [Diagramme](#2-diagramme)  
   2.1 [UML-Zustandsdiagramm](#21-uml-zustandsdiagramm)  
   2.2 [ERM/ERD](#22-ermerd)  
   2.3 [UML-Klassendiagramm](#23-uml-klassendiagramm)
3. [Journal](#3-journal)

---

## 1. Vorwort

Im Modul M223 „Multiuser-Applikationen objektorientiert realisieren“ bestand die Aufgabe darin, eine vollständige Webanwendung im Team zu entwickeln.  
Unsere Anwendung – **Termin Planer** – ermöglicht die Verwaltung von Räumen und Terminen in einem Unternehmensgebäude.  
Benutzer können Räume für Sitzungen oder Veranstaltungen reservieren, die Reservationen verwalten und mit privaten oder öffentlichen Schlüsseln teilen.  
Das Projekt umfasst die Modellierung (UML- und ER-Diagramme), die Implementierung der Model- und Controller-Klassen sowie eine funktionierende Datenbankanbindung mit MySQL und Docker.

---

## 2. Diagramme

### 2.1 UML-Zustandsdiagramm
Bild hinzufügen

### 2.2 ERM/ERD
![ERM Diagramm](./src/main/resources/static/images/ERD.png)

### 2.3 UML-Klassendiagramm
![UML Klassendiagramm](./src/main/resources/static/images/UML-Klassendiagramm.png)

---

## 3. Journal


| Datum      | Aufwand | Wer | Was                                                                                                                                                                                                                                                                                           |
|:-----------|:--------|:----|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 30.10.2025 | 30 min  | Team | Wir haben in einem Call geplant, wie wir vorgehen wollen und die ersten Aufgaben wie folgt aufgeteilt:<br><br>![Screenshot aus Teams](./src/main/resources/static/images/Teams-Screenshot-1.png)                                                                                              |
| 30.10.2025 | 1 h     | Luca | Informationen über die Datenbank gesammelt und ein ERM mit draw.io aufgesetzt.<br><br>![Screenshot aus Teams](./src/main/resources/static/images/Teams-Screenshot-2.png)<br>Zusätzlich habe ich den Doku-Ordner sowie ein Dokumentationsfile aufgesetzt und in die Versionierung aufgenommen. |
| 09.10.2025 | 1 h     | Luca | Erste Implementation vom UML-Klassendiagramm mit draw.io, muss aber noch erweitert werden.                                                                                                                                                                                                    |
| 06.11.2025 | 2 h     | Luca | UML-Klassendiagramm fertiggestellt, die Model-Klassen aufgesetzt und das Docker-Compose-File der Datenbank überarbeitet.                                                                                                                                                                      |
| 09.11.2025 | 5 h     | Luca | Zuerst habe ich die HTML Dateien überarbeitet und ein CSS hinzugefügt. Danach habe ich die Datenbank, den ReservationService, die Datenmodelle und mehr aufgesetzt. Es können nun Reservationen erfasst und in der Datenbank gespeichert werden.                                              |

---

*Stand: November 2025*
