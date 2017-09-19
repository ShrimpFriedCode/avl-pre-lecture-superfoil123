import static java.lang.Integer.max;
import static java.lang.Math.abs;

class BSTNode<T extends Comparable<T>> {
    T key;
    BSTNode<T> left, right;
    int height = 0;

    BSTNode(T k, BSTNode<T> l, BSTNode<T> r) {
        key = k;
        left = l;
        right = r;
        setHeight(); //set height of node
    }

    boolean insert(T k) {
        boolean inserted;
        if (k.compareTo(key) < 0) {
            if (left == null) {
                left = new BSTNode<T>(k, null, null);
                inserted = true;
            } else {
                inserted = left.insert(k);
            }
        } else if (k.compareTo(key) > 0) {
            if (right == null) {
                right = new BSTNode<>(k, null, null);
                inserted = true;
            } else {
                inserted = right.insert(k);
            }
        } else
            inserted = false;
        setHeight(); //renew heights of node after insert
        return inserted;
    }

    int setHeightH(BSTNode<T> n){

        if (n != null){ //if not null, add 1
            if(setHeightH(n.left) > setHeightH(n.right)){ //if  get max of either the left or right, to find the true height
                return setHeightH(n.left) +1;
            }
            else{
                return setHeightH(n.right) + 1;
            }
        }
        else { //else it DNE, so 0
            return 0;
        }

    }

    void setHeight(){
        height = setHeightH(this); //get height of this node
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
        } else {
            root.insert(key);
        }
    }

    void remove(T key) {
        root = remove_helper(root, key);
    }

    // student implements
    int height() {
        return root.height; //return root's height
    }

    int heightHelper(BSTNode<T> n){ //for use by isAVL. Not sure if 100% necessary, but helps me visualize
        if (n != null) {//if not null, it has a height
            return n.setHeightH(n);
        }
        else
            return 0;//else, it has no height
    }

    // student implements
    boolean isAVLH(BSTNode<T> n) {//isAVL helper
        if(n == null){
            return true;//if null, must be balanced
        }
        else if(((heightHelper(n.left)) - (heightHelper(n.right))) <= 1 && ((heightHelper(n.left)) - (heightHelper(n.right))) >= -1){
            //By def of AVL, height differences of branches must be between -1 to 1
            if(isAVLH(n.left) && isAVLH(n.right)){
                //verify that ALL branches in tree are balanced
                return true;
            }
        }
        return false; //else, must be false
    }

    boolean isAVL(){
        return isAVLH(root); //determine if root node and children are AVL
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
