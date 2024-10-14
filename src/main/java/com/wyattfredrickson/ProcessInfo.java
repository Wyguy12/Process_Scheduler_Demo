package com.wyattfredrickson;

/**
 * The ProcessInfo class represents information about different process run times and priorities and what the process is. 
 */
public class ProcessInfo implements Comparable<ProcessInfo> {
    private String processName;
    private int processId;
    private int processPriority;
    private int processRemainingRuntime;
    private long processStartTime;
    private long processEndTime;
    private long processElapsedTime;

    /**
     * Constructor for the ProcessInfo class
     * @param name the name of the process
     * @param id the id of the process
     * @param priority the priority of the process
     * @param remainingRuntime the remaining runtime of the process
     */
    public ProcessInfo(String name, int id, int priority, int remainingRuntime) {
        this.processName = name;
        this.processId = id;
        this.processPriority = priority;
        this.processRemainingRuntime = remainingRuntime;
        this.processStartTime = 0;
        this.processEndTime = 0;
        this.processElapsedTime = 0;
    }

    // Getter methods
    public String getProcessName() {
        return processName;
    }
    public int getProcessId() {
        return processId;
    }
    public int getProcessPriority() {
        return processPriority;
    }
    public int getProcessRemainingRuntime() {
        return processRemainingRuntime;
    }
    public long getProcessStartTime() {
        return processStartTime;
    }
    public long getProcessEndTime() {
        return processEndTime;
    }
    public long getProcessElapsedTime() {
        return processElapsedTime;
    }

    // Setter methods
    public void setProcessStartTime( long startTime) {
        this.processStartTime = startTime; 
    }
    public void setProcessEndTime(long endTime) {
        this.processEndTime = endTime;
    }
    public void setProcessElapsedTime(long elapsedTime) {
        this.processElapsedTime = elapsedTime;
    }

    /**
     * Method to execute the process with a given time slice
     * @param timeSlice the time slice for the process
     * @return true if the process is completed, false otherwise
     */
    public boolean executeProcess(int timeSlice) {
        if (processStartTime == 0) { // If the process start time is 0
            processStartTime = System.currentTimeMillis(); // Set the process start time to the current time in milliseconds
        }

        processRemainingRuntime -= timeSlice; // Assign the remaining runtime to the remaining runtime minus the time slice
        if (processRemainingRuntime <= 0) { // If the remaining runtime is less than or equal to 0
            endProcess(); // End the process by invoking the endProcess method
            return true; 
        }
        return false;
    }

    /**
     * Method to end the process
     * 
     */
    private void endProcess() {
        this.processEndTime = System.currentTimeMillis(); // Set the process end time to the current time in milliseconds
        this.processElapsedTime = processEndTime - processStartTime; // Calculate the process elapsed time
    }

    /**
     * Method to compare the process priority
     */
    @Override
    public int compareTo(ProcessInfo other) {
        return Integer.compare(this.processPriority, other.processPriority);
    }

    /**
     * Method to display the process information
     * @return the process information
     */
    @Override
    public String toString() {
        return "Process Name: " + processName + " Process Id: " + processId + " Process Priority: " + processPriority + " Process Remanining Runtime: " + processRemainingRuntime;
    }

    /**
     * Method to display the completed process information
     * @return the completed process information
     */
    public String displayCompletedInfo() {
        return "Process Name: " + processName + " Process Priority: " + processPriority + " Completion Time: " + processElapsedTime;
    }
}