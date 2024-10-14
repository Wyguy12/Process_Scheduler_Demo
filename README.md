Process Scheduler with AVL Tree


Project Overview
This project implements a Process Scheduler that utilizes an AVL Tree data structure to manage processes based on their priority, runtime, and other attributes. 
The project is designed to store, manage, and execute processes in an efficient and balanced manner, making use of the self-balancing properties of an AVL tree.

AVLTree- is a custom implementation of an AVL tree that stores ProcessInfo objects. 
The AVL tree maintains balance by performing rotations after every insertion.

ProcessInfo- is a class that stores information about a process.

Main- is the entry point of the project it will:
Reading process information from the processListMaster.txt file.
Inserting processes into the AVL tree.
Simulating the execution of processes based on their priority.
Displaying the tree structure and the list of completed processes.


processListMaster.txt-
This file contains a list of processes with the following format:
processName | processId | processPriority | processRemainingRuntime