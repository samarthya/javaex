import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class SpaceAge {
    private final static Logger logger = Logger.getLogger(SpaceAge.class.getName());
    private final double sec;
    private final double EARTH = 31557600;

    private final static Map<String, Double> yearsMap = new LinkedHashMap() {
        {
            put("mercury", 0.2408467);
            put("venus", 0.61519726);
            put("mars", 1.8808158);
            put("jupiter",11.862615);
            put("saturn",29.447498);
            put("uranus",84.016846);
            put("neptune", 164.79132);
        }
    };

    private double getYear(String planet){
        Double years = yearsMap.get(planet);
        
        if (years!= null) {
            return onEarth()/years.doubleValue();
        }
        return onEarth();
    }

    SpaceAge(double seconds) {
        logger.log(Level.INFO, " Seconds Initialization " + seconds);
        this.sec = seconds;
    }

    double getSeconds() {
        return this.sec;
    }

    double onEarth() {
        return getSeconds()/EARTH;
    }

    double onMercury() {
        return getYear("mercury");
    }

    double onVenus() {
        return getYear("venus");
    }

    double onMars() {
        return getYear("mars");
    }

    double onJupiter() {
        return getYear("jupiter");
    }

    double onSaturn() {
        return getYear("saturn");
    }

    double onUranus() {
        logger.log(Level.INFO, "Urnaus");
        return getYear("uranus");
    }

    double onNeptune() {
        logger.log(Level.INFO, "Neptune");
        return getYear("neptune");
    }

}
