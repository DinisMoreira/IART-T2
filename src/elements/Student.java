package elements;

import java.util.List;
import java.util.ArrayList;

public class Student {
    private int id;
    private List<Event> wantedEvents;

    public Student(int id) {
        this.id = id;
        this.wantedEvents = new ArrayList<>();
    }

    public Boolean hasEvent(Event event) {
        for(Event r : this.wantedEvents)
            if(r.equals(event))
                return true;
        return false;
    }

    public Boolean addEvent(Event event) {
        if(hasEvent(event) == false) {
            this.wantedEvents.add(event);
            return true;
        }
        else
            return false;
    }
}