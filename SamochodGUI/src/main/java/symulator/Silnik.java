package symulator;

public class Silnik extends Komponent {
    private int maxObroty;
    private int obroty; //aktualna liczba obrotow

    public Silnik(String nazwa, double waga, double cena, int maxObroty){
        super(nazwa, waga, cena);
        this.maxObroty = maxObroty;
        this.obroty = 0;
    }
    public void uruchom() {
        obroty = 800;
        System.out.println("Silnik uruchomiony.");
        System.out.println("obroty = " + obroty);
    }
    public void zatrzymaj() {
        obroty = 0;
        System.out.println("Silnik zatrzymany.");
        System.out.println("obroty = " + obroty);

    }
    public void zwiekszObroty() {
        if (obroty + 100 > maxObroty) {
            throw new SamochodException(
                    "Nie mozna zwiększyc obrotow powyzej " + maxObroty
            );
        }
        obroty += 100;
        System.out.println("Obroty wzrosly do: " + obroty);
    }

    public void zmniejszObroty() {
        if (obroty - 100 < 800) {
            throw new SamochodException(
                    "Nie mozna zmniejszyc obrotow ponizej 800"
            );
        }
        obroty -= 100;
        System.out.println("Obroty zmalaly do: " + obroty);
    }
    public int getObroty() {
        return obroty;
    }
    public int getMaxObroty() {
        return maxObroty;
    }
}
