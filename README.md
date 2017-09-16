# Pre-lecture assignment: BSTs with Height

Change the class `BSTWithHeight` to add the following two methods.

    ~~~~
    int height();
    boolean isAVL();
    ~~~~
    
The `height` method should return the height of the tree (that is, that
length of the longest path from the root to a leaf node).
The `height` method must have O(1) time complexity.
The `isAVL` method should return true or false depending on whether
the tree is an AVL tree. 
The `isAVL` method should have O(n) time complexity.