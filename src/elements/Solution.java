package elements;

import java.util.ArrayList;
import java.util.Map;

public class Solution {
    private ArrayList<Event> eventList;

    private Map<Event, ArrayList<Room>> eventPossibleRooms; //TO DO


    public Solution(Problem prob){
        this.eventList = new ArrayList<Event>();

        for(int i = 0; i < prob.getEvents().size(); i++){
            eventList.add(prob.getEvents().get(i));
        }
    }

    public ArrayList<Event> getEventList() {
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

        //Check if event can Take place in that Room (number of Attendees and feature check) - TO DO

        //Set event Time Slot and Room
        eventList.get(eventId).setRoom(room);
        eventList.get(eventId).setTimeSlot(timeSlot);
        System.out.println("Allocated event " + eventId);
        return true;

    }


}