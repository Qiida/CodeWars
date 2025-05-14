package codo;

public class Greeter {
    public static String greet(String name)
    {
        return "Hello, " + name + " how are you doing today?";
    }

    public static String greet_fstring(String name)
    {
        return String.format("Hello, %s how are you doing today?", name);
    }
}
