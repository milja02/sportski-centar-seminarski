package rs.fon.domen;

import java.util.Objects;

public class Mesto {

    private int idMesto;
    private String naziv;
    private int postanskiBroj;

    public Mesto() {
    }

    public Mesto(int idMesto, String naziv, int postanskiBroj) {
        setIdMesto(idMesto);
        setNaziv(naziv);
        setPostanskiBroj(postanskiBroj);
    }

    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        if (idMesto <= 0) {
            throw new IllegalArgumentException("Id mesta mora biti veci od nule.");
        }
        this.idMesto = idMesto;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        Objects.requireNonNull(naziv, "Naziv mesta je obavezan.");
        if (naziv.isBlank()) {
            throw new IllegalArgumentException("Naziv mesta ne sme biti prazan.");
        }
        this.naziv = naziv;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public void validiraj() {
        setIdMesto(idMesto);
        setNaziv(naziv);
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(naziv, postanskiBroj);
    }

    @Override
    public String toString() {
        return naziv;
    }
}
