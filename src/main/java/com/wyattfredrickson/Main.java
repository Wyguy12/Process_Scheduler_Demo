package com.wyattfredrickson;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


/**
 * The test class 
 */
public class Main {
    public static void main(String[] args) {

        // Display the programmers information
        System.out.println("Wyatt Frdrickson- fredricw@csp.edu");
        System.out.println("I certify that this is my own work");

        AVLTree processTree = new AVLTree(); // Create a new AVL tree
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("src/resources/processListMaster.txt"))) { 
            String line;
            while ((line = bufferedReader.readLine()) != null) { // While there are lines to read in the file
                String[] parts = line.split("\\|");
                String name = parts[0];
                int id = Integer.parseInt(parts[1]);
                int priority = Integer.parseInt(parts[2]);
                int remainingRuntime = Integer.parseInt(parts[3]);
                ProcessInfo process = new ProcessInfo(name, id, priority, remainingRuntime); // Create a new ProcessInfo object
                System.out.println("Adding Process Name: " + name + " Process ID: " + id + " Process Priority: " + priority + " Process Remaining Runtime: " + remainingRuntime);
                processTree.insert(process); // Insert the process into the AVL tree
            }
        } catch (Exception e) { // Catch any exceptions that occur
            e.printStackTrace(); // Print the stack trace of the exception
        }

        System.out.println(); // Print a blank line for formatting
        processTree.printTree(); // Invoke the printTree method to display the AVL tree


        List<ProcessInfo> processList = new ArrayList<>(); // Create a new list of ProcessInfo objects
        processTree.inOrderTraversal(processList); // Invoke the inOrderTraversal method to traverse the AVL tree and collect the processes

        List<ProcessInfo> completedProcesses = new ArrayList<>(); // Create a new list of ProcessInfo objects to store completed processes
        int totalTimeElapsed = 0; // Initialize the total time elapsed to 0

        while (!processList.isEmpty()) {
            List<ProcessInfo> remainingProcesses = new ArrayList<>(); // Create a new list of ProcessInfo objects to store remaining processes
            for (ProcessInfo process : processList) {
                int timeSlice = getTimeSlice(process.getProcessPriority()); // Get the time slice based on the process priority

                boolean isCompleted = process. executeProcess(timeSlice); // Execute the process with the time slice and check if it is completed
                totalTimeElapsed += timeSlice; // Increment the total time elapsed by the time slice

                if (!isCompleted) { // If the process is not completed
                    remainingProcesses.add(process); // Then add the process to the list of remaining processes
                } else {
                    process.setProcessElapsedTime(totalTimeElapsed); // Set the process elapsed time to the total time elapsed
                    System.out.println("Process has completed: " + process.getProcessName() + " - " + process.getProcessId()); // Print the completed process
                    completedProcesses.add(process); // Add the completed process to the list of completed processes
                }
            }
            processList = remainingProcesses; // Update the process list to the list of remaining processes
        }
        System.out.println("\nResults >"); // Print the results 
        for (ProcessInfo process : completedProcesses) {
            System.out.println(process.displayCompletedInfo());
        }
        System.out.println("\nProcess finished with exit code 0"); // Print the exit code
    }

    /**
     * Method for getting the time slice based on the priority
     * @param priority The priority of the process
     * @return The time slice based on the priority
     */
    private static int getTimeSlice(int priority) {
        return 10 - priority; // Return the time slice based on the priority
    }
}