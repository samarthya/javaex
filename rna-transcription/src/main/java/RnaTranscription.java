import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

class RnaTranscription {
    public RnaTranscription() {
    }


    // Friday 6th November 2020
    String transcribe(String dnaStrand) {
        String rnaStrand = null;
        Map<String, String> stringIntegerMap = new LinkedHashMap<>() {
            {
                put("T", "A");
                put("G","C");
                put("C","G");
                put("A","U");
            }
        };

        if (dnaStrand.isEmpty())
            return "";


        rnaStrand = new String(dnaStrand.chars().mapToObj(x -> String.valueOf((char) x).toUpperCase()).map( x ->  {
            // System.out.printf(" RNA: %s\n", stringIntegerMap.get(x));
            return stringIntegerMap.get(x);
        }).collect(Collectors.joining()));

        // System.out.println(rnaStrand);
        return rnaStrand;
    }


}
