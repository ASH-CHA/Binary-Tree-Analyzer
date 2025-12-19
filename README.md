# Binary Tree Analyzer

## Description

The **Binary Tree Analyzer** is a Java console application that constructs and analyzes binary trees based on user input. The program reads a binary tree described in **preorder notation**, validates its syntax, prints the tree in an indented format, and determines whether the tree is a **Binary Search Tree (BST)** and whether it is **balanced**.

If the tree is a valid BST but not balanced, the program automatically constructs a **balanced version** of the tree and compares the heights of the original and balanced trees.

---

## How It Works

1. The user enters a binary tree using **preorder notation**:

   * Integers represent node values
   * `*` represents a null child
   * Parentheses `(` and `)` define left and right subtrees

2. The program tokenizes and parses the input string to construct a binary tree.

   * Syntax errors are detected and reported using a custom exception.

3. Once the tree is constructed:

   * The tree is printed in an **indented hierarchical format**
   * The program checks whether the tree is:

     * A **Binary Search Tree**
     * **Balanced**

4. If the tree is a BST but not balanced:

   * A **balanced BST** is created using the tree’s values
   * The balanced tree is printed
   * Heights of the original and balanced trees are displayed

5. The user may enter additional trees until they choose to exit.

---

## Program Structure

* **`Main.java`**

  * Handles user input and program flow
  * Repeatedly prompts for tree input
  * Displays tree output and properties

* **`BinaryTree.java`**

  * Constructs binary trees from preorder strings or value lists
  * Validates BST properties
  * Checks tree balance
  * Prints trees in indented format
  * Builds a balanced BST when needed

* **`TreeSyntaxException.java`**

  * Custom exception for reporting syntax errors in tree input

---

## Input Format

* **Integers**: Node values
* **`*`**: Null child
* **Parentheses**: Subtrees

### Example Input

```
(53 (28 (11 * *) (41 * *)) (83 (67 * *) *))
```

---

## Example Output

```
53
  28
    11
    41
  83
    67
It is a balanced binary search tree
```

For an unbalanced BST:

```
It is a binary search tree but it is not balanced
Original tree has height 4
Balanced tree has height 3
```

---

## How to Run

1. Ensure all `.java` files are in the same directory.
2. Compile the program:

   ```bash
   javac Main.java BinaryTree.java TreeSyntaxException.java
   ```
3. Run the program:

   ```bash
   java Main
   ```
4. Enter a binary tree when prompted.
5. Choose **Y** to enter another tree or **N** to exit.
