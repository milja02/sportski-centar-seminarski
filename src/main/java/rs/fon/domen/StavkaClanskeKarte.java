package rs.fon.domen;

import java.util.Objects;

/**
 * Predstavlja stavku na članskoj karti.
 *
 * @author Nikola Miljković
 */
public class StavkaClanskeKarte {

    /** Članska karta kojoj stavka pripada. */
    private ClanskaKarta clanskaKarta;

    /** Redni broj stavke unutar karte. */
    private int rb;

    /** Broj termina za dati sport na stavci. */
    private int brojTermina;

    /** Iznos stavke (broj termina pomnožen cenom sporta). */
    private int iznosStavke;

    /** Sport na koji se stavka odnosi. */
    private Sport sport;

    /** Oznaka da je iznos stavke eksplicitno postavljen i treba validirati. */
    private boolean iznosPostavljen;

    /**
     * Konstruktor bez inicijalizacije atributa.
     */
    public StavkaClanskeKarte() {
    }

    /**
     * Konstruktor koji postavlja atribute.
     *
     * @param clanskaKarta članska karta kojoj stavka pripada
     * @param rb redni broj stavke
     * @param brojTermina broj termina za sport
     * @param iznosStavke iznos stavke
     * @param sport sport na koji se stavka odnosi
     */
    public StavkaClanskeKarte(ClanskaKarta clanskaKarta, int rb, int brojTermina, int iznosStavke, Sport sport) {
        setClanskaKarta(clanskaKarta);
        setRb(rb);
        setBrojTermina(brojTermina);
        setSport(sport);
        setIznosStavke(iznosStavke);
    }

    /**
     * Vraća člansku kartu kojoj stavka pripada.
     *
     * @return članska karta
     */
    public ClanskaKarta getClanskaKarta() {
        return clanskaKarta;
    }

    /**
     * Postavlja člansku kartu kojoj stavka pripada.
     *
     * @param clanskaKarta članska karta sa validnim id-jem
     * @throws NullPointerException ako je karta {@code null}
     * @throws IllegalArgumentException ako id karte nije veći od nule
     */
    public void setClanskaKarta(ClanskaKarta clanskaKarta) {
        Objects.requireNonNull(clanskaKarta, "Stavka mora pripadati clanskoj karti.");
        if (clanskaKarta.getIdClanskaKarta() <= 0) {
            throw new IllegalArgumentException("Id clanske karte na stavci mora biti veci od nule.");
        }
        this.clanskaKarta = clanskaKarta;
    }

    /**
     * Vraća redni broj stavke.
     *
     * @return redni broj stavke
     */
    public int getRb() {
        return rb;
    }

    /**
     * Postavlja redni broj stavke.
     *
     * @param rb redni broj, mora biti veći od nule
     * @throws IllegalArgumentException ako je redni broj manji ili jednak nuli
     */
    public void setRb(int rb) {
        if (rb <= 0) {
            throw new IllegalArgumentException("Redni broj stavke mora biti veci od nule.");
        }
        this.rb = rb;
    }

    /**
     * Vraća broj termina na stavci.
     *
     * @return broj termina
     */
    public int getBrojTermina() {
        return brojTermina;
    }

    /**
     * Postavlja broj termina na stavci.
     *
     * @param brojTermina broj termina, mora biti veći ili jednak 1
     * @throws IllegalArgumentException ako je broj termina manji od 1 ili iznos ne odgovara
     */
    public void setBrojTermina(int brojTermina) {
        if (brojTermina < 1) {
            throw new IllegalArgumentException("Broj termina mora biti veci ili jednak 1.");
        }
        this.brojTermina = brojTermina;
        if (iznosPostavljen) {
            proveriIznosStavke();
        }
    }

    /**
     * Vraća iznos stavke.
     *
     * @return iznos stavke
     */
    public int getIznosStavke() {
        return iznosStavke;
    }

    /**
     * Postavlja iznos stavke i proverava da li odgovara broju termina i ceni sporta.
     *
     * @param iznosStavke iznos stavke
     * @throws IllegalArgumentException ako iznos ne odgovara očekivanom iznosu
     */
    public void setIznosStavke(int iznosStavke) {
        this.iznosStavke = iznosStavke;
        if (sport != null && brojTermina >= 1) {
            proveriIznosStavke();
            iznosPostavljen = true;
        }
    }

    /**
     * Vraća sport na koji se stavka odnosi.
     *
     * @return sport stavke
     */
    public Sport getSport() {
        return sport;
    }

    /**
     * Postavlja sport stavke i validira njegove atribute.
     *
     * @param sport sport stavke, ne sme biti {@code null}
     * @throws NullPointerException ako je sport {@code null}
     * @throws IllegalArgumentException ako iznos ne odgovara novom sportu
     */
    public void setSport(Sport sport) {
        Objects.requireNonNull(sport, "Stavka mora imati definisan sport.");
        sport.setIdSport(sport.getIdSport());
        sport.setNaziv(sport.getNaziv());
        sport.setCena(sport.getCena());
        this.sport = sport;
        if (iznosPostavljen) {
            proveriIznosStavke();
        }
    }

    /**
     * Računa očekivani iznos stavke kao proizvod broja termina i cene sporta.
     *
     * @return očekivani iznos stavke
     * @throws NullPointerException ako sport nije definisan
     */
    public int izracunajOcekivaniIznos() {
        Objects.requireNonNull(sport, "Sport mora biti definisan da bi se izracunao iznos stavke.");
        return brojTermina * sport.getCena();
    }

    /**
     * Proverava da li je postavljeni iznos stavke jednak očekivanom iznosu.
     *
     * @throws IllegalArgumentException ako iznos ne odgovara proizvodu broja termina i cene sporta
     */
    private void proveriIznosStavke() {
        int ocekivaniIznos = izracunajOcekivaniIznos();
        if (iznosStavke != ocekivaniIznos) {
            throw new IllegalArgumentException(
                    "Iznos stavke mora biti jednak proizvodu broja termina i cene sporta");
        }
    }

    /**
     * Vraća String reprezentaciju objekta stavke članske karte.
     *
     * @return <p>"StavkaClanskeKarte[rb=####, brojTermina=####, iznosStavke=####, sport=####]"</p>
     */
    @Override
    public String toString() {
        return "StavkaClanskeKarte[rb=" + rb + ", brojTermina=" + brojTermina
                + ", iznosStavke=" + iznosStavke + ", sport=" + sport + "]";
    }
}
