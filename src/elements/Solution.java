package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution {
    private List<Event> eventList;
    private Problem prob;


    public Solution(Problem prob){
        this.eventList = prob.getEvents();
        this.prob = prob;
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

        //Check if event can Take place in that Room (number of Attendees and feature check)
        if(this.eventList.get(eventId).hasAcceptableRoom(room) == false) {
            System.out.println("Can't allocate event: " + eventId + ", no acceptable room");
            return false;
        }

        //Set event Time Slot and Room
        eventList.get(eventId).setRoom(room);
        eventList.get(eventId).setTimeSlot(timeSlot);
        System.out.println("Allocated event " + eventId);
        return true;

    }


    public void generateRandomSolution(){
        Random rand = new Random();
        
        int timeSlot = rand.nextInt(prob.getTimeSlots());
        int room = rand.nextInt(prob.getRooms().size());

        System.out.println("timeSlot: " + timeSlot);
        System.out.println("room: " + room);
    }


}