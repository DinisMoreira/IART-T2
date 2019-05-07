package elements;


import java.util.List;
import java.util.ArrayList;

public class Event {
    private int id;
    private List<Integer> requiredFeatures;
 
    private int attendeesNum; // TO DO
    

    //Solution Parameters
    private int timeSlot;
    private Room room;

    public Event(int id) {
        this.id = id;
        this.attendeesNum = 0;
        this.requiredFeatures = new ArrayList<>();
    }

    public Boolean hasFeature(int feature) {
        for(Integer f : this.requiredFeatures)
            if(f == feature)
                return true;
        return false;
    }

    public void addFeature(int feature) {
        if(hasFeature(feature) == false)
            this.requiredFeatures.add(feature);
    }


    public int getID() {
        return this.id;
    }

    public List<Integer> getRequiredFeatures() {
        return this.requiredFeatures;
    }


    public int getTimeSlot() {
        return this.timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setAttendeesNum(int attendeesNum) {
        this.attendeesNum = attendeesNum;
    }

    public int getAttendeesNum() {
        return this.attendeesNum;
    }

    @Override
    public boolean equals(Object e) {
        if (this == e) return true;
        if (e == null) return false;
        if (this.getClass() != e.getClass()) return false;

        Event event = (Event) e;
        return this.id == event.getID();
    }
}