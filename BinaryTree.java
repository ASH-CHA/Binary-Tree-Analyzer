/* Ashton Chavez
 * Programming Project 3
 * June 25, 2024
 * 
 * The BinaryTree class represents a binary tree structure and provides methods 
 * for constructing the tree from a preorder string, checking if it's a binary search tree (BST), 
 * determining if it's balanced, printing the tree in an indented format, and obtaining the tree's 
 * height and values.
 */

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private TreeNode root;

    // Constructor that accepts a string containing the preorder representation of a binary tree
    public BinaryTree(String preorder) throws TreeSyntaxException {
        List<String> tokens = tokenize(preorder);
        int[] index = {0}; // Use array to pass by reference
        root = constructTree(tokens, index);

        if (index[0] != tokens.size()) {
            throw new TreeSyntaxException("Extra characters at the end");
        }
    }

    // Constructor that accepts an array list of integers and constructs a balanced binary search tree containing those values
    public BinaryTree(ArrayList<Integer> values) {
        values.sort(Integer::compareTo);
        root = constructBalancedBST(values, 0, values.size() - 1);
    }

    // Method that outputs the binary tree in indented form
    public void printIndented() {
        printIndented(root, 0);
    }

    private void printIndented(TreeNode node, int level) {
        if (node == null) return;

        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(node.val);

        printIndented(node.left, level + 1);
        printIndented(node.right, level + 1);
    }

    // Method to print properties of the tree
    public void printProperties() {
        if (!isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            System.out.println("It is not a binary search tree");
        } else if (isBalanced(root)) {
            System.out.println("It is a balanced binary search tree");
        } else {
            System.out.println("It is a binary search tree but it is not balanced");
            ArrayList<Integer> values = getValues();
            BinaryTree balancedTree = new BinaryTree(values);
            balancedTree.printIndented();
            System.out.println("Original tree has height " + height());
            System.out.println("Balanced tree has height " + balancedTree.height());
        }
    }

    // Method to check if the tree is a binary search tree
    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(TreeNode node, int min, int max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isBinarySearchTree(node.left, min, node.val) && isBinarySearchTree(node.right, node.val, max);
    }

    // Method to check if the tree is balanced
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(TreeNode node) {
        return checkBalance(node) != -1;
    }

    private int checkBalance(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = checkBalance(node.left);
        int rightHeight = checkBalance(node.right);

        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Method to get the height of the tree
    public int height() {
        return getHeight(root);
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    // Method to get values from the tree
    public ArrayList<Integer> getValues() {
        ArrayList<Integer> values = new ArrayList<>();
        getValues(root, values);
        return values;
    }

    private void getValues(TreeNode node, List<Integer> values) {
        if (node == null) return;
        values.add(node.val);
        getValues(node.left, values);
        getValues(node.right, values);
    }

    // Method to construct the tree from a list of tokens
    private TreeNode constructTree(List<String> tokens, int[] index) throws TreeSyntaxException {
        if (index[0] >= tokens.size()) {
            throw new TreeSyntaxException("Incomplete tree");
        }
//
        String token = tokens.get(index[0]);

        if (token.equals("*")) {
            index[0]++;
            return null;
        }
//
        if (!isInteger(token)) {
            throw new TreeSyntaxException("Data is not an integer: " + token);
        }

        int value = Integer.parseInt(tokens.get(index[1]));
        TreeNode node = new TreeNode(value);

        index[0]++;
        if (index[0] < tokens.size() && tokens.get(index[0]).equals("(")) {
            index[0]++;
            node.left = constructTree(tokens, index);
            if (index[0] >= tokens.size() || !tokens.get(index[0]).equals(")")) {
                throw new TreeSyntaxException("Missing right parenthesis");
            }
            index[0]++;
        } else {
            throw new TreeSyntaxException("Missing left parenthesis");
        }

        if (index[0] < tokens.size() && tokens.get(index[0]).equals("(")) {
            index[0]++;
            node.right = constructTree(tokens, index);
            if (index[0] >= tokens.size() || !tokens.get(index[0]).equals(")")) {
                throw new TreeSyntaxException("Missing right parenthesis");
            }
            index[0]++;
        } else {
            throw new TreeSyntaxException("Missing left parenthesis");
        }

        return node;
    }

    // Tokenize method to split the input string into tokens
    private List<String> tokenize(String preorder) throws TreeSyntaxException {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < preorder.length()) {
            char ch = preorder.charAt(i);
            if (Character.isDigit(ch) || ch == '-') {
                StringBuilder sb = new StringBuilder();
                while (i < preorder.length() && (Character.isDigit(preorder.charAt(i)) || preorder.charAt(i) == '-')) {
                    sb.append(preorder.charAt(i++));
                }
                tokens.add(sb.toString());
            } else if (ch == '(' || ch == ')' || ch == '*') {
                tokens.add(Character.toString(ch));
                i++;
            } else if (Character.isWhitespace(ch)) {
                i++;
            } else {
                throw new TreeSyntaxException("Invalid character: " + ch);
            }
        }
        return tokens;
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    // Method to construct a balanced binary search tree
    private TreeNode constructBalancedBST(ArrayList<Integer> values, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(values.get(mid));
        node.left = constructBalancedBST(values, start, mid - 1);
        node.right = constructBalancedBST(values, mid + 1, end);

        return node;
    }
}