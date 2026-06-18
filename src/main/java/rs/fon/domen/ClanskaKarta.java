package rs.fon.domen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ClanskaKarta {

    private int idClanskaKarta;
    private Date datumUclanjenja;
    private int ukupanIznos;
    private Polaznik polaznik;
    private List<StavkaClanskeKarte> stavke = new ArrayList<>();

    public ClanskaKarta() {
    }

    public ClanskaKarta(int idClanskaKarta, Date datumUclanjenja, int ukupanIznos, Polaznik polaznik) {
        setIdClanskaKarta(idClanskaKarta);
        setDatumUclanjenja(datumUclanjenja);
        setPolaznik(polaznik);
        setUkupanIznos(ukupanIznos);
    }

    public int getIdClanskaKarta() {
        return idClanskaKarta;
    }

    public void setIdClanskaKarta(int idClanskaKarta) {
        if (idClanskaKarta <= 0) {
            throw new IllegalArgumentException("Id clanske karte mora biti veci od nule.");
        }
        this.idClanskaKarta = idClanskaKarta;
    }

    public Date getDatumUclanjenja() {
        return datumUclanjenja;
    }

    public void setDatumUclanjenja(Date datumUclanjenja) {
        if (datumUclanjenja == null) {
            throw new NullPointerException("Datum uclanjenja je obavezan.");
        }
        this.datumUclanjenja = datumUclanjenja;
    }

    public int getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(int ukupanIznos) {
        if (ukupanIznos <= 0) {
            throw new IllegalArgumentException("Ukupan iznos mora biti veci od nule.");
        }
        if (stavke != null && !stavke.isEmpty()) {
            int zbirStavki = izracunajZbirStavki();
            if (ukupanIznos != zbirStavki) {
                throw new IllegalArgumentException( "Ukupan iznos mora biti jednak zbiru iznosa stavki");
            }
        }
        this.ukupanIznos = ukupanIznos;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        if (polaznik == null) {
            throw new NullPointerException("Clanska karta mora imati polaznika.");
        }
        polaznik.validiraj();
        this.polaznik = polaznik;
    }

    public List<StavkaClanskeKarte> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaClanskeKarte> stavke) {
        if (stavke == null) {
            throw new NullPointerException("Lista stavki ne sme biti null.");
        }
        this.stavke = stavke;
    }

    public void dodajStavku(StavkaClanskeKarte stavka) {
        if (stavka == null) {
            throw new NullPointerException("Stavka ne sme biti null.");
        }
        stavka.setClanskaKarta(this);
        stavke.add(stavka);
    }

    public int izracunajZbirStavki() {
        int zbir = 0;
        if (stavke != null) {
            for (StavkaClanskeKarte stavka : stavke) {
                zbir += stavka.getIznosStavke();
            }
        }
        return zbir;
    }

    public void validiraj() {
        setIdClanskaKarta(idClanskaKarta);
        setDatumUclanjenja(datumUclanjenja);
        setPolaznik(polaznik);
        setStavke(stavke);
        if (ukupanIznos <= 0) {
            throw new IllegalArgumentException("Ukupan iznos mora biti veci od nule.");
        }

        for (StavkaClanskeKarte stavka : stavke) {
            stavka.validiraj();
        }

        int zbirStavki = izracunajZbirStavki();
        if (ukupanIznos != zbirStavki) {
            throw new IllegalArgumentException(
                    "Ukupan iznos mora biti jednak zbiru iznosa stavki.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ClanskaKarta other = (ClanskaKarta) obj;
        return Objects.equals(datumUclanjenja, other.datumUclanjenja)
                && Objects.equals(polaznik, other.polaznik);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datumUclanjenja, polaznik);
    }

    @Override
    public String toString() {
        return "ClanskaKarta{datumUclanjenja=" + datumUclanjenja
                + ", ukupanIznos=" + ukupanIznos + ", polaznik=" + polaznik + "}";
    }
}
