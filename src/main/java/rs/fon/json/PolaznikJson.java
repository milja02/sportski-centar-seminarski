package rs.fon.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import rs.fon.domen.Polaznik;

/**
 * Čuva i učitava polaznika u JSON fajl, kao i podatke sa RandomUser Web API-ja.
 * Za serijalizaciju i deserijalizaciju koristi Gson biblioteku.
 *
 * @author Nikola Miljković
 */
public class PolaznikJson {

    /** URL RandomUser API-ja za učitavanje nasumičnog polaznika. */
    private static final String RANDOM_USER_API_URL = "https://randomuser.me/api/?nat=rs&inc=name,cell";

    /** Gson parser. */
    private final Gson gson;

    /** HTTP klijent za slanje GET zahteva ka Web servisu. */
    private final HttpClient httpClient;

    /**
     * Konstruktor koji kreira Gson parser i HTTP klijent.
     */
    public PolaznikJson() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        httpClient = HttpClient.newHttpClient();
    }

    /**
     * Serijalizuje polaznika u JSON i upisuje ga u fajl.
     *
     * @param polaznik polaznik za čuvanje
     * @param putanjaFajla putanja do JSON fajla
     * @throws IOException ako upis u fajl ne uspe
     */
    public void sacuvaj(Polaznik polaznik, String putanjaFajla) throws IOException {
        Path putanja = Path.of(putanjaFajla);
        try (Writer writer = Files.newBufferedWriter(putanja, StandardCharsets.UTF_8)) {
            gson.toJson(polaznik, writer);
        }
    }

    /**
     * Deserijalizuje polaznika iz JSON fajla.
     *
     * @param putanjaFajla putanja do JSON fajla
     * @return učitani polaznik
     * @throws IOException ako čitanje fajla ne uspe
     */
    public Polaznik ucitaj(String putanjaFajla) throws IOException {
        Path putanja = Path.of(putanjaFajla);
        try (Reader reader = Files.newBufferedReader(putanja, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, Polaznik.class);
        }
    }

    /**
     * Šalje GET zahtev RandomUser API-ju i vraća novog polaznika
     * sa popunjenim imenom, prezimenom i brojem telefona.
     *
     * @return polaznik sa podacima sa API-ja
     * @throws IOException ako mrežna komunikacija ne uspe
     * @throws InterruptedException ako se zahtev prekine
     */
    public Polaznik ucitajNasumicnogPolaznika() throws IOException, InterruptedException {
        Polaznik polaznik = new Polaznik();
        popuniNasumicnogPolaznika(polaznik);
        return polaznik;
    }

    /**
     * Preuzima nasumičnog korisnika sa RandomUser API-ja i postavlja
     * ime, prezime i broj telefona na postojeći objekat.
     *
     * @param polaznik polaznik koji se popunjava
     * @throws IOException ako komnikacija ne uspe
     * @throws InterruptedException ako se zahtev prekine
     */
    public void popuniNasumicnogPolaznika(Polaznik polaznik) throws IOException, InterruptedException {
        HttpRequest zahtev = HttpRequest.newBuilder().uri(URI.create(RANDOM_USER_API_URL)).GET().build();

        HttpResponse<String> odgovor = httpClient.send(zahtev, HttpResponse.BodyHandlers.ofString());

        if (odgovor.statusCode() != 200) {
            throw new IOException("RandomUser API Error: " + odgovor.statusCode());
        }

        obradiOdgovor(odgovor.body(), polaznik);
    }

    /**
     * Parsira JSON odgovor Web servisa i postavlja tražena polja na polaznika.
     *
     * @param jsonOdgovor telo HTTP odgovora
     * @param polaznik polaznik koji se popunjava
     */
    private void obradiOdgovor(String jsonOdgovor, Polaznik polaznik) {
        JsonObject koren = JsonParser.parseString(jsonOdgovor).getAsJsonObject();
        JsonArray rezultati = koren.getAsJsonArray("results");
        JsonObject korisnik = rezultati.get(0).getAsJsonObject();
        JsonObject imeObjekat = korisnik.getAsJsonObject("name");

        polaznik.setIme(imeObjekat.get("first").getAsString());
        polaznik.setPrezime(imeObjekat.get("last").getAsString());
        polaznik.setBrojTelefona(korisnik.get("cell").getAsString());
    }
}
