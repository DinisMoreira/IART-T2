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

    private List<Map<Student, Event>> studentEvents; // List containg matches of Students and Events
    private List<Map<Room, Event>> roomEvents; // List containg matches of Rooms and Events

    public Problem() {
        this.rooms = new ArrayList<>();
        this.students = new ArrayList<>();
        this.events = new ArrayList<>();

        this.studentEvents = new ArrayList<>();
        this.roomEvents = new ArrayList<>();
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

    public List<Map<Student, Event>> getStudentEvents() {
        return this.studentEvents;
    }

    public void setStudentEvents(List<Map<Student, Event>> studentEvents) {
        this.studentEvents = studentEvents;
    }

    public List<Map<Room, Event>> getRoomEvents() {
        return this.roomEvents;
    }

    public void setRoomEvents(List<Map<Room, Event>> roomEvents) {
        this.roomEvents = roomEvents;
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
        if(this.students.contains(student) == false)
            return;
        if(this.events.contains(event) == false)
            return;

        final Map<Student, Event> element = new HashMap<>();
        element.put(student, event);
        if(this.studentEvents.contains(element) == false)
            this.studentEvents.add(element);
    }
    
    public void addRoomEventRelation(Room room, Event event) {
        if(this.rooms.contains(room) == false)
            return;
        if(this.events.contains(event) == false)
            return;
        
        if(doesRoomHaveRequiredFeatures(room, event) == false)
            return;
            
        final Map<Room, Event> element = new HashMap<>();
        element.put(room, event);
        if(this.roomEvents.contains(element) == false)
            this.roomEvents.add(element);
    }

    public void getProblemFromFile(File file) throws FileNotFoundException {
        Scanner f = new Scanner(file);
        int numEvents = f.nextInt();
        int numRooms = f.nextInt();
        int numFeatures = f.nextInt();
        int numStudents = f.nextInt();

        System.out.println(numEvents);
        System.out.println(numRooms);
        System.out.println(numFeatures);
        System.out.println(numStudents);


        for(int i = 0; i < numRooms; i++){
            Room room = new Room(i, f.nextInt());
        }
    }

    private Boolean doesRoomHaveRequiredFeatures(Room room, Event event) {
        for(int feature : event.getRequiredFeatures())
            if(room.getFeatures().contains(feature) == false)
                return false;
        return true;
    }
}