package elements;


import java.util.List;
import java.util.ArrayList;

public class Event {
    private int id;
    private List<Integer> requiredFeatures;

    public Event(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object e) {
        if (this == e) return true;
        if (e == null) return false;
        if (this.getClass() != e.getClass()) return false;

        Event event = (Event) e;
        return this.id == event.getID();
    }
}