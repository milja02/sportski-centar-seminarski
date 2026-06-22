package rs.fon.domen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClanskaKartaTest {

    private Mesto mesto;
    private Polaznik polaznik;
    private ClanskaKarta clanskaKarta;

    @BeforeEach
    void setUp() {
        mesto = new Mesto(1, "Beograd", 11000);
        polaznik = new Polaznik(1, "Marko", "Markovic", "0611234567", mesto);
        clanskaKarta = new ClanskaKarta(1, new Date(), 6000, polaznik);
        clanskaKarta.dodajStavku(new StavkaClanskeKarte(clanskaKarta, 1, 2, 4000, new Sport(1, "Plivanje", 2000)));
        clanskaKarta.dodajStavku(new StavkaClanskeKarte(clanskaKarta, 2, 1, 2000, new Sport(2, "Tenis", 2000)));
    }

    @AfterEach
    void tearDown() {
        clanskaKarta = null;
        polaznik = null;
        mesto = null;
    }

    @Test
    void konstruktorPostavljaAtribute() {
        Date datum = clanskaKarta.getDatumUclanjenja();

        assertEquals(1, clanskaKarta.getIdClanskaKarta());
        assertNotNull(datum);
        assertEquals(6000, clanskaKarta.getUkupanIznos());
        assertSame(polaznik, clanskaKarta.getPolaznik());
    }

    @Test
    void setteriPostavljajuAtribute() {
        clanskaKarta = new ClanskaKarta();
        Date datum = new Date();
        List<StavkaClanskeKarte> stavke = new ArrayList<>();

        clanskaKarta.setIdClanskaKarta(2);
        clanskaKarta.setDatumUclanjenja(datum);
        clanskaKarta.setUkupanIznos(2000);
        clanskaKarta.setPolaznik(polaznik);
        clanskaKarta.setStavke(stavke);

        assertEquals(2, clanskaKarta.getIdClanskaKarta());
        assertEquals(datum, clanskaKarta.getDatumUclanjenja());
        assertEquals(2000, clanskaKarta.getUkupanIznos());
        assertSame(polaznik, clanskaKarta.getPolaznik());
        assertSame(stavke, clanskaKarta.getStavke());
    }

    @Test
    void setIdClanskaKarta_bacaIllegalArgumentException_kadaJeNula() {
        assertThrows(IllegalArgumentException.class, () -> clanskaKarta.setIdClanskaKarta(0));
    }

    @Test
    void setStavke_bacaNullPointerException_kadaJeListaNull() {
        assertThrows(NullPointerException.class, () -> clanskaKarta.setStavke(null));
    }

    @Test
    void setDatumUclanjenja_bacaNullPointerException_kadaJeNull() {
        assertThrows(NullPointerException.class, () -> clanskaKarta.setDatumUclanjenja(null));
    }

    @Test
    void setPolaznik_bacaNullPointerException_kadaJeNull() {
        assertThrows(NullPointerException.class, () -> clanskaKarta.setPolaznik(null));
    }

    @Test
    void setPolaznik_bacaIllegalArgumentException_kadaJePolaznikNeispravan() {
        Polaznik lose = new Polaznik();
        lose.setIme("Marko");
        lose.setPrezime("Markovic");
        lose.setBrojTelefona("0611234567");
        lose.setMesto(mesto);
        assertThrows(IllegalArgumentException.class, () -> clanskaKarta.setPolaznik(lose));
    }

    @Test
    void setUkupanIznos_bacaIllegalArgumentException_kadaNijeZbirStavki() {
        assertThrows(IllegalArgumentException.class, () -> clanskaKarta.setUkupanIznos(9999));
    }

    @Test
    void dodajStavku_bacaNullPointerException_kadaJeStavkaNull() {
        assertThrows(NullPointerException.class, () -> clanskaKarta.dodajStavku(null));
    }

    @Test
    void dodajStavku_postavljaReferencuNaKartu() {
        clanskaKarta = new ClanskaKarta();
        clanskaKarta.setIdClanskaKarta(1);
        StavkaClanskeKarte stavka = new StavkaClanskeKarte(clanskaKarta, 1, 2, 4000, new Sport(1, "Plivanje", 2000));

        clanskaKarta.dodajStavku(stavka);

        assertEquals(1, clanskaKarta.getStavke().size());
        assertSame(clanskaKarta, stavka.getClanskaKarta());
    }

    @Test
    void izracunajZbirStavki_racunaZbirIznosa() {
        assertEquals(6000, clanskaKarta.izracunajZbirStavki());
    }

    @Test
    void izracunajZbirStavki_vracaNuluZaPraznuListu() {
        clanskaKarta.setStavke(new ArrayList<>());
        assertEquals(0, clanskaKarta.izracunajZbirStavki());
    }

    @Test
    void validiraj_prolaziZaIspravnuKartuSaStavkama() {
        assertDoesNotThrow(clanskaKarta::validiraj);
    }

    @Test
    void validiraj_bacaIllegalArgumentException_kadaJeIdNula() {
        clanskaKarta = new ClanskaKarta();
        clanskaKarta.setDatumUclanjenja(new Date());
        clanskaKarta.setPolaznik(polaznik);
        clanskaKarta.setUkupanIznos(6000);
        assertThrows(IllegalArgumentException.class, clanskaKarta::validiraj);
    }

    @Test
    void validiraj_bacaNullPointerException_kadaJeDatumNull() {
        clanskaKarta = new ClanskaKarta();
        clanskaKarta.setIdClanskaKarta(1);
        clanskaKarta.setPolaznik(polaznik);
        clanskaKarta.setUkupanIznos(6000);
        assertThrows(NullPointerException.class, clanskaKarta::validiraj);
    }

    @Test
    void validiraj_bacaNullPointerException_kadaJePolaznikNull() {
        clanskaKarta = new ClanskaKarta();
        clanskaKarta.setIdClanskaKarta(1);
        clanskaKarta.setDatumUclanjenja(new Date());
        clanskaKarta.setUkupanIznos(6000);
        assertThrows(NullPointerException.class, clanskaKarta::validiraj);
    }

    @Test
    void validiraj_bacaIllegalArgumentException_kadaJeUkupanIznosNula() {
        clanskaKarta = new ClanskaKarta();
        clanskaKarta.setIdClanskaKarta(1);
        clanskaKarta.setDatumUclanjenja(new Date());
        clanskaKarta.setPolaznik(polaznik);
        assertThrows(IllegalArgumentException.class, clanskaKarta::validiraj);
    }

    @Test
    void validiraj_bacaNullPointerException_kadaJeStavkaURListiNull() {
        clanskaKarta.getStavke().add(null);
        assertThrows(NullPointerException.class, clanskaKarta::validiraj);
    }

    @Test
    void validiraj_bacaIllegalArgumentException_kadaUkupanIznosNijeZbirStavki() {
        clanskaKarta.setStavke(new ArrayList<>());
        clanskaKarta.getStavke().add(new StavkaClanskeKarte(clanskaKarta, 1, 2, 4000, new Sport(1, "Plivanje", 2000)));
        assertThrows(IllegalArgumentException.class, clanskaKarta::validiraj);
    }

    @Test
    void validiraj_bacaIllegalArgumentException_kadaNemaStavkiAliIznosJeVećiOdNule() {
        clanskaKarta = new ClanskaKarta(1, new Date(), 3000, polaznik);
        assertThrows(IllegalArgumentException.class, clanskaKarta::validiraj);
    }

    @Test
    void equals_vracaTrueZaIstiObjekat() {
        assertEquals(clanskaKarta, clanskaKarta);
    }

    @Test
    void equals_vracaFalseZaNull() {
        assertFalse(clanskaKarta.equals(null));
    }

    @Test
    void equals_vracaFalseZaDruguKlasu() {
        assertFalse(clanskaKarta.equals("karta"));
    }

    @ParameterizedTest
    @MethodSource("clanskaKartaEquals")
    void equals_porediClanskeKarte(Polaznik drugiPolaznik, int idClanskaKarta, int ukupanIznos, boolean ocekivano) {
        Date datum = clanskaKarta.getDatumUclanjenja();
        ClanskaKarta druga = new ClanskaKarta(idClanskaKarta, datum, ukupanIznos, drugiPolaznik);
        if (ocekivano) {
            assertEquals(clanskaKarta, druga);
        } else {
            assertNotEquals(clanskaKarta, druga);
        }
    }

    private static Stream<Arguments> clanskaKartaEquals() {
        Mesto mesto = new Mesto(1, "Beograd", 11000);
        Polaznik polaznik = new Polaznik(1, "Marko", "Markovic", "0611234567", mesto);
        Polaznik drugiPolaznik = new Polaznik(2, "Ana", "Anic", "0629876543", mesto);
        return Stream.of(
                Arguments.of(polaznik, 2, 2000, true),
                Arguments.of(drugiPolaznik, 1, 6000, false)
        );
    }

    @Test
    void equals_vracaFalseZaRazlicitDatum() {
        Date drugiDatum = new Date(clanskaKarta.getDatumUclanjenja().getTime() + 1);
        ClanskaKarta druga = new ClanskaKarta(1, drugiDatum, 6000, polaznik);
        assertNotEquals(clanskaKarta, druga);
    }

    @Test
    void hashCode_jednakZaJednakeKarte() {
        Date datum = clanskaKarta.getDatumUclanjenja();
        ClanskaKarta druga = new ClanskaKarta(2, datum, 2000, polaznik);
        assertEquals(clanskaKarta.hashCode(), druga.hashCode());
    }

    @Test
    void toString_sadrziDatumUkupanIznosIPolaznika() {
        String tekst = clanskaKarta.toString();

        assertTrue(tekst.contains("ukupanIznos=6000"));
        assertTrue(tekst.contains("polaznik="));
        assertTrue(tekst.contains("datumUclanjenja="));
    }
}
