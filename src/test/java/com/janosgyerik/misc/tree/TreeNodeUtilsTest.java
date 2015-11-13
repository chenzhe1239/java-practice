package com.janosgyerik.misc.tree;

import org.junit.Test;

import java.util.*;

import static com.janosgyerik.misc.tree.TreeNodeUtils.toList;
import static com.janosgyerik.misc.tree.TreeNodeUtils.toTree;
import static org.junit.Assert.*;

public class TreeNodeUtilsTest {

    private <T> TreeNode<T> tree(T value, TreeNode<T> left, TreeNode<T> right) {
        TreeNode<T> node = new TreeNode<>(value);
        node.left = left;
        node.right = right;
        return node;
    }

    private <T> TreeNode<T> tree(T value) {
        return tree(value, null, null);
    }

    private <T> TreeNode<T> tree(T value, TreeNode<T> left) {
        return tree(value, left, null);
    }

    private <T> TreeNode<T> tree(T value, T left) {
        return tree(value, tree(left));
    }

    private <T> TreeNode<T> tree(T value, T left, T right) {
        return tree(value, tree(left), tree(right));
    }

    @Test
    public void testToList() {
        assertEquals(Arrays.asList(1, 2), toList(tree(1, 2)));
        assertEquals(Arrays.asList(1, 2, 3), toList(tree(1, 2, 3)));
        assertEquals(Arrays.asList(1, null, 3), toList(tree(1, null, tree(3))));
        assertEquals(Arrays.asList(1, null, 3, null, 5), toList(tree(1, null, tree(3, null, tree(5)))));
        assertEquals(Arrays.asList(1, 3, null, null, 5), toList(tree(1, tree(3, null, tree(5)))));
    }

    @Test
    public void testToTree() {
        List<Integer> list = toList(tree(1, tree(3, null, tree(5))));
        assertEquals(list, toList(toTree(list)));
    }

    private TreeNode<Character> getWikiTree() {
        return tree('F', tree('B', tree('A'), tree('D', 'C', 'E')), tree('G', null, tree('I', 'H')));
    }

    @Test
    public void testPreOrder() {
        assertEquals("[F, B, A, D, C, E, G, I, H]",
                iterateToList(new PreOrderIterator<>(getWikiTree())).toString());
    }

    @Test
    public void testInOrder() {
        assertEquals("[A, B, C, D, E, F, G, H, I]",
                iterateToList(new InOrderIterator<>(getWikiTree())).toString());
    }

    @Test
    public void testPostOrder() {
        assertEquals("[A, C, E, D, B, H, I, G, F]",
                iterateToList(new PostOrderIterator<>(getWikiTree())).toString());
    }

    @Test
    public void testLevelOrder() {
        assertEquals("[F, B, G, A, D, I, C, E, H]",
                iterateToList(new LevelOrderIterator<>(getWikiTree())).toString());
    }

    private <T> List<T> iterateToList(Iterator<T> iterator) {
        List<T> list = new LinkedList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    private static class PreOrderIterator<T> implements Iterator<T> {
        private final Stack<TreeNode<T>> stack = new Stack<>();

        private PreOrderIterator(TreeNode<T> root) {
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            TreeNode<T> node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            return node.value;
        }
    }

    private static class InOrderIterator<T> implements Iterator<T> {
        private Stack<TreeNode<T>> stack = new Stack<>();

        private InOrderIterator(TreeNode<T> root) {
            moveToLeftMost(root);
        }

        private void moveToLeftMost(TreeNode<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            TreeNode<T> current = stack.pop();
            moveToLeftMost(current.right);
            return current.value;
        }
    }

    private static class PostOrderIterator<T> implements Iterator<T> {
        private Stack<TreeNode<T>> stack = new Stack<>();

        private PostOrderIterator(TreeNode<T> root) {
            moveToNextLeaf(root);
        }

        private void moveToNextLeaf(TreeNode<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.left != null ? node.left : node.right;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            TreeNode<T> current = stack.pop();
            if (!stack.isEmpty()) {
                TreeNode<T> parent = stack.peek();
                if (parent.right != current) {
                    moveToNextLeaf(parent.right);
                }
            }
            return current.value;
        }
    }

    private static class LevelOrderIterator<T> implements Iterator<T> {
        private final Queue<TreeNode<T>> queue = new LinkedList<>();

        private LevelOrderIterator(TreeNode<T> root) {
            queue.add(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            TreeNode<T> current = queue.poll();

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }

            return current.value;
        }
    }

}