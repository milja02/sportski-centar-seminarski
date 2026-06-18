package rs.fon.domen;

import java.util.Objects;

public class StavkaClanskeKarte {

    private ClanskaKarta clanskaKarta;
    private int rb;
    private int brojTermina;
    private int iznosStavke;
    private Sport sport;
    private boolean iznosPostavljen;

    public StavkaClanskeKarte() {
    }

    public StavkaClanskeKarte(ClanskaKarta clanskaKarta, int rb, int brojTermina, int iznosStavke, Sport sport) {
        setClanskaKarta(clanskaKarta);
        setRb(rb);
        setBrojTermina(brojTermina);
        setSport(sport);
        setIznosStavke(iznosStavke);
    }

    public ClanskaKarta getClanskaKarta() {
        return clanskaKarta;
    }

    public void setClanskaKarta(ClanskaKarta clanskaKarta) {
        Objects.requireNonNull(clanskaKarta, "Stavka mora pripadati clanskoj karti.");
        if (clanskaKarta.getIdClanskaKarta() <= 0) {
            throw new IllegalArgumentException("Id clanske karte na stavci mora biti veci od nule.");
        }
        this.clanskaKarta = clanskaKarta;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        if (rb <= 0) {
            throw new IllegalArgumentException("Redni broj stavke mora biti veci od nule.");
        }
        this.rb = rb;
    }

    public int getBrojTermina() {
        return brojTermina;
    }

    public void setBrojTermina(int brojTermina) {
        if (brojTermina < 1) {
            throw new IllegalArgumentException("Broj termina mora biti veci ili jednak 1.");
        }
        this.brojTermina = brojTermina;
        if (iznosPostavljen) {
            proveriIznosStavke();
        }
    }

    public int getIznosStavke() {
        return iznosStavke;
    }

    public void setIznosStavke(int iznosStavke) {
        this.iznosStavke = iznosStavke;
        if (sport != null && brojTermina >= 1) {
            proveriIznosStavke();
            iznosPostavljen = true;
        }
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        Objects.requireNonNull(sport, "Stavka mora imati definisan sport.");
        sport.validiraj();
        this.sport = sport;
        if (iznosPostavljen) {
            proveriIznosStavke();
        }
    }

    public int izracunajOcekivaniIznos() {
        Objects.requireNonNull(sport, "Sport mora biti definisan da bi se izracunao iznos stavke.");
        return brojTermina * sport.getCena();
    }

    private void proveriIznosStavke() {
        int ocekivaniIznos = izracunajOcekivaniIznos();
        if (iznosStavke != ocekivaniIznos) {
            throw new IllegalArgumentException(
                    "Iznos stavke mora biti jednak proizvodu broja termina i cene sporta");
        }
    }

    public void validiraj() {
        setClanskaKarta(clanskaKarta);
        setRb(rb);
        setBrojTermina(brojTermina);
        setSport(sport);
        setIznosStavke(iznosStavke);
    }

    @Override
    public String toString() {
        return "StavkaClanskeKarte{rb=" + rb + ", brojTermina=" + brojTermina
                + ", iznosStavke=" + iznosStavke + ", sport=" + sport + "}";
    }
}
