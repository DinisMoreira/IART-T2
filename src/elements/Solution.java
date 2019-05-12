package elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Solution {
    private List<Event> eventList;
    private Problem prob;
    private int score;

    public Solution(Problem prob) {
        this.eventList = prob.getEvents();
        this.prob = prob;
        this.score = 2147483647;
    }

    public Solution(Solution sol) {
        this.prob = sol.prob;
        this.score = 2147483647;
        this.eventList = new ArrayList<Event>();

        for(Event e : sol.eventList){
            Event eCopy = new Event(e.getID());

            eCopy.setAttendeesNum(e.getAttendeesNum());
            eCopy.setRoom(e.getRoom());
            eCopy.setTimeSlot(e.getTimeSlot());
            eCopy.setAcceptableRooms(e.getAcceptableRooms());
            eCopy.setRequiredFeatures(e.getRequiredFeatures());

            this.eventList.add(eCopy);
        }
    }

    public List<Event> getEventList() {
        return this.eventList;
    }


    public Problem getProb() {
        return this.prob;
    }


    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }



    public boolean allocateEventHardConstraints(int eventId, int timeSlot, Room room) {
        ArrayList<Student> studList = new ArrayList<Student>();
        ArrayList<Integer> studEventList = new ArrayList<Integer>();

        // Check if event in that time slot and room already exists
        for (Event e : eventList) {
            if (e.getTimeSlot() == timeSlot && e.getRoom().getID() == room.getID()) {
                // System.out.println("Can't allocate event: " + eventId + " / Time slot " +
                // timeSlot + " in room " + room.getID() + " is already occupied");
                return false;
            }
        }

        // Check if event can Take place in that Room (number of Attendees and feature
        // check)
        if (this.eventList.get(eventId).hasAcceptableRoom(room) == false) {
            // System.out.println("Can't allocate event: " + eventId + " / Room " +
            // room.getID() + " not acceptable");
            return false;
        }

        // Check if any student attends any other event in the same timeslot
        studList = prob.getStudentsForEvent(eventId);

        for (Student s : studList) {
            studEventList = prob.getEventIdsForStudent(s.getID());
            for (int e : studEventList) {
                if (eventList.get(e).getTimeSlot() == timeSlot) {
                    // System.out.println("Can't allocate event: " + eventId + " / Student " +
                    // s.getID() + " already attends event " + e.getID() + " at the same time slot:
                    // " + e.getTimeSlot());
                    return false;
                }
            }
        }

        // Set event Time Slot and Room
        this.eventList.get(eventId).setRoom(room);
        this.eventList.get(eventId).setTimeSlot(timeSlot);
        allocateEventNoConstraints(eventId, timeSlot, room);
        //System.out.println("Allocated event " + eventId + " to Room " + room.getID() + " TimeSlot " + timeSlot);
        return true;

    }

    public boolean allocateEventNoConstraints(int eventId, int timeSlot, Room room) {
        this.eventList.get(eventId).setRoom(room);
        this.eventList.get(eventId).setTimeSlot(timeSlot);
        //System.out.println("Allocated event " + eventId + " to Room " + room.getID() + " TimeSlot " + timeSlot+ " (No constraints checked)");
        return true;
    }

    public void allocateEventToAcceptableRoom(int eventId, int timeSlot) {
        for (Room r : prob.getRooms()) {
            if (this.eventList.get(eventId).hasAcceptableRoom(r) == true) {
                allocateEventNoConstraints(eventId, timeSlot, r);
            }
        }
    }

    public void generateRandomSolution() {
        Random rand = new Random();

        int timeSlot = rand.nextInt(prob.getTimeSlots());
        int roomId = rand.nextInt(prob.getRooms().size());

        int numTries = 0;
        boolean success = false;

        for (Event e : eventList) {
            while (numTries < prob.getTimeSlots() * prob.getRooms().size()) {

                success = allocateEventHardConstraints(e.getID(), timeSlot, prob.getRooms().get(roomId));

                if (success)
                    break;

                timeSlot = rand.nextInt(prob.getTimeSlots());
                roomId = rand.nextInt(prob.getRooms().size());

                numTries++;
            }

            if (!success) {
                allocateEventToAcceptableRoom(e.getID(), timeSlot);
            }

            timeSlot = rand.nextInt(prob.getTimeSlots());
            roomId = rand.nextInt(prob.getRooms().size());

            numTries = 0;
            success = false;
        }

        calculateSolutionEval();
    }

    public int calculateSolutionEval(){
        int val = 0;

        val = getNumberOfHardInfractions() * 1000 + getNumberOfSoftInfractions();

        this.score = val;

        return val;
    }

    public int getNumberOfHardInfractions() {
        int sum = 0;

        sum += getNumberConflictingEvents();
        sum += getNumberOfEventsWithBadRoom();
        sum += getNumberofConflictingStudSchedules();

        return sum;
    }

    public int getNumberConflictingEvents() {
        int sum = 0;

        for (Event e1 : eventList) {
            for (Event e2 : eventList) {
                if (e1.getID() != e2.getID() && e1.getTimeSlot() == e2.getTimeSlot()
                        && e1.getRoom().getID() == e2.getRoom().getID()) {
                    sum++;
                }
            }
        }

        sum = sum / 2;
        return sum;
    }

    public int getNumberOfEventsWithBadRoom() {
        int sum = 0;

        for (Event e : eventList) {
            if (e.hasAcceptableRoom(e.getRoom()) == false) {
                sum++;
            }
        }

        return sum;
    }

    public int getNumberofConflictingStudSchedules() {
        ArrayList<Student> studList = new ArrayList<Student>();
        ArrayList<Integer> studEventList = new ArrayList<Integer>();

        int sum = 0;

        for (Event e1 : eventList) {

            studList = prob.getStudentsForEvent(e1.getID());

            for (Student s : studList) {
                studEventList = prob.getEventIdsForStudent(s.getID());
                for (int e2 : studEventList) {
                    if (e1.getID() != e2 && e1.getTimeSlot() == this.eventList.get(e2).getTimeSlot()) {
                        sum++;
                    }
                }
            }
        }

        sum = sum / 2;
        return sum;
    }

    public int getNumberOfSoftInfractions() {
        int sum = 0;

        sum += getTotalDaysWithOneClass();
        //System.out.println("DaysOneClass - " + getTotalDaysWithOneClass());
        sum += getTotalDaysWith2MoreConsClasses();
        //System.out.println("Days2MoreConsClasses - " + getTotalDaysWith2MoreConsClasses());
        sum += getTotalDaysWithLastClass();
        //System.out.println("DaysWithLastClass - " + getTotalDaysWithLastClass());

        return sum;
    }

    public int getStudDaysWithOneClass(Student student) {
        int sum = 0;

        ArrayList<Integer> studEvents = prob.getEventIdsForStudent(student.getID());

        ArrayList<Integer> studEventsDays = new ArrayList<Integer>();

        for(int e : studEvents){
            studEventsDays.add(this.eventList.get(e).getTimeSlot()/prob.getHoursPerDay());
        }

        ArrayList<Integer> counter = new ArrayList<Integer>();
        for(int i = 0; i < prob.getNumberOfDays(); i++){
            counter.add(0);
        }

        for (int i = 0; i < studEventsDays.size(); i++) {
            counter.set(studEventsDays.get(i),counter.get(studEventsDays.get(i)) + 1);
        }

        for(int i = 0; i < counter.size(); i++){
            if(counter.get(i) == 1){
                sum++;
            }
        }

        return sum;
    }

    public int getStudDaysWith2MoreConsClasses(Student student) {
        final List<Boolean> studentEvents = this.prob.getStudentEvents().get(student.getID());
        int returnValue = 0;
        int consecutiveEvents = 0;
        for (int timeSlotIterator = 0; timeSlotIterator < prob.getTimeSlots(); timeSlotIterator++) {
            // Check if student is registered in event of current Timeslot
            Boolean isStudentInEvent = false;
            for(int b = 0; b < studentEvents.size(); b++)
                if(studentEvents.get(b) == true && this.eventList.get(b).getTimeSlot() == timeSlotIterator)
                    isStudentInEvent = true;

            if(isStudentInEvent)
                consecutiveEvents++;
            else {
                if(consecutiveEvents > 2)
                    returnValue += (consecutiveEvents -2);
                consecutiveEvents = 0;
            }
            // If last class of day reset counter before next iteration
            if((timeSlotIterator +1) % prob.getHoursPerDay() == 0) {
                if(consecutiveEvents > 2)
                    returnValue += (consecutiveEvents -2);
                consecutiveEvents = 0;
            }
        }

        return returnValue;
    }

    public int getStudDaysWithLastClass(Student student) {
        int counter = 0;

        ArrayList<Integer> oldStudEvents = prob.getEventIdsForStudent(student.getID());

        ArrayList<Event> studEvents = new ArrayList<Event>();
        for(int e: oldStudEvents){
            studEvents.add(this.eventList.get(e));
        }

        for(Event e : studEvents){
            if ((e.getTimeSlot() + 1) % prob.hoursPerDay == 0) // Add 1 since timeslots start at 0
                    counter++;
        }

        return counter;
    }

    public int getTotalDaysWithOneClass() {
        int counter = 0;

        for (Student student : this.prob.getStudents())
            counter += getStudDaysWithOneClass(student);

        return counter;
    }

    public int getTotalDaysWith2MoreConsClasses() {
        int counter = 0;

        for (Student student : this.prob.getStudents())
            counter += getStudDaysWith2MoreConsClasses(student);

        return counter;
    }

    public int getTotalDaysWithLastClass() {
        int counter = 0;

        for (Student student : this.prob.getStudents())
            counter += getStudDaysWithLastClass(student);

        return counter;
    }

    

    public void outputSolutionToFile(String fileName) {
        File file = new File(fileName);
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            return;
        }
        for (Event e : this.prob.getEvents()) {
            // Pad number with max of 4 spaces
            printWriter.printf("%4d %4d\n", e.getTimeSlot(), ((e.getRoom() == null) ? -1 : e.getRoom().getID()));
        }
        printWriter.close();
    }

    public void showSolutionOrderedByEventId() {
        for (Event e : eventList) {
            System.out.println();
            System.out.println("Event " + e.getID());
            System.out.println("Time Slot: " + e.getTimeSlot());
            System.out.println("Room: " + e.getRoom().getID());
        }
    }

}