import static java.lang.Integer.max;
import static java.lang.Math.abs;

class BSTNode<T extends Comparable<T>> {
    T key;
    BSTNode<T> left, right;
    int height;

    BSTNode(T k, BSTNode<T> l, BSTNode<T> r) {
        key = k;
        left = l;
        right = r;
        height = 0;
    }

    boolean insert(T k) {
        boolean inserted;
        if (k.compareTo(key) < 0) {
            if (left == null) {
                left = new BSTNode<T>(k, null, null);
                inserted = true;
            } else
                inserted = left.insert(k);
        } else if (k.compareTo(key) > 0) {
            if (right == null) {
                right = new BSTNode<>(k, null, null);
                inserted = true;
            } else
                inserted = right.insert(k);
        } else
            inserted = false;
        return inserted;
    }

}

public class BSTWithHeight<T extends Comparable<T>> {
    BSTNode<T> root;

    BSTWithHeight() {
        root = null;
    }

    boolean find(T k) {
        return find_helper(root, k) != null;
    }

    void insert(T key) {
        if (root == null) {
            root = new BSTNode<>(key, null, null);
        } else
            root.insert(key);
    }

    void remove(T key) {
        root = remove_helper(root, key);
    }

    // student implements
    int height() {
        throw new UnsupportedOperationException();
    }

    // student implements
    boolean isAVL() {
        throw new UnsupportedOperationException();
    }

    public void print_tree() { System.out.print(tree_to_string(root)); }

    private String tree_to_string(BSTNode<T> n) {
        if (n != null) {
            return String.format("(%s %s(%d) %s)",
                    tree_to_string(n.left),
                    n.key.toString(),
                    n.height,
                    tree_to_string(n.right));
        }
        return "";
    }

    // Helper Functions

    private BSTNode<T> find_helper(BSTNode<T> n, T key) {
        if (n == null) {
            return null;
        } else if (key.compareTo(n.key) < 0) {
            return find_helper(n.left, key);
        } else if (key.compareTo(n.key) > 0) {
            return find_helper(n.right, key);
        } else {
            return n;
        }
    }

    private BSTNode<T> delete_min(BSTNode<T> n) {
        if (n.left == null) {
            return n.right;
        } else {
            n.left = delete_min(n.left);
            return n;
        }
    }

    private BSTNode<T> get_min(BSTNode<T> n) {
        if (n.left == null) return n;
        else return get_min(n.left);
    }

    private BSTNode<T> remove_helper(BSTNode<T> n, T key) {
        if (n == null) {
            return null;
        } else if (key.compareTo(n.key) < 0) { // remove in left subtree
            n.left = remove_helper(n.left, key);
            return n;
        } else if (key.compareTo(n.key) > 0) { // remove in right subtree
            n.right = remove_helper(n.right, key);
            return n;
        } else { // remove this node
            if (n.left == null) {
                return n.right;
            } else if (n.right == null) {
                return n.left;
            } else { // two children, replace this with min of right subtree
                BSTNode<T> min = get_min(n.right);
                n.key = min.key;
                n.right = delete_min(n.right);
                return n;
            }
        }
    }

}
