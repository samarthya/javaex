import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

public class SatelliteTest {
    Satellite satellite = new Satellite();

    @Test
    public void emptyTree() {
        List<Character> preorder = List.of();
        List<Character> inorder = List.of();

        Tree tree = satellite.treeFromTraversals(preorder, inorder);

        assertThat(tree.preorder()).containsExactlyElementsOf(preorder);
        assertThat(tree.inorder()).containsExactlyElementsOf(inorder);
        assertThat(tree.postorder()).isEmpty();
    }

    @Test
    public void treeWithOneItem() {
        List<Character> preorder = List.of('a');
        List<Character> inorder = List.of('a');

        Tree tree = satellite.treeFromTraversals(preorder, inorder);

        assertThat(tree.preorder()).containsExactlyElementsOf(preorder);
        assertThat(tree.inorder()).containsExactlyElementsOf(inorder);
        assertThat(tree.postorder()).containsExactly('a');
    }

    @Test
    public void treeWithManyItems() {
        List<Character> preorder = List.of('a', 'i', 'x', 'f', 'r');
        List<Character> inorder = List.of('i', 'a', 'f', 'x', 'r');

        Tree tree = satellite.treeFromTraversals(preorder, inorder);

        assertThat(tree.preorder()).containsExactlyElementsOf(preorder);
        assertThat(tree.inorder()).containsExactlyElementsOf(inorder);
        assertThat(tree.postorder()).containsExactly('i', 'f', 'r', 'x', 'a');
    }

    @Test
    public void treeWithBalancedItems() {
        List<Character> preorder = List.of('5', '3', '2', '4', '7', '6', '8');
        List<Character> inorder = List.of('2', '3', '4', '5', '6', '7', '8');

        Tree tree = satellite.treeFromTraversals(preorder, inorder);

        assertThat(tree.preorder()).containsExactlyElementsOf(preorder);
        assertThat(tree.inorder()).containsExactlyElementsOf(inorder);
        assertThat(tree.postorder()).containsExactly('2', '4', '3', '6', '8', '7', '5');
    }

    @Test
    public void rejectTraversalsOfDifferentLengths() {
        List<Character> preorder = List.of('a', 'b');
        List<Character> inorder = List.of('b', 'a', 'r');

        IllegalArgumentException expected =
            assertThrows(
                IllegalArgumentException.class,
                () -> satellite.treeFromTraversals(preorder, inorder));
        assertThat(expected)
            .hasMessage("traversals must have the same length");
    }

    @Test
    public void rejectInconsistentTraversalsOfSameLength() {
        List<Character> preorder = List.of('x', 'y', 'z');
        List<Character> inorder = List.of('a', 'b', 'c');

        IllegalArgumentException expected =
            assertThrows(
                IllegalArgumentException.class,
                () -> satellite.treeFromTraversals(preorder, inorder));
        assertThat(expected)
            .hasMessage("traversals must have the same elements");
    }

    @Test
    public void rejectTraversalsWithRepeatedItems() {
        List<Character> preorder = List.of('a', 'b', 'a');
        List<Character> inorder = List.of('b', 'a', 'a');

        IllegalArgumentException expected =
            assertThrows(
                IllegalArgumentException.class,
                () -> satellite.treeFromTraversals(preorder, inorder));
        assertThat(expected)
            .hasMessage("traversals must contain unique items");
    }
}
