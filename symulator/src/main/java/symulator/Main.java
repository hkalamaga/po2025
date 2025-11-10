package symulator;

public class Main {
    public static void main(String[] args) {
        Silnik silnik = new Silnik("Silnik 1.9 TDI", 150.0, 5000.0, 5500);
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("Skrzynia manualna", 50.0, 2500.0, 6);
        Sprzeglo sprzeglo = new Sprzeglo("Sprzeglo", 18.0, 600.0);
        Pozycja start = new Pozycja(0, 0);

        Samochod samochod = new Samochod("TSZ28312", 160, silnik, skrzynia, sprzeglo, start);

        samochod.wlacz();
        sprzeglo.wcisnij();
        skrzynia.zwiekszBieg();
        sprzeglo.zwolnij();

        System.out.println("Predkosc aktualna: " + samochod.getAktPredkosc() + " km/h");
        samochod.jedzDo(new Pozycja(10, 7));
        skrzynia.zwiekszBieg();
        System.out.println("Predkosc aktualna: " + samochod.getAktPredkosc() + " km/h");
        samochod.jedzDo(new Pozycja(60, 20));
        samochod.wylacz();

        System.out.println(samochod);
    }

}
