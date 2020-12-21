import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Anagram {
    private String txt;

    public Anagram(String txt) {
        if (txt != null && !txt.isEmpty()) {
            this.txt = txt.toLowerCase();
        }
    }


    public List<String> match(List<String> input) {
        BitSet sm = new BitSet(this.txt.length());
        sm.clear();

        Stream.of(this.txt.toLowerCase().toCharArray()).peek(System.out::println);

        return input.stream().filter(x -> {
            sm.clear();
            return (x.length() == this.txt.length()) && (!x.toLowerCase().contains(this.txt)) &&
                   (x.toLowerCase().chars().filter( y -> {
            int index = this.txt.indexOf(y);

            if (index >=0 ) {
                if (sm.get(index)) {
                    do {
                        index = this.txt.indexOf(y, index);

                        if (index < 0) {
                            return false;
                        }

                        if (sm.get(index)) {
                            index +=1;
                            continue;
                        } else {
                            sm.set(index);
                            return true;
                        }
                    } while(true);
                } else{
                    sm.set(index);
                    return true;
                }
            }else {
                return false;
            }
        })
        ).count() == txt.length();
        }).collect(Collectors.toList());
    }


}
