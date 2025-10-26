package lab3;
public class CodingBat {

    // Warmup 1 – sleepIn
    public static boolean sleepIn(boolean weekday, boolean vacation) {
        return (!weekday || vacation);
    }

    // Warmup 2 – monkeyTrouble
    public static boolean monkeyTrouble(boolean aSmile, boolean bSmile) {
        return (aSmile == bSmile);
    }

    // String 1 – helloName
    public static String helloName(String name) {
        return "Hello " + name + "!";
    }

    // Array 2 – countEvens
    public static int countEvens(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // test sleepIn
        System.out.println(sleepIn(true, false));
        System.out.println(sleepIn(false, false));
        System.out.println(sleepIn(true, true));

        // test monkeyTrouble
        System.out.println(monkeyTrouble(true, true));
        System.out.println(monkeyTrouble(false, false));
        System.out.println(monkeyTrouble(true, false));
        System.out.println(monkeyTrouble(false, true));

        // test helloName
        System.out.println(helloName("Alice"));
        System.out.println(helloName("Bob"));

        // test countEvens
        System.out.println(countEvens(new int[]{2, 1, 2, 3, 4}));
        System.out.println(countEvens(new int[]{2, 2, 0}));
        System.out.println(countEvens(new int[]{1, 3, 5}));
    }
}
