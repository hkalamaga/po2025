package symulator;

public class SkrzyniaBiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;

    public SkrzyniaBiegow(String nazwa, double waga, double cena, int iloscBiegow){
        super(nazwa, waga, cena);
        this.iloscBiegow = Math.max(1,iloscBiegow);
        this.aktualnyBieg = 0;
    }
    public void zwiekszBieg() {
        if (aktualnyBieg < iloscBiegow) {
            aktualnyBieg++;
        }
        System.out.println("Aktualny bieg: " + aktualnyBieg);
    }
    public void zmniejszBieg() {
        if(aktualnyBieg > 0) {
            aktualnyBieg--;
        }
        System.out.println("Aktualny bieg: " + aktualnyBieg);
    }
    public int getAktBieg() {
        return aktualnyBieg;
    }
    public double getIloscBiegow() {
        return iloscBiegow;


    }

}
