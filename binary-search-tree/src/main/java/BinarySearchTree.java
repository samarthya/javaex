import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class BinarySearchTree<T extends Comparable<T>> {
    private Logger logger = Logger.getLogger(BinarySearchTree.class.getName());

    Node<T> root;

    public BinarySearchTree(){
        logger.log(Level.INFO, " Binary Search Tree: Initialized.");
        root = null;
    }

    /**
     * If there is no node in the tree (An empty tree)
     * add it as the root (Vertex).
     * @param value
     */
    void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            /**
             * Root is already there so find the place and add.
             */
            insert(value, root);
        }
    }

    // Find the place to add the node.
    void insert(T value, Node<T> element) {
        if (value.compareTo(element.getData()) <= 0) {
            // Goes to left
            if (element.getLeft() == null) {
                element.left = new Node<>(value);
            } else {
                insert(value, element.left);
            }
        }

        if (value.compareTo(element.getData()) > 0) {
            // Goes to right
            if (element.getRight() != null) {
                insert(value, element.getRight());
            } else {
                element.right = new Node<>(value);
            }
        }
    }

    List<T> getAsSortedList() {
        return getAsLevelOrderList().stream().sorted().collect(Collectors.toList());
    }


    List<T> inOrder( Node<T> head, List<T> orderedList) {
        if (head != null) {
            logger.log(Level.INFO, " Data: " + head.getData());
            if (head.getLeft()!= null) {
                orderedList.add(head.getLeft().getData());
            }

            if (head.getRight()!= null) {
                orderedList.add(head.getRight().getData());
            }

            inOrder(head.getLeft(), orderedList);
            inOrder(head.getRight(), orderedList);
        }

        return orderedList;
    }

    List<T> getAsLevelOrderList() {
        List<T> orderedList = new LinkedList<>();
        orderedList.add(root.getData());
        return inOrder(this.root, orderedList);
    }

    Node<T> getRoot() {
        return root;
    }

    static class Node<T> {

        T data;

        Node<T> left, right;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        Node<T> getLeft() {
            return left;
        }

        Node<T> getRight() {
            return right;
        }

        T getData() {
            return data;
        }

    }
}
