package symulator;

public class Pozycja {
    private double x;
    private double y;
    double dt =0.1;

    public Pozycja(double x, double y){
        this.x=x;
        this.y=y;
    }
    public void aktualizujPozycje(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }
    public void przemiesc(double x_cel, double y_cel, double V){
        double x_po = x + (V*dt * (x_cel-x));
        double y_po = y + (V*dt * (y_cel-y));
        this.x = x_po;
        this.y = y_po;
    }
    public String getPozycja() {
        return "(" + x + ", " + y + ")";
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public String toString() {
        return "Pozycja: X= " + x + ", Y=" + y;
    }

}
