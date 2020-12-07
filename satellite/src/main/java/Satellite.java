import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Satellite {
    private final Logger logger = Logger.getLogger(Satellite.class.getName());

    public Satellite()
    {
        logger.log(Level.INFO," Satellite ");
    }

    public Tree treeFromTraversals (List<Character> a, List<Character> b) {
        if( (a.stream().parallel().distinct().count() != a.size()) || (b.stream().distinct().count() != b.size())) {
            throw new IllegalArgumentException("traversals must contain unique items");
        }

        if((a.stream().parallel().distinct().count() != b.size()) || (b.stream().distinct().count() != a.size())) {
            throw new IllegalArgumentException("traversals must have the same length");
        }

        //if (a.stream().filter( e -> b.contains(e) != false).count() == b.stream().filter( e -> a.contains(e) != false).count()) {
        if (!a.containsAll(b)) {
            throw new IllegalArgumentException("traversals must have the same elements");
        }


        return new Tree(buildNodes(a, b));
    }

    private void dumpTree(List<Character> list) {
        logger.log(Level.INFO, list.stream().map(x -> x.toString()).collect(Collectors.joining()));
    }
    /**
     * Builds the tree.
     * @param preOrder
     * @param inOrder
     * @return
     */
    private Node buildNodes(List<Character> preOrder, List<Character> inOrder) {
        // If any of the tree is empty return null.
        if(preOrder.isEmpty() || inOrder.isEmpty()){
            return null;
        }

        // Root is always the first element in InOrder
        Node root = new Node(preOrder.get(0).charValue());
        logger.log(Level.FINE," Vertex: " + preOrder.get(0).charValue());

        // The Index of the root i identifies the Left and Right Subtree
        // 0 - i-1 is left and i+1 - n is the Right subtree
        int iRoot = inOrder.indexOf(preOrder.get(0));

        // Enable to see the trees created.
        // dumpTree(preOrder.subList(1, preOrder.size()));
        // dumpTree(inOrder.subList(0, iRoot));
        // dumpTree(preOrder.subList(iRoot + 1, preOrder.size()));
        // dumpTree(inOrder.subList(iRoot+1, inOrder.size()));

        // Build the left tree first
        root.left = buildNodes(preOrder.subList(1, preOrder.size()), inOrder.subList(0, iRoot));

        // Build the right tree
        root.right = buildNodes(preOrder.subList(iRoot + 1, preOrder.size()), inOrder.subList(iRoot+1, inOrder.size()));

        return root;
    }
}