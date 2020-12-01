package me.samarthya;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        int myArray[] = { 1, 1, 2, 3, 4 , 5,6, 6,6, 7, 7, 7, 7, 7,7,3, 4, 3, 3, 2, 2, 2, 2, 2 };

        System.out.println(" Total Elements: " + myArray.length);
        // Simple map
        // Multiply each element by 5
        // Arrays.stream(myArray).map(index -> index * 5).forEach(System.out::println);

        // Will create a map of
        // Item that appears and the number of times it appears.
        Map<Integer, Integer> myMap = Arrays.stream(myArray).mapToObj(i -> Integer.valueOf(i)).sorted().collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> {
            System.out.println(" Element read: " + e);
            return 1;
        })));

//        myMap.forEach((x,i) -> {
//            System.out.println(" Index: " + x.intValue() + " Val: " + i.intValue() );
//        });

        myMap.keySet().forEach( e -> {
            System.out.println(" [ " + e + " ]  = " + myMap.get(e).intValue());
        });

        //[[1,2], [2,6], [3,4]]
        //[1,2,2,6....]
        int[] myInts = myMap.keySet().stream().mapToInt(i -> Integer.valueOf(i)).flatMap(i -> IntStream.of(i, myMap.get(i))).toArray();

        Stream.of(myInts).forEach(System.out::println);

    }
}
