package symulator;
import java.util.ArrayList;
import java.util.List;

public class Samochod extends Thread {
    private Silnik silnik;
    private SkrzyniaBiegow skrzynia;
    private Sprzeglo sprzeglo;
    private Pozycja pozycja;
    private boolean stanWlaczenia;
    private String nrRejest;
    private int predkoscMax;
    private String model;
    private Pozycja cel;
    private List<Listener> listeners = new ArrayList<>();
    private volatile boolean running = true;

    public Samochod(String model,String nrRejest, int predkoscMax, Silnik silnik,
                    SkrzyniaBiegow skrzynia, Sprzeglo sprzeglo, Pozycja pozycja) {
        this.model=model;
        this.nrRejest = nrRejest;
        this.predkoscMax = predkoscMax;
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.sprzeglo = sprzeglo;
        this.pozycja = pozycja;
        this.stanWlaczenia = false;
        this.start();
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
    public void jedzDo(Pozycja nowaPozycja) {
        if (!stanWlaczenia || silnik.getObroty() == 0) {
            System.out.println("Silnik wylaczony");
            return;
        }
        this.cel = nowaPozycja;
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
    @Override
    public String toString() {
        return model + " (" + nrRejest + ")";
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
    public String getModel() {
        return model;
    }
    public String getNrRejest(){
        return nrRejest;
    }
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (Listener listener : listeners) {
            listener.update();
        }
    }
    @Override
    public void run() {
        double deltat = 0.1; // 100 ms

        while (running) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }

            if (cel != null && stanWlaczenia) {
                double dx = cel.getX() - pozycja.getX();
                double dy = cel.getY() - pozycja.getY();

                double odleglosc = Math.sqrt(dx * dx + dy * dy);

                if (odleglosc < 2) {
                    pozycja.aktualizujPozycje(dx, dy);
                    cel = null;
                    continue;
                }

                double v = getAktPredkosc() / 3.6;

                double vx = v * deltat * (dx / odleglosc);
                double vy = v * deltat * (dy / odleglosc);

                pozycja.aktualizujPozycje(vx, vy);
                notifyListeners();

            }
        }
    }

    public Pozycja getPozycja() {
        return pozycja;
    }
}

