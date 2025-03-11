package codo;

public class EvenOrOdd {
    public static String evenOrOdd(int number) {
        if (number % 2 == 0) {
            return "Even";
        } else {
            return "Odd";
        }
    }

    public static String evenOrOdd_cw(int number) {
        return (number % 2) == 0 ? "Even" : "Odd";
    }
}
