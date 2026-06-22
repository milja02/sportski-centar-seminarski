package rs.fon.domen;

import java.util.Objects;

/**
 * Predstavlja sport koji se nudi u sportskom centru.
 *
 * @author Nikola Miljković
 */
public class Sport {

    /** Jedinstveni identifikator sporta u bazi. */
    private int idSport;

    /** Naziv sporta. */
    private String naziv;

    /** Cena jednog termina za dati sport. */
    private int cena;

    /**
     * Konstruktor bez inicijalizacije atributa.
     */
    public Sport() {
    }

    /**
     * Konstruktor koji postavlja atribute.
     *
     * @param idSport jedinstveni identifikator sporta
     * @param naziv naziv sporta
     * @param cena cena po terminu
     */
    public Sport(int idSport, String naziv, int cena) {
        setIdSport(idSport);
        setNaziv(naziv);
        setCena(cena);
    }

    /**
     * Vraća id sporta.
     *
     * @return id sporta
     */
    public int getIdSport() {
        return idSport;
    }

    /**
     * Postavlja id sporta.
     *
     * @param idSport jedinstveni identifikator sporta, mora biti veći od nule
     * @throws IllegalArgumentException ako je id manji ili jednak nuli
     */
    public void setIdSport(int idSport) {
        if (idSport <= 0) {
            throw new IllegalArgumentException("Id sporta mora biti veci od nule.");
        }
        this.idSport = idSport;
    }

    /**
     * Vraća naziv sporta.
     *
     * @return naziv sporta
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv sporta.
     *
     * @param naziv naziv sporta, ne sme biti {@code null} niti prazan
     * @throws NullPointerException ako je naziv {@code null}
     * @throws IllegalArgumentException ako je naziv prazan
     */
    public void setNaziv(String naziv) {
        Objects.requireNonNull(naziv, "Naziv sporta je obavezan.");
        if (naziv.isBlank()) {
            throw new IllegalArgumentException("Naziv sporta ne sme biti prazan.");
        }
        this.naziv = naziv;
    }

    /**
     * Vraća cenu sporta po terminu.
     *
     * @return cena po terminu
     */
    public int getCena() {
        return cena;
    }

    /**
     * Postavlja cenu sporta po terminu.
     *
     * @param cena cena po terminu, ne sme biti negativna
     * @throws IllegalArgumentException ako je cena negativna
     */
    public void setCena(int cena) {
        if (cena < 0) {
            throw new IllegalArgumentException("Cena sporta ne sme biti negativna.");
        }
        this.cena = cena;
    }

    /**
     * Poredi dva sporta na osnovu naziva.
     *
     * @param obj objekat za poređenje
     * @return {@code true} ako su sportovi jednaki, inače {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Sport other = (Sport) obj;
        return Objects.equals(naziv, other.naziv);
    }

    /**
     * Računa hash kod na osnovu naziva.
     *
     * @return hash kod
     */
    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }

    /**
     * Vraća String reprezentaciju objekta sporta.
     *
     * @return naziv sporta
     */
    @Override
    public String toString() {
        return naziv;
    }
}
