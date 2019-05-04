package elements;


public class Student {
    private int id;

    public Student(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public boolean equals(Object s) {
        if (this == s) return true;
        if (s == null) return false;
        if (this.getClass() != s.getClass()) return false;

        Student student = (Student) s;
        return this.id == student.getID();
    }
}