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

public class PolaznikJson {

    private static final String RANDOM_USER_API_URL = "https://randomuser.me/api/?nat=rs&inc=name,cell";

    private final Gson gson;
    private final HttpClient httpClient;

    public PolaznikJson() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        httpClient = HttpClient.newHttpClient();
    }

    public void sacuvaj(Polaznik polaznik, String putanjaFajla) throws IOException {
        Path putanja = Path.of(putanjaFajla);
        try (Writer writer = Files.newBufferedWriter(putanja, StandardCharsets.UTF_8)) {
            gson.toJson(polaznik, writer);
        }
    }

    public Polaznik ucitaj(String putanjaFajla) throws IOException {
        Path putanja = Path.of(putanjaFajla);
        try (Reader reader = Files.newBufferedReader(putanja, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, Polaznik.class);
        }
    }

    public Polaznik ucitajNasumicnogPolaznika() throws IOException, InterruptedException {
        Polaznik polaznik = new Polaznik();
        popuniNasumicnogPolaznika(polaznik);
        return polaznik;
    }

    public void popuniNasumicnogPolaznika(Polaznik polaznik) throws IOException, InterruptedException {
        HttpRequest zahtev = HttpRequest.newBuilder().uri(URI.create(RANDOM_USER_API_URL)).GET().build();

        HttpResponse<String> odgovor = httpClient.send(zahtev, HttpResponse.BodyHandlers.ofString());

        if (odgovor.statusCode() != 200) {
            throw new IOException("RandomUser API Error: " + odgovor.statusCode());
        }

        obradiOdgovor(odgovor.body(), polaznik);
    }

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
