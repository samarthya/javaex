import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForthEvaluator {

    private final Logger logger = Logger.getLogger(ForthEvaluator.class.getName());

    private final ArrayList<String> opList = new ArrayList<>() {
        {
            addAll(Stream.of("DUP, DROP, SWAP, OVER").collect(Collectors.toList()));
            addAll(Stream.of("+", "-", "*", "/").collect(Collectors.toList()));
        }
    };

    public List<Integer> evaluateProgram(List<String> list) {

        if (list.isEmpty()) {
            return Collections.emptyList();
        }


        if (list.size() == 1) {
            list.stream().forEach( x  -> {
            });
            //return list.stream().map(x -> x.toString().split(" ")).filter( y -> !opList.contains(y)).mapToInt( z -> Integer.valueOf(z)).collect(Collectors.toList());
        }


        return null;
    }
}