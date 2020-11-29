import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Will generalize it for complete chess
 */
enum Character {
    KING,
    QUEEN,
    PAWN,
    BISHOP,
    ROOK,
    KNIGHT
}

class Position extends Point {
    // represents the board size
    // ex. 8 => 8x8
    private int size = 8;

    public Position(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return String.format(" X: %d, Y: %d", x, y);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    boolean isValid() {
        return (x >= 0 && x < size) && (y >= 0 && y < size);
    }
}

/**
 * Every type of Character in the chess can be initialized and place identified by its position.
 */
abstract class ChessItem {

    protected ChessItem(Character type, Position position) {
        this.type = type;
        this.position = position;
        this.attackOptions = new LinkedHashSet<>();
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    private Character type;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    private Position position;

    protected Set<Position> attackOptions;

    abstract Set<Position> getAllMoves();
}

/**
 * The board for the items.
 */
class ChessBoard {
    // Default dimension 8
    private int dimension = 8;

    public void setDimension(int size) {
        this.dimension = size;
    }

    private Map<Integer, ChessItem> board = new LinkedHashMap<>();;

    // Initialize the ChessBoard
    public ChessBoard(int dimension) {
        setDimension(dimension);
        // board.keySet().addAll(IntStream.rangeClosed(0,63).mapToObj( i -> Integer.valueOf(i)).collect(Collectors.toList()));
    }

    public void placeTheItem(Position pos, ChessItem item) {
        if (this.board.get(Integer.valueOf((8 * pos.y) + pos.x)) != null) {
            throw new IllegalArgumentException(" The position " + pos + " already occupied by " + this.board.get(Integer.valueOf((8 * pos.y) + pos.x)).getType());
        }
        this.board.put(Integer.valueOf((8 * pos.y) + pos.x), item);
    }

    public void placeTheItem(Position pos, ChessItem item, boolean force) {
        if (force == true) {
            this.board.put(Integer.valueOf((8 * pos.y) + pos.x), item);
        }
    }
}

/**
 * CharType - Queen
 */
class Queen extends ChessItem{

    private void aptException() {
        if (getPosition().x < 0) {
            throw new IllegalArgumentException("Queen position must have positive row.");
        }

        if (getPosition().y < 0) {
            throw new IllegalArgumentException("Queen position must have positive column.");
        }

        if (getPosition().x >= getPosition().getSize()) {
            throw new IllegalArgumentException("Queen position must have row <= 7.");
        }

        if (getPosition().y >= getPosition().getSize()) {
            throw new IllegalArgumentException("Queen position must have column <= 7.");
        }
    }

    public Queen(int x, int y) {
        super(Character.QUEEN, new Position(x,y));
        if (!getPosition().isValid()) {
            aptException();
        }
    }

    @Override
    Set<Position> getAllMoves() {
        int x = getPosition().x;
        int y = getPosition().y;

        int size = getPosition().getSize();

        attackOptions.addAll(IntStream.range(0, x).mapToObj(index -> {
            Position pt = new Position(index, y);
            return pt;
        }).collect(Collectors.toSet()));

        attackOptions.addAll(IntStream.range(x, size).mapToObj(index -> {
            Position pt = new Position(index, y);
            return pt;
        }).collect(Collectors.toSet()));

        attackOptions.addAll(IntStream.range(0, y).mapToObj(index -> {
            Position pt = new Position(x, index);
            return pt;
        }).collect(Collectors.toSet()));


        attackOptions.addAll(IntStream.range(y, size).mapToObj(index -> {
            Position pt = new Position(x, index);
            return pt;
        }).collect(Collectors.toSet()));


        attackOptions.addAll(IntStream.range(0, size).mapToObj(index -> {
            int nextX = x - index;
            int nextY = y - index;
            Position pt = new Position(nextX, nextY);
            // Up
            if (pt.isValid()) {
                return pt;
            } else {
                return null;
            }
        }).collect(Collectors.toSet()));

        attackOptions.addAll(IntStream.rangeClosed(0, 7).mapToObj(index -> {
            int nextX = x + index;
            int nextY = y + index;
            Position pt = new Position(nextX, nextY);
            // Up
            if (pt.isValid()) {
                return pt;
            } else {
                return null;
            }
        }).collect(Collectors.toSet()));

        attackOptions.addAll(IntStream.rangeClosed(0, 7).mapToObj(index -> {
            int nextX = x + index;
            int nextY = y - index;
            Position pt = new Position(nextX, nextY);
            // Up
            if (pt.isValid()) {
                return pt;
            } else {
                return null;
            }
        }).collect(Collectors.toSet()));

        attackOptions.addAll(IntStream.rangeClosed(0, 7).mapToObj(index -> {
            int nextX = x - index;
            int nextY = y + index;
            Position pt = new Position(nextX, nextY);
            // Up
            if (pt.isValid()) {
                return pt;
            } else {
                return null;
            }
        }).collect(Collectors.toSet()));

        return attackOptions;
    }
}


/**
 * Sample Problem - 1
 */
public class QueenAttackCalculator {
    List<Queen> queenList;

    // The board
    ChessBoard myBoard = new ChessBoard(8);

    public QueenAttackCalculator(Queen ...queens) {

        queenList = Arrays.asList(queens);

        if (queenList.stream().filter(q -> q == null).count() > 0) {
            throw new IllegalArgumentException("You must supply valid positions for both Queens.");
        }

        try {
            queenList.stream().forEach( q -> {
                myBoard.placeTheItem(q.getPosition(), q);
            });
        } catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Queens cannot occupy the same position.");
        }
    }


    public boolean canQueensAttackOneAnother() {
        return queenList.get(0).getAllMoves().contains(queenList.get(1).getPosition());
    }

}