package me.samarthya;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static void ExampleOne() {
        Consumer<String> myString = (x) -> System.out.println(x);
        myString.accept("Hello World!");
    }

    public static void ExampleTwo() {
        Consumer<List<String>> myList = (x) -> x.stream().sorted().forEach(e -> System.out.println(" Element: " + e));
        List<String> dummyContents = new LinkedList<String>() {
            {
                add(" Hello");
                add(" World");
                add(" !");
            }
        };

        myList.accept(dummyContents);
    }

    public static void ExampleThree() {
        Consumer<List<String>> toUpper = (x) -> x.replaceAll(String::toUpperCase);
        Consumer<List<String>> toJoin = (x) -> x.stream().map(e -> String.format("%s(%d) ", e, e.length())).forEach(e -> System.out.printf(e));

        List<String> dummyContents = new LinkedList<String>() {
            {
                add("Hello");
                add("World");
                add("!");
            }
        };

        toUpper.andThen(toJoin).accept(dummyContents);

    }

    public static void main(String[] args) {
        System.out.println(" Ex1.");
        ExampleOne();
        System.out.println(" Ex2.");
        ExampleTwo();
        System.out.println(" Ex3.");
        ExampleThree();
    }
}
