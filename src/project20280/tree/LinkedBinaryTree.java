package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;


/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }



    //q4
    public ArrayList<ArrayList<E>> rootToLeafPaths() {
        ArrayList<ArrayList<E>> paths = new ArrayList<>();
        if (root != null) {
            rootToLeafPaths(root, new ArrayList<>(), paths);
        }
        return paths;
    }

    private void rootToLeafPaths(Node<E> node, ArrayList<E> currentPath, ArrayList<ArrayList<E>> paths) {
        if (node == null) return;
        currentPath.add(node.getElement());
        if (node.getLeft() == null && node.getRight() == null) {
            paths.add(new ArrayList<>(currentPath));
        } else {
            rootToLeafPaths(node.getLeft(), currentPath, paths);
            rootToLeafPaths(node.getRight(), currentPath, paths);
        }
        currentPath.remove(currentPath.size() - 1);
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String [] args) {
        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());

        Integer [] inorder= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
                18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        Integer [] preorder= {18, 2, 1, 14, 13, 12, 4, 3, 9, 6, 5, 8, 7, 10, 11, 15, 16,
                17, 28, 23, 19, 22, 20, 21, 24, 27, 26, 25, 29, 30};

        LinkedBinaryTree <Integer > bt1 = new LinkedBinaryTree <>();
        bt1.construct(inorder , preorder);
        System.out.println(bt1.toBinaryTreeString());


        Integer [] inorder1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                19, 20, 21, 22};
        Integer [] preorder1 = {6, 5, 3, 2, 1, 0, 4, 17, 10, 9, 8, 7, 16, 14, 13, 12, 11, 15, 21,
                20, 19, 18, 22};

        LinkedBinaryTree <Integer > bt2 = new LinkedBinaryTree <>();
        bt2.construct(inorder1 , preorder1);
        System.out.println(bt2.diameter());

        System.out.println(bt2.toBinaryTreeString());
        bt2.printLeafNodes(bt2.root);
    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }




    public void printLeafNodes(Position<E> root) {
        System.out.println("Leaf nodes:");
        printLeafNodesHelper(root);
    }

    public void printLeafNodesHelper(Position<E> root){
        if (root == null) return;
        if (left(root) == null && right(root) == null) {
            System.out.println(root.getElement());
        }
        printLeafNodesHelper(left(root));
        printLeafNodesHelper(right(root));
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }



    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (root != null) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }


    public void insert(E e) {
        if (root == null) {
            root = createNode(e, null, null, null);
            size = 1;
        } else {
            addRecursive(root, e);
        }
    }


    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        // TODO
        Comparable<E> cmp = (Comparable<E>) e;

        if (cmp.compareTo(p.getElement()) < 0) {
            if (p.getLeft() == null) {
                p.setLeft(createNode(e, p, null, null));
                size++;
            } else {
                addRecursive(p.getLeft(), e);
            }
        } else if (cmp.compareTo(p.getElement()) > 0) {
            if (p.getRight() == null) {
                p.setRight(createNode(e, p, null, null));
                size++;
            } else {
                addRecursive(p.getRight(), e);
            }
        }

        return p;
    }

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        if (p == null) throw new IllegalArgumentException("p is not a valid position for this tree");
        Node<E> parent = validate(p);
        if (parent.getLeft() != null) throw new IllegalArgumentException("p already has a left child");
        parent.setLeft(createNode(e, parent, null, null));
        size++;
        return parent.getLeft();
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        if (p == null) throw new IllegalArgumentException("p is not a valid position for this tree");
        Node<E> parent = validate(p);
        if (parent.getRight() != null) throw new IllegalArgumentException("p already has a right child");
        parent.setRight(createNode(e, parent, null, null));
        size++;
        return parent.getRight();
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        if (p == null) throw new IllegalArgumentException("p is not a valid position for this tree");
        Node<E> node = validate(p);
        E old = node.getElement();
        node.setElement(e);
        return old;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2)
            throws IllegalArgumentException {

        Node<E> node = validate(p);
        if (node.getLeft() != null || node.getRight() != null)
            throw new IllegalArgumentException("p must be a leaf");

        if (t1 != null && t1.root != null) {
            node.setLeft(t1.root);
            t1.root.setParent(node);
            size += t1.size;
            t1.root = null;
            t1.size = 0;
        }

        if (t2 != null && t2.root != null) {
            node.setRight(t2.root);
            t2.root.setParent(node);
            size += t2.size;
            t2.root = null;
            t2.size = 0;
        }
    }


    // q3
    public void construct(E[] inorder, E[] preorder) {
        int size = 0;
        root = constructHelper(inorder, preorder, null);
    }

    private Node<E> constructHelper(E[] inorder, E[] preorder, Node<E> parent) {
        if (inorder.length == 0 || preorder.length == 0) return null;
        E rootVal = preorder[0];
        Node<E> node = createNode(rootVal, parent, null, null);
        size++;

        int rootIndex = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i].equals(rootVal)) {
                rootIndex = i;
                break;
            }
        }

        if (rootIndex == -1) throw new IllegalArgumentException("Invalid input: preorder and inorder do not match");

        node.setLeft(constructHelper(
                java.util.Arrays.copyOfRange(inorder, 0, rootIndex),
                java.util.Arrays.copyOfRange(preorder, 1, rootIndex + 1),
                node
        ));
        node.setRight(constructHelper(
                java.util.Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length),
                java.util.Arrays.copyOfRange(preorder, rootIndex + 1, preorder.length),
                node
        ));

        return node;


    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);

        Node<E> left = node.getLeft();
        Node<E> right = node.getRight();
        if (left != null && right != null)
            throw new IllegalArgumentException("p has two children");

        Node<E> child = (left != null) ? left : right;

        if (child != null) child.setParent(node.getParent());

        if (node == root) {
            root = child;
        } else {
            Node<E> parent = node.getParent();
            if (parent.getLeft() == node) parent.setLeft(child);
            else parent.setRight(child);
        }

        size--;
        E removed = node.getElement();

        // mark as defunct (matches your validate() convention)
        node.setParent(node);
        node.setLeft(null);
        node.setRight(null);

        return removed;
    }


    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        size = 0;
        root = createLevelOrderHelper(l, null, 0);
    }


    private Node<E> createLevelOrderHelper(ArrayList<E> l, Node<E> parent, int i) {
        if (i >= l.size()) return null;
        E val = l.get(i);
        if (val == null) return null;

        Node<E> node = createNode(val, parent, null, null);
        size++;

        node.setLeft(createLevelOrderHelper(l, node, 2 * i + 1));
        node.setRight(createLevelOrderHelper(l, node, 2 * i + 2));

        return node;
    }


    private Node<E> createLevelOrderHelper(E[] arr, Node<E> parent, int i) {
        if (arr == null || i >= arr.length) return null;
        if (arr[i] == null) return null;

        Node<E> node = createNode(arr[i], parent, null, null);
        size++;

        node.setLeft(createLevelOrderHelper(arr, node, 2 * i + 1));
        node.setRight(createLevelOrderHelper(arr, node, 2 * i + 2));

        return node;
    }

    public void createLevelOrder(E[] arr) {
        size = 0;
        root = createLevelOrderHelper(arr, null, 0);
    }


    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    // diameter code q5
    public int diameter() {
        return diameter(root);
    }

    private int diameter(Position<E> r) {
        if (r == null) return 0;

        int leftD = diameter(left(r));
        int rightD = diameter(right(r));

        int throughRoot = 1 + height(left(r)) + height(right(r)); // <-- heights!

        return Math.max(throughRoot, Math.max(leftD, rightD));
    }

    private int height(Position<E> r) {
        if (r == null) return 0;
        return 1 + Math.max(height(left(r)), height(right(r)));
    }


    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append('\n');
            } else {
                sb.append(element);
            }
            return sb.toString();
        }

    }}
