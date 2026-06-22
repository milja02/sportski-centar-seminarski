package rs.fon.domen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PolaznikTest {

    private Mesto mesto;
    private Polaznik polaznik;

    @BeforeEach
    void setUp() {
        mesto = new Mesto(1, "Beograd", 11000);
        polaznik = new Polaznik(1, "Marko", "Markovic", "0611234567", mesto);
    }

    @AfterEach
    void tearDown() {
        polaznik = null;
        mesto = null;
    }

    @Test
    void konstruktorPostavljaAtribute() {
        assertEquals(1, polaznik.getIdPolaznik());
        assertEquals("Marko", polaznik.getIme());
        assertEquals("Markovic", polaznik.getPrezime());
        assertEquals("0611234567", polaznik.getBrojTelefona());
        assertEquals(mesto, polaznik.getMesto());
    }

    @Test
    void setteriPostavljajuAtribute() {
        polaznik = new Polaznik();
        polaznik.setIdPolaznik(2);
        polaznik.setIme("Ana");
        polaznik.setPrezime("Anic");
        polaznik.setBrojTelefona("0629876543");
        polaznik.setMesto(mesto);

        assertEquals(2, polaznik.getIdPolaznik());
        assertEquals("Ana", polaznik.getIme());
        assertEquals("Anic", polaznik.getPrezime());
        assertEquals("0629876543", polaznik.getBrojTelefona());
        assertEquals(mesto, polaznik.getMesto());
    }

    @Test
    void setIdPolaznik_bacaIllegalArgumentException_kadaJeNula() {
        polaznik = new Polaznik();
        assertThrows(IllegalArgumentException.class, () -> polaznik.setIdPolaznik(0));
    }

    @Test
    void setIme_bacaNullPointerException_kadaJeNull() {
        polaznik = new Polaznik();
        assertThrows(NullPointerException.class, () -> polaznik.setIme(null));
    }

    @Test
    void setIme_bacaIllegalArgumentException_kadaJePrazno() {
        polaznik = new Polaznik();
        assertThrows(IllegalArgumentException.class, () -> polaznik.setIme(""));
    }

    @Test
    void setPrezime_bacaNullPointerException_kadaJeNull() {
        polaznik = new Polaznik();
        assertThrows(NullPointerException.class, () -> polaznik.setPrezime(null));
    }

    @Test
    void setBrojTelefona_bacaNullPointerException_kadaJeNull() {
        polaznik = new Polaznik();
        assertThrows(NullPointerException.class, () -> polaznik.setBrojTelefona(null));
    }

    @Test
    void setBrojTelefona_bacaIllegalArgumentException_kadaJePrazan() {
        polaznik = new Polaznik();
        assertThrows(IllegalArgumentException.class, () -> polaznik.setBrojTelefona(""));
    }

    @Test
    void setMesto_bacaNullPointerException_kadaJeNull() {
        polaznik = new Polaznik();
        assertThrows(NullPointerException.class, () -> polaznik.setMesto(null));
    }

    @Test
    void setMesto_bacaIllegalArgumentException_kadaJeMestoNeispravno() {
        polaznik = new Polaznik();
        Mesto loseMesto = new Mesto();
        loseMesto.setNaziv("Beograd");
        assertThrows(IllegalArgumentException.class, () -> polaznik.setMesto(loseMesto));
    }

    @Test
    void equals_vracaTrueZaIstiObjekat() {
        assertEquals(polaznik, polaznik);
    }

    @Test
    void equals_vracaFalseZaNull() {
        assertFalse(polaznik.equals(null));
    }

    @Test
    void equals_vracaFalseZaDruguKlasu() {
        assertFalse(polaznik.equals("Marko Markovic"));
    }

    @ParameterizedTest
    @MethodSource("polaznikEquals")
    void equals_porediPolaznike(Polaznik drugi, boolean ocekivano) {
        if (ocekivano) {
            assertEquals(polaznik, drugi);
        } else {
            assertNotEquals(polaznik, drugi);
        }
    }

    private static Stream<Arguments> polaznikEquals() {
        Mesto mesto = new Mesto(1, "Beograd", 11000);
        return Stream.of(
                Arguments.of(new Polaznik(2, "Marko", "Markovic", "0611234567", mesto), true),
                Arguments.of(new Polaznik(1, "Petar", "Markovic", "0611234567", mesto), false),
                Arguments.of(new Polaznik(1, "Marko", "Anic", "0611234567", mesto), false),
                Arguments.of(new Polaznik(1, "Marko", "Markovic", "0629876543", mesto), false)
        );
    }

    @Test
    void hashCode_jednakZaJednakePolaznike() {
        Polaznik drugi = new Polaznik(2, "Marko", "Markovic", "0611234567", mesto);
        assertEquals(polaznik.hashCode(), drugi.hashCode());
    }

    @Test
    void toString_vracaImeIPrezime() {
        assertEquals("Marko Markovic", polaznik.toString());
    }
}
