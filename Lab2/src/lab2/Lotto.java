package lab2;
import java.util.Random;
import java.util.ArrayList;

public class Lotto {
    public static void main(String[] args) {
        //2
        ArrayList<Integer> types = new ArrayList<>();
        for (String arg : args) {
            int num = Integer.parseInt(arg);
            if (num < 1 || num > 49) {
                System.out.println("Liczby są spoza przedziału (1-49)");
                return;
            }
            types.add(num);
        }

        //1
        ArrayList<Integer> results = new ArrayList<>();
        Random rand = new Random();
        while (results.size() < 6) {
            int num = rand.nextInt(49) + 1;
            if (!results.contains(num)) {
                results.add(num);
            }
        }

        //2
        int hits = 0;
        for (int num : types) {
            if (results.contains(num)) {
                hits++;
            }
        }

        System.out.println("Typy: " + types);
        System.out.println("Wylosowane l: " + results);
        System.out.println("Liczba trafien: " + hits);

        // 3
        System.out.println("\nSymulacja pełnego trafienia: ");
        ArrayList<Integer> target = new ArrayList<>();
        while (target.size() < 6) {
            int num = rand.nextInt(49) + 1;
            if (!target.contains(num)) {
                target.add(num);
            }
        }

        int attempts = 0;
        long startTime = System.currentTimeMillis();
        ArrayList<Integer> temp;

        do {
            temp = new ArrayList<>();
            while (temp.size() < 6) {
                int num = rand.nextInt(49) + 1;
                if (!temp.contains(num)) {
                    temp.add(num);
                }
            }
            attempts++;
        } while (!temp.containsAll(target));

        long endTime = System.currentTimeMillis();

        System.out.println("Docelowe liczby: " + target);
        System.out.println("Liczba losowan potrzebnych do pelnego trafienia: " + attempts);
        System.out.println("Lączny czas dzialania programu: " + (endTime - startTime));
    }
}
