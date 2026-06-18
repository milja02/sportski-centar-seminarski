package rs.fon.domen;

import java.util.Objects;

public class Sport {

    private int idSport;
    private String naziv;
    private int cena;

    public Sport() {
    }

    public Sport(int idSport, String naziv, int cena) {
        setIdSport(idSport);
        setNaziv(naziv);
        setCena(cena);
    }

    public int getIdSport() {
        return idSport;
    }

    public void setIdSport(int idSport) {
        if (idSport <= 0) {
            throw new IllegalArgumentException("Id sporta mora biti veci od nule.");
        }
        this.idSport = idSport;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        Objects.requireNonNull(naziv, "Naziv sporta je obavezan.");
        if (naziv.isBlank()) {
            throw new IllegalArgumentException("Naziv sporta ne sme biti prazan.");
        }
        this.naziv = naziv;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        if (cena < 0) {
            throw new IllegalArgumentException("Cena sporta ne sme biti negativna.");
        }
        this.cena = cena;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }

    @Override
    public String toString() {
        return naziv;
    }
}
