package elements;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Problem{
    
    final private int numberOfDays = 5;
    final private int hoursPerDay = 9;
    final private int timeSlots = numberOfDays * hoursPerDay;

    private List<Room> rooms;
    private List<Student> students;
    private List<Event> events;

    private Map<Student, Event> studentEvents;
    private Map<Room, Event> roomEvents;

    //Constructor

    public Problem() {
    }
    
    
}