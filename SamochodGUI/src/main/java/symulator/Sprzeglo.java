package symulator;

public class Sprzeglo extends Komponent {
    private boolean stanSprzegla;

    public Sprzeglo(String nazwa, double waga, double cena){
        super(nazwa, waga, cena);
        this.stanSprzegla = false;
    }
    public void wcisnij() {
        if (stanSprzegla) {
            throw new SamochodException("Sprzeglo jest juz wcisniete");
        }
        stanSprzegla = true;
        System.out.println("Sprzeglo wcisniete");
    }
    public void zwolnij(){
        if (!stanSprzegla) {
            throw new SamochodException("Sprzeglo jest juz zwolnione");
        }
        stanSprzegla = false;
        System.out.println("Sprzeglo zwolnione.");
    }
    public boolean czyWcisniete(){
        return stanSprzegla;
    }
}
