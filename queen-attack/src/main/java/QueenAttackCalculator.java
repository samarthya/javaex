import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;

class Location {
    private final Logger logger = Logger.getLogger(Location.class.getName());

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    int x, y;

    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        logger.log(Level.INFO, this.x + " = " + ((Location)obj).x);
        logger.log(Level.INFO, this.y + " = " + ((Location)obj).y);
        return (obj != null) && ((this.x == ((Location)obj).x) && (this.y == ((Location)obj).y));
    }

    @Override
    public String toString() {
        return String.format(" [%d, %d]",getX(), getY());
    }
}


class Queen {
    private final Logger logger = Logger.getLogger(Queen.class.getName());
    public Location getPosition() {
        return position;
    }

    protected Location position;

    public Queen(int x, int y) {

        if (x < 0) {
            logger.log(Level.SEVERE, "Queen position must have positive row.");
            throw new IllegalArgumentException("Queen position must have positive row.");
        }

        if (x > 7) {
            logger.log(Level.SEVERE, "Queen position must have row <= 7.");
            throw new IllegalArgumentException("Queen position must have row <= 7.");
        }

        if (y < 0) {
            logger.log(Level.SEVERE, "Queen position must have positive column.");
            throw new IllegalArgumentException("Queen position must have positive column.");
        }

        if (y > 7) {
            logger.log(Level.SEVERE, "Queen position must have column <= 7.");
            throw new IllegalArgumentException("Queen position must have column <= 7.");
        }

        this.position = new Location(x,y);
    }


}

public class QueenAttackCalculator {
    private final Logger logger = Logger.getLogger(QueenAttackCalculator.class.getName());
    private final Queen []smq;

    public QueenAttackCalculator(Queen ...queens){
        smq = queens;
        if (smq.length  < 2) {
            throw new IllegalArgumentException(" Need two queens for checking attack.");
        }

        if (!Arrays.stream(queens).allMatch( q -> q != null)){
            throw new IllegalArgumentException("You must supply valid positions for both Queens.");
        }

        if (smq[0].getPosition().equals(smq[1].getPosition())) {
            throw new IllegalArgumentException("Queens cannot occupy the same position.");
        }

    }

    public boolean canQueensAttackOneAnother() {
        // Check if it is in the same horizontal line
        if ((smq[0].getPosition().getX() == smq[1].getPosition().getX()) || (smq[0].getPosition().getY() == smq[1].getPosition().getY())) {
            logger.log(Level.INFO, " In the same column or row, so easy attack!");
            return true;
        }

        if (Math.abs(smq[0].getPosition().getX()- smq[1].getPosition().getX()) - Math.abs(smq[0].getPosition().getY()- smq[1].getPosition().getY()) == 0) {
            logger.log(Level.INFO, " Attack Possible: " + smq[0].getPosition() + ", " + smq[1].getPosition());
            return true;
        }
        return false;
    }
}