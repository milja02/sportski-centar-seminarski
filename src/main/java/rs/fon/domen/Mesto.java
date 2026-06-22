package rs.fon.domen;

import java.util.Objects;

/**
 * Predstavlja mesto u sistemu sportskog centra.
 *
 * @author Nikola Miljković
 */
public class Mesto {

    /** Jedinstveni identifikator mesta u bazi. */
    private int idMesto;

    /** Naziv mesta. */
    private String naziv;

    /** Poštanski broj mesta. */
    private int postanskiBroj;

    /**
     * Konstruktor bez inicijalizacije atributa.
     */
    public Mesto() {
    }

    /**
     * Konstruktor koji postavlja atribute.
     *
     * @param idMesto jedinstveni identifikator mesta
     * @param naziv naziv mesta
     * @param postanskiBroj poštanski broj mesta
     */
    public Mesto(int idMesto, String naziv, int postanskiBroj) {
        setIdMesto(idMesto);
        setNaziv(naziv);
        setPostanskiBroj(postanskiBroj);
    }

    /**
     * Vraća identifikator mesta.
     *
     * @return id mesta
     */
    public int getIdMesto() {
        return idMesto;
    }

    /**
     * Postavlja identifikator mesta.
     *
     * @param idMesto jedinstveni identifikator mesta, mora biti veći od nule
     * @throws IllegalArgumentException ako je id manji ili jednak nuli
     */
    public void setIdMesto(int idMesto) {
        if (idMesto <= 0) {
            throw new IllegalArgumentException("Id mesta mora biti veci od nule.");
        }
        this.idMesto = idMesto;
    }

    /**
     * Vraća naziv mesta.
     *
     * @return naziv mesta
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv mesta.
     *
     * @param naziv naziv mesta, ne sme biti {@code null} niti prazan
     * @throws NullPointerException ako je naziv {@code null}
     * @throws IllegalArgumentException ako je naziv prazan
     */
    public void setNaziv(String naziv) {
        Objects.requireNonNull(naziv, "Naziv mesta je obavezan.");
        if (naziv.isBlank()) {
            throw new IllegalArgumentException("Naziv mesta ne sme biti prazan.");
        }
        this.naziv = naziv;
    }

    /**
     * Vraća poštanski broj mesta.
     *
     * @return poštanski broj
     */
    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    /**
     * Postavlja poštanski broj mesta.
     *
     * @param postanskiBroj poštanski broj mesta
     */
    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    /**
     * Poredi dva mesta na osnovu naziva i poštanskog broja.
     *
     * @param obj objekat za poređenje
     * @return {@code true} ako su mesta jednaka, inače {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Mesto other = (Mesto) obj;
        return postanskiBroj == other.postanskiBroj
                && Objects.equals(naziv, other.naziv);
    }

    /**
     * Računa hash kod na osnovu naziva i poštanskog broja.
     *
     * @return hash kod
     */
    @Override
    public int hashCode() {
        return Objects.hash(naziv, postanskiBroj);
    }

    /**
     * Vraća String reprezentaciju objekta mesta.
     *
     * @return naziv mesta
     */
    @Override
    public String toString() {
        return naziv;
    }
}
