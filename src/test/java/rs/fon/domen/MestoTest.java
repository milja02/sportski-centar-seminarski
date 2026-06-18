package rs.fon.domen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MestoTest {

    private Mesto mesto;

    @BeforeEach
    void setUp() {
        mesto = new Mesto(1, "Beograd", 11000);
    }

    @AfterEach
    void tearDown() {
        mesto = null;
    }

    @Test
    void konstruktorPostavljaAtribute() {
        assertEquals(1, mesto.getIdMesto());
        assertEquals("Beograd", mesto.getNaziv());
        assertEquals(11000, mesto.getPostanskiBroj());
    }

    @Test
    void setteriPostavljajuAtribute() {
        mesto = new Mesto();
        mesto.setIdMesto(2);
        mesto.setNaziv("Novi Sad");
        mesto.setPostanskiBroj(21000);

        assertEquals(2, mesto.getIdMesto());
        assertEquals("Novi Sad", mesto.getNaziv());
        assertEquals(21000, mesto.getPostanskiBroj());
    }

    @Test
    void setIdMesto_bacaIllegalArgumentException_kadaJeNula() {
        mesto = new Mesto();
        assertThrows(IllegalArgumentException.class, () -> mesto.setIdMesto(0));
    }

    @Test
    void setIdMesto_bacaIllegalArgumentException_kadaJeNegativan() {
        mesto = new Mesto();
        assertThrows(IllegalArgumentException.class, () -> mesto.setIdMesto(-1));
    }

    @Test
    void setNaziv_bacaNullPointerException_kadaJeNull() {
        mesto = new Mesto();
        assertThrows(NullPointerException.class, () -> mesto.setNaziv(null));
    }

    @Test
    void setNaziv_bacaIllegalArgumentException_kadaJePrazan() {
        mesto = new Mesto();
        assertThrows(IllegalArgumentException.class, () -> mesto.setNaziv(""));
    }

    @Test
    void setNaziv_bacaIllegalArgumentException_kadaJeBlank() {
        mesto = new Mesto();
        assertThrows(IllegalArgumentException.class, () -> mesto.setNaziv("   "));
    }

    @Test
    void equals_vracaTrueZaIstiObjekat() {
        assertEquals(mesto, mesto);
    }

    @Test
    void equals_vracaFalseZaNull() {
        assertFalse(mesto.equals(null));
    }

    @Test
    void equals_vracaFalseZaDruguKlasu() {
        assertFalse(mesto.equals("Beograd"));
    }

    @Test
    void equals_vracaTrueZaIstaPolja() {
        Mesto drugo = new Mesto(2, "Beograd", 11000);
        assertEquals(mesto, drugo);
    }

    @Test
    void equals_vracaFalseZaRazlicitNaziv() {
        Mesto drugo = new Mesto(1, "Nis", 11000);
        assertNotEquals(mesto, drugo);
    }

    @Test
    void equals_vracaFalseZaRazlicitPostanskiBroj() {
        Mesto drugo = new Mesto(1, "Beograd", 18000);
        assertNotEquals(mesto, drugo);
    }

    @Test
    void hashCode_jednakZaJednakaMesta() {
        Mesto drugo = new Mesto(2, "Beograd", 11000);
        assertEquals(mesto.hashCode(), drugo.hashCode());
    }

    @Test
    void toString_vracaNaziv() {
        assertEquals("Beograd", mesto.toString());
    }
}
