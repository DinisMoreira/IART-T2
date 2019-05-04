package elements;


import java.util.List;
import java.util.ArrayList;

public class Room {

    private int id;
    private int size;
    private List<Integer> features;

    public Room(int id, int size) {
        this.id = id;
        this.size = size;
        this.features = new ArrayList<>();
    }

    public Boolean hasFeature(int feature) {
        for(Integer f : this.features)
            if(f == feature)
                return true;
        return false;
    }

    public void addFeature(int feature) {
        if(hasFeature(feature) == false)
            this.features.add(feature);
    }

    public int getSize() {
        return this.size;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public boolean equals(Object r) {
        if (this == r) return true;
        if (r == null) return false;
        if (this.getClass() != r.getClass()) return false;
        Room room = (Room) r;
        return this.id == room.getID();
    }
}