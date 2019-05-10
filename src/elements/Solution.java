package elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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


    public boolean allocateEventHardConstraints(int eventId, int timeSlot, Room room){
        ArrayList<Student> studList = new ArrayList<Student>();
        ArrayList<Event> studEventList = new ArrayList<Event>();

        //Check if event in that time slot and room already exists
        for(Event e : eventList){               
            if(e.getTimeSlot() == timeSlot && e.getRoom().getID() == room.getID()){
                //System.out.println("Can't allocate event: " + eventId + " / Time slot " + timeSlot + " in room " + room.getID() + " is already occupied");
                return false;
            }
        }

        //Check if event can Take place in that Room (number of Attendees and feature check)
        if(this.eventList.get(eventId).hasAcceptableRoom(room) == false) {
            //System.out.println("Can't allocate event: " + eventId + " / Room " + room.getID() + " not acceptable");
            return false;
        }

        //Check if any student attends any other event in the same timeslot
        studList = prob.getStudentsForEvent(eventId);

        for(Student s : studList){
            studEventList = prob.getEventsForStudent(s.getID());
            for(Event e : studEventList){
                if(e.getTimeSlot() == timeSlot){
                    //System.out.println("Can't allocate event: " + eventId + " / Student " + s.getID() + " already attends event " + e.getID() + " at the same time slot: " + e.getTimeSlot());
                    return false;
                }
            }
        }

        //Set event Time Slot and Room
        this.eventList.get(eventId).setRoom(room);
        this.eventList.get(eventId).setTimeSlot(timeSlot);
        System.out.println("Allocated event " + eventId + " to Room " + room.getID() + " TimeSlot " + timeSlot);
        return true;

    }


    public boolean allocateEventNoConstraints(int eventId, int timeSlot, Room room){        
        this.eventList.get(eventId).setRoom(room);
        this.eventList.get(eventId).setTimeSlot(timeSlot);
        System.out.println("Allocated event " + eventId + " to Room " + room.getID() + " TimeSlot " + timeSlot + " (No constraints checked)");
        return true;
    }

    public void allocateEventToAcceptableRoom(int eventId, int timeSlot){
        for(Room r : prob.getRooms()){
            if(this.eventList.get(eventId).hasAcceptableRoom(r) == true) {
                allocateEventNoConstraints(eventId, timeSlot, r);
            }
        }
    }

    public void generateRandomSolution(){
        Random rand = new Random();
        
        int timeSlot = rand.nextInt(prob.getTimeSlots());
        int roomId = rand.nextInt(prob.getRooms().size());

        int numTries = 0;
        boolean success = false;


        for(Event e : eventList){
            while(numTries < prob.getTimeSlots() * prob.getRooms().size()){

                success = allocateEventHardConstraints(e.getID(), timeSlot, prob.getRooms().get(roomId));

                if(success)
                    break;

                timeSlot = rand.nextInt(prob.getTimeSlots());
                roomId = rand.nextInt(prob.getRooms().size());

                numTries++;
            }

            if(!success){
                allocateEventToAcceptableRoom(e.getID(), timeSlot);
            }

            timeSlot = rand.nextInt(prob.getTimeSlots());
            roomId = rand.nextInt(prob.getRooms().size());

            numTries = 0;
            success = false;
        }
    }

    public int getNumberOfHardInfractions(){
        int sum = 0;

        sum += getNumberConflictingEvents();
        sum += getNumberOfEventsWithBadRoom();
        sum += getNumberofConflictingStudSchedules();

        return sum;
    }

    public int getNumberConflictingEvents(){ //2 - Dinis
        //TO DO
        return 0;
    }

    public int getNumberOfEventsWithBadRoom(){//1 - Dinis
        //TO DO
        return 0;
    }

    public int getNumberofConflictingStudSchedules(){//2 - Dinis
        //TO DO
        return 0;
    }



    public int getNumberOfSoftInfractions(){
        int sum = 0;

        sum += getTotalDaysWithOneClass();
        sum += getTotalDaysWith2MoreConsClasses();
        sum += getTotalDaysWithLastClass();

        return sum;
    }

    public int getStudDaysWithOneClass(Student student){ //2 - Dinis
        //TO DO
        return 0;
    }

    public int getStudDaysWith2MoreConsClasses(Student student){
        int counter = 0;
        int previousEventId = -1;

        for(int slotIterator = 0; slotIterator < Problem.timeSlots; slotIterator++) {
            final int currentEventId = getEventIDFromTimeslot(student, slotIterator);
            //If this is the beginning of the day, reset previousEventId
            if(slotIterator % Problem.hoursPerDay == 0)
                previousEventId = -1;

            // Both are not -1, which means they are defined and events are sequential
            if(currentEventId != -1 && previousEventId != -1)
                counter++;
            
            previousEventId = currentEventId;
        }

        return counter;
    }

    private int getEventIDFromTimeslot(Student student, int timeslot) {
        final List<Boolean> studentEvents = this.prob.getStudentEvents().get(student.getID());

        for(int eventIterator = 0; eventIterator < studentEvents.size(); eventIterator++) {
            final Boolean isRegistrated = studentEvents.get(eventIterator);
            final int eventSlot = this.prob.getEvents().get(eventIterator).getTimeSlot();
            if(isRegistrated && eventSlot == timeslot)
                return this.prob.getEvents().get(eventIterator).getID();
        }

        return -1;
    }

    public int getStudDaysWithLastClass(Student student){
        int counter = 0;
        final List<Boolean> studentEvents = this.prob.getStudentEvents().get(student.getID());
        // Iterate through each student event and only analyse if student is present
        for(int eventIterator = 0; eventIterator < studentEvents.size(); eventIterator++)
            if(studentEvents.get(eventIterator) == true) {
                final int timeSlot = this.prob.getEvents().get(eventIterator).getTimeSlot();
                if(timeSlot == -1) //Dont analyse if timeslot is not defined
                    continue;
                if((timeSlot +1) % Problem.hoursPerDay == 0) //Add 1 since timeslots start at 0
                    counter++;
            }
        return counter;
    }


    public int getTotalDaysWithOneClass(){//1 - Diogo
        //TO DO
        return 0;
    }

    public int getTotalDaysWith2MoreConsClasses(){//1 - Diogo
        //TO DO
        return 0;
    }

    public int getTotalDaysWithLastClass(){//1 - Diogo
        //TO DO
        return 0;
    }

    public void outputSolutionToFile(String fileName) {
        File file = new File(fileName);
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(file);
        } catch(FileNotFoundException e) {return;}
        for(Event e : this.prob.getEvents()) {
            //Pad number with max of 4 spaces
            printWriter.printf("%4d %4d\n",
                e.getTimeSlot(),
                ((e.getRoom() == null) ? -1 : e.getRoom().getID()));
        }
        printWriter.close();
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