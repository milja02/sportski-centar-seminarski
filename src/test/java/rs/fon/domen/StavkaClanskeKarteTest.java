package rs.fon.domen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StavkaClanskeKarteTest {

    private ClanskaKarta clanskaKarta;
    private Sport sport;
    private StavkaClanskeKarte stavka;

    @BeforeEach
    void setUp() {
        clanskaKarta = new ClanskaKarta();
        clanskaKarta.setIdClanskaKarta(1);
        sport = new Sport(1, "Plivanje", 2000);
        stavka = new StavkaClanskeKarte(clanskaKarta, 1, 2, 4000, sport);
    }

    @AfterEach
    void tearDown() {
        stavka = null;
        sport = null;
        clanskaKarta = null;
    }

    @Test
    void konstruktorPostavljaAtribute() {
        assertSame(clanskaKarta, stavka.getClanskaKarta());
        assertEquals(1, stavka.getRb());
        assertEquals(2, stavka.getBrojTermina());
        assertEquals(4000, stavka.getIznosStavke());
        assertSame(sport, stavka.getSport());
    }

    @Test
    void setteriPostavljajuAtribute() {
        stavka = new StavkaClanskeKarte();
        ClanskaKarta karta = new ClanskaKarta();
        karta.setIdClanskaKarta(5);
        Sport noviSport = new Sport(2, "Tenis", 1500);

        stavka.setClanskaKarta(karta);
        stavka.setRb(2);
        stavka.setBrojTermina(4);
        stavka.setSport(noviSport);
        stavka.setIznosStavke(6000);

        assertSame(karta, stavka.getClanskaKarta());
        assertEquals(2, stavka.getRb());
        assertEquals(4, stavka.getBrojTermina());
        assertEquals(6000, stavka.getIznosStavke());
        assertSame(noviSport, stavka.getSport());
    }

    @Test
    void setClanskaKarta_bacaNullPointerException_kadaJeNull() {
        stavka = new StavkaClanskeKarte();
        assertThrows(NullPointerException.class, () -> stavka.setClanskaKarta(null));
    }

    @Test
    void setClanskaKarta_bacaIllegalArgumentException_kadaJeIdNula() {
        stavka = new StavkaClanskeKarte();
        ClanskaKarta karta = new ClanskaKarta();
        assertThrows(IllegalArgumentException.class, () -> stavka.setClanskaKarta(karta));
    }

    @Test
    void setSport_bacaNullPointerException_kadaJeNull() {
        stavka = new StavkaClanskeKarte();
        assertThrows(NullPointerException.class, () -> stavka.setSport(null));
    }

    @Test
    void setSport_bacaIllegalArgumentException_kadaJeSportNeispravan() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setSport(new Sport(0, "Plivanje", 2000)));
    }

    @Test
    void setRb_bacaIllegalArgumentException_kadaJeNula() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setRb(0));
    }

    @Test
    void setBrojTermina_bacaIllegalArgumentException_kadaJeNula() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setBrojTermina(0));
    }

    @Test
    void setIznosStavke_bacaIllegalArgumentException_kadaIznosNijeJednakProizvodu() {
        assertThrows(IllegalArgumentException.class, () -> stavka.setIznosStavke(5000));
    }

    @Test
    void izracunajOcekivaniIznos_racunaProizvodBrojaTerminaICene() {
        stavka = new StavkaClanskeKarte();
        stavka.setBrojTermina(3);
        stavka.setSport(new Sport(1, "Plivanje", 2000));

        assertEquals(6000, stavka.izracunajOcekivaniIznos());
    }

    @Test
    void izracunajOcekivaniIznos_bacaNullPointerException_kadaJeSportNull() {
        stavka = new StavkaClanskeKarte();
        assertThrows(NullPointerException.class, stavka::izracunajOcekivaniIznos);
    }

    @Test
    void konstruktor_prolaziZaGranicniBrojTerminaJedan() {
        StavkaClanskeKarte stavkaJedan = new StavkaClanskeKarte(clanskaKarta, 1, 1, 2000, sport);
        assertEquals(1, stavkaJedan.getBrojTermina());
        assertEquals(2000, stavkaJedan.getIznosStavke());
    }

    @Test
    void toString_sadrziRbBrojTerminaIIznos() {
        String tekst = stavka.toString();

        assertTrue(tekst.contains("rb=1"));
        assertTrue(tekst.contains("brojTermina=2"));
        assertTrue(tekst.contains("iznosStavke=4000"));
    }
}
