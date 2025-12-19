/* Ashton Chavez
 * Programming Project 3
 * June 25, 2024
 * 
 * The Main class handles user interaction, repeatedly prompting for binary tree input, 
 * validating the input, and displaying the results. It constructs the binary tree, checks 
 * its properties, and prints the necessary information as specified.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Continuously prompt for input until the user decides to stop
        while (true) {
            System.out.print("Enter a binary tree: ");
            String input = scanner.nextLine().trim();

            try {
                // Construct BinaryTree from input string
                BinaryTree tree = new BinaryTree(input);
                
                // Print indented representation of the tree
                tree.printIndented();
                
                // Print properties of the tree (BST check, balance check)
                tree.printProperties();

            } catch (TreeSyntaxException e) {
                // Catch and handle syntax errors in the input
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("More trees? Y or N: ");
            String response = scanner.nextLine().trim().toUpperCase();

            // Check if user wants to continue entering trees
            if (!response.equals("Y")) {
                break; // Exit loop if user inputs anything other than "Y"
            }
        }

        System.out.println("Program ended.");
        scanner.close();

        //Test Cases
        /*try {
            // Example 1: Balanced Binary Search Tree
            String input1 = "(53 (28 (11 * *) (41 * *)) (83 (67 * *) *))";
            BinaryTree tree1 = new BinaryTree(input1);
            tree1.printIndented();
            tree1.printProperties();

            // Example 2: Unbalanced Binary Search Tree
            String input2 = "(63 (51 (20 (13 * *) *) *) *)";
            BinaryTree tree2 = new BinaryTree(input2);
            tree2.printIndented();
            tree2.printProperties();

            // Example 3: Not a Binary Search Tree
            String input3 = "(13 (53 * *) (11 (59 * *) *))";
            BinaryTree tree3 = new BinaryTree(input3);
            tree3.printIndented();
            tree3.printProperties();
        } catch (TreeSyntaxException e) {
            System.out.println(e.getMessage());
        }*/
    }
}