package elements;

public class Room {

    private int id;
    private int size;

    public Room(int id, int size) {
        this.id = id;
        this.size = size;
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