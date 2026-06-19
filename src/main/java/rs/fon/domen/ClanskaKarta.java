package rs.fon.domen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja člansku kartu polaznika sportskog centra.
 *
 * @author Nikola Miljković
 */
public class ClanskaKarta {

    /** Jedinstveni identifikator članske karte u bazi. */
    private int idClanskaKarta;

    /** Datum kada je polaznik učlanjen. */
    private Date datumUclanjenja;

    /** Ukupan iznos članske karte (zbir svih stavki). */
    private int ukupanIznos;

    /** Polaznik kojem karta pripada. */
    private Polaznik polaznik;

    /** Lista stavki na članskoj karti. */
    private List<StavkaClanskeKarte> stavke = new ArrayList<>();

    /**
     * Konstruktor bez inicijalizacije atributa.
     */
    public ClanskaKarta() {
    }

    /**
     * Konstruktor koji postavlja atribute.
     *
     * @param idClanskaKarta jedinstveni identifikator karte
     * @param datumUclanjenja datum učlanjenja
     * @param ukupanIznos ukupan iznos karte
     * @param polaznik polaznik kojem karta pripada
     */
    public ClanskaKarta(int idClanskaKarta, Date datumUclanjenja, int ukupanIznos, Polaznik polaznik) {
        setIdClanskaKarta(idClanskaKarta);
        setDatumUclanjenja(datumUclanjenja);
        setPolaznik(polaznik);
        setUkupanIznos(ukupanIznos);
    }

    /**
     * Vraća identifikator članske karte.
     *
     * @return id članske karte
     */
    public int getIdClanskaKarta() {
        return idClanskaKarta;
    }

    /**
     * Postavlja identifikator članske karte.
     *
     * @param idClanskaKarta jedinstveni identifikator, mora biti veći od nule
     * @throws IllegalArgumentException ako je id manji ili jednak nuli
     */
    public void setIdClanskaKarta(int idClanskaKarta) {
        if (idClanskaKarta <= 0) {
            throw new IllegalArgumentException("Id clanske karte mora biti veci od nule.");
        }
        this.idClanskaKarta = idClanskaKarta;
    }

    /**
     * Vraća datum učlanjenja.
     *
     * @return datum učlanjenja
     */
    public Date getDatumUclanjenja() {
        return datumUclanjenja;
    }

    /**
     * Postavlja datum učlanjenja.
     *
     * @param datumUclanjenja datum učlanjenja, ne sme biti {@code null}
     * @throws NullPointerException ako je datum {@code null}
     */
    public void setDatumUclanjenja(Date datumUclanjenja) {
        if (datumUclanjenja == null) {
            throw new NullPointerException("Datum uclanjenja je obavezan.");
        }
        this.datumUclanjenja = datumUclanjenja;
    }

    /**
     * Vraća ukupan iznos članske karte.
     *
     * @return ukupan iznos
     */
    public int getUkupanIznos() {
        return ukupanIznos;
    }

    /**
     * Postavlja ukupan iznos članske karte.
     * Ako postoje stavke, iznos mora biti jednak njihovom zbiru.
     *
     * @param ukupanIznos ukupan iznos, mora biti veći od nule
     * @throws IllegalArgumentException ako je iznos neispravan ili ne odgovara zbiru stavki
     */
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

    /**
     * Vraća polaznika kojem karta pripada.
     *
     * @return polaznik
     */
    public Polaznik getPolaznik() {
        return polaznik;
    }

    /**
     * Postavlja polaznika i validira njegove atribute.
     *
     * @param polaznik polaznik, ne sme biti {@code null}
     * @throws NullPointerException ako je polaznik {@code null}
     */
    public void setPolaznik(Polaznik polaznik) {
        if (polaznik == null) {
            throw new NullPointerException("Clanska karta mora imati polaznika.");
        }
        polaznik.setIdPolaznik(polaznik.getIdPolaznik());
        polaznik.setIme(polaznik.getIme());
        polaznik.setPrezime(polaznik.getPrezime());
        polaznik.setBrojTelefona(polaznik.getBrojTelefona());
        polaznik.setMesto(polaznik.getMesto());
        this.polaznik = polaznik;
    }

