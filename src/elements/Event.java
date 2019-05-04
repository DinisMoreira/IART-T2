package elements;

import java.util.List;
import java.util.ArrayList;

public class Event {
    private int id;
    private List<Room> acceptableRooms;

    public Event(int id) {
        this.id = id;
        this.acceptableRooms = new ArrayList<>();
    }

    public int getID() {
        return this.id;
    }

    public List<Room> getAcceptableRooms() {
        return this.acceptableRooms;
    }

    public Boolean hasRoom(Room room) {
        for(Room r : this.acceptableRooms)
            if(r.equals(room))
                return true;
        return false;
    }

    public Boolean addRoom(Room room) {
        if(hasRoom(room) == false) {
            this.acceptableRooms.add(room);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean equals(Object e) {
        if (this == e) return true;
        if (e == null) return false;
        if (this.getClass() != e.getClass()) return false;

        Event event = (Event) e;
        if(this.id == event.getID())
            return true;
        else
            return false;
    }
}