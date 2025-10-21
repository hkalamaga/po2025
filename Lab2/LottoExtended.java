import java.util.Random;
import java.util.ArrayList;

public class LottoExtended {
    public static void main(String[] args) {
        ArrayList<Integer> types= new ArrayList<Integer>();
        for (String arg : args) {
            int num = Integer.parseInt(arg);
            if (num < 1 || num > 49) {
                System.out.println("Liczby są spoza przedzialu(1-49)");
                return;
            }
            types.add(num);
        }

        ArrayList<Integer> results = new ArrayList<Integer>();
        Random rand = new Random();
        while (results.size() < 6) {
            int num = rand.nextInt(49) + 1;
            if (!results.contains(num)) {
                results.add(num);
            }
        }
        //spr trafien 
        int hits = 0;
        for (int num : types) {
            if (results.contains(num)) {
                hits++;
            }
        }

        System.out.println("Twoje typy: " + types);
        System.out.println("Wylosowane liczby: " + results);
        System.out.println("Liczba trafień: " + hits);
    }
}
