package elements;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Event> eventList;


    public Solution(Problem prob){
        this.eventList = prob.getEvents();
    }

    public List<Event> getEventList() {
        return this.eventList;
    }


    public boolean allocateEvent(int eventId, int timeSlot, Room room){
        //Check if event in that time slot and room already exists
        for(Event e : eventList){               
            if(e.getTimeSlot() == timeSlot && e.getRoom().getID() == room.getID()){
                System.out.println("Can't allocate event: " + eventId + " / Time slot " + timeSlot + " in room " + room.getID() + " is already occupied");
                return false;
            }
        }
/*
        //Check if event can Take place in that Room (number of Attendees and feature check)
        if(this.eventList.get(eventId).hasAcceptableRoom(room) == false) {
            System.out.println("Can't allocate event: " + eventId + ", no acceptable room");
            return false;
        }
*/
        //Set event Time Slot and Room
        eventList.get(eventId).setRoom(room);
        eventList.get(eventId).setTimeSlot(timeSlot);
        System.out.println("Allocated event " + eventId);
        return true;

    }


}