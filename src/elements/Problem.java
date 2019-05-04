package elements;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Problem{
    
    final private int numberOfDays = 5;
    final private int hoursPerDay = 9;
    final private int timeSlots = numberOfDays * hoursPerDay;

    private List<Room> rooms;
    private List<Student> students;
    private List<Event> events;

    private List<Map<Student, Event>> studentEvents; //List containg matches of Students and Events
    private List<Map<Room, Event>> roomEvents; //List containg matches of Rooms and Events


    public Problem() {
        this.rooms = new ArrayList<>();
        this.students = new ArrayList<>();
        this.events = new ArrayList<>();

        this.studentEvents = new ArrayList<>();
        this.roomEvents = new ArrayList<>();
    }


    public void addRoom(Room room) {
        if(this.rooms.contains(room) == false)
            this.rooms.add(room);
    }

    public void addStudent(Student student) {
        if(this.students.contains(student) == false)
            this.students.add(student);
    }

    public void addEvent(Event event) {
        if(this.events.contains(event) == false)
            this.events.add(event);
    }


    public void addStudentEventRelation(Student student, Event event) {
        if(this.students.contains(student) == false)
            return;
        if(this.events.contains(event) == false)
            return;

        final Map<Student, Event> element = new HashMap<>();
        element.add(student, event);
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

        final Map<Student, Event> element = new HashMap<>();
        element.add(student, event);
        if(this.studentEvents.contains(element) == false)
            this.studentEvents.add(element);
    }


    private Boolean doesRoomHaveRequiredFeatures(Room room, Event event) {
        for(int feature : event.getRequiredFeatures())
            if(room.getRequiredFeatures().contains(feature) == false)
                return false;
        return true;
    }
}