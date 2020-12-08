import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CustomSet<T> {
    private final Logger logger = Logger.getLogger(CustomSet.class.getName());
    
    private final List<T> elements = new LinkedList<>();

    public CustomSet(Collection<T> list) {
        logger.log(Level.INFO, " Constructor called!");
        elements.addAll(list);
    }

    public void add(T element) {
        if (!this.getElements().contains(element)) {
            this.getElements().add(element);
        }
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean contains(T findIt) {
        return elements.contains(findIt);
    }

    public Collection<T> getElements() {
        return this.elements;
    }

    public boolean isSubset(CustomSet<T> customSet) {
        if (!customSet.isEmpty()) {
            return this.elements.containsAll(customSet.getElements());
        }
        return true;
    }

    public boolean isDisjoint(CustomSet<T> customSet) {
        if (this.isEmpty() || customSet.isEmpty()) {
            return true;
        }

        return this.getElements().parallelStream().filter(x -> customSet.contains(x)).count() <= 0;
    }

    public boolean equals(CustomSet<T> customSet) {
        if (this.isEmpty() && customSet.isEmpty()) {
            return true;
        }

        if (this.getElements().size() != customSet.getElements().size()) {
            return false;
        }

        return this.getElements().parallelStream().filter(x -> customSet.contains(x)).count() == this.getElements().size();
    }


    public CustomSet<T> getIntersection(CustomSet<T> customSet) {
        return new CustomSet<T>(this.getElements().parallelStream().filter(x -> customSet.contains(x)).collect(Collectors.toList()));
    }

    public CustomSet<T> getDifference(CustomSet<T> customSet) {
        return new CustomSet<T>(this.getElements().parallelStream().filter(x -> !customSet.contains(x)).collect(Collectors.toList()));
    }

    // Get the union
    // Difference + other unique elements
    public CustomSet<T> getUnion(CustomSet<T> customSet) {
        CustomSet<T> union = this.getDifference(customSet);
        if (this.isSubset(union)) {
            union.getElements().addAll(customSet.getElements());
        } else {
            union.getElements().addAll(this.getElements());
        }
        union.getElements().parallelStream().forEach(System.out::println);
        return union;
    }
}