// Alle Reservationen auf einmal laden, nur Scroll zeigt 10-15 sichtbar

const tbodyEl   = document.getElementById('reservations');
const headEl    = document.getElementById('resHead');
const emptyMsgEl= document.getElementById('emptyMsg');

// Datum + Zeit formatieren
function fmtDate(iso) {
    if (!iso) return { date: "", time: "" };
    const [d, t] = iso.split("T");
    const [year, month, day] = d.split("-");
    const time = (t || "").slice(0, 5);
    return { date: `${day}.${month}.${year}`, time: time };
}

// Tabellenzeile rendern
function renderItem(item) {
    const s = fmtDate(item.startAt);
    const e = fmtDate(item.endAt);
    const tr = document.createElement("tr");
    tr.innerHTML = `
    <td style="padding:8px 12px; border-bottom:1px solid #333;">${s.date}</td>
    <td style="padding:8px 12px; border-bottom:1px solid #333;">${s.time}</td>
    <td style="padding:8px 12px; border-bottom:1px solid #333;">${e.time}</td>
    <td style="padding:8px 12px; border-bottom:1px solid #333;">Raum ${item.roomId}</td>
  `;
    tbodyEl.appendChild(tr);
}

// Alles laden, Scroll begrenzt nur Anzeige
async function loadAll() {
    const resp = await fetch(`/api/reservations?page=0&size=9999`);
    const data = await resp.json();

    if (data.empty || data.content.length === 0) {
        emptyMsgEl.style.display = 'block';
        return;
    }

    headEl.style.display = 'table-header-group';
    data.content.forEach(renderItem);
}

// Start
document.addEventListener('DOMContentLoaded', loadAll);
