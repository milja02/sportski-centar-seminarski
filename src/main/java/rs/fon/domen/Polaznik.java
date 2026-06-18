package rs.fon.domen;

import java.util.Objects;

public class Polaznik {

    private int idPolaznik;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private Mesto mesto;

    public Polaznik() {
    }

    public Polaznik(int idPolaznik, String ime, String prezime, String brojTelefona, Mesto mesto) {
        setIdPolaznik(idPolaznik);
        setIme(ime);
        setPrezime(prezime);
        setBrojTelefona(brojTelefona);
        setMesto(mesto);
    }

    public int getIdPolaznik() {
        return idPolaznik;
    }

    public void setIdPolaznik(int idPolaznik) {
        if (idPolaznik <= 0) {
            throw new IllegalArgumentException("Id polaznika mora biti veci od nule.");
        }
        this.idPolaznik = idPolaznik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        Objects.requireNonNull(ime, "Ime polaznika je obavezno.");
        if (ime.isBlank()) {
            throw new IllegalArgumentException("Ime polaznika ne sme biti prazno.");
        }
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        Objects.requireNonNull(prezime, "Prezime polaznika je obavezno.");
        if (prezime.isBlank()) {
            throw new IllegalArgumentException("Prezime polaznika ne sme biti prazno.");
        }
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        Objects.requireNonNull(brojTelefona, "Broj telefona polaznika je obavezan.");
        if (brojTelefona.isBlank()) {
            throw new IllegalArgumentException("Broj telefona polaznika ne sme biti prazan.");
        }
        this.brojTelefona = brojTelefona;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        Objects.requireNonNull(mesto, "Polaznik mora imati definisano mesto.");
        mesto.validiraj();
        this.mesto = mesto;
    }

    public void validiraj() {
        setIdPolaznik(idPolaznik);
        setIme(ime);
        setPrezime(prezime);
        setBrojTelefona(brojTelefona);
        setMesto(mesto);
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(ime, prezime, brojTelefona);
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
}
