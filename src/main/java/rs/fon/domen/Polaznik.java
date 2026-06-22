package rs.fon.domen;

import java.util.Objects;

/**
 * Predstavlja polaznika sportskog centra.
 *
 * @author Nikola Miljković
 */
public class Polaznik {

    /** Jedinstveni identifikator polaznika u bazi. */
    private int idPolaznik;

    /** Ime polaznika. */
    private String ime;

    /** Prezime polaznika. */
    private String prezime;

    /** Kontakt telefon polaznika. */
    private String brojTelefona;

    /** Mesto prebivališta polaznika. */
    private Mesto mesto;

    /**
     * Konstruktor bez inicijalizacije atributa.
     */
    public Polaznik() {
    }

    /**
     * Konstruktor koji postavlja atribute.
     *
     * @param idPolaznik jedinstveni identifikator polaznika
     * @param ime ime polaznika
     * @param prezime prezime polaznika
     * @param brojTelefona kontakt telefon
     * @param mesto mesto prebivališta
     */
    public Polaznik(int idPolaznik, String ime, String prezime, String brojTelefona, Mesto mesto) {
        setIdPolaznik(idPolaznik);
        setIme(ime);
        setPrezime(prezime);
        setBrojTelefona(brojTelefona);
        setMesto(mesto);
    }

    /**
     * Vraća identifikator polaznika.
     *
     * @return id polaznika
     */
    public int getIdPolaznik() {
        return idPolaznik;
    }

    /**
     * Postavlja identifikator polaznika.
     *
     * @param idPolaznik jedinstveni identifikator polaznika, mora biti veći od nule
     * @throws IllegalArgumentException ako je id manji ili jednak nuli
     */
    public void setIdPolaznik(int idPolaznik) {
        if (idPolaznik <= 0) {
            throw new IllegalArgumentException("Id polaznika mora biti veci od nule.");
        }
        this.idPolaznik = idPolaznik;
    }

    /**
     * Vraća ime polaznika.
     *
     * @return ime polaznika
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime polaznika.
     *
     * @param ime ime polaznika, ne sme biti {@code null} niti prazno
     * @throws NullPointerException ako je ime {@code null}
     * @throws IllegalArgumentException ako je ime prazno
     */
    public void setIme(String ime) {
        Objects.requireNonNull(ime, "Ime polaznika je obavezno.");
        if (ime.isBlank()) {
            throw new IllegalArgumentException("Ime polaznika ne sme biti prazno.");
        }
        this.ime = ime;
    }

    /**
     * Vraća prezime polaznika.
     *
     * @return prezime polaznika
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime polaznika.
     *
     * @param prezime prezime polaznika, ne sme biti {@code null} niti prazno
     * @throws NullPointerException ako je prezime {@code null}
     * @throws IllegalArgumentException ako je prezime prazno
     */
    public void setPrezime(String prezime) {
        Objects.requireNonNull(prezime, "Prezime polaznika je obavezno.");
        if (prezime.isBlank()) {
            throw new IllegalArgumentException("Prezime polaznika ne sme biti prazno.");
        }
        this.prezime = prezime;
    }

    /**
     * Vraća broj telefona polaznika.
     *
     * @return broj telefona
     */
    public String getBrojTelefona() {
        return brojTelefona;
    }

    /**
     * Postavlja broj telefona polaznika.
     *
     * @param brojTelefona kontakt telefon, ne sme biti {@code null} niti prazan
     * @throws NullPointerException ako je broj telefona {@code null}
     * @throws IllegalArgumentException ako je broj telefona prazan
     */
    public void setBrojTelefona(String brojTelefona) {
        Objects.requireNonNull(brojTelefona, "Broj telefona polaznika je obavezan.");
        if (brojTelefona.isBlank()) {
            throw new IllegalArgumentException("Broj telefona polaznika ne sme biti prazan.");
        }
        this.brojTelefona = brojTelefona;
    }

    /**
     * Vraća mesto prebivališta polaznika.
     *
     * @return mesto polaznika
     */
    public Mesto getMesto() {
        return mesto;
    }

    /**
     * Postavlja mesto prebivališta polaznika i validira njegove atribute.
     *
     * @param mesto mesto prebivališta, ne sme biti {@code null}
     * @throws NullPointerException ako je mesto {@code null}
     */
    public void setMesto(Mesto mesto) {
        Objects.requireNonNull(mesto, "Polaznik mora imati definisano mesto.");
        mesto.setIdMesto(mesto.getIdMesto());
        mesto.setNaziv(mesto.getNaziv());
        this.mesto = mesto;
    }

    /**
     * Poredi dva polaznika na osnovu imena, prezimena i broja telefona.
     *
     * @param obj objekat za poređenje
     * @return {@code true} ako su polaznici jednaki, inače {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Polaznik other = (Polaznik) obj;
        return Objects.equals(ime, other.ime)
                && Objects.equals(prezime, other.prezime)
                && Objects.equals(brojTelefona, other.brojTelefona);
    }

    /**
     * Računa hash kod na osnovu imena, prezimena i broja telefona.
     *
     * @return hash kod
     */
    @Override
    public int hashCode() {
        return Objects.hash(ime, prezime, brojTelefona);
    }

    /**
     * Vraća String reprezentaciju objekta polaznika.
     *
     * @return ime i prezime polaznika
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }
}
