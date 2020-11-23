import java.time.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Gigasecond {
    private final static Logger log = Logger.getLogger(Gigasecond.class.getName());

    static {
        log.log(Level.INFO, "GIGASECOND Logger initialized");
    }

    private LocalDateTime localDate;

    public Gigasecond(LocalDate moment) {
        // Using the constructor call to initialize the localDate
        this(moment.atStartOfDay());
        log.log(Level.INFO, " Date initialized - " + this.localDate.toString());
    }

    public Gigasecond(LocalDateTime moment) {
        this.localDate = moment.plusSeconds(1000000000);
        log.log(Level.INFO, " 10^9 Adding to the instance - " + this.localDate.toString());
    }

    public LocalDateTime getDateTime() {
        log.log(Level.INFO, " Date & Time - " + this.localDate.toString());
        return localDate;
    }
}
