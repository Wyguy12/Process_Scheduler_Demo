package com.wyattfredrickson;

import java.util.List;


/**
 * The AVLTree class represents an AVL tree data structure that stores ProcessInfo objects
 */
public class AVLTree {
    private class Node {
        ProcessInfo data;
        Node left, right;
        int height;

        // Constructor for the Node class
        Node(ProcessInfo data) {
            this.data = data;
            this.height = 1;
        }
    }
    private Node root; // The root of the AVL tree

    /**
     * Get the height of the node
     */
    public void insert(ProcessInfo process) {
        root = insert(root, process);
    }

    /**
     * Method for inserting a process into the AVL tree
     * @param node the node to insert the process into
     * @param process the process to insert
     * @return the node with the process inserted
     */
    private Node insert(Node node, ProcessInfo process) {
        if (node == null) {
            return new Node(process); // If the node is null, create a new node with the process data
        }

        if (process.compareTo(node.data) < 0) { // If the process is less than the node data
            node.left = insert(node.left, process); // then insert the process into the left subtree
        } else {
            node.right = insert(node.right, process); // else insert the process into the right subtree
        }
        node.height = 1 + Math.max(height(node.left), height(node.right)); // Update the height of the node by taking the maximum height of the left and right subtrees
        return balance(node); // Return the balanced node
    }

    /**
     * Get the height of the node
     * @param node the node to get the height of
     * @return the height of the node
     */
    private int height(Node node) {
        return node == null ? 0 : node.height; // If the node is null, return 0, otherwise return the height of the node
    }

    /**
     * Method to balance the node in the AVL tree either by rotating left or right
     * @param node the node to balance
     * @return the balanced node
     */
    private Node balance(Node node) {
        int balanceFactor = height(node.left) - height(node.right); 
        // Left Case
        if (balanceFactor > 1) {
            if (height(node.left.left) >= height(node.left.right)) {
                node = rotateRight(node);
            } else {
                node.left = rotateLeft(node.left); // Rotate left
                node = rotateRight(node); // Rotate right
            }
        }
        // Right Case
        if (balanceFactor < -1) {
            if (height(node.right.right) >= height(node.right.left)) {
                node = rotateLeft(node);
            } else {
                node.right = rotateRight(node.right); // Rotate right
                node = rotateLeft(node);  // Rotate left
            }
        }
        return node; // Return the balanced node 
    }

    /**
     * Methood to rotate the node right
     * @param y the node to rotate right
     * @return the node rotated right
     */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    /**
     * Method to rotate the node left
     * @param x the node to rotate left
     * @return the node rotated left
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    /**
     * Method to print the node root 
     */
    public void printTree() {
        printTree(root, 0); 
    }
 
    /**
     * Method to print the AVL tree, either left or right subtree
     * @param node the node to print
     * @param level the level of the tree
     */
    private void printTree(Node node, int level) {
        if (node != null) { // If the node is not null
            printTree(node.right, level + 1);  // Print the right subtree
            System.out.println("Level " + level + " > Process Name: " + node.data.getProcessName() + " Process Id: " + node.data.getProcessId() + " Process Priority: " + node.data.getProcessPriority() + " Process Remaining Runtime: " + node.data.getProcessRemainingRuntime());
            printTree(node.left, level + 1); // Print the left subtree
        }
    }

    /**
     * Method to traverse the AVL tree in order
     * @param processList the list of processes
     */
    public void inOrderTraversal(List<ProcessInfo> processList) {
        inOrderTraversal(root, processList); // Invoke the inOrderTraversal method to traverse the AVL trees root node
    }

    /**
     * Method to traverse the AVL tree in order
     * @param node the node to traverse
     * @param processList the list of processes
     */
    private void inOrderTraversal(Node node, List<ProcessInfo> processList) {
        if (node != null) {
            inOrderTraversal(node.left, processList);
            processList.add(node.data);
            inOrderTraversal(node.right, processList); 
        }
    }












}