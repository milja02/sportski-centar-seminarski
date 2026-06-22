package rs.fon.json;

import rs.fon.domen.Mesto;
import rs.fon.domen.Polaznik;

public class MainPolaznikJson {

    public static void main(String[] args) throws Exception {
        PolaznikJson json = new PolaznikJson();
        String putanja = "polaznik.json";

        System.out.println("Upis i citanje iz fajla:");
        Mesto mesto = new Mesto(1, "Beograd", 11000);
        Polaznik polaznik = new Polaznik(1, "Marko", "Markovic", "0611234567", mesto);

        json.sacuvaj(polaznik, putanja);
        System.out.println("Sacuvano u: " + putanja);

        Polaznik ucitani = json.ucitaj(putanja);
        System.out.println("Ucitano iz fajla: " + ucitani);

        System.out.println();
        System.out.println("Ucitavanje sa RandomUser API-ja:");
        Polaznik nasumicanPolaznik = json.ucitajNasumicnogPolaznika();
        System.out.println("Ime: " + nasumicanPolaznik.getIme());
        System.out.println("Prezime: " + nasumicanPolaznik.getPrezime());
        System.out.println("Telefon: " + nasumicanPolaznik.getBrojTelefona());

        nasumicanPolaznik.setIdPolaznik(2);
        nasumicanPolaznik.setMesto(new Mesto(2, "Novi Sad", 21000));
        json.sacuvaj(nasumicanPolaznik, "nasumican_polaznik.json");
        System.out.println("Nasumican polaznik sacuvan u nasumican_polaznik.json");
    }
}
