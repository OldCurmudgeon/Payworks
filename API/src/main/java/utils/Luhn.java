package utils;

/**
 * Simple Luhn checker
 */
public class Luhn {

    public static boolean check(String pan) {
        int sum = 0;
        // Alternate digits are treated differently.
        boolean alternate = false;
        for (int i = pan.length() - 1; i >= 0; i--) {
            int n = Character.digit(pan.charAt(i), 10);
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
