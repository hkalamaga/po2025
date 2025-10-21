import java.util.Random;
import java.util.ArrayList; 

public class Lotto {
    public static void main(String[] args) {
        ArrayList<Integer> results = new ArrayList<Integer>(); 
        Random rand = new Random();

        while (results.size() < 6) {
            int liczba = rand.nextInt(49) + 1;
            if (!results.contains(liczba)){
                results.add(liczba);
            }
        }
        System.out.println("Result: " + results);
    }
}
