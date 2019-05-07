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


    public boolean allocateEventHard(int eventId, int timeSlot, Room room){
        ArrayList<Student> studList = new ArrayList<Student>();
        ArrayList<Event> studEventList = new ArrayList<Event>();

        //Check if event in that time slot and room already exists
        for(Event e : eventList){               
            if(e.getTimeSlot() == timeSlot && e.getRoom().getID() == room.getID()){
                System.out.println("Can't allocate event: " + eventId + " / Time slot " + timeSlot + " in room " + room.getID() + " is already occupied");
                return false;
            }
        }

        //Check if event can Take place in that Room (number of Attendees and feature check)
        if(this.eventList.get(eventId).hasAcceptableRoom(room) == false) {
            System.out.println("Can't allocate event: " + eventId + " / Room " + room.getID() + " not acceptable");
            return false;
        }

        //Check if any student attends any other event in the same timeslot
            studList = prob.getStudentsForEvent(eventId);

            for(Student s : studList){
                studEventList = prob.getEventsForStudent(s.getID());
                for(Event e : studEventList){
                    if(e.getTimeSlot() == timeSlot){
                        System.out.println("Can't allocate event: " + eventId + " / Student " + s.getID() + " already attends event " + e.getID() + " at the same time slot: " + e.getTimeSlot());
                        return false;
                    }
                }
            }

        //Set event Time Slot and Room
        this.eventList.get(eventId).setRoom(room);
        this.eventList.get(eventId).setTimeSlot(timeSlot);
        System.out.println("Allocated event " + eventId);
        return true;

    }


    public void generateRandomSolution(){
        Random rand = new Random();
        
        int timeSlot = rand.nextInt(prob.getTimeSlots());
        int roomId = rand.nextInt(prob.getRooms().size());


        for(Event e : eventList){
            //Grandes problemas com hard constraitns, fazer outra função que alloca sem verificar hard constraints
            while(!allocateEventHard(e.getID(), timeSlot, prob.getRooms().get(roomId))){

                timeSlot = rand.nextInt(prob.getTimeSlots());
                roomId = rand.nextInt(prob.getRooms().size());
            }

            timeSlot = rand.nextInt(prob.getTimeSlots());
            roomId = rand.nextInt(prob.getRooms().size());
        }
    }

    public void showSolutionOrderedByEventId(){
        for(Event e : eventList){
            System.out.println();
            System.out.println("Event " + e.getID());
            System.out.println("Time Slot: " + e.getTimeSlot());
            System.out.println("Room: " + e.getRoom().getID());
        }
    }


}