package elements;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Problem {

    final private int numberOfDays = 5;
    final private int hoursPerDay = 9;
    final private int timeSlots = numberOfDays * hoursPerDay;

    private List<Room> rooms;
    private List<Student> students;
    private List<Event> events;

    private ArrayList<ArrayList<Boolean>> studentEvents;

    //Constructor
    public Problem() {
        this.rooms = new ArrayList<>();
        this.students = new ArrayList<>();
        this.events = new ArrayList<>();

        this.studentEvents = new ArrayList<ArrayList<Boolean>>();
    }

    // Getters and Setters
    public int getNumberOfDays() {
        return this.numberOfDays;
    }

    public int getHoursPerDay() {
        return this.hoursPerDay;
    }

    public int getTimeSlots() {
        return this.timeSlots;
    }

    public List<Room> getRooms() {
        return this.rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public ArrayList<ArrayList<Boolean>> getStudentEvents() {
        return this.studentEvents;
    }

    public void setStudentEvents(ArrayList<ArrayList<Boolean>> studentEvents) {
        this.studentEvents = studentEvents;
    }


    // Functions
    public void addRoom(Room room) {
        if (this.rooms.contains(room) == false)
            this.rooms.add(room);
    }

    public void addStudent(Student student) {
        if (this.students.contains(student) == false)
            this.students.add(student);
    }

    public void addEvent(Event event) {
        if (this.events.contains(event) == false)
            this.events.add(event);
    }

    public void addStudentEventRelation(Student student, Event event) {
        if(this.students.contains(student) == false){
            System.out.println("Can't add student/event relation, student with id " + student.getID() + " doen not exist");
            return;
        }
        if(this.events.contains(event) == false){
            System.out.println("Can't add student/event relation, event with id " + event.getID() + " doen not exist");
            return;
        }

        this.studentEvents.get(student.getID()).set(event.getID(), true);
    }
    

    public void getProblemFromFile(File file) throws FileNotFoundException {
        Scanner fi = new Scanner(file);
        int numEvents = fi.nextInt();
        int numRooms = fi.nextInt();
        int numFeatures = fi.nextInt();
        int numStudents = fi.nextInt();

        //Initialize students/events relation matrix
        for(int s = 0; s < numStudents; s++){
            this.studentEvents.add(new ArrayList<Boolean>());
            for(int e = 0; e < numEvents; e++){
                this.studentEvents.get(s).add(false);
            }
        }

        //Add students
        for(int i = 0; i < numStudents; i++){
            Student stud = new Student(i);
            this.students.add(stud);
        }

        //Add Events (without required features)
        for(int i = 0; i < numEvents; i++){
            Event e = new Event(i);
            this.events.add(e);
        }

        //Add Rooms (without features)
        for(int i = 0; i < numRooms; i++){
            Room room = new Room(i, fi.nextInt());
            this.rooms.add(room);
        }

        
        
        //Add student Event relations
        for(int s = 0; s < numStudents; s++){
            for(int e = 0; e < numEvents; e++){
                if(fi.nextInt() == 1){
                    addStudentEventRelation(this.students.get(s), this.events.get(e));
                }
            }
        }

        //Add features to rooms
        for(int r = 0; r < numRooms; r++){
            for(int f = 0; f < numFeatures; f++){
                if(fi.nextInt() == 1){
                    this.rooms.get(r).addFeature(f);
                }
            }
        }

        //Add required features to events
        for(int e = 0; e < numEvents; e++){
            for(int f = 0; f < numFeatures; f++){
                if(fi.nextInt() == 1){
                    this.events.get(e).addFeature(f);
                }
            }
        }

        //Check if there are more values
        if(!fi.hasNext()){
            System.out.println("Successfully read file");
            return;
        }
        System.out.println("WARNING: Bad file values");

        
    }

    private Boolean roomHasRequiredFeatures(Room room, Event event) {
        for(int feature : event.getRequiredFeatures())
            if(room.getFeatures().contains(feature) == false)
                return false;
        return true;
    }
}