    /**
     * Vraća listu stavki članske karte.
     *
     * @return lista stavki
     */
    public List<StavkaClanskeKarte> getStavke() {
        return stavke;
    }

    /**
     * Postavlja listu stavki članske karte.
     *
     * @param stavke lista stavki, ne sme biti {@code null}
     * @throws NullPointerException ako je lista {@code null}
     */
    public void setStavke(List<StavkaClanskeKarte> stavke) {
        if (stavke == null) {
            throw new NullPointerException("Lista stavki ne sme biti null.");
        }
        this.stavke = stavke;
    }

    /**
     * Dodaje stavku na člansku kartu i povezuje je sa ovom kartom.
     *
     * @param stavka stavka za dodavanje, ne sme biti {@code null}
     * @throws NullPointerException ako je stavka {@code null}
     */
    public void dodajStavku(StavkaClanskeKarte stavka) {
        if (stavka == null) {
            throw new NullPointerException("Stavka ne sme biti null.");
        }
        stavka.setClanskaKarta(this);
        stavke.add(stavka);
    }

    /**
     * Računa zbir iznosa svih stavki na karti.
     *
     * @return zbir iznosa stavki
     */
    public int izracunajZbirStavki() {
        int zbir = 0;
        if (stavke != null) {
            for (StavkaClanskeKarte stavka : stavke) {
                zbir += stavka.getIznosStavke();
            }
        }
        return zbir;
    }

    /**
     * Validira celokupno stanje članske karte, uključujući sve stavke
     * i uslov da ukupan iznos mora biti jednak zbiru stavki.
     *
     * @throws NullPointerException ako obavezni podaci nedostaju
     * @throws IllegalArgumentException ako bilo koji atribut nije ispravan
     */
    public void validiraj() {
        setIdClanskaKarta(idClanskaKarta);
        setDatumUclanjenja(datumUclanjenja);
        setPolaznik(polaznik);
        setStavke(stavke);
        if (ukupanIznos <= 0) {
            throw new IllegalArgumentException("Ukupan iznos mora biti veci od nule.");
        }

        for (StavkaClanskeKarte stavka : stavke) {
            Objects.requireNonNull(stavka, "Stavka u listi ne sme biti null.");
            stavka.setClanskaKarta(stavka.getClanskaKarta());
            stavka.setRb(stavka.getRb());
            stavka.setBrojTermina(stavka.getBrojTermina());
            stavka.setSport(stavka.getSport());
            stavka.setIznosStavke(stavka.getIznosStavke());
        }

        int zbirStavki = izracunajZbirStavki();
        if (ukupanIznos != zbirStavki) {
            throw new IllegalArgumentException(
                    "Ukupan iznos mora biti jednak zbiru iznosa stavki.");
        }
    }

    /**
     * Poredi dve članske karte na osnovu datuma učlanjenja i polaznika.
     *
     * @param obj objekat za poređenje
     * @return {@code true} ako su karte jednake, inače {@code false}
     */
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

    /**
     * Računa hash kod na osnovu datuma učlanjenja i polaznika.
     *
     * @return hash kod
     */
    @Override
    public int hashCode() {
        return Objects.hash(datumUclanjenja, polaznik);
    }

    /**
     * Vraća String reprezentaciju objekta članske karte.
     *
     * @return <p>"ClanskaKarta [datumUclanjenja=####, ukupanIznos=####, polaznik=####]"</p>
     */
    @Override
    public String toString() {
        return "ClanskaKarta[datumUclanjenja=" + datumUclanjenja
                + ", ukupanIznos=" + ukupanIznos + ", polaznik=" + polaznik + "]";
    }
}
