package elements;

public class Problem{
    
    private int numEvents;
    private int numRooms;
    private int numFeatures;
    private int numStudents;
    private Vector<Vector<int>> roomSizes;
    private Vector<Vector<boolean>> studentEvents;
    private Vector<Vector<boolean>> roomFeatures;

    //Constructor

    public Problem(int numEvents, int numRooms, int numFeatures, int numStudents, Vector<Vector<int>> roomSizes, Vector<Vector<boolean>> studentEvents, Vector<Vector<boolean>> roomFeatures) {
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

    public Vector<Vector<int>> getRoomSizes() {
        return this.roomSizes;
    }

    public void setRoomSizes(Vector<Vector<int>> roomSizes) {
        this.roomSizes = roomSizes;
    }

    public Vector<Vector<boolean>> getStudentEvents() {
        return this.studentEvents;
    }

    public void setStudentEvents(Vector<Vector<boolean>> studentEvents) {
        this.studentEvents = studentEvents;
    }

    public Vector<Vector<boolean>> getRoomFeatures() {
        return this.roomFeatures;
    }

    public void setRoomFeatures(Vector<Vector<boolean>> roomFeatures) {
        this.roomFeatures = roomFeatures;
    }


    //Functions
    
    
}