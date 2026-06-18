package rs.fon.domen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SportTest {

    private Sport sport;

    @BeforeEach
    void setUp() {
        sport = new Sport(1, "Plivanje", 2000);
    }

    @AfterEach
    void tearDown() {
        sport = null;
    }

    @Test
    void konstruktorPostavljaAtribute() {
        assertEquals(1, sport.getIdSport());
        assertEquals("Plivanje", sport.getNaziv());
        assertEquals(2000, sport.getCena());
    }

    @Test
    void setteriPostavljajuAtribute() {
        sport = new Sport();
        sport.setIdSport(2);
        sport.setNaziv("Tenis");
        sport.setCena(1500);

        assertEquals(2, sport.getIdSport());
        assertEquals("Tenis", sport.getNaziv());
        assertEquals(1500, sport.getCena());
    }

    @Test
    void setIdSport_bacaIllegalArgumentException_kadaJeNula() {
        sport = new Sport();
        assertThrows(IllegalArgumentException.class, () -> sport.setIdSport(0));
    }

    @Test
    void setIdSport_bacaIllegalArgumentException_kadaJeNegativan() {
        sport = new Sport();
        assertThrows(IllegalArgumentException.class, () -> sport.setIdSport(-5));
    }

    @Test
    void setNaziv_bacaNullPointerException_kadaJeNull() {
        sport = new Sport();
        assertThrows(NullPointerException.class, () -> sport.setNaziv(null));
    }

    @Test
    void setNaziv_bacaIllegalArgumentException_kadaJePrazan() {
        sport = new Sport();
        assertThrows(IllegalArgumentException.class, () -> sport.setNaziv(""));
    }

    @Test
    void setNaziv_bacaIllegalArgumentException_kadaJeBlank() {
        sport = new Sport();
        assertThrows(IllegalArgumentException.class, () -> sport.setNaziv("  "));
    }

    @Test
    void setCena_bacaIllegalArgumentException_kadaJeNegativna() {
        assertThrows(IllegalArgumentException.class, () -> sport.setCena(-100));
    }

    @Test
    void equals_vracaTrueZaIstiObjekat() {
        assertEquals(sport, sport);
    }

    @Test
    void equals_vracaFalseZaNull() {
        assertFalse(sport.equals(null));
    }

    @Test
    void equals_vracaFalseZaDruguKlasu() {
        assertFalse(sport.equals("Plivanje"));
    }

    @Test
    void equals_vracaTrueZaIstiNaziv() {
        Sport drugi = new Sport(2, "Plivanje", 3000);
        assertEquals(sport, drugi);
    }

    @Test
    void equals_vracaFalseZaRazlicitNaziv() {
        Sport drugi = new Sport(1, "Tenis", 2000);
        assertNotEquals(sport, drugi);
    }

    @Test
    void hashCode_jednakZaIstiNaziv() {
        Sport drugi = new Sport(2, "Plivanje", 3000);
        assertEquals(sport.hashCode(), drugi.hashCode());
    }

    @Test
    void toString_vracaNaziv() {
        assertEquals("Plivanje", sport.toString());
    }
}
