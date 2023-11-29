import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static private final String[] romanNumeralsList = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII",
            "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV",
            "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI",
            "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII",
            "XCIX", "C"
    };

    public static void main(String[] args) {
        System.out.println("Введите выражение:  (для выхода наберите exit)");
        Scanner console = new Scanner(System.in);
        String enter = console.nextLine();
        if (enter.equals("exit")) {
            return;
        }
        try {
            String result = calc(enter);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        main(args);
    }

    public static String calc(String input) throws Exception {
        String[] args = input.split("[+-/*]");
        String action = input.replaceAll("[^+-/*]", "");

        if (args.length != 2 || action.length() != 1) {
            throw new Exception("Ошибка ввода выражения (должно быть: количество переменных: 2, знаков: 1)");
        }

        String number1 = args[0].trim();
        String number2 = args[1].trim();

        if (isNumeric(number1) && isNumeric(number2)) {
            return String.valueOf(process(Integer.parseInt(number1), Integer.parseInt(number2), action));
        }

        int romanNumber1 = romanToNumber(number1);
        int romanNumber2 = romanToNumber(number2);

        return numToRoman(process(romanNumber1, romanNumber2, action));
    }

    private static int process(int number1, int number2, String action) throws Exception {
        return switch (action) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            case "/" -> number1 / number2;
            default -> throw new Exception("не верное действие");
        };
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int romanToNumber(String roman) throws Exception {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new Exception("Должны быть введены римские числа от I до X, либо арабские от 1 до 10");
        };
    }

    private static String numToRoman(int numArabian) throws Exception {
        if (numArabian == 0) {
            throw new Exception("Римские числа должны быть > 0");
        }
        return romanNumeralsList[numArabian];
    }
}
