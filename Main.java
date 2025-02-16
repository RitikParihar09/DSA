import java.util.*;

class Node {
    int val;
    Node left, right;

    Node(int val) {
        this.val = val;
        left = right = null;
    }
}

class BinaryTree {
    public Node builtTree(int[] nodes) {
        return builtTreeHelper(nodes, new int[]{0});
    }

    private Node builtTreeHelper(int[] nodes, int[] index) {
        int ind = index[0];
        if (ind >= nodes.length || nodes[ind] == -1) {
            index[0]++;
            return null;
        }
        Node newNode = new Node(nodes[ind]);
        index[0]++;
        newNode.left = builtTreeHelper(nodes, index);
        newNode.right = builtTreeHelper(nodes, index);
        return newNode;
    }
}

class Main {
    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, 8};
        BinaryTree tree = new BinaryTree();
        Node root = tree.builtTree(nodes);

        System.out.print("Pre-order Traversal:");
        preOrder(root);
        System.out.print("\nIn-order Traversal:");
        inOrder(root);
        System.out.print("\nPost-order Traversal:");
        postOrder(root);
        System.out.print("\nLevel-order Traversal:");
        levelOrder(root);
        System.out.println("\nNumber of nodes: " + count(root));
        System.out.println("Sum of nodes: " + sumOfNodes(root));
        System.out.println("Height of tree: " + Height(root));
        System.out.println("Diameter of tree: " + diameter(root));
        ArrayList<Integer> right=new ArrayList<>();
        rightView(root,right,0);
        System.out.println("Right View of tree: " + right);
        ArrayList<Integer> left=new ArrayList<>();
        leftView(root,left,0);
        System.out.println("Left View of tree: " + left);
    }

    // Pre-order Traversal
    static void preOrder(Node root) {
        if (root == null) return;
        System.out.print(root.val + " -> ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // In-order Traversal
    static void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " -> ");
        inOrder(root.right);
    }

    // Post-order Traversal
    static void postOrder(Node root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " -> ");
    }

    // Level-order Traversal
    static void levelOrder(Node root) {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node curr = q.remove();
            System.out.print(curr.val + " ");
            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
        }
    }

    // Count number of nodes
    static int count(Node root) {
        if (root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }

    // Sum of nodes
    static int sumOfNodes(Node root) {
        if (root == null) return 0;
        return root.val + sumOfNodes(root.left) + sumOfNodes(root.right);
    }

    // Height of tree
    static int Height(Node root) {
    if (root == null) return 0;
    return Math.max(Height(root.left), Height(root.right)) + 1;
    }
    
    static int diameter(Node root){
        if(root==null){
            return 0;
        }
        int dem1=diameter(root.left);
        int dem2=diameter(root.right);
        int dem3=Height(root.left)+Height(root.right)+1;
        return Math.max(dem1,Math.max(dem2,dem3));
    }
    static void rightView(Node root,ArrayList<Integer> list,int level){
        if(root==null){
            return; 
        }
        if(list.size()==level) list.add(root.val);
        rightView(root.right,list,level+1);
        rightView(root.left,list,level+1);
    }
    static void leftView(Node root,ArrayList<Integer> list,int level){
        if(root==null){
            return; 
        }
        if(list.size()==level) list.add(root.val);
        rightView(root.left,list,level+1);
        rightView(root.right,list,level+1);
    }
    
}