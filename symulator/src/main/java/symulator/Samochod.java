package symulator;

public class Samochod {
    private Silnik silnik;
    private SkrzyniaBiegow skrzynia;
    private Sprzeglo sprzeglo;
    private Pozycja pozycja;
    private boolean stanWlaczenia;
    private String nrRejest;
    private int predkoscMax;

    public Samochod(String nrRejest, int predkoscMax, Silnik silnik,
                    SkrzyniaBiegow skrzynia, Sprzeglo sprzeglo, Pozycja pozycja) {
        this.nrRejest = nrRejest;
        this.predkoscMax = predkoscMax;
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.sprzeglo = sprzeglo;
        this.pozycja = pozycja;
        this.stanWlaczenia = false;
    }
    public void wlacz(){
        stanWlaczenia = true;
        silnik.uruchom();
        System.out.println("Samochod wlaczony");
    }
    public void wylacz() {
        stanWlaczenia = false;
        silnik.zatrzymaj();
        System.out.println("Samochod wylaczony");
    }
    public void jedzDo(Pozycja cel){
        if (!stanWlaczenia || silnik.getObroty() ==0){
            System.out.println("Silnik wylaczony");
            return;
        }
        double xcel = cel.getX() - pozycja.getX();
        double ycel = cel.getY() - pozycja.getY();
        System.out.println("Samochod jedzie do: (" + xcel + ", " + ycel + ")");

        while (true) {
            double v = getAktPredkosc() / 3.6;
            if (v==0){
                System.out.println("Samochod nie jedzie");
                return;
            }

            //jeden krok ruchu
            pozycja.przemiesc(xcel, ycel, v);
            //spr czy blisko cel
            if(Math.abs(pozycja.getX() -xcel) < 0.01
                && Math.abs(pozycja.getY() - ycel) <0.01) {
                pozycja.aktualizujPozycje(xcel - pozycja.getX(), ycel - pozycja.getY());

                System.out.println("Samochod dojechal do: " + getAktPozycja());
                break;
            }
        }
    }
    public double getWaga(){
        return silnik.getWaga() + skrzynia.getWaga() + sprzeglo.getWaga();
    }
    public double getAktPredkosc(){
        if (!stanWlaczenia || silnik.getObroty() == 0 || skrzynia.getAktBieg() == 0) {
            return 0;
        }
        int predkosc = skrzynia.getAktBieg() * 20;
        return Math.min(predkosc, predkoscMax);
    }
    public String getAktPozycja(){
        return pozycja.getPozycja();
    }
    public String toString() {
        return "nrRejest: " + nrRejest + "\n" + "Pozycja: " + getAktPozycja() + "\n" +
                "Predkosc: " + getAktPredkosc() + "km/h\n" + "Waga: " + getWaga() + "kg\n";
    }
    public Silnik getSilnik() {
        return silnik;
    }
    public SkrzyniaBiegow getSkrzynia() {
        return skrzynia;
    }
    public Sprzeglo getSprzeglo() {
        return sprzeglo;
    }



}
