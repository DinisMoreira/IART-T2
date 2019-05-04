package elements;

import java.util.List;
import java.util.ArrayList;

public class Problem{
    
    private int numEvents;
    private int numRooms;
    private int numFeatures;
    private int numStudents;
    private List<List<Integer>> roomSizes;
    private List<List<Boolean>> studentEvents;
    private List<List<Boolean>> roomFeatures;

    //Constructor

    public Problem(int numEvents, int numRooms, int numFeatures, int numStudents, List<List<Integer>> roomSizes, List<List<Boolean>> studentEvents, List<List<Boolean>> roomFeatures) {
        this.numEvents = numEvents;
        this.numRooms = numRooms;
        this.numFeatures = numFeatures;
        this.numStudents = numStudents;
        this.roomSizes = roomSizes;
        this.studentEvents = studentEvents;
        this.roomFeatures = roomFeatures;
    }
    
    
    //Getters & Setters
    public int getNumEvents() {
        return this.numEvents;
    }

    public void setNumEvents(int numEvents) {
        this.numEvents = numEvents;
    }

    public int getNumRooms() {
        return this.numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public int getNumFeatures() {
        return this.numFeatures;
    }

    public void setNumFeatures(int numFeatures) {
        this.numFeatures = numFeatures;
    }

    public int getNumStudents() {
        return this.numStudents;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    public List<List<Integer>> getRoomSizes() {
        return this.roomSizes;
    }

    public void setRoomSizes(List<List<Integer>> roomSizes) {
        this.roomSizes = roomSizes;
    }

    public List<List<Boolean>> getStudentEvents() {
        return this.studentEvents;
    }

    public void setStudentEvents(List<List<Boolean>> studentEvents) {
        this.studentEvents = studentEvents;
    }

    public List<List<Boolean>> getRoomFeatures() {
        return this.roomFeatures;
    }

    public void setRoomFeatures(List<List<Boolean>> roomFeatures) {
        this.roomFeatures = roomFeatures;
    }


    //Functions
    
    
}