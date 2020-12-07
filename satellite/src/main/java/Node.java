/**
 * Representation of the node
 */
class Node {
    public final char value;
    /**
     * Left Subtree.
     */
    public Node left;
    /**
     * Right Subtree
     */
    public Node right;

    public Node(char value) {
        this(value, null, null);
    }

    /** For testing. */
    Node(char value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
